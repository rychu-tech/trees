package com.trees.trees.features.tree_structure.exceptions;

public class NodeDoesNotBelongToTreeException extends RuntimeException {
    public NodeDoesNotBelongToTreeException() {
        super("Ten węzeł nie należy do podanego drzewa!");
    }
}
