package it.voxibyte.voxiapi.processing.plugin;

import java.lang.annotation.*;

/**
 * Defines when the plugin should load: On Startup or Post world creation.
 * <p>
 * If used, MUST appear on same Element as {@link Plugin}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Load {

    /**
     * @return When this plugin should be loaded
     */
    LoadType value() default LoadType.POST_WORLD;

    /**
     * When this plugin should be loaded
     */
    @SuppressWarnings("unused")
    enum LoadType {
        /**
         * Use the default value for Spigot plugins
         */
        DEFAULT(null),
        /**
         * Before the world is loaded
         */
        STARTUP("STARTUP"),
        /**
         * After the world is loaded
         */
        POST_WORLD("POSTWORLD");

        private final String loadTypeValue;

        LoadType(String version) {
            this.loadTypeValue = version;
        }

        /**
         * @return The String version of this LoadType
         */
        public String getLoadType() {
            return loadTypeValue;
        }
    }

}
