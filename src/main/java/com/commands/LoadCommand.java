package com.commands;

import com.entities.Catalog;
import com.utils.CatalogUtils;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class LoadCommand implements Command {
    private final Catalog receiverCatalog;
    private final String loadLocation;

    @Override
    public void execute() {
        try {
            Catalog loadedCatalog = CatalogUtils.load(loadLocation);
            receiverCatalog.setName(loadedCatalog.getName());
            receiverCatalog.setDocuments(loadedCatalog.getDocuments());
        } catch (IOException exception) {
            System.out.println("The catalog couldn't be loaded from the " + loadLocation + " location");
        }
    }
}
