package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class TagCategory extends AbstractNamedPersistable implements Comparable<TagCategory> {

    /**
     * TagNames that are in this category.
     */
    @OneToMany
    @JsonIgnoreProperties("tags")
    private List<TagName> tagnames;

    @Override
    public int compareTo(final TagCategory o) {

        if (getName() == null) {
            return -1;
        }

        return getName().compareTo(o.getName());
    }

    public List<TagName> getTagnames() {

        if (tagnames == null) {
            tagnames = new ArrayList<>();
        }

        return tagnames;
    }

    public void addTagName(final TagName tagname) {

        if (!getTagnames().contains(tagname)) {
            getTagnames().add(tagname);
        }
    }

    public void removeTagName(final TagName tagname) {

        tagnames.remove(tagname);
    }

    public void setTagnames(final List<TagName> tagnames) {

        this.tagnames = tagnames;
    }

}

