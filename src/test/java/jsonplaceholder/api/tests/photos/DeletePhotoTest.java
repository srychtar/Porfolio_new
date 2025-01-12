package jsonplaceholder.api.tests.photos;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.services.photos.DeletePhotoService;
import jsonplaceholder.api.services.photos.GetPhotoService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("DELETE")
@ResourceName("Photo")
public class DeletePhotoTest extends TestBase {

    photos receivedPhoto;
    photos photo = new photos();
    Integer photoId = 4;
    photos deletedPhoto = new photos();
    photos expectedPhoto = new photos();
    DeletePhotoService photoService = new DeletePhotoService();

    @Test
    @TestId("5.1")
    public void shouldFindAndDeleteUserTest() {

        photo.setId(photoId);
        receivedPhoto = new GetPhotoService().setPath(photo.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info("<pre>" + photoService.gson.toJson(receivedPhoto) + "</pre>");
        deletedPhoto = photoService.setPath(receivedPhoto.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(photoService.getDetails());
        Assertions.assertThat(deletedPhoto).describedAs("Photo was not deleted by API").usingRecursiveComparison().isEqualTo(expectedPhoto);
    }

}
