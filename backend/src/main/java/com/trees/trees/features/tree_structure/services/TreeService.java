package com.trees.trees.features.tree_structure.services;

import com.trees.trees.features.tree_structure.helpers.TreeValidator;
import com.trees.trees.features.tree_structure.models.Node;
import com.trees.trees.features.tree_structure.models.NodeView;
import com.trees.trees.features.tree_structure.models.Tree;
import com.trees.trees.features.tree_structure.models.TreeView;
import com.trees.trees.features.tree_structure.repositories.NodeRepository;
import com.trees.trees.features.tree_structure.repositories.TreeRepository;
import com.trees.trees.features.tree_structure.requests.TreeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TreeService {
    private final NodeRepository nodeRepository;
    private final NodeService nodeService;
    private final TreeRepository treeRepository;
    @Autowired
    public TreeService(
            NodeRepository nodeRepository,
            TreeRepository treeRepository,
            NodeService nodeService
    )
    {
        this.nodeRepository = nodeRepository;
        this.treeRepository = treeRepository;
        this.nodeService = nodeService;
    }

    public Tree addTree(TreeRequest treeRequest) {
        Tree tree = new Tree(treeRequest.getName());
        Tree savedTree = treeRepository.save(tree);
        nodeService.addRootNode(tree);
        return savedTree;
    }

    public List<Tree> getTreeList() {
        List<Tree> trees = treeRepository.findAll();
        return trees;
    }

    public NodeView generateTreeView(Long treeId) {
        Node root = nodeRepository.findByParentNodeIdAndTreeId(null, treeId);
        NodeView rootNode = new NodeView(root.getValue());
        rootNode.setId(root.getId());
        buildTreeView(root, rootNode);
        return rootNode;
    }

    private void buildTreeView(Node node, NodeView parentNodeView) {
        List<Node> children = nodeRepository.findByParentNodeId(node.getId());
        for (Node child : children) {
            NodeView nodeView = new NodeView(child.getValue());
            nodeView.setId(child.getId());
            nodeView.setParentNodeId(child.getParentNode().getId());
            parentNodeView.addChild(nodeView);
            buildTreeView(child, nodeView);
        }
    }
}
