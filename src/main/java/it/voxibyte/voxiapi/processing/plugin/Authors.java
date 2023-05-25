package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * Defines multiple authors of the plugin.
 * <p>
 * Mutually exclusive with {@link Author}
 * <p>
 * If used, MUST appear on same Element as {@link Plugin}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Authors {

    /**
     * @return The authors of this plugin
     */
    String[] value() default {};

}
