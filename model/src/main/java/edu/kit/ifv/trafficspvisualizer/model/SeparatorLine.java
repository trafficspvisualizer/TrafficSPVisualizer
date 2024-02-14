package edu.kit.ifv.trafficspvisualizer.model;

public class SeparatorLine extends AbstractAttribute {
    public SeparatorLine() {
        super(true);
    }

    @Override
    public boolean hasValues() {
        return false;
    }
}
