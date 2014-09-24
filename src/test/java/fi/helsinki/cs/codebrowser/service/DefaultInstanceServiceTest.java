package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.Instance;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.ClassRule;
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

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8089);

    private static final BackendServerStub SERVER = new BackendServerStub();

    @Autowired
    private InstanceService instanceService;

    @BeforeClass
    public static void setUpClass() {

        SERVER.initialiseServer();
    }

    private void assertEitherHasName(final List<Instance> instances, final String name) {

        for (Instance instance : instances) {
            if (instance.getName().equals(name)) {
                return;
            }
        }

        fail("No element has name " + name);
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
