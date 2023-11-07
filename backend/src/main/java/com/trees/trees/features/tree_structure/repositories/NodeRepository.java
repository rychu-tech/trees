package com.trees.trees.features.tree_structure.repositories;

import com.trees.trees.features.tree_structure.models.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long> {
    List<Node> findByTreeId(Long treeId);
}
