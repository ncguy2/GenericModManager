package net.ncguy.manager.api.databinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class MappedProperty<T> implements ReadOnlyProperty<T> {

    private final Property<?> sourceProperty;
    private final Function<Object, T> mapperFunc;
    private final List<IPropertyChangeListener<T>> listeners;

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public <U> MappedProperty(Property<U> sourceProperty, Function<U, T> mapperFunc) {
        this.sourceProperty = sourceProperty;
        this.mapperFunc = (Function<Object, T>) mapperFunc;
        this.listeners = new ArrayList<>();
    }

    @Override
    public T getValue() {
        return mapperFunc.apply(sourceProperty.getValue());
    }

    @Override
    public Collection<IPropertyChangeListener<T>> getListeners() {
        return listeners;
    }

}
