package edu.kit.ifv.trafficspvisualizer.model.settings;

/**
 * This class describes an abstract Attribute in a {@link Project}.
 */
public abstract class AbstractAttribute {
    private boolean active;

    /**
     * Constructor for a {@link AbstractAttribute}. Since this class is abstract this is only used by subclasses.
     *
     * @param active whether the {@link AbstractAttribute} is activated or not
     */
    protected AbstractAttribute(boolean active) {
        this.active = active;
    }

    /**
     * Returns whether the {@link AbstractAttribute} is active or not.
     *
     * @return {@code true} if the {@link AbstractAttribute} is active, {@code false} else
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the activation of the {@link AbstractAttribute}.
     *
     * @param active the new activation state
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns whether the {@link AbstractAttribute} contains any modifiable values.
     *
     * @return whether the {@link AbstractAttribute} contains any modifiable values
     */
    public abstract boolean hasValues();
}
