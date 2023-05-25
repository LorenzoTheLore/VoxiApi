package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * Defines the author of the plugin.
 * <p>
 * Mutually exclusive with {@link Authors}
 * <p>
 * If used, MUST appear on same Element as {@link Plugin}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Author {

    /**
     * @return The author of this plugin
     */
    String value();

}
