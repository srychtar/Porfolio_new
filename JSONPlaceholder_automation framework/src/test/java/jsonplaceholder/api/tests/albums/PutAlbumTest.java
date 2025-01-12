package jsonplaceholder.api.tests.albums;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.albums.GetAlbumService;
import jsonplaceholder.api.services.albums.PutAlbumService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("PUT")
@ResourceName("Album")
public class PutAlbumTest extends TestBase{

    albums receivedAlbum;
    albums album = new albums();
    albums updatedAlbum;
    PutAlbumService albumService = new PutAlbumService();

    @Test
    @TestId("4.2")
    public void shouldChangeExistingAlbumTest() {
        album.setId(3);
        receivedAlbum = new GetAlbumService().setPath(album.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();

        receivedAlbum.setTitle("New Title");
        test.info("<pre>" + albumService.gson.toJson(receivedAlbum) + "</pre>");
        updatedAlbum = albumService.setBody(receivedAlbum).setPath(receivedAlbum.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(albumService.getDetails());
        Assertions.assertThat(receivedAlbum).describedAs("album correct updated").usingRecursiveComparison().isEqualTo(updatedAlbum);
        test.info("Expected album title: " + receivedAlbum.getTitle());
        test.info("Actual album title: " + updatedAlbum.getTitle());

    }

}
