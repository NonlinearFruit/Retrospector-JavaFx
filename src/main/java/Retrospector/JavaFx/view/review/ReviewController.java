package retrospector.javafx.view.review;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.inject.Inject;
import org.controlsfx.control.Rating;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.crud.CrudRequest.Crud;
import retrospector.core.interactor.crud.CrudReviewRequest;
import retrospector.core.request.model.RequestableReview;
import retrospector.javafx.presenter.CrudPublisher;

public class ReviewController implements Initializable {

  private String saveKey = "save";
  private String deleteKey = "delete";
  private String cancelKey = "cancel";
  private String userKey = "user";
  private String dateKey = "date";
  private String reviewKey = "review";

  private Integer reviewId;

  @Inject
  private RequestRouter router;
  @Inject
  private CrudPublisher<RequestableReview> reviewPublisher;
  
  @FXML
  private Text maxRating;
  @FXML
  private TextField userBox;
  @FXML
  private DatePicker dateBox;
  @FXML
  private TextArea descriptionBox;
  @FXML
  private Button saveButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Button cancelButton;
  @FXML
  private Rating stars;
  @FXML
  private Text rating;
  @FXML
  private Slider ratingBox;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    saveButton.setText(rb.getString(saveKey));
    deleteButton.setText(rb.getString(deleteKey));
    cancelButton.setText(rb.getString(cancelKey));

    userBox.setPromptText(rb.getString(userKey));
    dateBox.setPromptText(rb.getString(dateKey));
    descriptionBox.setPromptText(rb.getString(reviewKey));

    saveButton.setOnAction(this::handleSave);
    deleteButton.setOnAction(this::handleDelete);
    cancelButton.setOnAction(this::handleCancel);

    rating.textProperty().bind(ratingBox.valueProperty().asString());
    stars.ratingProperty().bind(ratingBox.valueProperty());

    reviewPublisher.addAddedListener(this::setReview);

    clearBoxes();
  }  

  private void handleSave(ActionEvent event) {
    RequestableReview review = getReview();
    Crud crud = Crud.Update;
    if (reviewId == null)
      crud = Crud.Create;
    router.disseminate(new CrudReviewRequest(crud, review));
  }

  private void handleDelete(ActionEvent event) {
    clearBoxes();
  }

  private void handleCancel(ActionEvent event) {
    clearBoxes();
  }

  private void setReview(RequestableReview review) {
    reviewId = review.getId();
  }

  private RequestableReview getReview() {
    Integer rating = (int) ratingBox.getValue();
    String user = userBox.getText();
    LocalDate date = dateBox.getValue();
    String description = descriptionBox.getText();
    return new RequestableReview(rating, date, user, description);
  }

  private void clearBoxes() {
    ratingBox.setValue(ratingBox.getMin());
    userBox.setText("");
    dateBox.setValue(LocalDate.now());
    descriptionBox.setText("");
  }
}