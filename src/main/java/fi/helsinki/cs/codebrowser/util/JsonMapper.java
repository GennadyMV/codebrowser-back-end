package fi.helsinki.cs.codebrowser.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonMapper extends ObjectMapper {

    private JsonNode traverseParseTree(final String json, final String[] path) throws IOException {

        System.out.println("JSON: " + json);

        JsonNode currentNode = readTree(json);
        for (String pathElement : path) {
            System.out.println("CurrentNode: " + currentNode);
            currentNode = currentNode.path(pathElement);
        }

        System.out.println("Last node: " + currentNode);
        return currentNode;
    }

    public <T> T mapSubElement(final String json, final Class<T> type, final String... path) throws IOException {

        final JsonNode node = traverseParseTree(json, path);

        return treeToValue(node, type);
    }
}
