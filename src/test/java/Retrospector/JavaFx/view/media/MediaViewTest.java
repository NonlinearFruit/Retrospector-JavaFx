package retrospector.javafx.view.media;

import com.airhacks.afterburner.injection.Injector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import retrospector.core.request.model.RequestableMedia;
import retrospector.javafx.presenter.CrudRetaliator;
import retrospector.javafx.view.ResourceBundleTestDouble;

public class MediaViewTest extends ApplicationTest {
  
  private Stage stage;
  private String titleBoxId = "#titleBox";
  private String creatorBoxId = "#creatorBox";
  private String seasonBoxId = "#seasonBox";
  private String episodeBoxId = "#episodeBox";
  private String descriptionBoxId = "#descriptionBox";
  private String saveButtonId = "#saveButton";
  private String deleteButtonId = "#deleteButton";
  private String newButtonId = "#newButton";
  private String newSeasonButtonId = "#newSeasonButton";
  private String newEpisodeButtonId = "#newEpisodeButton";
  private String cancelButtonId = "#cancelButton";
  private String categoryDisplayId = "#categoryDisplay";
  private String typeDisplayId = "#typeDisplay";
  private String descriptionDisplayId = "#descriptionDisplay";
  private ResourceBundle bundle = new ResourceBundleTestDouble();
  private String[] keys = {
    "title", "creator", "season", "episode", "description", "save", "delete", "new", "add", "cancel", "category", "type"
  };
  
  @Test
  public void GetView_Works() {
    MediaView loader = new MediaView(bundle);

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void GetPresenter_Works() {
    MediaView loader = new MediaView(bundle);

    MediaController controller = (MediaController) loader.getPresenter();

    assertNotNull(controller);
  }

  @Test
  public void setBundle_Works() {
    ResourceBundle bundle = new ResourceBundleTestDouble();
    MediaView loader = new MediaView(bundle);

    Parent parent = loader.getView();
    interact(showStage(parent));

    verifyTranslationIsDisplayed(bundle);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);

    CrudRetaliator<RequestableMedia> presenter = new CrudRetaliator<>();
    context.put("publisher", presenter);

    MediaRequestRouterTestDouble router = new MediaRequestRouterTestDouble(presenter);
    context.put("router", router);
  }

  private Runnable showStage(Parent parent) {
    return () -> {
      stage.setScene(new Scene(parent));
      stage.show();
    };
  }

  private void verifyTranslationIsDisplayed(ResourceBundle bundle) {
    List<String> translations = getBundleStrings(bundle);
    List<String> displays = getDisplayedStrings();
    for (String translation : translations)
      assertTrue("Verifying: " + translation, displays.contains(translation));
  }
  
  private List<String> getDisplayedStrings() {
    List<String> strings = new ArrayList<>();
    strings.add(lookup(titleBoxId).<TextField>queryFirst().getPromptText());
    strings.add(lookup(creatorBoxId).<TextField>queryFirst().getPromptText());
    strings.add(lookup(seasonBoxId).<TextField>queryFirst().getPromptText());
    strings.add(lookup(episodeBoxId).<TextField>queryFirst().getPromptText());
    strings.add(lookup(descriptionBoxId).<TextArea>queryFirst().getPromptText());
    strings.add(lookup(saveButtonId).<Button>queryFirst().getText());
    strings.add(lookup(deleteButtonId).<Button>queryFirst().getText());
    strings.add(lookup(newButtonId).<Button>queryFirst().getText());
    strings.add(lookup(newSeasonButtonId).<Button>queryFirst().getText());
    strings.add(lookup(newEpisodeButtonId).<Button>queryFirst().getText());
    strings.add(lookup(cancelButtonId).<Button>queryFirst().getText());
    strings.add(lookup(categoryDisplayId).<Text>queryFirst().getText());
    strings.add(lookup(typeDisplayId).<Text>queryFirst().getText());
    strings.add(lookup(descriptionDisplayId).<Text>queryFirst().getText());
    return strings;
  }

  private List<String> getBundleStrings(ResourceBundle bundle) {
    List<String> strings = new ArrayList<>();
    for (String key : keys)
      strings.add(bundle.getString(key));
    return strings;
  }
}
