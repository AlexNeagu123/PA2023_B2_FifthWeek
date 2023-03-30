package com.commands;

import com.entities.Catalog;
import com.exceptions.InvalidHTMLFile;
import com.utils.CatalogUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

import java.io.IOException;

/**
 * The <tt>ReportCommand</tt> class is responsible for creating an HTML Report generated using <tt>FreeMarker</tt>
 * for a given {@link Catalog} object.
 * <p>
 * The path of the HTML file where the report will be created should be specified.
 */
@AllArgsConstructor
public class ReportCommand implements Command {
    private final Catalog catalog;
    private final String reportPath;
    private final Configuration cfg;

    /**
     * The HTML report is generated into the .
     * <p>
     * All the information extracted is copied into the {@code receiverCatalog} object.
     * <p>
     * Logs if the load couldn't take place.
     */
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
