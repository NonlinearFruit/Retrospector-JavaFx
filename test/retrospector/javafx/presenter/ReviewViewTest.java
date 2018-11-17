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
import retrospector.core.boundry.RequestRouter;
import retrospector.javafx.bundles.BundleType;
import retrospector.javafx.bundles.BundleUtils;

public class ReviewViewTest extends ApplicationTest {

  private Stage stage;
  private String[] keys = {
    "save",
    "delete",
    "cancel"
  };
  private ResourceBundle bundle = new ResourceBundleTestDouble();
  
  @Test
  public void getView_Works() {
    ReviewView loader = new ReviewView(bundle);

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void getPresenter_Works() {
    ReviewView loader = new ReviewView(bundle);

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

      for (String key : keys) {
        String buttonText = bundle.getString(key);
        clickOn(buttonText);
      }
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);

    MediaPresenter presenter = new MediaPresenter();
    context.put("publisher", presenter);
    
    RequestRouter router = new MediaRequestRouterTestDouble(presenter);
    context.put("router", router);
    
    this.stage = stage;
  }
}