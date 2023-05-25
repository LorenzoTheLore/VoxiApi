package it.voxibyte.voxiapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandMeta {
    String name();
    String description() default "";
    String usage() default "";
    String permission() default "";
    String[] aliases() default {};
    boolean playerOnly() default false;
}
