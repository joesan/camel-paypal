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
 * Created with IntelliJ IDEA.
 * User: jothi
 * Date: 2/16/13
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class PayPalConfiguration implements Cloneable {

    /* The PayPalEndpointType */
    private String type;

    /* HTTP Connection Parameters */
    private long httpConnectionTimeout = 5000;
    private int httpRetry = 2;
    private long httpReadTimeout = 30000;
    private long httpMaxConnection = 100;
    private String httpIPAddress = "127.0.0.1";

    /* HTTP Proxy Configuration Parameters */
    private boolean useProxy = false; // Set this to true when connecting from behind a proxy
    private long proxyPort = 8080;
    private String proxyHost = "127.0.0.1";
    private String proxyUsername;
    private String proxyPassword;

    /* Paypal API Specific Parameters */
    private String appId = "APP-80W284485P519543T";
    private String requestEnvelopErrorLang = "en_US";
    private String currencyCode = "USD";

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getRequestEnvelopErrorLang() {
        return requestEnvelopErrorLang;
    }

    public void setRequestEnvelopErrorLang(final String requestEnvelopErrorLang) {
        this.requestEnvelopErrorLang = requestEnvelopErrorLang;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public long getHttpConnectionTimeout() {
        return httpConnectionTimeout;
    }

    public void setHttpConnectionTimeout(long httpConnectionTimeout) {
        this.httpConnectionTimeout = httpConnectionTimeout;
    }

    public int getHttpRetry() {
        return httpRetry;
    }

    public void setHttpRetry(int httpRetry) {
        this.httpRetry = httpRetry;
    }

    public long getHttpReadTimeout() {
        return httpReadTimeout;
    }

    public void setHttpReadTimeout(long httpReadTimeout) {
        this.httpReadTimeout = httpReadTimeout;
    }

    public long getHttpMaxConnection() {
        return httpMaxConnection;
    }

    public void setHttpMaxConnection(long httpMaxConnection) {
        this.httpMaxConnection = httpMaxConnection;
    }

    public String getHttpIPAddress() {
        return httpIPAddress;
    }

    public void setHttpIPAddress(String httpIPAddress) {
        this.httpIPAddress = httpIPAddress;
    }

    public boolean isUseProxy() {
        return useProxy;
    }

    public void setUseProxy(boolean useProxy) {
        this.useProxy = useProxy;
    }

    public long getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(long proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }
}
