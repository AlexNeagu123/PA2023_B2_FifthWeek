package com.commands;

import com.entities.Catalog;
import com.utils.CatalogUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The <tt>SaveCommand</tt> class is responsible for saving a {@link Catalog} object into a JSON file specified by its
 * absolute path.
 */
@AllArgsConstructor
public class SaveCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SaveCommand.class);
    private final Catalog savedCatalog;
    private final String saveLocation;

    /**
     * The <tt>Catalog</tt>> is saved into the json file specified by {@code saveLocation}.
     * <p>
     * Logs if the saving couldn't take place.
     */
    @Override
    public void execute() {
        try {
            CatalogUtils.save(savedCatalog, saveLocation);
        } catch (IOException exception) {
            LOGGER.warn("The catalog " + savedCatalog.getName() + " couldn't be saved in the " + saveLocation + " location");
        }
    }
}
