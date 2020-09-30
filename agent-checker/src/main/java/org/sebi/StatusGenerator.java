package org.sebi;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class StatusGenerator {
    
    private enum Status { ACTIF, CONGE, MALADIE, RETRAITE }

    private static final RandomEnum<Status> r =
        new RandomEnum<Status>(Status.class);

    @Incoming("numen")
    @Outgoing("agent-status")
    public String assignStatus(String numen) {
        Jsonb jsonb = JsonbBuilder.create();
        Agent agent = jsonb.fromJson(numen, Agent.class);
        AgentStatus agentStatus = agent.withStatus(r.random().name());
        return jsonb.toJson(agentStatus);
    }

    private static class RandomEnum<E extends Enum<E>> {

        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }
}