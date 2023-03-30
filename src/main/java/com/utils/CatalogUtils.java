package com.utils;

import com.entities.Catalog;
import com.entities.Document;
import com.exceptions.InvalidHTMLFile;
import com.exceptions.UnrecognizedPathException;
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
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
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

        if (viewDocumentAsLocalFile(reportPath)) return;

        throw new InvalidHTMLFile(reportPath);
    }

    private static InputStream getLocalFileStream(String filePath) {
        try {
            return Files.newInputStream(Paths.get(filePath));
        } catch (IOException | InvalidPathException fileException) {
            return null;
        }
    }

    private static InputStream getURLStream(String urlPath) {
        try {
            URL url = new URL(urlPath);
            return url.openStream();
        } catch (IOException urlException) {
            return null;
        }
    }

    public static void printDocumentMetadata(Document document) throws IOException, TikaException, SAXException, UnrecognizedPathException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        String docLocation = document.getLocation();

        InputStream stream = getLocalFileStream(docLocation);
        if (stream == null) {
            stream = getURLStream(docLocation);
        }
        if (stream == null) {
            throw new UnrecognizedPathException(docLocation);
        }

        parser.parse(stream, handler, metadata, parseContext);
        stream.close();

        for (String name : metadata.names()) {
            document.addMetadata(name, metadata.get(name));
        }
    }
}
