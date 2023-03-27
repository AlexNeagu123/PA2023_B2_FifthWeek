package com;

import com.commands.AddCommand;
import com.commands.Command;
import com.commands.InfoCommand;
import com.commands.ReportCommand;
import com.entities.Catalog;
import com.entities.Document;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        configureFreeMarker(cfg);

        Catalog catalog = new Catalog("wtf???");

        // First Document Creation
        Document doc1 = new Document("23dsw1", "curs3java", "C:\\Users\\alexneagu\\Faculty_Y2\\Second_Semester\\Advanced_Programming\\Courses\\Curs4\\streams.pdf");
        doc1.addTag("iaibal", 2212);
        doc1.addTag("hmm", 2212);
        doc1.addTag("yeaah", 2212);

        // Second Document Creation
        Document doc2 = new Document("dsds", "wqsds", "a.com");

        // Commands Creation
        Command addCommand1 = new AddCommand(catalog, doc1);
        Command addCommand2 = new AddCommand(catalog, doc2);
        Command reportCommand = new ReportCommand(catalog, "src/main/resources/report.html", cfg);
        Command infoCommand = new InfoCommand(doc1);

        addCommand1.execute();
        addCommand2.execute();
        reportCommand.execute();
        infoCommand.execute();
    }

    public static void configureFreeMarker(Configuration cfg) throws IOException {
        cfg.setDirectoryForTemplateLoading(new File("src/main/java/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
    }
}
