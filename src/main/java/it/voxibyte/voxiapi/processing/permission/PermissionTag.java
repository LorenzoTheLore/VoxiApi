package it.voxibyte.voxiapi.processing.permission;

import org.bukkit.permissions.PermissionDefault;

import java.lang.annotation.*;

/**
 * Defines a permission for the plugin.
 * <p>
 * You do not need to create a permission for a command, as the permission
 * is generated when the command is defined.
 * <p>
 * See: https://www.spigotmc.org/wiki/plugin-yml/
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(PermissionsTag.class)
public @interface PermissionTag {

    /**
     * @return The permission string for this permission
     */
    String name();

    /**
     * @return The brief description for this permission
     */
    String desc();

    /**
     * See {@link PermissionDefault}
     *
     * @return The default permission level of this permission
     */
    PermissionDefault defaultValue() default PermissionDefault.OP;

}
