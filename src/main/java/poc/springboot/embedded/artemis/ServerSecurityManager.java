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

import org.apache.activemq.artemis.core.security.CheckType;
import org.apache.activemq.artemis.core.security.Role;
import org.apache.activemq.artemis.spi.core.security.ActiveMQSecurityManager;

import java.util.Set;

/**
 * Server security Manager for JMS.
 * @author Gabriel Dimitriu
 *
 */
public class ServerSecurityManager implements ActiveMQSecurityManager{

	/**
	 * constructor.
	 */
	public ServerSecurityManager() {
		// nothing to do.
	}

	/* (non-Javadoc)
	 * @see org.apache.activemq.artemis.spi.core.security.ActiveMQSecurityManager#validateUserAndRole(java.lang.String, java.lang.String, java.util.Set, org.apache.activemq.artemis.core.security.CheckType)
	 */
	@Override
	public boolean validateUserAndRole(final String user, final String password, final Set<Role> roles, final CheckType checkType) {
/*		if (!validateUser(user, password)) {
			return false;
		}
		if (CheckType.CREATE_NON_DURABLE_QUEUE.equals(checkType) || CheckType.DELETE_NON_DURABLE_QUEUE.equals(checkType)) {
			return true;
		}
		for (Role role : roles) {
			if (role.getName().equals(user)) {
				if (checkType.hasRole(role)) {
					return true;
				}
			}
		}
		return false;
		*/
		return  true;
	}

	/* (non-Javadoc)
	 * @see org.apache.activemq.artemis.spi.core.security.ActiveMQSecurityManager#validateUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateUser(final String user, final String password) {
		return true;
	}
}
