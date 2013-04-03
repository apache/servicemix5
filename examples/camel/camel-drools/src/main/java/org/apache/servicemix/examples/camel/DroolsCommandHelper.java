/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicemix.examples.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.drools.command.impl.GenericCommand;
import org.drools.command.runtime.BatchExecutionCommandImpl;
import org.drools.command.runtime.rule.FireAllRulesCommand;
import org.drools.command.runtime.rule.InsertObjectCommand;

import java.util.List;

/**
 * Class to help create wrapper Drools Expert Command for use with
 * org.drools/drools-camel component.
 */
public class DroolsCommandHelper {
	public void insertAndFireAll(Exchange exchange) {
		final Message in = exchange.getIn();
		final Object body = in.getBody();

		// TODO: add type checking to handle arrays of objects

		BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
		final List<GenericCommand<?>> commands = command.getCommands();
		commands.add(new InsertObjectCommand(body, "obj1"));
		commands.add(new FireAllRulesCommand());

		in.setBody(command);
	}

	// TODO: add other command handler methods (e.g. getGlobalObject, etc.)
}
