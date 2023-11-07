package com.trees.trees.features.tree_structure.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nodes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "parentNodeId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Node parentNode;

    @Column(name = "value", columnDefinition = "INT")
    private Integer value;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "treeId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tree tree;

    public Node (Tree tree) {
        this.tree = tree;
    }

    public Node (Node parentNode,  Tree tree, Integer value) {
        this.parentNode = parentNode;
        this.tree = tree;
        this.value = value;
    }
}
