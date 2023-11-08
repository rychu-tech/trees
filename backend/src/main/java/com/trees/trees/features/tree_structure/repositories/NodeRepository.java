package com.trees.trees.features.tree_structure.repositories;

import com.trees.trees.features.tree_structure.models.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long> {
    List<Node> findByTreeId(Long treeId);
    Node findByIdAndTreeId(Long id, Long treeId);
    List<Node> findByParentNodeId(Long parentNodeId);
    Node findByParentNodeIdAndTreeId(Long parentNodeId, Long treeId);
    Node findByValueAndTreeId(Integer value, Long treeId);
}
