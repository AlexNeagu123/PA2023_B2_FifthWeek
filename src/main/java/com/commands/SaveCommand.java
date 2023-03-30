package com.commands;

import com.entities.Catalog;
import com.utils.CatalogUtils;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class SaveCommand implements Command {
    private final Catalog savedCatalog;
    private final String saveLocation;

    @Override
    public void execute() {
        try {
            CatalogUtils.save(savedCatalog, saveLocation);
        } catch (IOException exception) {
            System.out.println("The catalog " + savedCatalog.getName() + " couldn't be saved in the " + saveLocation + " location");
        }
    }
}
