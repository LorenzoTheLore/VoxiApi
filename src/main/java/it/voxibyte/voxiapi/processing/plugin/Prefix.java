package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * The name to use when logging to console instead of the plugin's name.
 * <p>
 * If used, MUST appear on same Element as {@link Plugin}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Prefix {

    /**
     * @return The custom log prefix to use for this plugin
     */
    String value();

}
