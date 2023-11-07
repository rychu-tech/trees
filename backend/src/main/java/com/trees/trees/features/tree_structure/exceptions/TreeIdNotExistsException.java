package com.trees.trees.features.tree_structure.exceptions;

public class TreeIdNotExistsException extends RuntimeException {
    public TreeIdNotExistsException() {
        super("Drzewo o tym Id nie istnieje!");
    }
}
