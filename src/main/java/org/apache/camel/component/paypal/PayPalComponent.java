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

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.component.paypal.endpoint.PayPalAdaptivePaymentsEndpoint;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jothi
 * Date: 2/16/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class PayPalComponent extends DefaultComponent {

    /** Default constructor */
    public PayPalComponent() {}

    /** Set the CamelContext */
    public PayPalComponent(final CamelContext context) {
        super(context);
    }

    @Override
    protected Endpoint createEndpoint(final String uri, final String remaining, final Map < String, Object > parameters) throws Exception {
        PayPalConfiguration config = new PayPalConfiguration();

        setProperties(config, parameters);

        return createEndpoint(uri, config);
    }

    /**
     * TODO... Javadoc
     * @param uri
     * @param config
     * @return
     * @throws Exception
     */
    protected Endpoint createEndpoint(final String uri, final PayPalConfiguration config) throws Exception {
        PayPalEndpoint endpoint = null;
        switch (PayPalEndpointType.fromUri(config.getType())) {
            case ADAPTIVE_PAYMENTS:
                endpoint = new PayPalAdaptivePaymentsEndpoint(uri, this, config);
                break;
            case ADAPTIVE_ACCOUNTS:
                endpoint = new PayPalAdaptivePaymentsEndpoint(uri, this, config);
                break;
            default:
                endpoint = new PayPalAdaptivePaymentsEndpoint(uri, this, config);
                break;
        }
        return endpoint;
    }
}
