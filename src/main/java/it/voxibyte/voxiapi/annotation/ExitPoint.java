package it.voxibyte.voxiapi.annotation;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to declare the exit point method for the plugin.
 * The method annotated with this will be called on {@link JavaPlugin#onDisable()}
 *
 * <strong>NOTE:</strong> If no entrypoint is specified plugin will not load
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExitPoint {
}
