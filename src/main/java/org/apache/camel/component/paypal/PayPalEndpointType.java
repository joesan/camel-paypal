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
package org.apache.camel.component.paypal;

/**
 * User: Apache Software Foundation :: Apache Camel
 * Date: 2/25/13
 * Time: 7:25 PM
 * Year: 2013
 * Project: camel
 */
public enum PayPalEndpointType {

    ADAPTIVE_PAYMENTS, ADAPTIVE_ACCOUNTS, INVOICING, TRANSACTION_DETAILS;

    /**
     * TODO... Javadoc!
     * @param uri
     * @return
     */
    public static PayPalEndpointType fromUri(final String uri) {
        for (PayPalEndpointType endpointType : PayPalEndpointType.values()) {
            if (endpointType.name().equalsIgnoreCase(uri)) {
                return endpointType;
            }
        }
        return PayPalEndpointType.TRANSACTION_DETAILS;
    }

}
