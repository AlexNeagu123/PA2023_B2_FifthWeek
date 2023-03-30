package com.generators;

import com.entities.Catalog;
import com.entities.Document;
import com.exceptions.DuplicateDocumentException;
import com.problem.GraphColoringProblem;

public class ColoringProblemGenerator {
    public static GraphColoringProblem generateColoringProblem(int nodeCount) {
        Catalog catalog = new Catalog("testCatalog");
        for (int i = 0; i < nodeCount; ++i) {
            Document doc = new Document(Integer.toString(i), "testDoc", "/");
            int edgeId = (int) (Math.random() * nodeCount / 10);
            try {
                doc.addTag("edge", Integer.toString(edgeId));
                catalog.add(doc);
            } catch (DuplicateDocumentException duplicateDocument) {
                System.out.println(duplicateDocument.getMessage());
            }
        }
        return new GraphColoringProblem(catalog);
    }
}
