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
package org.apache.camel.component.paypal.producer;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.component.paypal.PayPalConfiguration;
import org.apache.camel.component.paypal.PayPalEndpoint;
import org.apache.camel.component.paypal.request.PayPalRequest;
import org.apache.camel.impl.DefaultProducer;

/**
 * User: Apache Software Foundation :: Apache Camel
 * Date: 2/25/13
 * Time: 8:04 PM
 * Year: 2013
 * Project: camel
 */
public class PayPalAdaptivePaymentsProducer extends DefaultProducer {

    private PayPalConfiguration configuration;

    public PayPalAdaptivePaymentsProducer(final Endpoint endpoint, final PayPalConfiguration configuration) {
        super(endpoint);

        this.configuration = configuration;
    }

    /**
     * Returns the PayPalConfiguration for this producer
     *
     * @return the configuration
     */
    public PayPalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void process(final Exchange exchange) throws Exception {

        final PayPalRequest request = getEndpoint().getPayPalBinding().getPayPalRequest(exchange);
        request.doRequest(exchange);
    }

    @Override
    public PayPalEndpoint getEndpoint() {
        return (PayPalEndpoint) super.getEndpoint();
    }

    @Override
    public String toString() {
        return "PayPalProducer[" + getEndpoint().getEndpointUri() + "]";
    }
}
