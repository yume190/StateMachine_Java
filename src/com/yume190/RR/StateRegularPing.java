package com.yume190.RR;

import com.yume190.fsm.Event;
import com.yume190.fsm.State;

public class StateRegularPing extends State{

	public StateRegularPing(){
		registerEvent(Event.TIME_OUT,"StatePinging");
		registerEvent(Event.NETWORK_CHANGE_OFF,"StateNetworkDisconnected");
		registerEvent(Event.NETWORK_CHANGE_ON,"StateRegularPing");
	}
	
	@Override
	public void enterAction(){}
	
}
