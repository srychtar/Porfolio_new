package jsonplaceholder.api.tests.photos;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.services.photos.GetPhotoService;
import jsonplaceholder.api.services.photos.PutPhotoService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("PUT")
@ResourceName("Photo")
public class PutPhotoTest extends TestBase {

    photos receivedPhoto;
    photos photo = new photos();
    photos updatedPhotos;
    PutPhotoService photoService = new PutPhotoService();


    @Test
    @TestId("4.2")
    public void shouldChangeExistingUserTest() {
        photo.setId(1);
        receivedPhoto = new GetPhotoService().setPath(photo.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();

        receivedPhoto.setTitle("New Title");
        test.info("<pre>" + photoService.gson.toJson(receivedPhoto) + "</pre>");
        updatedPhotos = photoService.setBody(receivedPhoto).setPath(receivedPhoto.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(photoService.getDetails());
        Assertions.assertThat(receivedPhoto).describedAs("photo correct updated").usingRecursiveComparison().isEqualTo(updatedPhotos);
        test.info("Expected photo title: " + receivedPhoto.getTitle());
        test.info("Actual photo title: " + updatedPhotos.getTitle());

    }
}

