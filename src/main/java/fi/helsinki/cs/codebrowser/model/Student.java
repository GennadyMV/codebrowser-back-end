package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;

public final class Student {

    private String id;
    private String username;

    private String firstName;
    private String lastName;

    private List<Course> courses;


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

        return username;
    }

    public void setCourses(final List<Course> courses) {

        this.courses = courses;
    }

    public List<Course> getCourses() {

        return courses;
    }

    @JsonProperty("first_name")
    public void setFirstN(final String firstName) {

        this.firstName = firstName;
    }

    @JsonProperty("firstName")
    public String getFirstName() {

        return firstName;
    }

    @JsonProperty("last_name")
    public void setLastN(final String lastName) {

        this.lastName = lastName;
    }
    @JsonProperty("lastName")
    public String getLastName() {

        return lastName;
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
