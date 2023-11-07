package com.trees.trees.features.tree_structure.controllers;

import com.trees.trees.features.tree_structure.helpers.TreeValidator;
import com.trees.trees.features.tree_structure.models.Node;
import com.trees.trees.features.tree_structure.models.NodeView;
import com.trees.trees.features.tree_structure.models.Tree;
import com.trees.trees.features.tree_structure.requests.NodeRequest;
import com.trees.trees.features.tree_structure.requests.TreeRequest;
import com.trees.trees.features.tree_structure.services.NodeService;
import com.trees.trees.features.tree_structure.services.TreeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trees")
public class TreeController {
    private final TreeService treeService;
    private final NodeService nodeService;
    private final TreeValidator treeValidator;
    public TreeController(
            TreeService treeService,
            TreeValidator treeValidator,
            NodeService nodeService
    )
    {
        this.treeService = treeService;
        this.treeValidator = treeValidator;
        this.nodeService = nodeService;
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

    @PostMapping("/{treeId}/nodes")
    public void addNodeToTree(
        @RequestBody NodeRequest nodeRequest,
        @PathVariable Long treeId
    )
    {
        Long nodeId = nodeRequest.getParentNodeId();
        treeValidator.validateTreeId(treeId);
        treeValidator.validateNodeId(nodeId);
        treeValidator.validateNodeBelongingToTree(nodeId, treeId);

        nodeService.addNodeToTree(treeId, nodeRequest);
    }

    @PutMapping("/{treeId}/nodes/{nodeId}")
    public void editTreeNode(
            @RequestBody NodeRequest nodeRequest,
            @PathVariable Long treeId,
            @PathVariable Long nodeId
    )
    {
        Long parentNodeId = nodeRequest.getParentNodeId();
        treeValidator.validateTreeId(treeId);
        treeValidator.validateNodeId(nodeId);
        treeValidator.validateNodeId(parentNodeId);
        treeValidator.validateNodeBelongingToTree(nodeId, treeId);
        treeValidator.validateNodeBelongingToTree(parentNodeId, treeId);

        nodeService.editNode(nodeId, nodeRequest);
    }

    @DeleteMapping("/{treeId}/nodes/{nodeId}")
    public void deleteTreeNode(
            @PathVariable Long treeId,
            @PathVariable Long nodeId
    )
    {
        treeValidator.validateTreeId(treeId);
        treeValidator.validateNodeId(nodeId);
        treeValidator.validateNodeBelongingToTree(nodeId, treeId);
        treeValidator.validateTreeRootNodeDeletion(nodeId);

        nodeService.deleteNode(nodeId);
    }

    @GetMapping("/{treeId}")
    public NodeView getTree(
            @PathVariable Long treeId
    )
    {
        treeValidator.validateTreeId(treeId);
        return treeService.generateTreeView(treeId);
    }

}
