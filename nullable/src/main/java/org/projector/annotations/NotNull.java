package org.projector.annotations;

/**
 * Requires parameter's value is not null.
 * If some method's parameter marked as NotNull, it means that you need to expect a NullPointerException error when using <code>null</code> value.
 * As a rule, such methods contains null checking and throws NullPointerException manually.
 * 
 * @author Kirill Bogatikov
 */
public @interface NotNull {
    String value() default "";

    boolean strict() default true;
}
