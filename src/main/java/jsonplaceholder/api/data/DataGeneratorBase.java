package jsonplaceholder.api.data;
import net.datafaker.Faker;
import java.util.Random;

public class DataGeneratorBase {

    public Faker faker(){

        return new Faker();
    }

    public Random random(){

        return new Random();
    }

    public String LongTextGenerator(int size){
        char character='w';
        StringBuilder textBuilder = new StringBuilder();
        while (textBuilder.length() < size) {
            textBuilder.append(character);
        }
        return textBuilder.toString();
    }
}

    