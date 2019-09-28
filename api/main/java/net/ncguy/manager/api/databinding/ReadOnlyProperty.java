package net.ncguy.manager.api.databinding;

import net.ncguy.manager.api.databinding.exceptions.ReadOnlyException;

public interface ReadOnlyProperty<T> extends Property<T> {

    @Override
    default void setValue(T val) {
        ReadOnlyException.raise();
    }

    @Override
    default void setValue_impl(T val) {
        ReadOnlyException.raise();
    }
}
