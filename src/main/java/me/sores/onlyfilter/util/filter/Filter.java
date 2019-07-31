package me.sores.onlyfilter.util.filter;

import me.sores.onlyfilter.config.Config;

/**
 * Created by sores on 7/31/2019.
 */
public class Filter {

    private boolean active;
    private String mode;

    public Filter() {
        this.active = Config.FILTER_ENABLED;
        this.mode = Config.FILTER_MODE;
    }

    public boolean isActive() {
        return active;
    }

    public String getMode() {
        return mode;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
