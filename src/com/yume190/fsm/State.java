package com.yume190.fsm;

import java.util.HashMap;

public abstract class State implements IState,IStateEvent{
	
	private StateMachine sm;
	private HashMap<Event, String> eventMap;
	
	State(){
		eventMap = new HashMap<Event, String>();
	}
	
	@Override
	public void enter() {
		enterStart();
		enterAction();
		enterEnd();
	}
	
	private void enterStart(){
		System.out.println("enter " + getName());
	}
	
	private void enterEnd(){
		transition();
	}

	@Override
	public void transition(){
		if (eventMap.containsKey(Event.PASS)){
			triggerEvent(Event.PASS);
		}
	}
	
	@Override
	public void exit() {
		System.out.println("exit " + getName());
	}

	@Override
	public String receiveEvent(Event event) {
		return eventMap.get(event);
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
	
	public void setStateMachine(StateMachine stateMachine){
		sm = stateMachine;
	}
	
	protected void triggerEvent(Event event){
		sm.addEvent(event);
	}
	
	protected void registerEvent(Event event,String stateName) {
		eventMap.put(event,stateName);
	}
}

interface IStateEvent{
	void enterAction();
	void transition();
}
