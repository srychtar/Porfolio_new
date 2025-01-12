package jsonplaceholder.api.tests.albums;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.data.AlbumsBaseData;
import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.albums.PostAlbumService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("POST")
@ResourceName("Album")
public class PostAlbumTest extends TestBase {


    PostAlbumService albumService = new PostAlbumService();
    albums createdAlbum;
    AlbumsBaseData albumData = new AlbumsBaseData();
    albums album = albumData.crateAlbum();


    @Test
    @TestId("2.1")
    public void shouldCreateAlbumTest() {

        test.info("<pre>" + albumService.gson.toJson(album) + "</pre>");
        createdAlbum = albumService.setBody(album).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(albumService.getDetails());
        Assertions.assertThat(createdAlbum).describedAs("Album uncorrected created")
                .usingRecursiveComparison().ignoringFields("id").isEqualTo(album);

    }

}
