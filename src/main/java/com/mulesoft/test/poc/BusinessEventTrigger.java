package com.mulesoft.test.poc;

import java.util.Map;

import org.mule.api.MuleEvent;

import com.mulesoft.mule.tracking.event.AbstractEventNotificationFiringMessageProcessor;
import com.mulesoft.mule.tracking.event.EventNotification;

public class BusinessEventTrigger extends AbstractEventNotificationFiringMessageProcessor {

	@Override
	protected EventNotification createNotification(MuleEvent muleEvent) {
		// TODO Auto-generated method stub
		return createNotification(muleEvent, "time-event", null, null);
	}
	
	public EventNotification createBE(MuleEvent muleEvent, Map<String, String> meta){
		return createNotification(muleEvent, "time-event", null, meta);
	}

}
