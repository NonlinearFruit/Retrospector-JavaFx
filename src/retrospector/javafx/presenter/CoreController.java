package retrospector.javafx.presenter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javax.inject.Inject;

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
