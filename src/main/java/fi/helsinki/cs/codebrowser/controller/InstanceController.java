package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Instance;
import fi.helsinki.cs.codebrowser.service.InstanceService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.GET, produces = "application/json")
public class InstanceController {

    @Autowired
    private InstanceService instanceService;

    @RequestMapping(value = "instances")
    public Collection<Instance> list() {

        return instanceService.findAll();
    }
}
