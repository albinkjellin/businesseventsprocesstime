package com.mulesoft.test.poc;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.api.context.notification.MessageProcessorNotificationListener;
import org.mule.api.context.notification.ServerNotification;
import org.mule.context.notification.MessageProcessorNotification;

public class MPEventListener
		implements MessageProcessorNotificationListener<MessageProcessorNotification>, MuleContextAware {
	MuleContext context;
	protected static final Log LOGGER = LogFactory.getLog("MPLog");
	private long startTime = System.currentTimeMillis();
	

	BusinessEventTrigger bet = new BusinessEventTrigger();

	@Override
	public void onNotification(MessageProcessorNotification notification) {
		

		if (notification.getAction() == MessageProcessorNotification.MESSAGE_PROCESSOR_PRE_INVOKE) {
			startTime = System.currentTimeMillis();
		} else if (notification.getAction() == MessageProcessorNotification.MESSAGE_PROCESSOR_POST_INVOKE) {

			long diff = notification.getTimestamp() - startTime;
			LOGGER.info("Process time:" + diff);

			if (diff > 100) {
				Map<String, String> metaData = new HashMap<String, String>();
				metaData.put("Processor", notification.getProcessor().toString());
				metaData.put("ProcessingTime", Long.toString(diff));
				final ServerNotification servNot = bet.createBE(notification.getSource(), metaData);
				context.fireNotification(servNot);
			}
		}

	}

	@Override
	public void setMuleContext(MuleContext context) {
		// TODO Auto-generated method stub
		this.context = context;
	}

}