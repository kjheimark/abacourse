package com.telenordigital.abacourse.backend.rs;

import com.telenordigital.abacourse.backend.database.DatabaseManager;
import com.telenordigital.abacourse.backend.smsclient.SmsClient;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("")
public class BackendResource {

    private static final Logger LOGGER = Logger.getLogger(BackendResource.class.getName());

    private final UriInfo uriInfo;
    private final SmsClient smsClient;
    private final DatabaseManager databaseManager;

    public BackendResource(
            final @Context UriInfo uriInfo,
            final @Context SmsClient smsClient,
            final @Context DatabaseManager databaseManager) {
        this.uriInfo = uriInfo;
        this.smsClient = smsClient;
        this.databaseManager = databaseManager;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("hello")
    public Response test() {
        return Response.ok("Hello World!\n").build();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("sendsms")
    public Response sendSms(final @FormParam("message") String message) {
        if (message == null || message.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("No message provided.\n")
                    .build();
        }
        // Insert into database
        final boolean successfulInsert = databaseManager.insert(message);
        if (!successfulInsert) {
            LOGGER.warning("Could not insert message to database.");
        }
        // Send using sms client
        final boolean successfulSend = smsClient.sendSms(message);
        if (!successfulSend) {
            LOGGER.warning("Could not send message with client.");
        }
        return Response.created(uriInfo.getAbsolutePathBuilder().path("sendsms").build()).build();
    }

}
