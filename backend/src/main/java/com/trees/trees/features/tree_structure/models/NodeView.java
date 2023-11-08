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
public class NodeView {
    private Long id;
    private Long parentNodeId;
    private Integer value;
    private List<NodeView> children;
    private Integer leafSum;

    public NodeView(Integer value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
    public List<NodeView> getChildren() {
        return children;
    }

    public void addChild(NodeView child) {
        children.add(child);
    }
}