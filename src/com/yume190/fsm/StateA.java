package com.yume190.fsm;

public class StateA extends State{
	
	public StateA(){
		super();
		registerEvent(Event.NETWORK_CHANGE_ON,"StateA");
		registerEvent(Event.NETWORK_CHANGE_OFF,"StateB");
	}
		
	@Override
	public void enterAction(){}
}
