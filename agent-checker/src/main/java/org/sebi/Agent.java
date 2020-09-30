package org.sebi;

import javax.json.bind.annotation.JsonbCreator;

public class Agent {

    String numen;

    private Agent(String numen) {
        this.numen = numen;
    }

    @JsonbCreator
    public static Agent of(String numen) {
        return new Agent(numen);
    }
    
    public AgentStatus withStatus(String status) {
        return AgentStatus.of(this.numen, status);
    }

    public String getNumen() {
        return numen;
    }
    
}