package jsonplaceholder.api.tests.photos;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.data.PhotosBaseData;
import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.services.photos.PostPhotoService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("POST")
@ResourceName("Photo")
public class PostPhotoTest extends TestBase {

    PostPhotoService photoService = new PostPhotoService();
    photos createdPhoto;

    PhotosBaseData photoData = new PhotosBaseData();
    photos photo = photoData.createPhoto();

    @Test
    @TestId("2.1")
    public void shouldCreatePhotoTest() {

        test.info("<pre>" + photoService.gson.toJson(photo) + "</pre>");
        createdPhoto = photoService.setBody(photo).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(photoService.getDetails());
        Assertions.assertThat(createdPhoto).describedAs("photo uncorrected created").usingRecursiveComparison().isNotEqualTo(photo);

    }
}
