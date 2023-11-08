package com.trees.trees.features.tree_structure.exceptions;

public class RootNodeCannotBeDeletedException extends RuntimeException {
    public RootNodeCannotBeDeletedException() {
        super("Root node cannot be deleted!");
    }
}
