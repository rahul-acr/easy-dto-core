package com.easydto.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DtoField {

    String DEFAULT = "$DEFAULT$";

    String value() default DEFAULT;

    String[] profile() default {};

}
