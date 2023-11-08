package com.trees.trees.features.tree_structure.exceptions;

public class NodeIdNotExistsException extends RuntimeException {
    public NodeIdNotExistsException() {
        super ("Node with such id does not exist!");
    }
}
