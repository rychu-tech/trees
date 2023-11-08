package com.trees.trees.features.tree_structure.exceptions;

public class NodeDoesNotBelongToTreeException extends RuntimeException {
    public NodeDoesNotBelongToTreeException() {
        super("This root does not belong to such tree!");
    }
}
