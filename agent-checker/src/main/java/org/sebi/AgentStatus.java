package org.sebi;

public class AgentStatus {

    private String numen;

    private String status; 

    private AgentStatus(String numen, String status) {
        this.numen = numen;
        this.status = status;
    }

    static AgentStatus of(String numen, String status) {
        return new AgentStatus(numen, status);
    }
    
    public String getNumen() {
        return numen;
    }

    public String getStatus() {
        return status;
    }

}
