package retrospector.javafx.startup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashScreenController implements Initializable {
  @FXML
  private VBox root;
  @FXML
  private ImageView splash;
  @FXML
  private ProgressBar progress;
  @FXML
  private Label label;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    progress.prefWidthProperty().bind(splash.fitWidthProperty());
    label.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,new CornerRadii(5),new Insets(0))));
    root.setBackground(Background.EMPTY);
  }  

  public void setTheStageAndShow(Stage stage) {
    Scene scene = new Scene(root, Color.TRANSPARENT);
    stage.setScene(scene);
    stage.setAlwaysOnTop(true);
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.show();
  }
  
  public void fadeOut() {
    progress.progressProperty().unbind();
    label.textProperty().unbind();
    FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), root);
    fadeSplash.setFromValue(1.0);
    fadeSplash.setToValue(0.0);
    fadeSplash.setOnFinished(e -> exit());
    fadeSplash.play();
  }

  private void exit() {
    Stage stage = (Stage) splash.getScene().getWindow();
    stage.close();
  }
}
