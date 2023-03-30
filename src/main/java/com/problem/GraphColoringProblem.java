package com.problem;

import com.entities.Catalog;
import com.entities.Document;
import com.utils.MetadataUtils;
import lombok.ToString;

import java.util.*;

@ToString
public class GraphColoringProblem {
    private final Map<Document, Set<Document>> neighbours;
    private final int nodeCount;
    private int edgeCount;

    public GraphColoringProblem(Catalog catalog) {
        this.nodeCount = catalog.documentCount();
        this.edgeCount = 0;
        neighbours = new HashMap<>();
        for (Document firstDoc : catalog.getDocuments()) {
            for (Document secondDoc : catalog.getDocuments()) {
                if (firstDoc.equals(secondDoc)) {
                    continue;
                }
                if (MetadataUtils.existsCommonTag(firstDoc.getTags(), secondDoc.getTags())
                        || MetadataUtils.existsCommonTag(firstDoc.getCachedMetadata(), secondDoc.getCachedMetadata())) {
                    addEdge(firstDoc, secondDoc);
                }
            }
        }
        System.out.println("Created a graph coloring problem instance with " + nodeCount + " nodes and " + edgeCount + " edges");
    }

    public List<Document> getNeighboursOfNode(Document doc) {
        if (neighbours.get(doc) == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(neighbours.get(doc));
    }

    public List<Document> getAllNodes() {
        return new ArrayList<>(neighbours.keySet());
    }

    private void addEdge(Document firstDoc, Document secondDoc) {
        this.edgeCount++;
        neighbours.computeIfAbsent(firstDoc, k -> new HashSet<>());
        neighbours.computeIfAbsent(secondDoc, k -> new HashSet<>());
        neighbours.get(firstDoc).add(secondDoc);
        neighbours.get(secondDoc).add(firstDoc);
    }
}
