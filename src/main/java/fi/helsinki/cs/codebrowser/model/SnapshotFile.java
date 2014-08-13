package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.helsinki.cs.codebrowser.codeanalyzer.model.DiffList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.persistence.Entity;

@Entity
public class SnapshotFile extends AbstractNamedPersistable {

    @JsonIgnore
    private String filepath;

    private long filesize;
    private DiffList diffs;

    public String getFilepath() {

        return filepath;
    }

    public void setFilepath(final String filepath) {

        this.filepath = filepath;
    }

    @JsonIgnore
    public String getContent() throws FileNotFoundException {

        final Scanner scanner = new Scanner(new File(getFilepath()));
        final StringBuilder result = new StringBuilder();

        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();

            if (line.toLowerCase().contains("author")) {
                continue;
            }

            result.append(line).append("\n");
        }

        return result.toString();
    }

    public long getFilesize() {

        return filesize;
    }

    public void setFilesize(final long filesize) {

        this.filesize = filesize;
    }

    public DiffList getDiffs() {

        return diffs;
    }

    public void setDiffs(final DiffList diffs) {

        this.diffs = diffs;
    }
}
