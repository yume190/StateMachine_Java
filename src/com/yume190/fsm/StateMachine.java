package com.yume190.fsm;

import java.util.HashMap;

public class StateMachine {
	
	State currentState;
	HashMap<String,State> states;
	
	public StateMachine(State... allStates) {
		states = new HashMap<String,State>();
		for(State state:allStates){
			states.put(state.getName(), state);
			state.setStateMachine(this);
		}
	}
	
	public boolean start(String stateName){
		if (states.containsKey(stateName)){
			currentState = states.get(stateName);
			currentState.enter();
			return true;
		}
		return false;
	}
	
	public String getCurrentState(){
		return currentState.getName();
	}

	public boolean addEvent(Event event){
		String nextStateName = currentState.receiveEvent(event);
		if (nextStateName == null){
			return false;
		}
		State nextState = states.get(nextStateName);
		
		currentState.exit();
		currentState = nextState;
		nextState.enter();
		
		return true;
	}
}
