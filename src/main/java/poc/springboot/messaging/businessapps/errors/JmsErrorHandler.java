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
package poc.springboot.messaging.businessapps.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

/**
 * Error handling
 * @author Gabriel Dimitriu
 */
@Service
public class JmsErrorHandler implements ErrorHandler {
    private static Logger log = LoggerFactory.getLogger(JmsErrorHandler.class);

    @Override
    public void handleError(Throwable throwable) {
        log.error("JMS Error catched : ", throwable.getMessage());
    }
}
