package com.commands;

import com.entities.Catalog;
import lombok.NonNull;

public class ListCommand implements Command {
    private final Catalog listedCatalog;

    public ListCommand(@NonNull Catalog listedCatalog) {
        this.listedCatalog = listedCatalog;
    }

    @Override
    public void execute() {
        System.out.println(listedCatalog);
    }
}
