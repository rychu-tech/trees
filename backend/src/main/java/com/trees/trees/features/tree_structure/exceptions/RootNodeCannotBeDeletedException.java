package com.trees.trees.features.tree_structure.exceptions;

public class RootNodeCannotBeDeletedException extends RuntimeException {
    public RootNodeCannotBeDeletedException() {
        super("Nie można usunąć korzenia drzewa!");
    }
}
