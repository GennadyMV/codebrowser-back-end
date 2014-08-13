package fi.helsinki.cs.codebrowser.codeanalyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;

    /**
     * Start position of diff in current file.
     */
    private int rowStart;

    /**
     * End position of diff in current file.
     */
    private int rowEnd;

    /**
     * Amount of deleted lines in file before this diff.
     */
    private int offset;

    /**
     * StartPosition of delete in previous file.
     * Not set for other types.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer fromRowEnd;

     /**
     * EndPosition of delete in previous file.
     * Not set for other types.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer fromRowStart;

    /**
     * Deleted lines as a String.
     * Not set for other types.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lines;

    public String getType() {

        return type;
    }

    public void setType(final String type) {

        this.type = type;
    }

    public int getRowStart() {

        return rowStart;
    }

    public void setRowStart(final int startpos) {

        this.rowStart = startpos;
    }

    public int getRowEnd() {

        return rowEnd;
    }

    public void setRowEnd(final int endpos) {

        this.rowEnd = endpos;
    }

    @JsonIgnore
    public Long getId() {

        return id;
    }

    public int getOffset() {

        return offset;
    }

    public void setOffset(final int offset) {

        this.offset = offset;
    }

    public Integer getFromRowEnd() {

        return type.equals("delete") ? fromRowEnd : null;
    }

    public void setFromRowEnd(final int fromRowEnd) {

        this.fromRowEnd = fromRowEnd;
    }

    public Integer getFromRowStart() {

        return type.equals("delete") ? fromRowStart : null;
    }

    public void setFromRowStart(final int fromRowStart) {

        this.fromRowStart = fromRowStart;
    }

    public String getLines() {

        return type.equals("delete") ? lines : null;

    }

    public void setLines(final String lines) {

        this.lines = lines;
    }
}
