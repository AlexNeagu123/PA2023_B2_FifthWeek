package com;

import com.entities.Catalog;
import com.entities.Document;
import com.exceptions.DuplicateDocumentException;
import com.utils.CatalogUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, DuplicateDocumentException {
        Catalog catalog = new Catalog("myDoc");
        Document doc1 = new Document("23dsw1", "curs3java", "www.html.ro");
        doc1.addTag("year", 2032);
        doc1.addTag("description", "huinea de carte");
        catalog.add(doc1);
//        CatalogUtils.save(catalog, "src/main/java/com/wtf.json");
        Catalog loadedCatalog = CatalogUtils.load("src/main/java/com/wtf.json");
        System.out.println(loadedCatalog);
        System.out.println(catalog.equals(loadedCatalog));
    }
}
