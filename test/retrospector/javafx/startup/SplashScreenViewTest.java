package retrospector.javafx.startup;

import retrospector.javafx.view.splash.SplashScreenView;
import retrospector.javafx.view.splash.SplashScreenController;
import javafx.scene.Parent;
import javafx.stage.Stage;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class SplashScreenViewTest extends ApplicationTest {
  
  @Test
  public void GetView_Works() {
    SplashScreenView loader = new SplashScreenView();

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void GetPresenter_Works() {
    SplashScreenView loader = new SplashScreenView();

    SplashScreenController controller = (SplashScreenController) loader.getPresenter();

    assertNotNull(controller);
  }

  @Override
  public void start(Stage stage) throws Exception {
  }
}
