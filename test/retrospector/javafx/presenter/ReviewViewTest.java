package retrospector.javafx.presenter;

import com.airhacks.afterburner.injection.Injector;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import retrospector.javafx.bundles.BundleType;
import retrospector.javafx.bundles.BundleUtils;

public class ReviewViewTest extends ApplicationTest {

  private Stage stage;
  private String saveText = "save";
  
  @Test
  public void getView_Works() {
    ReviewView loader = new ReviewView(null);

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void getPresenter_Works() {
    ReviewView loader = new ReviewView(null);

    ReviewController controller = (ReviewController) loader.getPresenter();

    assertNotNull(controller);
  }

  @Test
  public void setBundle_Works() {
    for (ResourceBundle bundle : BundleUtils.getResourceBundles(BundleType.Core)) {
      ReviewView loader = new ReviewView(bundle);

      Parent parent = loader.getView();
      interact(() -> {
        stage.setScene(new Scene(parent));
        stage.show();
              });

      String saveButtonText = bundle.getString(saveText);
      clickOn(saveButtonText);
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);
    this.stage = stage;
  }
}