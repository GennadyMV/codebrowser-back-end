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

    private String fullName = "";
    private String firstName = "";
    private String lastName = "";

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

        if (!fullName.isEmpty()) {
            return fullName;
        }

        return String.format("%s %s", firstName, lastName).trim();
    }

    @JsonProperty("first_name")
    public void setFirstName(final String firstName) {

        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public void setLastName(final String lastName) {

        this.lastName = lastName;
    }

    @JsonProperty("koko_nimi")
    public void setFullName(final String name) {

        this.fullName = name;
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

        return Objects.equals(username, other.getUsername());
    }
}
