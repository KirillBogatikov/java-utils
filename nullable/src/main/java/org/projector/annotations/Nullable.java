package org.projector.annotations;

/**
 * Allows parameter's value to be nullable.
 * Values marked with this annotations requires checking for null values. 
 * If some method's parameter marked as Nullable, it means that you don't need to expect a NullPointerException error when using <code>null</code> value.
 * 
 * @author Kirill Bogatikov
 */
public @interface Nullable {
    String value() default "";
}
