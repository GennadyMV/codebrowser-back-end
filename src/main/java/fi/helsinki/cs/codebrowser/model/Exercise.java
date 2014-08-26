package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.apache.tomcat.util.codec.binary.Base64;

public final class Exercise {

    private String id;
    private String name;

    public void setPlainId(final String id) {

        this.id = id;
    }

    @JsonIgnore
    public String getPlainId() {

        return id;
    }

    public String getId() {

        return Base64.encodeBase64URLSafeString(name.getBytes());
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
