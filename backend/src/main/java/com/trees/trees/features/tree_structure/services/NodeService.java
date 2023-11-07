package com.trees.trees.features.tree_structure.services;

import com.trees.trees.features.tree_structure.models.Node;
import com.trees.trees.features.tree_structure.models.Tree;
import com.trees.trees.features.tree_structure.repositories.NodeRepository;
import com.trees.trees.features.tree_structure.repositories.TreeRepository;
import com.trees.trees.features.tree_structure.requests.NodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NodeService {
    private final NodeRepository nodeRepository;
    private final TreeRepository treeRepository;
    @Autowired
    public NodeService(
            NodeRepository nodeRepository,
            TreeRepository treeRepository
    )
    {
        this.nodeRepository = nodeRepository;
        this.treeRepository = treeRepository;
    }

    public Node addRootNode(Tree tree) {
        Node node = new Node(tree);
        return nodeRepository.save(node);
    }

    public Node addNodeToTree(Long treeId, NodeRequest nodeRequest) {
        Node parentNode = nodeRepository.findById(nodeRequest.getParentNodeId()).orElse(null);
        Tree tree = treeRepository.findById(treeId).orElse(null);

        Node node = new Node(parentNode, tree, nodeRequest.getValue());
        return nodeRepository.save(node);
    }

    public Node editNode(Long nodeId, NodeRequest nodeRequest) {
        Node parentNode = nodeRepository.findById(nodeRequest.getParentNodeId()).orElse(null);
        Node node = nodeRepository.findById(nodeId).orElse(null);
        node.setParentNode(parentNode);
        node.setValue(nodeRequest.getValue());
        return nodeRepository.save(node);
    }

    public void deleteNode(Long nodeId) {
        Node nodeToDelete = nodeRepository.findById(nodeId).orElse(null);
        deleteNodeAndChildrenRecursively(nodeToDelete);
    }

    private void deleteNodeAndChildrenRecursively(Node node) {
        if (node == null) {
            return;
        }

        List<Node> children = nodeRepository.findByParentNodeId(node.getId());
        for (Node child : children) {
            deleteNodeAndChildrenRecursively(child);
        }

        nodeRepository.delete(node);
    }
}
