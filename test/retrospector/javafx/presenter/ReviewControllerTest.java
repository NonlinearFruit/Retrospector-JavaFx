package retrospector.javafx.presenter;

import com.airhacks.afterburner.injection.Injector;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class ReviewControllerTest extends ApplicationTest {

  private RequestRouterTestDouble router;
  private CrudMediaPresenter presenter;
  private String saveButton = "#saveButton";
  private String deleteButton = "#deleteButton";
  private String cancelButton = "#cancelButton";
  private String[] boxes = new String[] {
      "#userBox",
      "#descriptionBox"
    };

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);
    
    presenter = new CrudMediaPresenter();
    context.put("publisher", presenter);
    
    router = new RequestRouterTestDouble(presenter);
    context.put("router", router);

    ReviewView loader = new ReviewView(null);

    stage.setScene(new Scene(loader.getView()));
    stage.show();
  }

  @Test
  public void f() {
    
  }
}