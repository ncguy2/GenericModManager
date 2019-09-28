package net.ncguy.manager.api.databinding;

@FunctionalInterface
public interface IPropertyChangeListener<T> {

    void changed(T oldValue, T newValue);

}
