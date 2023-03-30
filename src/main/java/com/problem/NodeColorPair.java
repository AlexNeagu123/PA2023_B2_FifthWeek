package com.problem;

import com.entities.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NodeColorPair {
    private Document document;
    private int color;

    @Override
    public String toString() {
        return "Document with id " + document.getId() + " colored with " + color;
    }
}

