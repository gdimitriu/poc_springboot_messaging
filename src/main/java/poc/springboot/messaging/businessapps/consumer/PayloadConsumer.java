/*
 Copyright (c) 2020 Gabriel Dimitriu All rights reserved.
 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.

 This file is part of poc_springboot_messaging project.

 poc_springboot_messaging is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 poc_springboot_messaging is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with poc_aws.  If not, see <http://www.gnu.org/licenses/>.
 */
package poc.springboot.messaging.businessapps.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import poc.springboot.messaging.configuration.IQueueNames;
import poc.springboot.messaging.payload.MyPayload;

import javax.jms.Message;
import javax.jms.Session;

/**
 * Payload Consumer.
 * This is the consumer from the queue is the equivalent of the consumer in
 * classic implementation (OnMessage implementation).
 * @author Gabriel Dimitriu
 */
@Component
public class PayloadConsumer {
    private static Logger log = LoggerFactory.getLogger(PayloadConsumer.class);

    /**
     * This is the equivalent of the OnMessage from classic implementation.
     * @param payload the payload
     * @param headers the headers
     * @param message the message
     * @param session the session
     */
    @JmsListener(destination = IQueueNames.PAYLOAD_QUEUE)
    public void receiveMessage(@Payload MyPayload payload, @Headers MessageHeaders headers,
                               Message message, Session session) {
        log.info("Received <" + payload + ">");
    }
}
