package org.easydto.conversion.converter.impl;

import org.easydto.conversion.converter.DtoDeConverter;
import org.easydto.domain.ConversionContext;
import org.easydto.domain.PropertyConfiguration;
import org.easydto.domain.WriteProperty;
import org.easydto.exception.DtoConversionException;
import org.easydto.proxy.Dto;

public abstract class StdDeConverter implements DtoDeConverter {

    @Override
    public <T> T convert(ConversionContext<T> cc) throws DtoConversionException {
        Dto<T> dto = cc.getDto();
        T obj = cc.getDomainObject();
        while(cc.nextProperty()){
            PropertyConfiguration cp = cc.getCurrentPropertyConfiguration();

            WriteProperty property = (WriteProperty) cp.property;

            Object dtoValue = dto.getProperty(cp);

            if (dtoValue == null)
                continue;

            switch (property.propertyType()) {
                case SIMPLE:
                    convertPrimitive(cc,  dtoValue);
                    break;
                case BOXED:
                    convertBoxing(cc,  dtoValue);
                    break;
                case COMPLEX:
                    convertNested(cc, dtoValue);
                    break;
                default:
                    throw new UnsupportedOperationException(property.propertyType() + " is not supported");
            }
        }

        return obj;
    }


    abstract void convertPrimitive(ConversionContext<?> cc, Object value);

    abstract void convertBoxing(ConversionContext<?> cc,  Object value);

    abstract void convertNested(ConversionContext<?> cc, Object value);

}
