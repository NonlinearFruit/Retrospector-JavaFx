package retrospector.javafx.view.search;

import com.airhacks.afterburner.injection.Injector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import retrospector.javafx.view.ResourceBundleTestDouble;

public class SearchViewTest extends ApplicationTest {
  private Stage stage;
  private String resultsId = "#results";
  private String newButtonId = "#newButton";
  private String editButtonId = "#editButton";
  private String deleteButtonId = "#deleteButton";
  private String resultsDisplayId = "#resultsDisplay";
  private String meanDisplayId = "#meanDisplay";
  private String currentDisplayId = "#currentDisplay";
  private ResourceBundle bundle = new ResourceBundleTestDouble();
  private String[] keys = new String[]{
            "title", "creator", "season", "episode", "category", "reviews", "delete", "new", "edit", "results", "average", "current"
          };
  
  @Test
  public void getView_Works() {
    SearchView loader = new SearchView(bundle);

    Parent parent = loader.getView();

    assertNotNull(parent);
  }

  @Test
  public void getPresenter_Works() {
    SearchView loader = new SearchView(bundle);

    SearchController controller = (SearchController) loader.getPresenter();

    assertNotNull(controller);
  }

  @Test
  public void setBundle_Works() {
    ResourceBundle bundle = new ResourceBundleTestDouble();
    SearchView loader = new SearchView(bundle);

    Parent parent = loader.getView();
    interact(showStage(parent));

    verifyTranslationIsDisplayed(bundle);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);

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
    TableView table = lookup(resultsId).<TableView>queryFirst();
    List columns = table.getColumns();
    columns.forEach(c -> {
      TableColumn column = (TableColumn) c;
      strings.add(column.getText());
      column.getColumns().forEach(
        x -> strings.add(((TableColumn) x).getText()));
    });
    strings.add(lookup(newButtonId).<Button>queryFirst().getText());
    strings.add(lookup(editButtonId).<Button>queryFirst().getText());
    strings.add(lookup(deleteButtonId).<Button>queryFirst().getText());
    strings.add(lookup(resultsDisplayId).<Text>queryFirst().getText());
    strings.add(lookup(meanDisplayId).<Text>queryFirst().getText());
    strings.add(lookup(currentDisplayId).<Text>queryFirst().getText());
    return strings;
  }

  private List<String> getBundleStrings(ResourceBundle bundle) {
    List<String> strings = new ArrayList<>();
    for (String key : keys)
      strings.add(bundle.getString(key));
    return strings;
  }
  
}
