package com.telenordigital.abacourse.backend.smsclient;

import java.net.URI;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.json.JSONStringer;

public class SmsClient {

    private final WebTarget webTarget;
    private final String apiKey;

    public SmsClient(final URI uri, final String apiKey) {
        webTarget = createWebTarget(uri);
        this.apiKey = apiKey;
    }

    public boolean sendSms(final String message) {
        final String json = new JSONStringer()
                .object()
                .key("key").value(apiKey)
                .key("message").value(message)
                .endObject()
                .toString();
        final Response response =  webTarget
                .path("sms")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON_TYPE));
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            return true;
        }
        return false;
    }

    private static WebTarget createWebTarget(final URI uri) {
        final int connectionTimeoutMs = 20_000;
        final int readTimeoutMs = 20_000;
        final ClientBuilder builder = ClientBuilder.newBuilder();
        builder.property(ClientProperties.CONNECT_TIMEOUT, connectionTimeoutMs);
        builder.property(ClientProperties.READ_TIMEOUT, readTimeoutMs);
        final ClientConfig config = new ClientConfig();
        builder.withConfig(config);
        return builder.build().target(uri);
    }

}
