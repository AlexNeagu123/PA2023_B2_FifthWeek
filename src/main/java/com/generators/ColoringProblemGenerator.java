package com.generators;

import com.entities.Catalog;
import com.entities.Document;
import com.exceptions.DuplicateDocumentException;
import com.problem.GraphColoringProblem;

/**
 * This class is able to generate random instances of a {@link GraphColoringProblem}
 */
public class ColoringProblemGenerator {
    /**
     * Generate a random instance of a {@link GraphColoringProblem} considering specific parameters
     *
     * @param nodeCount The number of nodes the graph of documents should have
     * @return A randomly generated graph coloring problem
     */
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
