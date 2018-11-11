package retrospector.javafx.presenter;

import com.airhacks.afterburner.injection.Injector;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.testfx.framework.junit.ApplicationTest;

public class CoreViewTest extends ApplicationTest {

  @Test
  public void getView_Works() {
    CoreView loader = new CoreView();

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void getPresenter_Works() {
    CoreView loader = new CoreView();

    CoreController controller = (CoreController) loader.getPresenter();

    assertNotNull(controller);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);

    CrudMediaView view = mock(CrudMediaView.class);
    when(view.getView()).thenReturn(new HBox());
    context.put("mediaView", view);

    ReviewView view1 = mock(ReviewView.class);
    when(view1.getView()).thenReturn(new HBox());
    context.put("reviewView", view1);
  }
}
