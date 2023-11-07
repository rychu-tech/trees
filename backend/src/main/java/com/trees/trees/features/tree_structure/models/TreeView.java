package com.trees.trees.features.tree_structure.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreeView {
    private String name;
    private List<NodeView> children;

    public TreeView(String name) {
        this.name = name;
    }

    public void addChild(NodeView child) {
        children.add(child);
    }
}
