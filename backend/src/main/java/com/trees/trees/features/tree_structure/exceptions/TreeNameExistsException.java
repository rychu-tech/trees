package com.trees.trees.features.tree_structure.exceptions;

public class TreeNameExistsException extends RuntimeException {
    public TreeNameExistsException() {
        super("Istnieje już drzewo z taką nazwą!");
    }
}
