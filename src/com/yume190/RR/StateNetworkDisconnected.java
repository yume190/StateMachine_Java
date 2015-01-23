package com.yume190.RR;

import com.yume190.fsm.Event;
import com.yume190.fsm.State;

public class StateNetworkDisconnected extends State{

	public StateNetworkDisconnected(){
		registerEvent(Event.TIME_OUT,"StateModemDisable");
		registerEvent(Event.NETWORK_CHANGE_OFF,"StateNetworkDisconnected");
		registerEvent(Event.NETWORK_CHANGE_ON,"StateRegularPing");
	}
	
	@Override
	public void enterAction(){}
}
