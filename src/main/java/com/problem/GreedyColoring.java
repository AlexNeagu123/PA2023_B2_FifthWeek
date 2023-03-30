package com.problem;

import com.entities.Document;

import java.util.*;

public class GreedyColoring extends GraphColoringAlgorithm {

    private final Map<Document, Integer> coloring;

    public GreedyColoring(GraphColoringProblem graphColoringProblem) {
        super(graphColoringProblem);
        coloring = new HashMap<>();
    }

    private int firstAvailable(Set<Integer> usedColors) {
        int color = 1;
        while (true) {
            if (!usedColors.contains(color)) {
                return color;
            }
            color++;
        }
    }

    public List<NodeColorPair> getColoring() {
        for (Document node : graphColoringProblem.getAllNodes()) {
            Set<Integer> usedColors = new HashSet<>();
            for (Document neig : graphColoringProblem.getNeighboursOfNode(node)) {
                if (coloring.containsKey(neig))
                    usedColors.add(coloring.get(neig));
            }
            coloring.put(node, firstAvailable(usedColors));
        }
        List<NodeColorPair> graphColoring = new ArrayList<>();
        for (Document node : coloring.keySet()) {
            graphColoring.add(new NodeColorPair(node, coloring.get(node)));
        }
        return graphColoring;
    }
}
