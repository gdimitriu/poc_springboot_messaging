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
package poc.springboot.messaging.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import poc.springboot.messaging.businessapps.producer.PayloadProducer;
import poc.springboot.messaging.configuration.IQueueNames;
import poc.springboot.messaging.payload.MyPayload;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * RunnerEmbeddedTest:
 * Send three messages to a queue and waiting for them to be consumed.
 * @author Gabriel Dimitriu
 */
@SpringBootApplication
@ComponentScan(basePackages = {"poc.springboot.messaging.businessapps", "poc.springboot.messaging.payload"})
public class RunnerEmbeddedTest implements ApplicationRunner {
    private static Logger log = LoggerFactory.getLogger(RunnerEmbeddedTest.class);

    @Autowired
    private PayloadProducer payloadSender;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("RunnerEmbeddedTest starting...");
        for (int i = 0; i < 3 ; i++) {
            log.info("Send " + i + " message to the queue : " + IQueueNames.PAYLOAD_QUEUE);
            MyPayload myPayload = new MyPayload(i + " - payload send", new Date());
            payloadSender.send(IQueueNames.PAYLOAD_QUEUE, myPayload);
        }
        log.info("Waiting 60s for all messages to be consumed ");
        TimeUnit.SECONDS.sleep(60);
        System.exit(1);
    }

    public static void main(String...args) {
        SpringApplication.run(RunnerEmbeddedTest.class, args);
    }
}
