package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * The description of the plugin
 * <p>
 * If used, MUST appear on same Element as {@link Plugin}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Description {

    /**
     * @return The brief description of this plugin
     */
    String value() default "";

}
