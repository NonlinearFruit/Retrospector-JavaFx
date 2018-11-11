package retrospector.javafx.presenter;

import com.airhacks.afterburner.injection.Injector;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;

public class CrudMediaViewTest extends ApplicationTest {
  
  @Test
  public void GetView_Works() {
    CrudMediaView loader = new CrudMediaView();

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void GetPresenter_Works() {
    CrudMediaView loader = new CrudMediaView();

    CrudMediaController controller = (CrudMediaController) loader.getPresenter();

    assertNotNull(controller);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);

    CrudMediaPresenter presenter = new CrudMediaPresenter();
    context.put("publisher", presenter);

    RequestRouterTestDouble router = new RequestRouterTestDouble(presenter);
    context.put("router", router);
  }
}
