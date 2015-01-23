package com.yume190.RRTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yume190.fsm.Event;
import com.yume190.RR.*;
import com.yume190.fsm.StateMachine;

public class RRTest {
	
	StateModemDisable stateModemDisable = new StateModemDisable();
	StateModemEnable stateModemEnable = new StateModemEnable();
	StateNetworkDisconnected stateNetworkDisconnected = new StateNetworkDisconnected();
	IPing pingStub = new IPing() {
		
		private boolean stub = false;
		
		@Override
		public boolean ping() {
			return stub;
		}
		
		public void setPingResult(boolean result){
			stub = result;
		}
	};
	StatePinging statePinging = new StatePinging(pingStub);
	StateRegularPing StateRegularPing = new StateRegularPing();
	StateRapidPing StateRapidPing = new StateRapidPing();
	
	
	StateMachine sm;

	public RRTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		statePinging = new StatePinging(pingStub);
		sm = new StateMachine(stateModemDisable,stateModemEnable,stateNetworkDisconnected,statePinging,StateRegularPing,StateRapidPing);
	}

	@After
	public void tearDown() throws Exception {
		sm = null;
		
		System.out.println("----------------------- Tear Down! -----------------------");
	}

	@Test
	public void testOnOff() {
		assertTrue(sm.start("StateRegularPing"));
		assertEquals(sm.getCurrentState(), "StateRegularPing");
		
		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_ON));
		assertEquals(sm.getCurrentState(), "StateRegularPing");
		
		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_ON));
		assertEquals(sm.getCurrentState(), "StateRegularPing");
		
		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_OFF));
		assertEquals(sm.getCurrentState(), "StateNetworkDisconnected");
		
		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_OFF));
		assertEquals(sm.getCurrentState(), "StateNetworkDisconnected");

		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_ON));
		assertEquals(sm.getCurrentState(), "StateRegularPing");
	}
	
	@Test
	public void testA1() {
		assertTrue(sm.start("StateRegularPing"));
		assertEquals(sm.getCurrentState(), "StateRegularPing");

		pingStub.setPingResult(true);
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateRegularPing");
		assertEquals(statePinging.getPingFailCount(), 0);
		
		pingStub.setPingResult(false);
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateRapidPing");
		assertThat(statePinging.getPingFailCount(),is(1));
		
		pingStub.setPingResult(false);
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateRapidPing");
		assertThat(statePinging.getPingFailCount(),is(2));
		
		pingStub.setPingResult(true);
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateRegularPing");
		assertThat(statePinging.getPingFailCount(),is(0));
		
		pingStub.setPingResult(false);
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateRapidPing");
		assertThat(statePinging.getPingFailCount(),is(1));
		
		pingStub.setPingResult(false);
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateRapidPing");
		assertThat(statePinging.getPingFailCount(),is(2));
		
		pingStub.setPingResult(false);
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateRapidPing");
		assertThat(statePinging.getPingFailCount(),is(3));
		
		pingStub.setPingResult(false);
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateModemDisable");
		assertThat(statePinging.getPingFailCount(),is(0));
		
		assertTrue(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateNetworkDisconnected");
	}
	
}
