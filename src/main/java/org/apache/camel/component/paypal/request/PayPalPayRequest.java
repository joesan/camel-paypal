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
package org.apache.camel.component.paypal.request;

import com.paypal.exception.*;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.common.ClientDetailsType;
import com.paypal.svcs.types.common.RequestEnvelope;
import org.apache.camel.Exchange;
import org.apache.camel.component.paypal.PayPalConfiguration;
import org.apache.camel.component.paypal.PayPalConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Apache Software Foundation :: Apache Camel
 * Date: 2/21/13
 * Time: 10:53 PM
 * Year: 2013
 * Project: camel
 */
public class PayPalPayRequest implements PayPalRequest {

    protected PayPalConfiguration config;

    public PayPalPayRequest(final PayPalConfiguration config) {
        this.config = config;
    }

    @Override
    public void doRequest(final Exchange exchange) throws Exception {
        final PayRequest req = populatePayRequest(exchange);

        try {
            final PayRequest request = this.populatePayRequest(exchange);
            PayResponse resp = this.sendPayRequest(request);

        }
        catch (Exception ex) {
            // TODO... Log the exception and throw it back bloddy!
        }
    }

    /**
     *
     * @param req
     * @return
     */
    private PayResponse sendPayRequest(final PayRequest req) throws PayPalException {
        PayResponse resp = null;

        try {
            // TODO... Get the properties from a Properties file
            AdaptivePaymentsService service = new AdaptivePaymentsService(this
                    .getClass().getResourceAsStream("/sdk_config.properties"));
            resp = service.pay(req);

        } catch (IOException ex) {

        } catch (MissingCredentialException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientActionRequiredException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SSLConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (HttpErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidCredentialException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidResponseDataException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            // Do some cleanup if necessary...
        }

        return resp;
    }

    /**
     * Populate the pay request
     * @param exchange
     * @return
     */
    private PayRequest populatePayRequest(Exchange exchange) {

        //final Map<String, Object> mapReq = (Map<String, Object>) exchange.getIn().getBody(Map.class);

        Map < String, Object > headers = exchange.getIn().getHeaders();

        final PayRequest req = new PayRequest();

        final String currencyCode = headers.get(PayPalConstants.PAYPAL_CURRENCY_CODE) != null
                ? (String) headers.get(PayPalConstants.PAYPAL_CURRENCY_CODE)
                : config.getCurrencyCode();
        req.setCurrencyCode(currencyCode);



        final RequestEnvelope envelop = headers.get(PayPalConstants.PAYPAL_REQUEST_ENVELOP) != null
                ? new RequestEnvelope((String)headers.get(PayPalConstants.PAYPAL_REQUEST_ENVELOP))
                : new RequestEnvelope(config.getRequestEnvelopErrorLang());

        final List < Receiver > receiver = populateReceiver(headers);
        final ClientDetailsType clientDetails = populateClientDetails(headers);


        return req;
    }

    /**
     * Populate the receiver information
     * @param headers
     * @return
     */
    private List < Receiver > populateReceiver(final Map < String, Object > headers) {

        List < Receiver > receiverList = new ArrayList < Receiver >();

        final Receiver rec = new Receiver();


        return receiverList;
    }

    /**
     * Populate the ClientDetailsType information
     * @param headers
     * @return
     */
    private ClientDetailsType populateClientDetails(final Map < String, Object > headers) {
        final ClientDetailsType clientDetailsType = new ClientDetailsType();

        final String appId = headers.get(PayPalConstants.PAYPAL_API) != null
                ? (String)headers.get(PayPalConstants.PAYPAL_APP_ID)
                : config.getAppId();
        clientDetailsType.setApplicationId(appId);



        return clientDetailsType;
    }


/*

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        com.paypal.svcs.types.ap.RequestEnvelope requestEnvelope = new com.paypal.svcs.types.ap.RequestEnvelope("en_US");

        com.paypal.svcs.types.ap.PayRequest req = new com.paypal.svcs.types.ap.PayRequest();

        List<com.paypal.svcs.types.ap.Receiver> receiver = new ArrayList<com.paypal.svcs.types.ap.Receiver>();

        com.paypal.svcs.types.ap.Receiver rec = new com.paypal.svcs.types.ap.Receiver();
        if (request.getParameter("amount") != "")
            rec.setAmount(Double.parseDouble(request.getParameter("amount")));
        if (request.getParameter("mail") != "")
            rec.setEmail(request.getParameter("mail"));
        if (request.getParameter("invoiceID") != "")
            rec.setInvoiceId(request.getParameter("invoiceID"));
        if (request.getParameter("paymentSubType") != "")
            rec.setPaymentSubType(request.getParameter("paymentSubType"));
        if (request.getParameter("paymentType") != "")
            rec.setPaymentType(request.getParameter("paymentType"));
        if (request.getParameter("phoneNumber") != "") {
            PhoneNumberType phone = new PhoneNumberType(
                    request.getParameter("countryCode"),
                    request.getParameter("phoneNumber"));
            phone.setExtension(request.getParameter("extension"));
            rec.setPhone(phone);
        }
        if (request.getParameter("setPrimary") != "")
            rec.setPrimary(Boolean.parseBoolean(request
                    .getParameter("setPrimary")));
        receiver.add(rec);
        ReceiverList receiverlst = new ReceiverList(receiver);
        req.setReceiverList(receiverlst);
        req.setRequestEnvelope(requestEnvelope);
        ClientDetailsType clientDetails = new ClientDetailsType();
        if (request.getParameter("applicationID") != "")
            clientDetails.setApplicationId(request
                    .getParameter("applicationID"));
        if (request.getParameter("customerID") != "")
            clientDetails.setCustomerId(request.getParameter("customerID"));
        if (request.getParameter("customerType") != "")
            clientDetails.setCustomerType(request.getParameter("customerType"));
        if (request.getParameter("deviceID") != "")
            clientDetails.setDeviceId(request.getParameter("deviceID"));
        if (request.getParameter("location") != "")
            clientDetails.setGeoLocation(request.getParameter("location"));
        if (request.getParameter("ipAddress") != "")
            clientDetails.setIpAddress(request.getParameter("ipAddress"));
        if (request.getParameter("model") != "")
            clientDetails.setModel(request.getParameter("model"));
        if (request.getParameter("partnerName") != "")
            clientDetails.setPartnerName(request.getParameter("partnerName"));
        req.setClientDetails(clientDetails);
        if (request.getParameter("ipnNotificationURL") != "")
            req.setIpnNotificationUrl(request
                    .getParameter("ipnNotificationURL"));
        if (request.getParameter("memo") != "")
            req.setMemo(request.getParameter("memo"));
        if (request.getParameter("pin") != "")
            req.setPin(request.getParameter("pin"));
        if (request.getParameter("senderEmail") != "")
            req.setSenderEmail(request.getParameter("senderEmail"));
        if (request.getParameter("feesPayer") != "")
            req.setFeesPayer(request.getParameter("feesPayer"));
        FundingConstraint fundingConstraint = new FundingConstraint();
        List<FundingTypeInfo> fundingTypeInfoList = new ArrayList<FundingTypeInfo>();
        if (request.getParameter("fundingType") != "") {
            FundingTypeInfo fundingTypeInfo = new FundingTypeInfo(
                    request.getParameter("fundingType"));
            fundingTypeInfoList.add(fundingTypeInfo);
        }
        FundingTypeList fundingTypeList = new FundingTypeList(
                fundingTypeInfoList);
        fundingConstraint.setAllowedFundingType(fundingTypeList);
        req.setFundingConstraint(fundingConstraint);
        if (request.getParameter("preapprovalKey") != "")
            req.setPreapprovalKey(request.getParameter("preapprovalKey"));
        if (request.getParameter("reverseAllPaymentsOnError") != "")
            req.setReverseAllParallelPaymentsOnError(Boolean
                    .parseBoolean(request
                            .getParameter("reverseAllPaymentsOnError")));

        SenderIdentifier senderIdentifier = new SenderIdentifier();
        if (request.getParameter("senderIdentifierEmail") != "")
            senderIdentifier.setEmail(request
                    .getParameter("senderIdentifierEmail"));
        if (request.getParameter("senderCountryCode") != ""
                && request.getParameter("senderPhoneNumber") != "") {
            PhoneNumberType senderPhone = new PhoneNumberType(
                    request.getParameter("senderCountryCode"),
                    request.getParameter("senderPhoneNumber"));
            if (request.getParameter("senderExtension") != "")
                senderPhone.setExtension(request
                        .getParameter("senderExtension"));
            senderIdentifier.setPhone(senderPhone);
        }
        if (request.getParameter("useCredentials") != "")
            senderIdentifier.setUseCredentials(Boolean.parseBoolean(request
                    .getParameter("useCredentials")));
        req.setSender(senderIdentifier);

        if (request.getParameter("trackingID") != "")
            req.setTrackingId(request.getParameter("trackingID"));
        if (request.getParameter("actionType") != "")
            req.setActionType(request.getParameter("actionType"));
        if (request.getParameter("cancelURL") != "")
            req.setCancelUrl(request.getParameter("cancelURL"));
        if (request.getParameter("currencyCode") != "")
            req.setCurrencyCode(request.getParameter("currencyCode"));
        if (request.getParameter("returnURL") != "")
            req.setReturnUrl(request.getParameter("returnURL"));
        AdaptivePaymentsService service = new AdaptivePaymentsService(this
                .getClass().getResourceAsStream("/sdk_config.properties"));
        try {
            com.paypal.svcs.types.ap.PayResponse resp = service.pay(req);
            response.setContentType("text/html");
            if (resp != null) {
                session.setAttribute("RESPONSE_OBJECT", resp);
                session.setAttribute("lastReq", service.getLastRequest());
                session.setAttribute("lastResp", service.getLastResponse());
                if (resp.getResponseEnvelope().getAck().toString()
                        .equalsIgnoreCase("SUCCESS")) {
                    Map<Object, Object> map = new LinkedHashMap<Object, Object>();
                    map.put("Ack", resp.getResponseEnvelope().getAck());
                    map.put("Correlation ID", resp.getResponseEnvelope()
                            .getCorrelationId());
                    map.put("Time Stamp", resp.getResponseEnvelope()
                            .getTimestamp());
                    map.put("Pay Key", resp.getPayKey());
                    map.put("Payment Execution Status",
                            resp.getPaymentExecStatus());
                    if (resp.getDefaultFundingPlan() != null)
                        map.put("Default Funding Plan", resp
                                .getDefaultFundingPlan().getFundingPlanId());
                    map.put("Redirect URL",
                            "<a href=https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey="
                                    + resp.getPayKey()
                                    + ">https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey="
                                    + resp.getPayKey() + "</a>");
                    session.setAttribute("map", map);
                    response.sendRedirect("Response.jsp");
                } else {
                    session.setAttribute("Error", resp.getError());
                    response.sendRedirect("Error.jsp");
                }
            }
        } catch (SSLConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidCredentialException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (HttpErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidResponseDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientActionRequiredException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MissingCredentialException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OAuthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


*/


}
