package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * Defines the website for this plugin.
 * <p>
 * If used, MUST appear on same Element as {@link Plugin}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Website {

    /**
     * @return The website link for this plugin
     */
    String value();

}
