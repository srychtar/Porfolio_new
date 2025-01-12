package jsonplaceholder.api.data;

import jsonplaceholder.api.models.albums;

public class AlbumsBaseData extends DataGeneratorBase {

    public albums crateAlbum(){

        albums album = new albums();
        album.setUserId(faker().number().numberBetween(1,99));
        album.setId(faker().number().numberBetween(1,99));
        album.setTitle(faker().job().position());
        return album;
    }
}
