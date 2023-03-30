package com.problem;

import com.entities.Document;
import com.utils.GraphUtils;
import org.jgrapht.Graph;
import org.jgrapht.alg.color.BrownBacktrackColoring;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JgraphTBrownColoring extends GraphColoringAlgorithm {
    public JgraphTBrownColoring(GraphColoringProblem graphColoringProblem) {
        super(graphColoringProblem);
    }

    public List<NodeColorPair> getColoring() {
        Graph<Document, DefaultEdge> graph = GraphUtils.convertToJgrapht(graphColoringProblem);
        BrownBacktrackColoring<Document, DefaultEdge> brownColoring = new BrownBacktrackColoring<>(graph);
        Map<Document, Integer> jgraphTColoring = brownColoring.getColoring().getColors();
        List<NodeColorPair> graphColoring = new ArrayList<>();
        for (Document doc : jgraphTColoring.keySet()) {
            graphColoring.add(new NodeColorPair(doc, jgraphTColoring.get(doc)));
        }
        return graphColoring;
    }
}
