package com.commands;

import com.entities.Catalog;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The <tt>ListCommand</tt> class is responsible for displaying information about all the documents contained in a {@link Catalog} object
 */
@AllArgsConstructor
public class ListCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoadCommand.class);
    private final Catalog listedCatalog;

    @Override
    public void execute() {
        LOGGER.info(listedCatalog);
    }
}
