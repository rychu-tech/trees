package com.trees.trees.features.tree_structure.repositories;

import com.trees.trees.features.tree_structure.models.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    Tree findByName(String name);
}
