package jsonplaceholder.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  // Adnotacja będzie dostępna w czasie wykonywania programu
@Target(ElementType.TYPE)  // Adnotacja może być używana tylko na klasach
public @interface RequestType {
    String value();  // Wartość przechowująca typ żądania HTTP
}
