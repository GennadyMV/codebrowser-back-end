package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import org.apache.commons.codec.binary.Base64;

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

        return 37 * 7 + Objects.hashCode(username);
    }

    @Override
    public boolean equals(final Object object) {

        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        final Student other = (Student) object;

        return username.equals(other.getUsername());
    }
}
