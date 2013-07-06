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
package org.apache.camel.component.paypal.endpoint;

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.paypal.PayPalConfiguration;
import org.apache.camel.component.paypal.PayPalEndpoint;
import org.apache.camel.component.paypal.PayPalEndpointType;
import org.apache.camel.component.paypal.binding.PayPalAdaptivePaymentsBinding;
import org.apache.camel.component.paypal.binding.PayPalBinding;
import org.apache.camel.component.paypal.consumer.PayPalAdaptivePaymentsConsumer;
import org.apache.camel.component.paypal.producer.PayPalAdaptivePaymentsProducer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * User: Apache Software Foundation :: Apache Camel
 * Date: 2/25/13
 * Time: 7:38 PM
 * Year: 2013
 * Project: camel
 */
public class PayPalAdaptivePaymentsEndpoint extends DefaultEndpoint implements PayPalEndpoint {

    private PayPalConfiguration configuration;
    private PayPalBinding payPalBinding;

    /* The default constructor */
    public PayPalAdaptivePaymentsEndpoint() {}

    /* Create a PayPalEndpoint based on the uri, component and configuration */
    public PayPalAdaptivePaymentsEndpoint(final String endpointUri, final Component component, final PayPalConfiguration configuration) {
        super(endpointUri, component);
        this.configuration = configuration;
    }

    @Override
    public Producer createProducer() throws Exception {
        return new PayPalAdaptivePaymentsProducer(this, configuration);
    }

    @Override
    public Consumer createConsumer(final Processor processor) throws Exception {
        return new PayPalAdaptivePaymentsConsumer(this, configuration, processor);
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    /**
     * Returns the  PayPalConfiguration
     *
     * @return the configuration
     */
    public PayPalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public PayPalBinding getPayPalBinding() {
        if (payPalBinding == null) {
            payPalBinding = new PayPalAdaptivePaymentsBinding(getConfiguration());
        }
        return payPalBinding;
    }

    public void setPayPalBinding(final PayPalBinding payPalBinding) {
        this.payPalBinding = payPalBinding;
    }

    @Override
    public PayPalConfiguration getPayPalConfiguration() {
        return this.configuration;
    }

    @Override
    public PayPalEndpointType getEndpointType() {
        return PayPalEndpointType.ADAPTIVE_PAYMENTS;
    }
}
