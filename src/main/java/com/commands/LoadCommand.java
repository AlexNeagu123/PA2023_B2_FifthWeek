package com.commands;

import com.entities.Catalog;
import com.utils.CatalogUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The <tt>LoadCommand</tt> class is responsible for loading a {@link Catalog} object from a JSON file specified by its
 * absolute path.
 */
@AllArgsConstructor
public class LoadCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoadCommand.class);
    private final Catalog receiverCatalog;
    private final String loadLocation;

    /**
     * The <tt>Catalog</tt>> is extracted from the json file.
     * <p>
     * All the information extracted is copied into the {@code receiverCatalog} object.
     * <p>
     * Logs if the loading couldn't take place.
     */
    @Override
    public void execute() {
        try {
            Catalog loadedCatalog = CatalogUtils.load(loadLocation);
            receiverCatalog.setName(loadedCatalog.getName());
            receiverCatalog.setDocuments(loadedCatalog.getDocuments());
        } catch (IOException exception) {
            LOGGER.warn("The catalog couldn't be loaded from the " + loadLocation + " location");
        }
    }
}
