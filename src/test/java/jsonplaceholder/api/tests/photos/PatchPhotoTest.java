package jsonplaceholder.api.tests.photos;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.services.photos.PatchPhotoService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("PATCH")
@ResourceName("Photo")
public class PatchPhotoTest extends TestBase {

    photos updatedPhoto = new photos();
    photos photo = new photos();
    PatchPhotoService photoService = new PatchPhotoService();
    int photoId = 1;


    @Test
    @TestId("4.1")
    public void shouldUpdatePhotoTest() {
        photo.setId(photoId);
        photo.setTitle("New Title");
        test.info("<pre>" + photoService.gson.toJson(photo) + "</pre>");
        updatedPhoto = photoService.setBody(photo).setPath(photo.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(photoService.getDetails());
        Assertions.assertThat(photo).describedAs("photo as modify as expected").usingRecursiveComparison().isNotEqualTo(updatedPhoto);
        test.info("Expected photo title: " + photo.getTitle());
        test.info("Actual photo title: " + updatedPhoto.getTitle());

    }


}
