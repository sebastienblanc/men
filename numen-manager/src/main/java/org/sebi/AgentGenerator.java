package org.sebi;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class AgentGenerator {
    
    private static final List<Agent> AGENTS = 
    Arrays.asList("1245AB456","888XX456","1245WH456").stream().map(Agent::of).collect(Collectors.toList());

    @Outgoing("numen")
    Multi<String> numen(){
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
        .onOverflow().drop()
        .map(tick -> AGENTS.get(ThreadLocalRandom.current().nextInt(0, AGENTS.size())))
        .map(JsonbBuilder.create()::toJson);
    }
}