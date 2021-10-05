package org.easydto.conversion.converter.impl;

import org.easydto.domain.ConversionContext;
import org.easydto.domain.PropertyConfiguration;
import org.easydto.domain.WriteProperty;
import org.easydto.exception.DtoConversionException;
import org.easydto.proxy.Dto;
import org.easydto.proxy.DtoFactory;

import java.util.Map;

public class DefaultDtoDeConverter extends StdDeConverter {

    @Override
    void convertPrimitive(ConversionContext<?> cc, Object value) {
        PropertyConfiguration cp = cc.getCurrentPropertyConfiguration();
        ((WriteProperty) cp.property).write(cc.getDomainObject(), value);
    }

    @Override
    void convertBoxing(ConversionContext<?> cc, Object value) {
        PropertyConfiguration cp = cc.getCurrentPropertyConfiguration();
        WriteProperty property = ((WriteProperty) cp.property);
        Object target = cc.getDomainObject();

        if (property.getType() == Long.class) {
            property.write(target, ((Number) value).longValue());
        } else if (property.getType() == Integer.class) {
            property.write(target, ((Number) value).intValue());
        } else if (property.getType() == Double.class) {
            property.write(target, ((Number) value).doubleValue());
        } else if (property.getType() == Float.class) {
            property.write(target, ((Number) value).floatValue());
        } else {
            throw new UnsupportedOperationException("Boxing not fully supported yet");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    void convertNested(ConversionContext<?> cc, Object value) {
        PropertyConfiguration cp = cc.getCurrentPropertyConfiguration();
        WriteProperty property = ((WriteProperty) cp.property);
        Object target = cc.getDomainObject();

        Object child = ReflectionUtils.createNoArgInstance(property.getType());

        Dto<?> childDto;
        if (value instanceof Map) {
            childDto = DtoFactory.INSTANCE.createDtoFor(property.getType(), cc.getProfile());
            ((Map<String, Object>) value).forEach(childDto::putProperty);
        } else if (value instanceof Dto) {
            childDto = (Dto<?>) value;
        } else {
            throw new DtoConversionException("Unsupported nested type : " + value.getClass());
        }

        ConversionContext<?> childContext = cc.createChildContext((Dto<Object>)childDto, child);
        Object vv = convert(childContext);
        property.write(target, vv);
    }


}
