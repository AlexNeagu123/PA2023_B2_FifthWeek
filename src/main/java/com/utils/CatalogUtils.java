package com.utils;

import com.entities.Catalog;
import com.exceptions.InvalidHTMLFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CatalogUtils {
    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), Catalog.class);
    }

    public static boolean viewDocumentOnWeb(String uriPath) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI documentURI = new URI(uriPath);
            desktop.browse(documentURI);
            return true;
        } catch (URISyntaxException | IOException uriException) {
            return false;
        }
    }

    public static boolean viewDocumentAsLocalFile(String filePath) {
        try {
            Desktop desktop = Desktop.getDesktop();
            File documentFile = new File(filePath);
            desktop.open(documentFile);
            return true;
        } catch (IOException | IllegalArgumentException pathException) {
            return false;
        }
    }

    public static void generateCatalogReport(Catalog catalog, String reportPath, Configuration cfg) throws IOException, TemplateException, InvalidHTMLFile {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("catalog", catalog);
        dataModel.put("title", "catalogReport");

        Template template = cfg.getTemplate("template.ftl");
        Writer fileWriter = new BufferedWriter(new FileWriter(reportPath));
        template.process(dataModel, fileWriter);
        fileWriter.close();

        if (viewDocumentAsLocalFile(reportPath))
            return;

        throw new InvalidHTMLFile(reportPath);
    }

    public static void printDocumentMetadata(String documentPath) throws IOException, TikaException, SAXException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(System.out);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();

        InputStream stream = Files.newInputStream(Paths.get(documentPath));
        parser.parse(stream, handler, metadata, parseContext);

        for (String metadataName : metadata.names()) {
            System.out.println(metadataName + " : " + metadata.get(metadataName));
        }
    }
}
