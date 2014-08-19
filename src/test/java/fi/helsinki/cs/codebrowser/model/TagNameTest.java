package fi.helsinki.cs.codebrowser.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TagNameTest {

    private TagName tagName;

    @Before
    public void setUp() {

        tagName = new TagName();
    }

    @Test
    public void canSetTags() {

        final List<Tag> tags = new ArrayList<>();
        tagName.setTags(tags);

        assertEquals(tags, tagName.getTags());
    }

    @Test
    public void canAddTag() {

        final Tag tag = new Tag();
        tagName.addTag(tag);

        assertEquals(1, tagName.getTags().size());
        assertEquals(tag, tagName.getTags().get(0));
    }

    @Test
    public void compareToHandlesNull() {

        assertEquals(-1, tagName.compareTo(null));
    }

    @Test
    public void compareToUsesNamesToCompare() {

        final TagName tn1 = new TagName();
        tn1.setName("tag1");

        final TagName tn2 = new TagName();
        tn2.setName("tag2");

        int expectedResult = tn1.getName().compareTo(tn2.getName());
        assertEquals(expectedResult, tn1.compareTo(tn2));

        expectedResult = tn2.getName().compareTo(tn1.getName());
        assertEquals(expectedResult, tn2.compareTo(tn1));

        assertEquals(0, tn1.compareTo(tn1));
    }
}
