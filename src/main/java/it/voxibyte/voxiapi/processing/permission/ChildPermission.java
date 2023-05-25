package it.voxibyte.voxiapi.processing.permission;

import java.lang.annotation.*;

/**
 * Defines a child permission under a {@link PermissionTag}
 * <p>
 * See: https://www.spigotmc.org/wiki/plugin-yml/
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ChildPermissions.class)
public @interface ChildPermission {

    /**
     * @return The parent permission's name
     */
    String parent();

    /**
     * @return The name of this permission
     */
    String name();

    /**
     * @return True if this permission should have the same default access as the parent, false to have the inverse of the parent
     */
    boolean inherit() default true;

}
