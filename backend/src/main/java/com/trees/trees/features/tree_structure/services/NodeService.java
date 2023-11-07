package com.trees.trees.features.tree_structure.services;

import com.trees.trees.features.tree_structure.models.Node;
import com.trees.trees.features.tree_structure.models.Tree;
import com.trees.trees.features.tree_structure.repositories.NodeRepository;
import com.trees.trees.features.tree_structure.repositories.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Node addRootNode(Tree tree)
    {
        Node node = new Node(tree);
        return nodeRepository.save(node);
    }
}
