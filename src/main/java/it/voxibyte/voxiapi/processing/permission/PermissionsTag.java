package it.voxibyte.voxiapi.processing.permission;

import java.lang.annotation.*;

/**
 * Container for repeatable {@link PermissionTag}.
 * <p>
 * Should NOT be used directly.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionsTag {

    /**
     * The {@link PermissionTag}s appearing on the same {@link javax.lang.model.element.Element}
     *
     * @return The PermissionsTags
     */
    PermissionTag[] value() default {};

}
