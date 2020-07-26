package org.projector.annotations;

public @interface NotNull {
    String value() default "";

    boolean strict() default true;
}
