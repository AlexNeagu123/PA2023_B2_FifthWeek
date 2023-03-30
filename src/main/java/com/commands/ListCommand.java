package com.commands;

import com.entities.Catalog;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * The <tt>ListCommand</tt> class is responsible for displaying information about all the documents contained in a {@link Catalog} object
 */
@AllArgsConstructor
public class ListCommand implements Command {
    @NonNull
    private final Catalog listedCatalog;

    @Override
    public void execute() {
        System.out.println(listedCatalog);
    }
}
