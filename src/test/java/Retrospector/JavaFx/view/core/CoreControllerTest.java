package retrospector.javafx.view.core;

import com.airhacks.afterburner.injection.Injector;
import com.airhacks.afterburner.views.FXMLView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.testfx.framework.junit.ApplicationTest;
import retrospector.javafx.view.ResourceBundleTestDouble;
import retrospector.javafx.view.media.MediaView;
import retrospector.javafx.view.search.SearchView;

public class CoreControllerTest extends ApplicationTest {

  private Map<Object, Object> context = new HashMap<>();
  private FXMLView search;
  private String searchId = "searchView";
  private FXMLView media;
  private String mediaId = "mediaView";

  @Test
  public void displaysMediaView() {
    verify(media, times(1)).getView();
  }

  @Test
  public void displaySearchView() {
    verify(search, times(1)).getView();
  }

  @Override
  public void start(Stage stage) throws Exception {
    Injector.setConfigurationSource(context::get);

    media = mock(MediaView.class);
    context.put(mediaId, media);

    search = mock(SearchView.class);
    context.put(searchId, search);

    stage.setScene(new Scene(new CoreView(new ResourceBundleTestDouble()).getView()));
    stage.show();
  }
}
