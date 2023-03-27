package com.commands;

import com.entities.Catalog;
import com.utils.CatalogUtils;
import lombok.NonNull;

import java.io.IOException;

public class LoadCommand implements Command {
    private final Catalog receiverCatalog;
    private final String loadLocation;

    public LoadCommand(@NonNull Catalog receiverCatalog, String loadLocation) {
        this.loadLocation = loadLocation;
        this.receiverCatalog = receiverCatalog;
    }

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
