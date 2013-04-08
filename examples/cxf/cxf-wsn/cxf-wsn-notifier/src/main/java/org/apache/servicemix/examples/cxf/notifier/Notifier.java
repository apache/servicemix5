/*
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
package org.apache.servicemix.examples.cxf.notifier;

import org.apache.cxf.wsn.client.NotificationBroker;
import org.apache.servicemix.examples.cxf.base.Email;
import org.osgi.framework.ServiceReference;

public class Notifier {

    private NotificationBroker notificationBroker;

    private String topic;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setNotificationBroker(ServiceReference reference) {
        this.notificationBroker = new NotificationBroker((String)reference.getProperty("address"),Email.class);
    }

    public void emailNotify(Email email){

        notificationBroker.notify(topic,email);
                /*new JAXBElement<Email>(new QName("test:test:test","email-notification"),
                        Email.class, email));*/
    }

}
