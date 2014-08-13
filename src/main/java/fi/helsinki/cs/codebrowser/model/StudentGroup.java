package fi.helsinki.cs.codebrowser.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class StudentGroup extends AbstractNamedPersistable {

    @ManyToMany(mappedBy = "groups")
    private List<Student> students;

    public List<Student> getStudents() {

        return students;
    }

    public void setStudents(final List<Student> students) {

        this.students = students;
    }
}

