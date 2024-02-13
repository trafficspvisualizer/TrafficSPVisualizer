package edu.kit.ifv.trafficspvisualizer.model;

public abstract class AbstractAttribute {
    private boolean active;

    public AbstractAttribute() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
