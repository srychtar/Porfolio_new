package jsonplaceholder.api.tests.photos;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.services.photos.GetPhotoService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;


@Listeners(TestBase.class)
@RequestType("GET")
@ResourceName("Photo")
public class GetPhotoTest extends TestBase {

    photos photo = new photos();
    photos receivedPhoto;
    List<photos> receivedPhotos;
    Integer photoId = 4;
    GetPhotoService photoService = new GetPhotoService();

    @Test
    @TestId("1.1")
    public void shouldFetchPhotoTest() {

        photo.setId(photoId);
        receivedPhoto = photoService.setPath(photo.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(photoService.getDetails());
        Assertions.assertThat(receivedPhoto.getId()).describedAs("Photo ID is different").isEqualTo(photo.getId());
        test.info("Expected photo ID: " + photo.getId());
        test.info("Actual photo ID: " + receivedPhoto.getId());
    }

    @Test
    @TestId("1.2")
    public void shouldFetchAllUsersTest() {

        receivedPhotos = photoService.executeRequestForAll().validateSuccessRequest().getResponseUserModelList();
        test.info(photoService.getDetails());
        Assertions.assertThat(receivedPhotos).describedAs("Photos list should no be empty").isNotEmpty();
        test.info("Fetched " + receivedPhotos.size() + " photos.");
    }

    @Test
    @TestId("1.4")
    public void shouldFetchKeyValueUsersTest() {

        receivedPhotos = photoService.executeRequestForKeyValue("id", photoId ).validateSuccessRequest().getResponseModelList(photos.class);
        test.info(photoService.getDetails());
        Assertions.assertThat(receivedPhotos).describedAs("photos list should not be empty").isNotEmpty();
        test.info("Fetched " + receivedPhotos.size() + " user with ID: " + photoId);
        Assertions.assertThat(receivedPhotos).allMatch(photos->photos.getId() == 4);
    }
}
