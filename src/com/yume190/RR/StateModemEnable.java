package com.yume190.RR;

import com.yume190.fsm.Event;
import com.yume190.fsm.State;

public class StateModemEnable extends State{
	
	public StateModemEnable(){
		registerEvent(Event.PASS,"StateNetworkDisconnected");
//		registerEvent(Event.NETWORK_CHANGE_OFF,"StateNetworkDisconnected");
//		registerEvent(Event.NETWORK_CHANGE_ON,"StateRegularPing");
	}
	
	@Override
	public void enterAction(){
		// RR enable
		System.out.println("RR enable");
	}

}
