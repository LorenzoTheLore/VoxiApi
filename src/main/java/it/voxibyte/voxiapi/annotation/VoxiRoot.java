package it.voxibyte.voxiapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface VoxiRoot {
    /**
     * Specifies packages containing classes representing configurations
     *
     * @return packages containing configuration classes
     */
    String[] packages();
}
