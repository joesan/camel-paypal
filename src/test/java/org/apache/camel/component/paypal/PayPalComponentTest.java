package org.apache.camel.component.paypal;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ExchangePattern;
import org.apache.camel.component.paypal.binding.PayPalBinding;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * User: Apache Software Foundation :: Apache Camel
 * Date: 2/24/13
 * Time: 8:09 PM
 * Year: 2013
 * Project: camel
 */
public class PayPalComponentTest {

    public final String PAYPAL_ENDPOINT_URI = "paypal://adaptivepayments";

    private PayPalComponent component;
    private DefaultCamelContext context;
    private PayPalConfiguration config;

    @Before
    public void setUp() {
        context = new DefaultCamelContext();
        component = new PayPalComponent(context);
        config = new PayPalConfiguration();
    }

    @Test
    public void constructorPayPalComponentConstructorTest() {
        final CamelContext context = new DefaultCamelContext();
        component = new PayPalComponent(context);

        assertSame(context, component.getCamelContext());
    }

    @Test
    public void createEndpointStringStringMapShouldReturnAPayPalEndpoint() throws Exception {
        final CamelContext context = new DefaultCamelContext();
        component = new PayPalComponent(context);

        final Map < String, Object > parameters = new HashMap < String, Object > ();
        parameters.put(PayPalConstants.PAYPAL_CURRENCY_CODE, config.getCurrencyCode());

        final Endpoint endpoint = component.createEndpoint(PAYPAL_ENDPOINT_URI, "?operation=pay", parameters);
        PayPalEndpoint payPalEndpoint = (PayPalEndpoint) endpoint;

        assertEquals(PAYPAL_ENDPOINT_URI, payPalEndpoint.getEndpointUri());
        assertEquals(PAYPAL_ENDPOINT_URI, payPalEndpoint.getEndpointKey());
        assertSame(component, payPalEndpoint.getComponent());
        assertNotNull(payPalEndpoint.getConfiguration());
        assertEquals(config.getCurrencyCode(), payPalEndpoint.getConfiguration().getCurrencyCode());
        assertEquals(ExchangePattern.InOnly, payPalEndpoint.getExchangePattern());
        assertTrue(payPalEndpoint.getBinding() instanceof PayPalBinding);
        assertNotNull(payPalEndpoint.getCamelContext());
    }
}
