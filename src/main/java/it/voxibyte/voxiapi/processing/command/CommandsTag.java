package it.voxibyte.voxiapi.processing.command;

import java.lang.annotation.*;

/**
 * Container for repeatable {@link CommandTag}.
 * <p>
 * Should NOT be used directly.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandsTag {

    /**
     * The {@link CommandTag}s appearing on the same {@link javax.lang.model.element.Element}
     *
     * @return The CommandTags
     */
    CommandTag[] value() default {};

}
