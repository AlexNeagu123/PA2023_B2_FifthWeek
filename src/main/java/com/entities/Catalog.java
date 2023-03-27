package com.entities;

import com.exceptions.DuplicateDocumentException;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Catalog implements Serializable {
    private String name;
    private List<Document> documents;

    public Catalog(String name) {
        this.name = name;
        documents = new ArrayList<>();
    }

    public void add(Document doc) throws DuplicateDocumentException {
        if (documents.contains(doc)) {
            throw new DuplicateDocumentException(doc.getTitle(), name);
        }
        documents.add(doc);
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
