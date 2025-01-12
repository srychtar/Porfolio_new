package jsonplaceholder.api.tests.albums;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.albums.DeleteAlbumService;
import jsonplaceholder.api.services.albums.GetAlbumService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestBase.class)
@RequestType("DELETE")
@ResourceName("Album")
public class DeleteAlbumTest extends TestBase {

    albums receivedAlbum;
    albums album = new albums();
    albums deletedAlbum = new albums();
    Integer albumId = 3;
    albums expectedAlbum = new albums();
    DeleteAlbumService albumService = new DeleteAlbumService();


    @Test
    @TestId("5.1")
    public void shouldFindAndDeleteAlbumTest() {

        album.setId(albumId);
        receivedAlbum = new GetAlbumService().setPath(album.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info("<pre>" + albumService.gson.toJson(receivedAlbum) + "</pre>");
        deletedAlbum = albumService.setPath(receivedAlbum.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(albumService.getDetails());
        Assertions.assertThat(deletedAlbum).describedAs("User was not deleted by API").usingRecursiveComparison().isEqualTo(expectedAlbum);
    }

}
