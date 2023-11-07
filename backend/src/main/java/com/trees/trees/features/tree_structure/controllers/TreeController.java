package com.trees.trees.features.tree_structure.controllers;

import com.trees.trees.features.tree_structure.helpers.TreeValidator;
import com.trees.trees.features.tree_structure.models.Tree;
import com.trees.trees.features.tree_structure.requests.TreeRequest;
import com.trees.trees.features.tree_structure.services.TreeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trees")
public class TreeController {
    private final TreeService treeService;
    private final TreeValidator treeValidator;
    public TreeController(
            TreeService treeService,
            TreeValidator treeValidator
    )
    {
        this.treeService = treeService;
        this.treeValidator = treeValidator;
    }

    @PostMapping
    public Tree addTree(
            @RequestBody TreeRequest treeRequest
    )
    {
        String treeName = treeRequest.getName();
        treeValidator.validateTreeName(treeName);
        return treeService.addTree(treeRequest);
    }

    @GetMapping
    public List<Tree> getTreeList() {
        return treeService.getTreeList();
    }

}
