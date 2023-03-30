package com.commands;

import com.entities.Catalog;
import com.entities.Document;
import com.exceptions.DuplicateDocumentException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddCommand implements Command {
    private final Catalog catalog;
    private final Document addedDoc;

    @Override
    public void execute() {
        try {
            catalog.add(addedDoc);
        } catch (DuplicateDocumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
