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
import org.apache.camel.Message;
import org.apache.camel.component.paypal.PayPalConfiguration;
import org.apache.camel.component.paypal.PayPalConstants;
import org.apache.camel.component.paypal.request.PayPalPaymentDetailsRequest;
import org.apache.camel.component.paypal.request.PayPalRequest;
import org.apache.camel.component.paypal.request.PayPalPayRequest;

/**
 * User: Apache Software Foundation :: Apache Camel
 * Date: 2/21/13
 * Time: 10:34 PM
 * Year: 2013
 * Project: camel
 */
public enum PayPalAdaptivePaymentsAPIOperationType {

    PAY("Pay") {
        @Override
        public PayPalPayRequest createRequest(final PayPalConfiguration config) {
            return new PayPalPayRequest(config);
        }
    },
    PAYMENT_DETAILS("PaymentDetails") {
        @Override
        public PayPalPaymentDetailsRequest createRequest(final PayPalConfiguration config) {
            return new PayPalPaymentDetailsRequest(config);
        }
    };

    private String requestName;

    private PayPalAdaptivePaymentsAPIOperationType(final String requestName) {
        this.requestName = requestName;
    }

    public String getRequestName() {
        return requestName;
    }

    public abstract PayPalRequest createRequest(final PayPalConfiguration config);

    /**
     * Tries to return an instance of {@link PayPalAdaptivePaymentsAPIOperationType} using
     * {@link org.apache.camel.component.paypal.PayPalConstants#PAYPAL_REQUEST} header of the incoming message.
     * <p/>
     * Returns {@link #PAYMENT_DETAILS} if there is no {@link org.apache.camel.component.paypal.PayPalConstants#PAYPAL_REQUEST}
     * header in the incoming message or value of such a header cannot be
     * recognized.
     * <p/>
     * The valid values for the {@link org.apache.camel.component.paypal.PayPalConstants#PAYPAL_REQUEST} header are: <span
     * style="font: bold;">Pay</span> <span
     * style="font: bold;">PaymentDetails</span>, <span
     * style="font: bold;">TODO...</span>, <span
     * style="font: bold;">TODO...</span>, <span
     * style="font: bold;">TODO...</span>, <span
     * style="font: bold;">TODO...</span>.
     *
     * @param exchange
     *            an exchange to get an incoming message from
     * @return an instance of {@link PayPalAdaptivePaymentsAPIOperationType}
     */
    public static PayPalAdaptivePaymentsAPIOperationType fromExchange(final Exchange exchange) {
        Message in = exchange.getIn();

        String requestName = null;
        if (in.getHeaders().containsKey(PayPalConstants.PAYPAL_API_OPERATION)) {
            requestName = in.getHeader(PayPalConstants.PAYPAL_API_OPERATION, String.class);
        }

        PayPalAdaptivePaymentsAPIOperationType requestType = PAYMENT_DETAILS;
        for (PayPalAdaptivePaymentsAPIOperationType nextRequestType : values()) {
            if (nextRequestType.requestName.equals(requestName)) {
                requestType = nextRequestType;
                break;
            }
        }

        return requestType;
    }
}