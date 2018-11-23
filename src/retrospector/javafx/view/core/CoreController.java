package retrospector.javafx.view.core;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javax.inject.Inject;
import retrospector.javafx.view.media.MediaView;
import retrospector.javafx.view.review.ReviewView;

public class CoreController implements Initializable {

  @Inject
  MediaView mediaView;
  @Inject
  ReviewView reviewView;
  @FXML
  private HBox anchor;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    anchor.getChildren().add(mediaView.getView());
    anchor.getChildren().add(reviewView.getView());
  }  
}
