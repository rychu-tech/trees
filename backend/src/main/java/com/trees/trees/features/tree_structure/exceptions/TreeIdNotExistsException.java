package com.trees.trees.features.tree_structure.exceptions;

public class TreeIdNotExistsException extends RuntimeException {
    public TreeIdNotExistsException() {
        super("Tree with such id does not exist!");
    }
}
