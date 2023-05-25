package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * Defines the main class of the plugin.
 * <p>
 * See: https://www.spigotmc.org/wiki/plugin-yml/
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {

    /**
     * @return The name of this plugin
     */
    String name();

    /**
     * Recommended to use SemVer
     *
     * @return The version of this plugin
     */
    String version();

}
