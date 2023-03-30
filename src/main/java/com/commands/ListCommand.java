package com.commands;

import com.entities.Catalog;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ListCommand implements Command {
    @NonNull
    private final Catalog listedCatalog;

    @Override
    public void execute() {
        System.out.println(listedCatalog);
    }
}
