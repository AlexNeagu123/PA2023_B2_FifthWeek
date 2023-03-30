package com.commands;

import com.entities.Catalog;
import com.exceptions.InvalidHTMLFile;
import com.utils.CatalogUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class ReportCommand implements Command {
    private final Catalog catalog;
    private final String reportPath;
    private final Configuration cfg;

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
