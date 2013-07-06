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
package org.apache.camel.component.paypal.binding;

import org.apache.camel.Exchange;
import org.apache.camel.component.paypal.PayPalConfiguration;
import org.apache.camel.component.paypal.request.PayPalRequest;

/**
 * User: Apache Software Foundation :: Apache Camel
 * Date: 2/25/13
 * Time: 9:10 PM
 * Year: 2013
 * Project: camel
 */
public class PayPalAdaptivePaymentsBinding implements PayPalBinding {

    private PayPalConfiguration configuration;

    public PayPalAdaptivePaymentsBinding() {
        this.configuration = new PayPalConfiguration();
    }

    public PayPalAdaptivePaymentsBinding(final PayPalConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public PayPalRequest getPayPalRequest(final Exchange exchange) {
        PayPalAdaptivePaymentsAPIOperationType requestType = PayPalAdaptivePaymentsAPIOperationType.fromExchange(exchange);
        PayPalRequest command = requestType.createRequest(configuration);

        return command;
    }
}