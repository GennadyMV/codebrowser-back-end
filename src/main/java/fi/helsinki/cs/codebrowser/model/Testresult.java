package fi.helsinki.cs.codebrowser.model;

import javax.persistence.Entity;

@Entity
public class Testresult extends AbstractNamedPersistable {

    private String message;
    private boolean passed;

    public String getMessage() {

        return message;
    }

    public void setMessage(final String message) {

        this.message = message;
    }

    public boolean isPassed() {

        return passed;
    }

    public void setPassed(final boolean passed) {
        this.passed = passed;
    }

}
