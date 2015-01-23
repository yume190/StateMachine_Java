package com.yume190.RR;

import com.yume190.fsm.Event;
import com.yume190.fsm.State;

public class StateModemDisable extends State{
	
	public StateModemDisable(){
		registerEvent(Event.TIME_OUT,"StateModemEnable");
		registerEvent(Event.NETWORK_CHANGE_OFF,"StateNetworkDisconnected");
		registerEvent(Event.NETWORK_CHANGE_ON,"StateRegularPing");
	}
	
	@Override
	public void enterAction(){
		//RR disable
		System.out.println("RR disable");
	}

}
