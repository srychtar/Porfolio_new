package jsonplaceholder.api.data;
import jsonplaceholder.api.models.photos;

public class PhotosBaseData extends DataGeneratorBase {

    public photos createPhoto(){

        photos photo = new photos();
        photo.setAlbumId(faker().number().numberBetween(1,99));
        photo.setId(faker().number().numberBetween(1,99));
        photo.setTitle(faker().name().title());
        photo.setUrl(faker().internet().url());
        photo.setThumbnailUrl(faker().internet().url());
        return photo;
    }

}
