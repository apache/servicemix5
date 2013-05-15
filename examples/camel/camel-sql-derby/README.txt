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

CAMEL SQL EXAMPLE
=================

Purpose
-------
Deploys a Camel EIP route that uses a database to store messages after
receiving. A second route illustrates how to read messages from the database
in order to process them.


Explanation
-----------
The example exists of two modules:

- Camel-Sql-Derby-Datasource

  This module creates an in-memory Derby database utilizing a
  BasicDataSource bean. The Database bean takes care of the table
  creation and the graceful shutdown of the database connection.

  The datasource is exposed as a OSGI service so it can be referenced
  in other OSGI bundles.

- Camel-Sql-Derby-Orders

  The Orders module has two Camel routes defined in a Blueprint XML
  file. Both routes use the camel-sql feature and some predefined queries
  which are located in the sql.properties file. The datasource is referenced
  from the service we exposed in the first module.

  The first route periodically creates a random order using the OrderBean
  and submits it to the database. The route also logs every oder to the
  Servicemix log. It displays the item-name from the order, for example:

    | Inserted new order of Gummi bears

  The second route consumes the unconsumed orders from the database
  and processes them using the OrderBean. The result is logged to the
  Servicemix log. For example:

    | {ID=10, ITEM=Gummi bears, AMOUNT=46, CONSUMED=false,
      description=The red ones are the cutest [PROCESSED], processed=true}

Both modules are deployed in an OSGi bundle.

   
Prerequisites for Running the Example
-------------------------------------
1. You must have the following installed on your machine:

   - JDK 1.6 or higher

   - Maven 2.2.1 or higher (for building)
   
  For more information, see the README in the top-level examples
  directory.

2. This example requires some additional configuration to allow
   the JVM to use more PermGen memory:

    export JAVA_MAX_PERM_MEM=128m           (on UNIX)
    set JAVA_MAX_PERM_MEM=128m              (on Windows)

3. Start ServiceMix by running the following command:

    <servicemix_home>/bin/servicemix          (on UNIX)
    <servicemix_home>\bin\servicemix          (on Windows)


Running the Example
-------------------
You can run the example in two ways:

- A. Using a Prebuilt Deployment Bundle: Quick and Easy
  This option is useful if you want to see the example up and
  running quickly.

- B. Building the Example Bundle Yourself
  This option is useful if you want to change the example in any way.
  It tells you how to build and deploy the example. This option might
  be slower than option A because, if you do not already have the
  required bundles in your local Maven repository, Maven will have to
  download the bundles it needs.


A. Using a Prebuilt Deployment Bundle: Quick and Easy
-----------------------------------------------------
To install and run a prebuilt version of this example, enter the
following command in the ServiceMix console:

  features:install examples-camel-sql-derby
  
This command makes use of the ServiceMix features facility. For more
information about the features facility, see the README.txt file in the
examples parent directory.

Once the example is running, periodic events are routed to the
generateOrder-route which crates routes en sends them to the database.
The processOrder-route picks those orders from the database and
processes them. You should see a similar log output:

  | Inserted a new order of Cookies
  | {ID=8, ITEM=Cookies, AMOUNT=35, CONSUMED=false,
    description=Grandma's recipe and fresh from the bakery! [PROCESSED], processed=true}


B. Building the Example Bundle Yourself
---------------------------------------
To install and run the example where you build the example bundle
yourself, complete the following steps:

1. If you have already run the example using the prebuilt version as
   described above, you must first uninstall the examples-camel-sql-derby
   feature by entering the following command in the ServiceMix console:

     features:uninstall examples-camel-sql-derby

2. Build the example by opening a command prompt, changing directory to
   examples/camel/camel-sql-derby (this example) and entering the following Maven
   command:

     mvn install
   
   If all of the required OSGi bundles are available in your local Maven
   repository, the example will build very quickly. Otherwise it may
   take some time for Maven to download everything it needs.
   
   The mvn install command builds the example deployment bundle and
   copies it to your local Maven repository and to the target directory
   of this example.
     
3. Install the example by entering the following command in
   the ServiceMix console:
   
     features:install examples-camel-sql-derby
       
   It makes use of the ServiceMix features facility. For more information
   about the features facility, see the README.txt file in the examples
   parent directory.
   
Once the example is running, periodic events are routed to the
generateOrder-route which crates routes en sends them to the database.
The processOrder-route picks those orders from the database and
processes them. You should see a similar log output:

  | Inserted a new order of Cookies
  | {ID=8, ITEM=Cookies, AMOUNT=35, CONSUMED=false,
    description=Grandma's recipe and fresh from the bakery! [PROCESSED], processed=true}


Stopping and Uninstalling the Example
-------------------------------------
To stop the example, enter the following command in the ServiceMix
console:

  osgi:stop <bundle_id>

For information on how to find the bundle_id assigned to the example,
see step 3 in the "Updating and Redeploying the Properties File 
from the Console" section above.

To uninstall the example, enter one of the following commands in
the ServiceMix console:

  features:uninstall examples-camel-sql-derby
 
or
 
  osgi:uninstall <bundle_id>
  

Viewing the Log Entries
-----------------------
You can view the entries in the log file in the data/log
directory of your ServiceMix installation, or by typing
the following command in the ServiceMix console:

  log:display
