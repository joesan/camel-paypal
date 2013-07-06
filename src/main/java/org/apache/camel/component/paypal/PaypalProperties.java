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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Manage Paypal specific properties.
 * 
 * @author jiakuanwang
 */
public class PaypalProperties {
    static final Logger LOG = Logger.getLogger(PaypalProperties.class.getName());

    private static final Properties paypalProperties = new Properties();
    static {
        InputStream is = PaypalProperties.class.getResourceAsStream("/paypal.properties");
        try {
            paypalProperties.load(is);
            LOG.info("Load Paypal properties: " + paypalProperties);
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        }
    }

    public static final String getEndPointPrefix() {
        return paypalProperties.getProperty("END_POINT_PREFIX");
    }

    public static final Map<String, String> getHeaders() {
        Set<String> propertyNames = paypalProperties.stringPropertyNames();
        Map<String, String> headers = new HashMap<String, String>();
        for (String propertyName : propertyNames) {
            if (propertyName.contains("X-PAYPAL")) {
                String value = paypalProperties.getProperty(propertyName);
                headers.put(propertyName, value);
            }
        }
        return headers;
    }

    public static void main(String[] args) {
        LOG.info("Endpoint prefix: " + getEndPointPrefix());
        LOG.info("Headers: " + getHeaders());
    }
}
