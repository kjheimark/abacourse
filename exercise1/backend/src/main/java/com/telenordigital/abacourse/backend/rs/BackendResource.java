package com.telenordigital.abacourse.backend.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class BackendResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("hello")
    public Response test() {
        return Response.ok("Hello World!\n").build();
    }

}
