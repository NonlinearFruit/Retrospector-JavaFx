package retrospector.javafx.startup;

import javafx.stage.Stage;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class SplashScreenControllerTest extends ApplicationTest{

  private SplashScreenController controller;
  private Stage stage;

  @Test
  public void testInitialize() {
  }

  @Test
  public void testSetTheStageAndShow() {
    controller.fadeOut();
  }

  @Test
  public void testFadeOut() {
  }

  @Override
  public void start(Stage stage) throws Exception {
    System.out.println("H");
    SplashScreenView view = new SplashScreenView();
    controller = (SplashScreenController) view.getPresenter();
    this.stage = stage;
  }
}
