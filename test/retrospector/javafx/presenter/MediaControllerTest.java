package retrospector.javafx.presenter;

import com.airhacks.afterburner.injection.Injector;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import retrospector.core.interactor.CrudMediaRequest;
import retrospector.core.interactor.CrudRequest.Crud;
import retrospector.core.request.model.RequestableMedia;

public class MediaControllerTest extends ApplicationTest {

  private MediaRequestRouterTestDouble router;
  private MediaPresenter presenter;
  private String filler = "x";
  private String saveButton = "#saveButton";
  private String newButton = "#newButton";
  private String[] boxes = new String[] {
      "#titleBox",
      "#creatorBox",
      "#seasonBox",
      "#episodeBox",
      "#categoryBox",
      "#descriptionBox"
    };

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);
    
    presenter = new MediaPresenter();
    context.put("publisher", presenter);
    
    router = new MediaRequestRouterTestDouble(presenter);
    context.put("router", router);

    MediaView loader = new MediaView();

    stage.setScene(new Scene(loader.getView()));
    stage.show();
  }
  
  @Test
  public void newButton_ClearsText() {
    fillTextfields();

    clickOn(newButton);

    verifyTextfieldsEmpty();
  }

  @Test
  public void newButton_SendsNoRequest() {
    clickOn(newButton);

    assertEquals(0, router.getSentRequests().size());
  }

  @Test
  public void saveButton_SendsRequestableMedia() {
    fillTextfields();
     
    clickOn(saveButton);

    assertEquals(1, router.getSentRequests().size());
    CrudMediaRequest request = (CrudMediaRequest) router.getSentRequests().get(0);
    RequestableMedia media = request.getRequestable();
    assertEquals(filler, media.getTitle());
    assertEquals(filler, media.getCreator());
    assertEquals(filler, media.getSeason());
    assertEquals(filler, media.getEpisode());
    assertEquals(filler, media.getCategory());
    assertEquals(filler, media.getDescription());
  }

  @Test
  public void saveButton_SendsCreate_WhenNewMedia() {
    fillTextfields();
     
    clickOn(saveButton);

    assertEquals(1, router.getSentRequests().size());
    CrudMediaRequest request = (CrudMediaRequest) router.getSentRequests().get(0);
    assertEquals(Crud.Create, request.getCrud());
  }

  @Test
  public void saveButton_SendsUpdate_WhenMediaPreviouslySaved() {
    fillTextfields();
    clickOn(saveButton);
    clickOn(boxes[0]).write("?");

    clickOn(saveButton);

    assertEquals(2, router.getSentRequests().size());
    CrudMediaRequest request = (CrudMediaRequest) router.getSentRequests().get(1);
    assertEquals(Crud.Update, request.getCrud());
  }

  private void verifyTextfieldsEmpty() {
    for (String box : boxes)
      verifyThat(box, hasText(""));
  }

  private void fillTextfields() {
    for (String box : boxes)
      clickOn(box).write(filler);
  }
}
