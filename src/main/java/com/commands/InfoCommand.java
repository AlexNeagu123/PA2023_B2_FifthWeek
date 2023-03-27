package com.commands;

import com.entities.Document;
import com.utils.CatalogUtils;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

public class InfoCommand implements Command {
    private Document document;

    public InfoCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        try {
            CatalogUtils.printDocumentMetadata(document.getLocation());
        }
        catch (IOException fileException) {
            System.out.println("The document's location: " + document.getLocation() + " is invalid.");
        }
        catch (TikaException | SAXException tikaException) {
            System.out.println("Failed to extract metadata from the document located at: " + document.getLocation());
        }
    }
}
