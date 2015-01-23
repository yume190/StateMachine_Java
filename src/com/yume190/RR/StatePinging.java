package com.yume190.RR;

import com.yume190.fsm.Event;
import com.yume190.fsm.State;

public class StatePinging extends State{
	
	private int pingFailCount;
	private final int PING_FAIL_THRESHOLD = 3;
	IPing pingTool;
	
	public StatePinging(IPing ping){
		registerEvent(Event.PING_SUCCESS,"StateRegularPing");
		registerEvent(Event.PING_FAIL,"?");
//		registerEvent(Event.TIME_OUT,"StateModemEnable");
//		registerEvent(Event.NETWORK_CHANGE_OFF,"StateNetworkDisconnected");
//		registerEvent(Event.NETWORK_CHANGE_ON,"StateRegularPing");
		
		this.pingTool = ping;
		pingFailCount = 0;
	}
	
	@Override
	public void enterAction(){
		boolean pingResult = pingTool.ping();
		if (pingResult){
			triggerEvent(Event.PING_SUCCESS);
		}else{
			triggerEvent(Event.PING_FAIL);
		}
	}
	
	@Override
	public String receiveEvent(Event event) {
		String nextStateName = super.receiveEvent(event);
		switch (event) {
		case PING_FAIL:
			if (pingFailCount >= PING_FAIL_THRESHOLD){
				pingFailCount = 0;
				nextStateName = "StateModemDisable";

			}else{
				pingFailCount++;
				nextStateName = "StateRapidPing";
				System.out.println("Fail Count : " + pingFailCount);
			}
			break;
		case PING_SUCCESS:
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