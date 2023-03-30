package com.entities;

import com.exceptions.DuplicateDocumentException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Catalog implements Serializable {
    private String name;
    private List<Document> documents;

    public Catalog() {
        documents = new ArrayList<>();
    }

    public Catalog(String name) {
        this.name = name;
        documents = new ArrayList<>();
    }

    public void add(Document doc) throws DuplicateDocumentException {
        if (documents.contains(doc)) {
            throw new DuplicateDocumentException(doc.getId(), name);
        }
        documents.add(doc);
    }

    public int documentCount() {
        return documents.size();
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder();
        stringRepresentation.append("Catalog '").append(name).append("' contains the following documents:\n");
        int counter = 1;
        for (Document doc : documents) {
            stringRepresentation.append(counter).append(".\n");
            stringRepresentation.append(doc);
            counter++;
        }
        return stringRepresentation.toString();
    }
}
