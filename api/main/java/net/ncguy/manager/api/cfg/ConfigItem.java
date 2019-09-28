package net.ncguy.manager.api.cfg;

import javafx.beans.property.Property;

public class ConfigItem<T> {

    public String internalName;
    public String displayName;

    public Class<T> valueType;
    public Property<T> binding;


}
