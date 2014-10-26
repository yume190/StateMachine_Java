package com.yume190.fsm;

public class StateC extends State{

	private int pingFailCount = 0;
	
	public StateC(){
		registerEvent(Event.PING_FAIL,"StateC");
		registerEvent(Event.PING_SUCCESS,"StateC");
		registerEvent(Event.GUARD,"StateD");
	}
	
	@Override
	public void enter() {
		super.enter();
		
		
//		if (pingFailCount > 3){
//			sm.addEvent(Event.GUARD);
//		}
	}
	
	@Override
	public void enterAction(){}

	@Override
	public String receiveEvent(Event event) {
		String nextStateName = super.receiveEvent(event);
		switch (event) {
		case PING_FAIL:
			pingFailCount++;
			System.out.println("Fail Count : " + pingFailCount);
			if (pingFailCount > 3){
				nextStateName = "StateD";
				pingFailCount = 0;
//				sm.addEvent(Event.GUARD);
			}
			break;
		case PING_SUCCESS:
		case GUARD:
			pingFailCount = 0;
		default:
			break;
		}
		
		return nextStateName;
	}

	public int getPingFailCount(){
		return pingFailCount;
	}
	
}
