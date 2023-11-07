package com.trees.trees.features.tree_structure.exceptions;

public class NodeIdNotExistsException extends RuntimeException {
    public NodeIdNotExistsException() {
        super ("Węzeł o tym Id nie istnieje!");
    }
}
