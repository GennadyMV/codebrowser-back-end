package fi.helsinki.cs.codebrowser.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public final class JsonMapper extends ObjectMapper {

    private JsonNode traverseParseTree(final String json, final String[] path) throws IOException {

        JsonNode currentNode = readTree(json);

        for (String pathElement : path) {
            currentNode = currentNode.path(pathElement);
        }

        return currentNode;
    }

    /**
     * Returns T[].class for any T.
     * This is a workaround for Java’s lackluster support of generic arrays.
     */
    private <T> Class getArrayType(final Class<T> type) {

        final T[] arrayType = (T[]) Array.newInstance(type, 0);

        return arrayType.getClass();
    }

    public <T> T readSubElementValue(final String json, final Class<T> type, final String... path) throws IOException {

        final JsonNode node = traverseParseTree(json, path);

        return treeToValue(node, type);
    }

    public <T> List<T> readSubElementValueToList(final String json, final Class<T> type, final String... path) throws IOException {

        final T[] array = (T[]) readSubElementValue(json, getArrayType(type), path);

        return Arrays.asList(array);
    }

    public <T> List<T> readValueToList(final String json, final Class<T> type) throws IOException {

        return readSubElementValueToList(json, type);
    }
}
