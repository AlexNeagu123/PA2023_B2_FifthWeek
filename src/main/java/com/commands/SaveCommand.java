package com.commands;

import com.entities.Catalog;
import com.utils.CatalogUtils;
import lombok.NonNull;

import java.io.IOException;

public class SaveCommand implements Command {
    private final Catalog savedCatalog;
    private final String saveLocation;

    public SaveCommand(@NonNull Catalog savedCatalog, String saveLocation) {
        this.saveLocation = saveLocation;
        this.savedCatalog = savedCatalog;
    }

    @Override
    public void execute() {
        try {
            CatalogUtils.save(savedCatalog, saveLocation);
        } catch (IOException exception) {
            System.out.println("The catalog " + savedCatalog.getName() + " couldn't be saved in the " + saveLocation + " location");
        }
    }
}
