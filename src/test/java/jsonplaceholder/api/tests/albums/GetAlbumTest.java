package jsonplaceholder.api.tests.albums;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.ResourceType;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.services.albums.GetAlbumService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;


@Listeners(TestBase.class)
@RequestType("GET")
@ResourceName("Album")
public class GetAlbumTest extends TestBase {

    albums album = new albums();
    albums receivedAlbum;
    List<albums> receivedAlbums;
    List<photos> receivedPhotos;
    GetAlbumService albumService = new GetAlbumService();
    Integer albumId = 1;
    Integer userId = 1;

    @Test
    @TestId("1.1")
    public void shouldFetchAlbumTest() {

        album.setId(albumId);
        receivedAlbum = albumService.setPath(album.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(albumService.getDetails());
        Assertions.assertThat(receivedAlbum.getId()).describedAs("Album ID is different").isEqualTo(album.getId());
        test.info("Expected Album ID: " + album.getId());
        test.info("Actual Album ID: " + receivedAlbum.getId());
    }


    @Test
    @TestId("1.2")
    public void shouldFetchAllAlbumsTest() {

        receivedAlbums = albumService.executeRequestForAll().validateSuccessRequest().getResponseAlbumModelList();
        test.info(albumService.getDetails());
        Assertions.assertThat(receivedAlbums).describedAs("Albums list should no be empty").isNotEmpty();
        test.info("Fetched " + receivedAlbums.size() + " users.");
    }

    @Test
    @TestId("1.3")
    public void shouldFetchNestedPhotosForAlbumsTest() {

        receivedPhotos = albumService.executeRequestForNested(ResourceType.PHOTOS,albumId).validateSuccessRequest().getResponseModelList(photos.class);
        test.info(albumService.getDetails());
        Assertions.assertThat(receivedPhotos).describedAs("Photos list should not be empty").isNotEmpty();
        Assertions.assertThat(receivedPhotos).allMatch(photos-> Objects.equals(photos.getAlbumId(), albumId), "All posts should have the userId equal to 1");
        test.info("Fetched " + receivedPhotos.size() + " photos for album with ID: " + albumId);
    }

    @Test
    @TestId("1.4")
    public void shouldFetchKeyValueAlbumsTest() {

        receivedAlbums = albumService.executeRequestForKeyValue("userId", userId ).validateSuccessRequest().getResponseModelList(albums.class);
        test.info(albumService.getDetails());
        Assertions.assertThat(receivedAlbums).describedAs("posts list should not be empty").isNotEmpty();
        test.info("Fetched " + receivedAlbums.size() + " albums with userId: " + userId);
        Assertions.assertThat(receivedAlbums).allMatch(albums->albums.getUserId() == 1);
    }

    @Test
    @TestId("1.1")
    public void shouldNoFetchAlbumTest() {

        album.setId(101);
        receivedAlbum = albumService.setPath(album.getId()).executeRequestForSingle().validateFailureRequest().getResponseModel();
        test.info(albumService.getDetails());
        Assertions.assertThat(receivedAlbum.getId()).describedAs("Founded not exist album").isNotEqualTo(album.getId());
        test.info("Actual album ID: " + receivedAlbum.getId());
    }

}
