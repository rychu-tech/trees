package com.trees.trees.features.tree_structure.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NodeRequest {
    private Long parentNodeId;
    private Integer value;
}
