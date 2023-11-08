package com.trees.trees.features.tree_structure.exceptions;

public class TreeNameExistsException extends RuntimeException {
    public TreeNameExistsException() {
        super("Tree with such name exists!");
    }
}
