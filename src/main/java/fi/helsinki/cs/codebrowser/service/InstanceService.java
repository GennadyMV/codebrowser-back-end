package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Instance;

import java.util.Collection;

public interface InstanceService {

    Collection<Instance> findAll();
    Instance find(String id);

}
