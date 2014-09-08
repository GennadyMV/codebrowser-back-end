package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Instance;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DefaultInstanceService implements InstanceService {

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    @Override
    public Collection<Instance> findAll() {

        return Arrays.asList(snapshotRestTemplate.getForObject("#", Instance[].class));
    }

    @Override
    public Instance find(final String id) {

        return snapshotRestTemplate.getForObject("{id}", Instance.class, id);
    }
}
