package dev.ghimire.entities;

public class Client {

    private int clientId;
    private String fName;
    private String lName;
    private String email;

    public Client() {
    }

    public Client(int clientId, String fName, String lName, String email) {
        this.clientId = clientId;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "clientId=" + clientId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
