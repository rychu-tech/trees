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
    private int value;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "treeId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tree tree;
}