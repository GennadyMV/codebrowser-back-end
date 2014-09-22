package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.Instance;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static fi.helsinki.cs.codebrowser.testutil.BackendServerStub.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultInstanceServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Autowired
    private InstanceService instanceService;

    private final BackendServerStub server = new BackendServerStub();

    private void assertEitherHasName(final List<Instance> instances, final String name) {

        for (Instance instance : instances) {
            if (instance.getName().equals(name)) {
                return;
            }
        }

        fail("No element has name " + name);
    }

    @Before
    public void setUp() {

        server.reset();
    }

    @Test
    public void findAllReturnsCorrectInstances() {

        final List<Instance> instances = (List<Instance>) instanceService.findAll();

        assertEquals(2, instances.size());
        assertEitherHasName(instances, INSTANCE_NAME);
    }

    @Test
    public void findReturnsCorrectInstance() {

        final Instance instance = instanceService.find(INSTANCE_ID);

        assertEquals(INSTANCE_NAME, instance.getName());
    }
}
