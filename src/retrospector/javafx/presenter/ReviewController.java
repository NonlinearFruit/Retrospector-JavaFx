package retrospector.javafx.presenter;

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
import retrospector.core.interactor.CrudRequest.Crud;
import retrospector.core.interactor.CrudReviewRequest;

public class ReviewController implements Initializable {

  private String saveKey = "save";
  private String deleteKey = "delete";
  private String cancelKey = "cancel";

  @Inject
  private RequestRouter router;
  
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

    rating.textProperty().bind(ratingBox.valueProperty().asString());
    stars.ratingProperty().bind(ratingBox.valueProperty());

    clearBoxes();
  }  

  @FXML
  private void handleSave(ActionEvent event) {
    router.disseminate(new CrudReviewRequest(Crud.Create));
  }

  @FXML
  private void handleDelete(ActionEvent event) {
    clearBoxes();
  }

  @FXML
  private void handleCancel(ActionEvent event) {
    clearBoxes();
  }

  private void clearBoxes() {
    ratingBox.setValue(ratingBox.getMin());
    userBox.setText("");
    dateBox.setValue(LocalDate.now());
    descriptionBox.setText("");
  }
}