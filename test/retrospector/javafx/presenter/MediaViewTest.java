package retrospector.javafx.presenter;

import com.airhacks.afterburner.injection.Injector;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import retrospector.core.request.model.RequestableMedia;

public class MediaViewTest extends ApplicationTest {
  
  @Test
  public void GetView_Works() {
    MediaView loader = new MediaView();

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void GetPresenter_Works() {
    MediaView loader = new MediaView();

    MediaController controller = (MediaController) loader.getPresenter();

    assertNotNull(controller);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);

    CrudRetaliator<RequestableMedia> presenter = new CrudRetaliator<>();
    context.put("publisher", presenter);

    MediaRequestRouterTestDouble router = new MediaRequestRouterTestDouble(presenter);
    context.put("router", router);
  }
}
