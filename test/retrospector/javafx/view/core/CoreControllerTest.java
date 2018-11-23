package retrospector.javafx.view.core;

import retrospector.javafx.view.review.ReviewView;
import retrospector.javafx.view.media.MediaView;
import retrospector.javafx.view.core.CoreView;
import com.airhacks.afterburner.injection.Injector;
import com.airhacks.afterburner.views.FXMLView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasChild;

public class CoreControllerTest extends ApplicationTest {

  private Map<Object, Object> context = new HashMap<>(); 
  private String anchor = "#anchor";
  private String mediaViewVariable = "mediaView";
  private String mediaViewId = "#"+mediaViewVariable;
  private String reviewViewVariable = "reviewView";
  private String reviewViewId = "#"+reviewViewVariable;
  
  @Test
  public void displaysMediaView() {
    verifyThat(anchor, hasChild(mediaViewId));
  }

  @Test
  public void displayReviewView() {
    verifyThat(anchor, hasChild(reviewViewId));
  }
  
  @Override
  public void start(Stage stage) throws Exception {
    Injector.setConfigurationSource(context::get);
    
    addToContext(mediaViewVariable, MediaView.class);
    addToContext(reviewViewVariable, ReviewView.class);

    stage.setScene(new Scene(new CoreView().getView()));
    stage.show();
  }

  private void addToContext(String key, Class viewClass) {
    FXMLView loader = (FXMLView) mock(viewClass);
    HBox box = new HBox();
    box.setId(key);
    when(loader.getView()).thenReturn(box);
    context.put(key, loader);
  }
}
