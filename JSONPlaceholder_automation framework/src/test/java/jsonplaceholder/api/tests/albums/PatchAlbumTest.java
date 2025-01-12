package jsonplaceholder.api.tests.albums;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.albums.PatchAlbumService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("PATCH")
@ResourceName("Album")
public class PatchAlbumTest extends TestBase{

    albums updatedAlbum = new albums();
    albums album = new albums();
    PatchAlbumService albumService = new PatchAlbumService();
    int userId = 3;
    @Test
    @TestId("4.1")
    public void shouldUpdatedAlbumTest() {
        album.setId(userId);
        album.setTitle("New Title");
        test.info("<pre>" + albumService.gson.toJson(album) + "</pre>");
        updatedAlbum = albumService.setBody(album).setPath(album.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(albumService.getDetails());
        Assertions.assertThat(album).describedAs("user as modify as expected").usingRecursiveComparison().isNotEqualTo(updatedAlbum);
        test.info("Expected album Title: " + album.getTitle());
        test.info("Actual album Title: " + updatedAlbum.getTitle());

    }

}
