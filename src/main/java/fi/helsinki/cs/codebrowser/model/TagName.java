package fi.helsinki.cs.codebrowser.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

/**
 * Text that can be shared by multiple tag instances.
 */
//@Entity
public class TagName extends AbstractNamedPersistable implements Comparable<TagName> {

    /**
     * Tags that are using this text.
     */
    @OneToMany(mappedBy = "tagName")
    private List<Tag> tags;

    public List<Tag> getTags() {

        if (tags == null) {
            tags = new ArrayList<>();
        }

        return tags;
    }

    public void setTags(final List<Tag> tags) {

        this.tags = tags;
    }

    public void addTag(final Tag tag) {

        if (!getTags().contains(tag)) {
            getTags().add(tag);
        }
    }

    @Override
    public int compareTo(final TagName o) {

        if (getName() == null) {
            return -1;
        }

        return getName().compareTo(o.getName());
    }
}
