package net.ncguy.manager.api.databinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleProperty<T> implements Property<T> {

    private T value;
    private final List<IPropertyChangeListener<T>> listeners;

    public SimpleProperty() {
        this(null);
    }

    @SuppressWarnings("WeakerAccess")
    public SimpleProperty(T value) {
        setValue_impl(value);
        listeners = new ArrayList<>();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue_impl(T val) {
        this.value = val;
    }

    @Override
    public Collection<IPropertyChangeListener<T>> getListeners() {
        return listeners;
    }
}
