package net.ncguy.manager.api.databinding;

import java.util.Collection;
import java.util.function.Function;

public interface Property<T> {

    T getValue();
    void setValue_impl(T val);
    Collection<IPropertyChangeListener<T>> getListeners();

    default void setValue(T val) {
        notifyListeners(val);
        setValue_impl(val);
    }

    default void bind(Property<T> bindTo) {
        bindTo.addListener((oldValue, newValue) -> setValue_impl(newValue));
    }

    default void bindBidirectional(Property<T> bindTo) {
        this.bind(bindTo);
        bindTo.bind(this);
    }

    default <U> Property<U> map(Function<T, U> mapper) {
        return new MappedProperty<>(this, mapper);
    }

    default void addListener(IPropertyChangeListener<T> listener) {
        getListeners().add(listener);
    }
    default void removeListener(IPropertyChangeListener<T> listener) {
        getListeners().remove(listener);
    }

    default void notifyListeners(T newValue) {
        getListeners().forEach(l -> l.changed(this.getValue(), newValue));
    }

}
