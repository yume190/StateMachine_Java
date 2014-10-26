package com.yume190.fsmtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yume190.fsm.Event;
import com.yume190.fsm.StateA;
import com.yume190.fsm.StateB;
import com.yume190.fsm.StateC;
import com.yume190.fsm.StateD;
import com.yume190.fsm.StateMachine;

public class FSMTest {
	
	StateA a = new StateA();
	StateB b = new StateB();
	StateC c;
	StateD d = new StateD();
	StateMachine sm;

	public FSMTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		c = new StateC();
		sm = new StateMachine(a,b,c,d);
	}

	@After
	public void tearDown() throws Exception {
		sm = null;
		c = null;
		System.out.println("----------------------- Tear Donw! -----------------------");
	}

	@Test
	public void testA1() {
		assertTrue(sm.start("StateA"));
		assertEquals(sm.getCurrentState(), "StateA");
		
		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_ON));
		assertEquals(sm.getCurrentState(), "StateA");
		
		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_ON));
		assertEquals(sm.getCurrentState(), "StateA");

		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_OFF));
		assertEquals(sm.getCurrentState(), "StateC");
	}
	
	@Test
	public void testA2() {
		assertTrue(sm.start("StateA"));
		assertEquals(sm.getCurrentState(), "StateA");
		
		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_ON));
		assertEquals(sm.getCurrentState(), "StateA");
		
		assertTrue(sm.addEvent(Event.NETWORK_CHANGE_ON));
		assertEquals(sm.getCurrentState(), "StateA");

		assertFalse(sm.addEvent(Event.TIME_OUT));
		assertEquals(sm.getCurrentState(), "StateA");
	}
	
	@Test
	public void testB() {
		assertTrue(sm.start("StateB"));
		assertEquals(sm.getCurrentState(), "StateC");
	}
	
	@Test
	public void testC1() {
		assertTrue(sm.start("StateC"));
		assertEquals(sm.getCurrentState(), "StateC");
		
		assertTrue(sm.addEvent(Event.PING_SUCCESS));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 0);
		
		assertTrue(sm.addEvent(Event.PING_SUCCESS));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 0);

		assertTrue(sm.addEvent(Event.PING_FAIL));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 1);

		assertTrue(sm.addEvent(Event.PING_FAIL));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 2);
		
		assertTrue(sm.addEvent(Event.PING_FAIL));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 3);
		
		assertTrue(sm.addEvent(Event.PING_SUCCESS));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 0);
	}

	@Test
	public void testC2() {
		assertTrue(sm.start("StateC"));
		assertEquals(sm.getCurrentState(), "StateC");
		
		assertTrue(sm.addEvent(Event.PING_SUCCESS));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 0);
		
		assertTrue(sm.addEvent(Event.PING_SUCCESS));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 0);

		assertTrue(sm.addEvent(Event.PING_FAIL));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 1);

		assertTrue(sm.addEvent(Event.PING_FAIL));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 2);
		
		assertTrue(sm.addEvent(Event.PING_FAIL));
		assertEquals(sm.getCurrentState(), "StateC");
		assertEquals(c.getPingFailCount(), 3);

		assertTrue(sm.addEvent(Event.PING_FAIL));
		assertEquals(sm.getCurrentState(), "StateD");
		assertEquals(c.getPingFailCount(), 0);		
	}
}
