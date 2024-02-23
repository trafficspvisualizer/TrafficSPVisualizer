package edu.kit.ifv.trafficspvisualizer.util.project;

/**
 * Enum representing various keys used in JSON processing.
 */
public enum JsonKeys {

    /**
     * Key for image height.
     */
    KEY_IMAGE_HEIGHT("imageHeight"),

    /**
     * Key for image width.
     */
    KEY_IMAGE_WIDTH("imageWidth"),

    /**
     * Key for file format.
     */
    KEY_FILE_FORMAT("fileFormat"),

    /**
     * Key for export type.
     */
    KEY_EXPORT_TYPE("exportType"),

    /**
     * Key for icon.
     */
    KEY_ICON("icon"),

    // Keys related to choice options and route sections

    /**
     * Key for choice data key.
     */
    KEY_CHOICE_DATA_KEY("choiceDataKey"),

    /**
     * Key for line type.
     */
    KEY_LINE_TYPE("lineType"),

    /**
     * Key for name of choice option.
     */
    KEY_NAME_CHOICE_OPTION("name"),

    /**
     * Key for route sections.
     */
    KEY_ROUTE_SECTIONS("routeSections"),

    /**
     * Key for choice option mappings.
     */
    KEY_CHOICE_OPTION_MAPPINGS("choiceOptionMappings"),

    // Keys related to attributes

    /**
     * Key for name.
     */
    KEY_NAME("name"),

    /**
     * Key for prefix.
     */
    KEY_PREFIX("prefix"),

    /**
     * Key for suffix.
     */
    KEY_SUFFIX("suffix"),

    /**
     * Key for permanently visible.
     */
    KEY_PERMANENTLY_VISIBLE("permanentlyVisible"),

    /**
     * Key for decimal places.
     */
    KEY_DECIMAL_PLACES("decimalPlaces"),

    /**
     * Key for attributes.
     */
    KEY_ATTRIBUTES("attributes"),

    /**
     * Key for attribute.
     */
    KEY_ATTRIBUTE("Attribute"),

    /**
     * Key for active.
     */
    KEY_ACTIVE("Active"),

    // Choice Options

    /**
     * Key for title.
     */
    KEY_TITLE("title"),

    /**
     * Key for color.
     */
    KEY_COLOR("color"),

    /**
     * Key for export settings.
     */
    KEY_EXPORT_SETTINGS("exportSettings"),

    /**
     * Key for line separator.
     */
    KEY_LINE_SEPARATOR("LineSeparator"),

    /**
     * Key for choice options.
     */
    KEY_CHOICE_OPTIONS("ChoiceOptions"),

    /**
     * Key for choice option.
     */
    KEY_CHOICE_OPTION("ChoiceOption"),

    /**
     * Key for list.
     */
    KEY_LIST("List"),

    /**
     * Key for HTML variable.
     */
    KEY_HTML_VARIABLE("HtmlVariable");

    /**
     * The actual key string.
     */
    private final String key;

    /**
     * Constructor for JsonKeys.
     *
     * @param key The key string.
     */
    JsonKeys(String key) {
        this.key = key;
    }

    /**
     * Getter for the key string.
     *
     * @return The key string.
     */
    public String getKey() {
        return this.key;
    }
}
