package com.commands;

import com.entities.Catalog;
import com.exceptions.InvalidHTMLFile;
import com.utils.CatalogUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.NonNull;

import java.io.IOException;

public class ReportCommand implements Command {
    private final Catalog catalog;

    private final String reportPath;
    private final Configuration cfg;

    public ReportCommand(@NonNull Catalog catalog, String reportPath, @NonNull Configuration cfg) {
        this.catalog = catalog;
        this.reportPath = reportPath;
        this.cfg = cfg;
    }

    @Override
    public void execute() {
        try {
            CatalogUtils.generateCatalogReport(catalog, reportPath, cfg);
        } catch (TemplateException | IOException templateException) {
            System.out.println("The report couldn't be generated.");
        } catch (InvalidHTMLFile openingException) {
            System.out.println(openingException.getMessage());
        }
    }

}
