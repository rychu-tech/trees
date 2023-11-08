package com.trees.trees.features.tree_structure.exceptions;

public class EmptyValueException extends RuntimeException {
    public EmptyValueException() {
        super("Value cannot be empty!");
    }
}
