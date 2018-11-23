package retrospector.javafx.presenter;

import com.airhacks.afterburner.injection.Injector;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import retrospector.core.interactor.CrudRequest.Crud;
import retrospector.core.interactor.CrudReviewRequest;
import retrospector.core.request.model.RequestableReview;

public class ReviewControllerTest extends ApplicationTest {

  private ReviewRequestRouterTestDouble router;
  private CrudRetaliator<RequestableReview> presenter;
  private String saveButtonId = "#saveButton";
  private String deleteButtonId = "#deleteButton";
  private String cancelButtonId = "#cancelButton";
  private String ratingId = "#rating";
  private String starsId = "#stars";
  private String ratingBoxId = "#ratingBox";
  private String userBoxId = "#userBox";
  private String descriptionBoxId = "#descriptionBox";
  private String dateBoxId = "#dateBox";
  private RequestableReview review = new RequestableReview(
          5, 
          LocalDate.now().minusDays(3), 
          "Steve Rogers", 
          "..."
  );

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);
    
    presenter = new CrudRetaliator<>();
    context.put("reviewPublisher", presenter);
    
    router = new ReviewRequestRouterTestDouble(presenter);
    context.put("router", router);

    ReviewView loader = new ReviewView(new ResourceBundleTestDouble());

    stage.setScene(new Scene(loader.getView()));
    stage.show();
  }

  @Test
  public void initialize_ClearsValues() {
    verifyBoxesAreCleared();
  }

  @Test
  public void saveButton_SendsCreate_WhenNewReview() {
    enterReview(review);

    clickOn(saveButtonId);

    assertEquals(1, router.getSentRequests().size());
    CrudReviewRequest request = (CrudReviewRequest) router.getSentRequests().get(0);
    assertEquals(Crud.Create, request.getCrud());
  }

  @Test
  public void saveButton_SendsUpdate_WhenExistingReview() {
    enterReview(review);
    clickOn(saveButtonId);

    clickOn(saveButtonId);

    assertEquals(2, router.getSentRequests().size());
    CrudReviewRequest request = (CrudReviewRequest) router.getSentRequests().get(1);
    assertEquals(Crud.Update, request.getCrud());
  }

  @Test
  public void saveButton_SendsReview() {
    enterReview(review);

    clickOn(saveButtonId);

    assertEquals(1, router.getSentRequests().size());
    CrudReviewRequest request = (CrudReviewRequest) router.getSentRequests().get(0);
    verifyAreEqual(review, request.getRequestable());
  }

  @Test
  public void deleteButton_ClearsValues() {
    enterReview(review);

    clickOn(deleteButtonId);

    verifyBoxesAreCleared();
  }

  @Test
  public void cancelButton_ClearsValues() {
    enterReview(review);

    clickOn(cancelButtonId);

    verifyBoxesAreCleared();
  }

  @Test
  public void rating_AdjustsWithRatingBox() {
    Text rating = lookup(ratingId).<Text>queryFirst();
    Slider ratingBox = lookup(ratingBoxId).<Slider>queryFirst();
    
    verifyHaveEqualValues(ratingBox, rating);
    
    ratingBox.setValue(ratingBox.getMax());
    verifyHaveEqualValues(ratingBox, rating);
    
    ratingBox.setValue(ratingBox.getMin());
    verifyHaveEqualValues(ratingBox, rating);
  }

  @Test
  public void stars_AdjustsWithRatingBox() {
    Rating stars = lookup(starsId).<Rating>queryFirst();
    Slider ratingBox = lookup(ratingBoxId).<Slider>queryFirst();
    
    verifyHaveEqualValues(ratingBox, stars);
    
    ratingBox.setValue(ratingBox.getMax());
    verifyHaveEqualValues(ratingBox, stars);
    
    ratingBox.setValue(ratingBox.getMin());
    verifyHaveEqualValues(ratingBox, stars);
  }

  private void verifyHaveEqualValues(Slider slider, Text text) {
    double textValue = Double.parseDouble(text.getText());
    double sliderValue = slider.getValue();
    verifyHaveEqualValues(sliderValue, textValue);
  }

  private void verifyHaveEqualValues(Slider slider, Rating rating) {
    double ratingValue = rating.getRating();
    double sliderValue = slider.getValue();
    verifyHaveEqualValues(sliderValue, ratingValue);
  }

  private void verifyHaveEqualValues(double actual, double expected) {
    double delta = 0.001d;
    assertEquals(actual, expected, delta);
  }

  private void enterReview(RequestableReview review) {
    Slider ratingBox = lookup(ratingBoxId).<Slider>queryFirst();
    ratingBox.setValue(review.getRating());

    TextField userBox = lookup(userBoxId).<TextField>queryFirst(); 
    userBox.setText(review.getUser());

    DatePicker dateBox = lookup(dateBoxId).<DatePicker>queryFirst();
    dateBox.setValue(review.getDate());

    TextArea descriptionBox = lookup(descriptionBoxId).<TextArea>queryFirst();
    descriptionBox.setText(review.getReview());
  }

  private void verifyAreEqual(RequestableReview expected, RequestableReview actual) {
    assertEquals(expected.getMediaId(), actual.getMediaId());
//    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getRating(), actual.getRating());
    assertEquals(expected.getUser(), actual.getUser());
    assertEquals(expected.getDate(), actual.getDate());
    assertEquals(expected.getReview(), actual.getReview());
  }

  private void verifyBoxesAreCleared() {
    Slider ratingBox = lookup(ratingBoxId).<Slider>queryFirst();
    verifyHaveEqualValues(ratingBox.getMin(), ratingBox.getValue());

    TextField userBox = lookup(userBoxId).<TextField>queryFirst(); 
    assertEquals("", userBox.getText());
    
    DatePicker dateBox = lookup(dateBoxId).<DatePicker>queryFirst();
    assertEquals(LocalDate.now(), dateBox.getValue());
    
    TextArea descriptionBox = lookup(descriptionBoxId).<TextArea>queryFirst();
    assertEquals("", descriptionBox.getText());
  }
}