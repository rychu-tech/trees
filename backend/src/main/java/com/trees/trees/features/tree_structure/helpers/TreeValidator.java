package com.trees.trees.features.tree_structure.helpers;

import com.trees.trees.features.tree_structure.exceptions.TreeNameExistsException;
import com.trees.trees.features.tree_structure.models.Tree;
import com.trees.trees.features.tree_structure.repositories.TreeRepository;
import org.springframework.stereotype.Component;

@Component
public class TreeValidator {
    private final TreeRepository treeRepository;
    public TreeValidator(
            TreeRepository treeRepository
    )
    {
        this.treeRepository = treeRepository;
    }

    public void validateTreeName(String name) throws TreeNameExistsException {
        Tree tree = treeRepository.findByName(name);
        if (tree != null || name == null) {
            throw new TreeNameExistsException();
        }
    }
}
