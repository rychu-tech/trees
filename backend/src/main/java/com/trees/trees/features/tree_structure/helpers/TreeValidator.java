package com.trees.trees.features.tree_structure.helpers;

import com.trees.trees.features.tree_structure.exceptions.*;
import com.trees.trees.features.tree_structure.models.Node;
import com.trees.trees.features.tree_structure.models.Tree;
import com.trees.trees.features.tree_structure.repositories.NodeRepository;
import com.trees.trees.features.tree_structure.repositories.TreeRepository;
import org.springframework.stereotype.Component;

@Component
public class TreeValidator {
    private final TreeRepository treeRepository;
    private final NodeRepository nodeRepository;
    public TreeValidator(
            TreeRepository treeRepository,
            NodeRepository nodeRepository
    )
    {
        this.treeRepository = treeRepository;
        this.nodeRepository = nodeRepository;
    }

    public void validateTreeName(String name) throws TreeNameExistsException, TreeNameIsEmptyException {
        Tree tree = treeRepository.findByName(name);
        if (tree != null) {
            throw new TreeNameExistsException();
        }
        if (name == null) {
            throw new TreeNameIsEmptyException();
        }
    }

    public void validateTreeId(Long treeId) throws TreeIdNotExistsException {
        Tree tree = treeRepository.findById(treeId).orElse(null);
        if (tree == null) {
            throw new TreeIdNotExistsException();
        }
    }

    public void validateNodeId(Long nodeId) throws NodeIdNotExistsException {
        Node node = nodeRepository.findById(nodeId).orElse(null);
        if (node == null) {
            throw new NodeIdNotExistsException();
        }
    }

    public void validateNodeBelongingToTree(Long nodeId, Long treeId) throws NodeDoesNotBelongToTreeException {
        Node node = nodeRepository.findByIdAndTreeId(nodeId, treeId);
        if (node == null) {
            throw new NodeDoesNotBelongToTreeException();
        }
    }

    public void validateTreeRootNodeDeletion(Long nodeId) throws RootNodeCannotBeDeletedException {
        Node node = nodeRepository.findById(nodeId).orElse(null);
        if (node.getParentNode() == null) {
            throw new RootNodeCannotBeDeletedException();
        }
    }

    public void validateValue(Integer value) throws EmptyValueException {
        if (value == null) {
            throw new EmptyValueException();
        }
    }
}
