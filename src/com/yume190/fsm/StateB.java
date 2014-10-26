package com.yume190.fsm;

public class StateB extends State{

	public StateB(){
		registerEvent(Event.PASS,"StateC");
	}

	@Override
	public void enter() {
		super.enter();
		triggerEvent(Event.PASS);
	}
	
	@Override
	public void enterAction(){}
}
