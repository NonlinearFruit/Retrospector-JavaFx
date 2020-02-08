package retrospector.javafx.view.core;

import com.airhacks.afterburner.injection.Injector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.testfx.framework.junit.ApplicationTest;
import retrospector.javafx.view.ResourceBundleTestDouble;
import retrospector.javafx.view.media.MediaView;
import retrospector.javafx.view.search.SearchView;

public class CoreViewTest extends ApplicationTest {
  private Stage stage;
  private ResourceBundle bundle = new ResourceBundleTestDouble();
  private String tabsId = "#tabs";
  private String[] keys = {
    "search", "media", "chart", "wishlist", "list", "achievement"
  };

  @Test
  public void getView_Works() {
    CoreView loader = new CoreView(bundle);

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void setBundle_Works() {
    CoreView loader = new CoreView(bundle);

    Parent parent = loader.getView();
    interact(showStage(parent));

    verifyTranslationIsDisplayed(bundle);
  }

  @Test
  public void getPresenter_Works() {
    CoreView loader = new CoreView(bundle);

    CoreController controller = (CoreController) loader.getPresenter();

    assertNotNull(controller);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    Map<Object, Object> context = new HashMap<>();
    Injector.setConfigurationSource(context::get);

    MediaView view = mock(MediaView.class);
    when(view.getView()).thenReturn(new HBox());
    context.put("mediaView", view);

    SearchView view1 = mock(SearchView.class);
    when(view1.getView()).thenReturn(new HBox());
    context.put("searchView", view1);
  }

  private void verifyTranslationIsDisplayed(ResourceBundle bundle) {
    List<String> translations = getBundleStrings(bundle);
    List<String> displays = getDisplayedStrings();
    for (String translation : translations)
      assertTrue("Verifying: " + translation, displays.contains(translation));
  }

  private Runnable showStage(Parent parent) {
    return () -> {
      stage.setScene(new Scene(parent));
      stage.show();
    };
  }

  private List<String> getDisplayedStrings() {
    List<String> strings = new ArrayList<>();
    TabPane tabs = lookup(tabsId).<TabPane>queryFirst();
    tabs.getTabs().forEach(tab -> strings.add(tab.getText()));
    return strings;
  }

  private List<String> getBundleStrings(ResourceBundle bundle) {
    List<String> strings = new ArrayList<>();
    for (String key : keys)
      strings.add(bundle.getString(key));
    return strings;
  }
}
