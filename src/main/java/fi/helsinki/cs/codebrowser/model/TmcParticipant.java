package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.binary.Base64;

public class TmcParticipant {

    private String id;
    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    public String getId() {
        return Base64.encodeBase64URLSafeString(username.getBytes());
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {

        return username;
    }

    public String getPlainId() {

        return id;
    }

    // For Jackson deserialisation
    public void setLogin(final String login) {

        username = login;
    }
}
