package com.bastawesy.doctor_reservation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Supplier;

public class CommonUtils {
    public static final String BUNDLE_LOCATION = "";
    private static ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    private CommonUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Helper method to retrieve the messages from the localization file
     *
     * @param key    key for the message in the file
     * @param lang   locale to be retrieved e.g {en, ar. du}
     * @param params one or more parameter to be combined with the retrieved
     *               message
     * @return message as String
     **/
    public static String resourceBundle(String key, Locale lang, Object... params) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_LOCATION, lang);
        return MessageFormat.format(resourceBundle.getString(key), params);
    }

    /**
     * Helper method to retrieve the messages from the localization file
     *
     * @param key  key for the message in the file
     * @param lang locale to be retrieved e.g {en, ar. du}
     * @return message as String
     **/
    public static String resourceBundle(String key, Locale lang) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_LOCATION, lang);
        return resourceBundle.getString(key);
    }

    /**
     * Helper method to retrieve the messages from the default locale (en)
     *
     * @param key key for the message in the file
     * @return message as String
     **/
    public static String resourceBundle(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_LOCATION, Locale.ENGLISH);
        return resourceBundle.getString(key);
    }

    /**
     * Helper method to retrieve the messages from the default locale (en)
     *
     * @param key    key for the message in the file
     * @param params one or more parameter to be combined with the retrieved
     *               message
     * @return message as String
     **/
    public static String resourceBundle(String key, Object... params) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_LOCATION, Locale.ENGLISH);
        return MessageFormat.format(resourceBundle.getString(key), params);
    }

    public static Optional<String> toJson(Object data) {
        try {
            return Optional.ofNullable(mapper.writeValueAsString(data));
        } catch (Exception e) {
            logger.error("Error converting data : {} object to json", data, e);
            return Optional.empty();
        }
    }

    public static int asIntOr(String text, int def) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            logger.error("Error parsing text : {} to int. Returning default value : {}", text, def, e);
            return def;
        }
    }

    public static <t> t executeSafe(Supplier<t> supplier, t def) {
        try {
            return supplier.get();
        } catch (Exception e) {
            logger.error("Error executeSafe : {}. Returning default value : {}", supplier, def, e);
            return def;
        }
    }

    /**
     * Utility method to check null or empty for passed object
     *
     * @param object to be checked
     * @return true if passed object is null or empty
     */
    public static boolean isBlankOrNull(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return ((String) object).isEmpty();
        }
        if (object instanceof Collection) {
            return ((Collection<?>) object).isEmpty();
        }
        if (object instanceof Map) {
            return ((Map<?, ?>) object).isEmpty();
        }
        if (object.getClass().isArray()) {
            return ((Object[]) object).length == 0;
        }
        return false;
    }

    /**
     * Utility method to check that passed object is not null or empty
     *
     * @param object to be checked
     * @return true if passed object is null or empty
     */
    public static boolean isNotBlankOrNull(Object object) {
        return !isBlankOrNull(object);
    }

    public static <T> Optional<T> fromJson(String json, Class<T> klass) {
        try {
            return Optional.ofNullable(mapper.readValue(json, klass));
        } catch (Exception e) {
            logger.error("Could not create object from passed json ", e);
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> asList(T... values) {
        if (isBlankOrNull(values)) {
            return new ArrayList<>();
        }
        return new LinkedList<>(Arrays.asList(values));
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> asSet(T... values) {
        if (isBlankOrNull(values)) {
            return new HashSet<>();
        }
        return new HashSet<>(Arrays.asList(values));
    }


    /**
     * Map the passed object to string
     *
     * @param object to be mapped
     * @return Mapped String
     */
    public static String getObjectAsString(Object object) {
        if (isBlankOrNull(object)) {
            return "";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Could not convert object {} to String", object, e);
            return "";
        }
    }

    /**
     * Get stack trace of the passed exception as String
     * @param e Exception
     * @return Stack trace as string
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

}
