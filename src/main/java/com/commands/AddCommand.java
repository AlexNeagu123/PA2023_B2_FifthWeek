package com.commands;

import com.entities.Catalog;
import com.entities.Document;
import com.exceptions.DuplicateDocumentException;
import lombok.AllArgsConstructor;

/**
 * The <tt>AddCommand</tt> class is responsible for adding a {@link Document} object into a specified {@link Catalog}
 */
@AllArgsConstructor
public class AddCommand implements Command {
    private final Catalog catalog;
    private final Document addedDoc;

    /**
     * Adds a <tt>Document</tt> object to a <tt>Catalog</tt> object.
     * <p>
     * Reports if the insertion didn't took place
     */
    @Override
    public void execute() {
        try {
            catalog.add(addedDoc);
        } catch (DuplicateDocumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
