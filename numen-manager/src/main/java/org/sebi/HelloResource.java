package org.sebi;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;

import io.smallrye.mutiny.Multi;

@Path("/status")
public class HelloResource {

    @Channel("agent-status")
    Multi<String> agentStatus;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Multi<AgentStatus> agents() {
        return agentStatus.map(s ->JsonbBuilder.create().fromJson(s, AgentStatus.class));
    }
}