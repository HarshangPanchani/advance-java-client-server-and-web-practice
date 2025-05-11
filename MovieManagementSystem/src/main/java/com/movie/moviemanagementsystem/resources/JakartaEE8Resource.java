package com.movie.moviemanagementsystem.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * A simple REST resource
 */
@Path("rest")
public class JakartaEE8Resource {
    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    public Response ping() {
        return Response
                .ok("ping Java EE")
                .build();
    }
}