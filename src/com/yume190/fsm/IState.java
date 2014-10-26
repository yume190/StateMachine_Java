package com.yume190.fsm;

public interface IState {
	
    void enter();

    void exit();
    
    public String receiveEvent(Event event);
    
    String getName();
}
