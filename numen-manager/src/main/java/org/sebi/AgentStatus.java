package org.sebi;

import javax.json.bind.annotation.JsonbCreator;

public class AgentStatus {

    private String numen;

    private String status; 

    private AgentStatus(String numen, String status) {
        this.numen = numen;
        this.status = status;
    }

    @JsonbCreator
    public static AgentStatus of(String numen, String status) {
        return new AgentStatus(numen, status);
    }
    
    public String getNumen() {
        return numen;
    }

    public String getStatus() {
        return status;
    }

}
