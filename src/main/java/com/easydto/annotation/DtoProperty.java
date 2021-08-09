package com.easydto.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DtoProperty {

    String DEFAULT = "$DEFAULT$";

    String value() default DEFAULT;

    String[] profile() default {};

}
