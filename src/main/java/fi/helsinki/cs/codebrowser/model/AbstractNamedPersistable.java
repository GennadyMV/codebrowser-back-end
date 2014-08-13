package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public abstract class AbstractNamedPersistable extends AbstractPersistable<Long> implements Serializable {

    private String name;

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {

        return super.isNew();
    }
}
