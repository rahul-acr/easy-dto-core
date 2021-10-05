package org.easydto.domain;

import org.easydto.caching.Caching;
import org.easydto.enums.ConversionType;
import org.easydto.proxy.Dto;

import java.util.Iterator;

public class StdConversionContext<T> implements ConversionContext<T> {

    private ConversionType conversionType;

    private String profile;

    private ConversionContext<?> parentContext;

    private Dto<T> dto;

    private T domainObject;

    private Iterator<PropertyConfiguration> propertyIterator;
    private PropertyConfiguration currentPropertyConfiguration;

    private StdConversionContext(ConversionContext<?> parentContext, T domainObject, Dto<T> dto) {
        this.parentContext = parentContext;
        this.domainObject = domainObject;
        this.dto = dto;
        this.profile = parentContext.getProfile();
        this.conversionType = parentContext.getConversionType();
        fetch();
    }

    private StdConversionContext() {

    }

    public static <C> ConversionContext<C> forDeConversion(Dto<C> dto, C domainObject, String profile) {
        StdConversionContext<C> cc = new StdConversionContext<>();
        cc.conversionType = ConversionType.DTO_TO_DOMAIN;
        cc.profile = profile;
        cc.dto = dto;
        cc.domainObject = domainObject;
        cc.fetch();
        return cc;
    }

    public static <C> ConversionContext<C> forConversion(C domainObject, String profile) {
        StdConversionContext<C> cc = new StdConversionContext<>();
        cc.conversionType = ConversionType.DOMAIN_TO_DTO;
        cc.profile = profile;
        cc.domainObject = domainObject;
        cc.fetch();
        return cc;
    }

    public <C> ConversionContext<C> createChildContext(C childObject) {
        return new StdConversionContext<>(this, childObject, null);
    }

    public <C> ConversionContext<C> createChildContext(Dto<C> cDto, C childObject) {
        return new StdConversionContext<>(this, childObject, cDto);
    }

    public ConversionType getConversionType() {
        return conversionType;
    }

    public String getProfile() {
        return profile;
    }

    public ConversionContext<?> getParentContext() {
        return parentContext;
    }

    public Dto<T> getDto() {
        return dto;
    }

    public T getDomainObject() {
        return domainObject;
    }

    @Override
    public PropertyConfiguration getCurrentPropertyConfiguration() {
        return currentPropertyConfiguration;
    }

    @Override
    public boolean nextProperty() {
        while (propertyIterator.hasNext()) {
            PropertyConfiguration pc = propertyIterator.next();

            if (!pc.isAllowedInProfile(profile))
                continue;

            if (conversionType == ConversionType.DOMAIN_TO_DTO && pc.property.canRead()) {
                currentPropertyConfiguration = pc;
                return true;
            }

            if (conversionType == ConversionType.DTO_TO_DOMAIN && pc.property.canWrite()) {
                currentPropertyConfiguration = pc;
                return true;
            }
        }

        return false;
    }

    private void fetch() {
        if (domainObject == null) return;
        this.propertyIterator = Caching.getConfiguration(domainObject.getClass())
                .getPropertyConfigurations().iterator();
    }
}
