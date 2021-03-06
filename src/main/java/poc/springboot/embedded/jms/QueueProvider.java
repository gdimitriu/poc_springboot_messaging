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
package poc.springboot.embedded.jms;

import java.util.ArrayList;
import java.util.List;

/**
 * Queue provider. This will return all queue that have to be created on the JMS server.
 * @author Gabriel Dimitriu
 */
public class QueueProvider {
    private static QueueProvider singleton = new QueueProvider();

    /**
     * private constructor the singleton.
     */
    private QueueProvider() {

    }
    public static QueueProvider getInstance() {
        return singleton;
    }
    public List<String> getQueues() {
        return new ArrayList<>();
    }
}
