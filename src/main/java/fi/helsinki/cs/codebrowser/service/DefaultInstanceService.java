package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Instance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultInstanceService implements InstanceService {

    @Value("#{'${spyware.instances}'.split(' ')}")
    private List<String> spywareInstances;

    @Override
    public Collection<Instance> findAll() {

        final List<Instance> instances = new ArrayList<>();

        for (String instance : spywareInstances) {
            instances.add(new Instance(instance));
        }

        return instances;
    }
}
