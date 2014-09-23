package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import org.apache.commons.codec.binary.Base64;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Student {

    private String id;
    private String username;

    private String firstName;
    private String lastName;

    public void setPlainId(final String id) {

        this.id = id;
    }

    @JsonIgnore
    public String getPlainId() {

        return id;
    }

    public String getId() {

        return Base64.encodeBase64URLSafeString(username.getBytes());
    }

    public void setUsername(final String username) {

        this.username = username;
    }

    // For Jackson deserialisation from TMC
    public void setLogin(final String login) {

        setUsername(login);
    }

    public String getUsername() {

        return username;
    }

    public String getName() {

        return firstName + " " + lastName;
    }

    @JsonProperty("first_name")
    public void setFirstName(final String firstName) {

        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public void setLastName(final String lastName) {

        this.lastName = lastName;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Student other = (Student) obj;

        if (!Objects.equals(this.username, other.username)) {
            return false;
        }

        return true;
    }
}
