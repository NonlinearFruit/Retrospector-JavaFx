package retrospector.javafx.view.search;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SearchController implements Initializable {

  private String deleteKey = "delete";
  private String newKey = "new";
  private String editKey = "edit";
  private String resultsKey = "results";
  private String meanKey = "average";
  private String currentKey = "current";
  private String titleKey = "title";
  private String creatorKey = "creator";
  private String seasonKey = "season";
  private String episodeKey = "episode";
  private String categoryKey = "category";
  private String reviewsKey = "reviews";
  
  @FXML
  private VBox anchor;
  @FXML
  private TextField searchBox;
  @FXML
  private TableView<?> results;
  @FXML
  private TableColumn<?, ?> numberColumn;
  @FXML
  private TableColumn<?, ?> titleColumn;
  @FXML
  private TableColumn<?, ?> creatorColumn;
  @FXML
  private TableColumn<?, ?> seasonColumn;
  @FXML
  private TableColumn<?, ?> episodeColumn;
  @FXML
  private TableColumn<?, ?> categoryColumn;
  @FXML
  private TableColumn<?, ?> reviewsColumn;
  @FXML
  private TableColumn<?, ?> ratingColumn;
  @FXML
  private TableColumn<?, ?> meanRatingColumn;
  @FXML
  private TableColumn<?, ?> currentRatingColumn;
  @FXML
  private Button newButton;
  @FXML
  private Button editButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Text resultsDisplay;
  @FXML
  private Text resultsCount;
  @FXML
  private Text meanDisplay;
  @FXML
  private Text meanRating;
  @FXML
  private Text currentDisplay;
  @FXML
  private Text meanCurrentRating;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    deleteButton.setText(rb.getString(deleteKey));
    newButton.setText(rb.getString(newKey));
    editButton.setText(rb.getString(editKey));
    resultsDisplay.setText(rb.getString(resultsKey));
    meanDisplay.setText(rb.getString(meanKey));
    currentDisplay.setText(rb.getString(currentKey));
    titleColumn.setText(rb.getString(titleKey));
    creatorColumn.setText(rb.getString(creatorKey));
    seasonColumn.setText(rb.getString(seasonKey));
    episodeColumn.setText(rb.getString(episodeKey));
    categoryColumn.setText(rb.getString(categoryKey));
    reviewsColumn.setText(rb.getString(reviewsKey));
    meanRatingColumn.setText(rb.getString(meanKey));
    currentRatingColumn.setText(rb.getString(currentKey));
  }  
}
