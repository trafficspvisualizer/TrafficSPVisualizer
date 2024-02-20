package edu.kit.ifv.trafficspvisualizer.model.settings;

/**
 * This class represents an {@link AbstractAttribute} in form of a separator line.
 * {@link SeparatorLine}s can be used in a {@link edu.kit.ifv.trafficspvisualizer.model.Project} to group
 * {@link AbstractAttribute}s.
 */
public class SeparatorLine extends AbstractAttribute {
    /**
     * Constructs a new {@link SeparatorLine}.
     */
    public SeparatorLine() {
        super(true);
    }

    @Override
    public boolean hasValues() {
        return false;
    }
}
