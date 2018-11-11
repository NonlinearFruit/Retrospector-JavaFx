package retrospector.javafx.presenter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;

public class ReviewController implements Initializable {

  @FXML
  private Rating reviewStars;
  @FXML
  private Text reviewRating;
  @FXML
  private Text maxRating;
  @FXML
  private Slider slider;
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

  @Override
  public void initialize(URL url, ResourceBundle rb) {
  }  
}
