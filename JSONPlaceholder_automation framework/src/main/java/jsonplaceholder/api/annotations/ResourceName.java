package jsonplaceholder.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //adnotacja może być używana w klasach
@Retention(RetentionPolicy.RUNTIME) //adnotacja dostępna w czasie działania
public @interface ResourceName {
    String value();
}
