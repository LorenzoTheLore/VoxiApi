package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * A list of plugins that should load AFTER this plugin.
 * <p>
 * If used, MUST appear on same Element as {@link Plugin}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadBefore {

    /**
     * @return Plugins that should be loaded after this plugin is loaded
     */
    String[] value() default {};

}
