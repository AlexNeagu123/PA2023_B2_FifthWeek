package com.exceptions;

import com.entities.Document;

public class DuplicateDocumentException extends Exception {
    public DuplicateDocumentException(String documentId, String catalogName) {
        super("Document with id " + documentId + " has already been added to the catalog '" +catalogName + "'");
    }
}
