package com.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document implements Serializable {
    private String id;
    private String title;
    private String location;
    private Map<String, String> tags;
    private Map<String, String> cachedMetadata;

    public Document(String id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
        tags = new HashMap<>();
        cachedMetadata = new HashMap<>();
    }

    public void addTag(String tagKey, String tagValue) {
        tags.put(tagKey, tagValue);
    }

    public void addMetadata(String metadataKey, String metadataValue) {
        if (cachedMetadata.containsKey(metadataKey)) {
            return;
        }
        cachedMetadata.put(metadataKey, metadataValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return id.equals(document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder();

        stringRepresentation.append("Document's Name: ").append(title).append('\n');
        stringRepresentation.append("Document's Id: ").append(id).append('\n');
        stringRepresentation.append("Document's Path Location: ").append(location).append('\n');
        stringRepresentation.append("Document's Tags: {\n");
        for (String tag : tags.keySet()) {
            stringRepresentation.append("   ").append(tag).append(" : ").append(tags.get(tag)).append("\n");
        }
        stringRepresentation.append("}\n");
        return stringRepresentation.toString();
    }
}
