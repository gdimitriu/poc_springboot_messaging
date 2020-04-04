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
package poc.springboot.embedded.artemis;

import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.jms.server.config.ConnectionFactoryConfiguration;
import org.apache.activemq.artemis.jms.server.config.JMSConfiguration;
import org.apache.activemq.artemis.jms.server.config.JMSQueueConfiguration;
import org.apache.activemq.artemis.jms.server.config.impl.ConnectionFactoryConfigurationImpl;
import org.apache.activemq.artemis.jms.server.config.impl.JMSConfigurationImpl;
import org.apache.activemq.artemis.jms.server.config.impl.JMSQueueConfigurationImpl;
import org.apache.activemq.artemis.jms.server.embedded.EmbeddedJMS;
import poc.springboot.embedded.jms.QueueProvider;

import java.util.Arrays;

//import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;

/**
 * Artemis embedded server.
 * @author Gabriel Dimitriu
 *
 */
public class JmsServer {
    /** JMS embedded server */
    private EmbeddedJMS jmsServer = null;

    /** configuration for jms */
    private JMSConfiguration jmsConfig = null;

    //from configuration.xml
    /** JMS communication port */
    private String serverPort = "61616";

    /** name of the server */
    private String serverName = "Andromeda";

    /** name of the protocol */
    private String protocolType = "tcp";

    /** name of the default connection factory */
    private String connectionDefaultFactoryName = "default";

    /** name of the journal directory */
    private String journalDirectory = "target/data/journal";

    /** name of the server host */
    private String serverHost = "127.0.0.1";

    /** durability of queue */
    private String queueDurable = "true";

    /** server security is enabled */
    private String serverSecurityEnabled = "false";

    /** server persistence is enabled */
    private String serverPersistenceEnabled = "true";

    /** binding directory */
    private String bindindDirectory = "target/data/bindings";

    /** large message directory */
    private String largeMessageDirectory = "target/data/largemessage";

    public static void main(String...args) {
        JmsServer server = new JmsServer();
        try {
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() throws Exception {
        // Step 1. Create ActiveMQ Artemis core configuration, and set the properties accordingly
        Configuration configuration = new ConfigurationImpl().setPersistenceEnabled(true)
                .setJournalDirectory(journalDirectory)
                .setBindingsDirectory(bindindDirectory)
                .setLargeMessagesDirectory(largeMessageDirectory)
                .setSecurityEnabled(Boolean.parseBoolean(serverSecurityEnabled))
                .addAcceptorConfiguration(protocolType, protocolType + "://" + serverHost + ":" + serverPort)
                .addConnectorConfiguration(serverName, protocolType + "://" + serverHost + ":" + serverPort);
        // Step 2. Create the JMS configuration
        jmsConfig = new JMSConfigurationImpl();
        for (String queueName : QueueProvider.getInstance().getQueues()) {
            JMSQueueConfiguration queueConfig = new JMSQueueConfigurationImpl()
                    .setName(queueName).setDurable(Boolean.parseBoolean(queueDurable))
                    .setBindings("queue/" + queueName);
            jmsConfig.getQueueConfigurations().add(queueConfig);
        }
        // Step 3. Configure the JMS default ConnectionFactory
        ConnectionFactoryConfiguration cfConfig = new ConnectionFactoryConfigurationImpl()
                .setName(connectionDefaultFactoryName).setConnectorNames(Arrays.asList(serverName))
                .setBindings(connectionDefaultFactoryName);
        jmsConfig.getConnectionFactoryConfigurations().add(cfConfig);

        // Step 5. Start the JMS Server using the ActiveMQ Artemis core server and the JMS configuration
        jmsServer = new EmbeddedJMS().setConfiguration(configuration).setJmsConfiguration(jmsConfig);
        jmsServer.setSecurityManager(new ServerSecurityManager());
        jmsServer = jmsServer.start();
        System.out.println("Started Embedded Artemis JMS Server");
        System.in.read();
    }
}
