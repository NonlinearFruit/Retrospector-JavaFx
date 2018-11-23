package retrospector.javafx.view.review;

import retrospector.javafx.view.review.ReviewView;
import retrospector.javafx.view.review.ReviewController;
import com.airhacks.afterburner.injection.Injector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.request.model.RequestableReview;
import retrospector.javafx.bundles.BundleType;
import retrospector.javafx.bundles.BundleUtils;
import retrospector.javafx.presenter.CrudRetaliator;
import retrospector.javafx.view.ResourceBundleTestDouble;

public class ReviewViewTest extends ApplicationTest {

  private Stage stage;
  private String saveButtonId = "#saveButton";
  private String deleteButtonId = "#deleteButton";
  private String cancelButtonId = "#cancelButton";
  private String userBoxId = "#userBox";
  private String descriptionBoxId = "#descriptionBox";
  private String dateBoxId = "#dateBox";
  private ResourceBundle bundle = new ResourceBundleTestDouble();
  private String[] keys = new String[]{
            "save", "delete", "cancel", "date", "review", "user" 
          };
  
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
      interact(showStage(parent));

      verifyTranslationIsDisplayed(bundle);
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);

    CrudRetaliator<RequestableReview> presenter = new CrudRetaliator<>();
    context.put("reviewPublisher", presenter);
    
    RequestRouter router = new ReviewRequestRouterTestDouble(presenter);
    context.put("router", router);
    
    this.stage = stage;
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
    strings.add(lookup(saveButtonId).<Button>queryFirst().getText());
    strings.add(lookup(deleteButtonId).<Button>queryFirst().getText());
    strings.add(lookup(cancelButtonId).<Button>queryFirst().getText());
    strings.add(lookup(userBoxId).<TextField>queryFirst().getPromptText());
    strings.add(lookup(descriptionBoxId).<TextArea>queryFirst().getPromptText());
    strings.add(lookup(dateBoxId).<DatePicker>queryFirst().getPromptText());
    return strings;
  }

  private List<String> getBundleStrings(ResourceBundle bundle) {
    List<String> strings = new ArrayList<>();
    for (String key : keys)
      strings.add(bundle.getString(key));
    return strings;
  }
}