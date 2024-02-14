package edu.kit.ifv.trafficspvisualizer.util.project;

public enum JsonKeys {
    KEY_IMAGE_HEIGHT("imageHeight"),
    KEY_IMAGE_WIDTH("imageWidth"),
    KEY_FILE_FORMAT("fileFormat"),
    KEY_EXPORT_TYPE("exportType"),
    KEY_ICON("icon"),

    // Keys related to choice options and route sections
    KEY_CHOICE_DATA_KEY("choiceDataKey"),
    KEY_LINE_TYPE("lineType"),
    KEY_NAME_CHOICE_OPTION("name"),
    KEY_ROUTE_SECTIONS("routeSections"),
    KEY_CHOICE_OPTION_MAPPINGS("choiceOptionMappings"),

    // Keys related to attributes
    KEY_NAME("name"),
    KEY_PREFIX("prefix"),
    KEY_SUFFIX("suffix"),
    KEY_PERMANENTLY_VISIBLE("permanentlyVisible"),
    KEY_DECIMAL_PLACES("decimalPlaces"),
    KEY_ATTRIBUTES("attributes"),
    KEY_ATTRIBUTE("Attribute"),

    // Choice Options
    KEY_TITLE("title"),
    KEY_COLOR("color"),
    KEY_EXPORT_SETTINGS("exportSettings"),
    KEY_LINE_SEPARATOR("LineSeparator"),
    KEY_CHOICE_OPTIONS("ChoiceOptions");
    private final String key;

    JsonKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
