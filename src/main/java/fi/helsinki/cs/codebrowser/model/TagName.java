package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Text that can be shared by multiple tag instances.
 */
@Entity
public class TagName extends AbstractNamedPersistable implements Comparable<TagName> {

    /**
     * Tags that are using this text.
     */
    @OneToMany(mappedBy = "tagName")
    private List<Tag> tags;

    /**
     * Categories that this tagName is listed in.
     */
    @ManyToMany
    @JsonIgnoreProperties("tagnames")
    private List<TagCategory> tagCategories;

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

    public List<TagCategory> getTagCategories() {

        if (tagCategories == null) {
            return new ArrayList<>();
        }

        return tagCategories;
    }

    public void setTagCategories(final List<TagCategory> tagCategories) {

        this.tagCategories = tagCategories;
    }

    /**
     * Adds a category for tagName if category has not been added yet.
     * @param tagCategory category to be added
     * @return true if category was added, false otherwise
     */
    public boolean addTagCategory(final TagCategory tagCategory) {

        if (!getTagCategories().contains(tagCategory)) {
            getTagCategories().add(tagCategory);
            return true;
        }

        return false;
    }

    public void removeTagCategory(final TagCategory tagCategory) {

        getTagCategories().remove(tagCategory);
    }
}
