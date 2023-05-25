package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * A list of plugins that are required for this plugin to have full functionality.
 * <p>
 * This plugin will load after any listed here.
 * <p>
 * If used, MUST appear on same Element as {@link Plugin}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SoftDepend {

    /**
     * @return Plugins that this plugin depends on to have full functionality, but not required
     */
    String[] value() default {};

}
