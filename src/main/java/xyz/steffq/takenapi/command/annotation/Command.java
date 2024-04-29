package xyz.steffq.takenapi.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String value();

    boolean requirePlayer() default false;

    int minArguments() default 0;

    int maxArguments() default 1000;

    String permission() default "";

}
