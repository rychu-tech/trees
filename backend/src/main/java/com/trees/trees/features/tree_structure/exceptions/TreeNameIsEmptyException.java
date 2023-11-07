package com.trees.trees.features.tree_structure.exceptions;

public class TreeNameIsEmptyException extends RuntimeException {
    public TreeNameIsEmptyException() {
        super("Nazwa drzewa nie może być pusta!");
    }
}
