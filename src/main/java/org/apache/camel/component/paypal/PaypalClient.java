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

import com.paypal.exception.*;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.*;
import com.paypal.svcs.types.common.ClientDetailsType;
import com.paypal.svcs.types.common.PhoneNumberType;
import com.paypal.svcs.types.common.RequestEnvelope;
import org.apache.camel.component.paypal.model.PayRequest;
import org.apache.camel.component.paypal.model.PayResponse;
import org.apache.camel.component.paypal.model.Receiver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jiakuanwang
 */
public class PaypalClient {
    private static final Logger LOG = Logger.getLogger(PaypalClient.class.getName());
/*



    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        session.setAttribute("url", request.getRequestURI());
        session.setAttribute(
                "relatedUrl",
                "<ul><li><a href='Pay'>Pay</a></li><li><a href='PaymentDetails'>PaymentDetails</a></li><li><a href='Refund'>Refund</a></li><li><a href='GetPaymentOptions'>GetPaymentOptions</a></li><li><a href='ExecutePayment'>ExecutePayment</a></li><li><a href='SetPaymentOptions'>SetPaymentOptions</a></li></ul>");
        RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");

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








    public static final String pay(PayRequest payRequest) {
        try {
            String endPoint = PaypalProperties.getEndPointPrefix() + "/" + PaypalAPIOperation.Pay.name();
            LOG.info("EndPoint: " + endPoint);
            URL url = new URL(endPoint);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);

            // Set request headers
            Map<String, String> headers = PaypalProperties.getHeaders();
            LOG.info("Headers: " + headers);
            Iterator<String> iter = headers.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = headers.get(key);
                urlConnection.setRequestProperty(key, value);
            }

            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            String requestBody = payRequest.toXml();
            LOG.info("Request Body: " + requestBody);
            out.write(requestBody);
            out.flush();
            out.close();

            PayResponse response = getResponse(urlConnection);
            LOG.info("Got pay key: " + response.getPayKey());
            return response.getPayKey();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    private static PayResponse getResponse(URLConnection urlConnection) throws IOException {
        StringBuffer responseBody = new StringBuffer();

        // Get the response
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            responseBody.append(line);
        }
        bufferedReader.close();

        LOG.info("Response body: " + responseBody);
        PayResponse response = new PayResponse().fromXml(responseBody.toString());
        return response;
    }

    public static void main(String[] args) {
        PayRequest payRequest = new PayRequest();
        payRequest.setActionType("PAY");
        payRequest.setCancelUrl("http://www.google.com");
        payRequest.setCurrencyCode("USD");

        List<Receiver> receiverList = new ArrayList<Receiver>();
        Receiver receiver1 = new Receiver();
        receiver1.setAmount("10");
        receiver1.setEmail("jkbuy_1324559904_per@gmail.com");
        Receiver receiver2 = new Receiver();
        receiver2.setAmount("20");
        receiver2.setEmail("jksell_1324559823_biz@gmail.com");
        receiverList.add(receiver1);
        receiverList.add(receiver2);
        payRequest.setReceiverList(receiverList);

        payRequest.setReturnUrl("http://www.google.com");

        LOG.info("PayRequest:\n" + payRequest.toXml());
        pay(payRequest);
    }*/
}
