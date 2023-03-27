package com.commands;

import com.entities.Document;
import com.utils.CatalogUtils;
import lombok.NonNull;

public class ViewCommand implements Command {
    private final Document document;

    public ViewCommand(@NonNull Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        if (CatalogUtils.viewDocumentOnWeb(document.getLocation()))
            return;

        if (CatalogUtils.viewDocumentAsLocalFile(document.getLocation()))
            return;

        System.out.println("The document with id '" + document.getId() + "' couldn't be opened neither as web URI nor as local file");
    }
}

