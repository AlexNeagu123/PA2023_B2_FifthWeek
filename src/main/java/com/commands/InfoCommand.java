package com.commands;

import com.entities.Catalog;
import com.entities.Document;
import com.exceptions.UnrecognizedPathException;
import com.utils.CatalogUtils;
import lombok.AllArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * The <tt>InfoCommand</tt> class is responsible for displaying the metadata of all the documents contained in a {@link Catalog} object
 */
@AllArgsConstructor
public class InfoCommand implements Command {
    private Catalog catalog;

    /**
     * Extracts metadata from all the documents contained in the catalog.
     * <p>
     * Logs if some extraction couldn't take place.
     */
    @Override
    public void execute() {
        for (Document doc : catalog.getDocuments()) {
            System.out.println("Extracting metadata from document with id '" + doc.getId() + "'...");
            try {
                CatalogUtils.printDocumentMetadata(doc);
            } catch (IOException fileException) {
                System.out.println("The document's location: " + doc.getLocation() + " is invalid.");
            } catch (TikaException | SAXException tikaException) {
                System.out.println("Failed to extract metadata from the document located at: " + doc.getLocation());
            } catch (UnrecognizedPathException unrecognizedException) {
                System.out.println(unrecognizedException.getMessage());
            }
        }
    }
}

