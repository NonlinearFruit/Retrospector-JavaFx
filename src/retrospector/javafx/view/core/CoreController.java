package retrospector.javafx.view.core;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javax.inject.Inject;
import retrospector.javafx.view.media.MediaView;
import retrospector.javafx.view.search.SearchView;

public class CoreController implements Initializable {

  private String searchKey = "search";
  private String mediaKey = "media";
  private String chartKey = "chart";
  private String wishlistKey = "wishlist";
  private String listKey = "list";
  private String achievementKey = "achievement";

  @Inject
  SearchView searchView;
  @Inject
  MediaView mediaView;

  @FXML
  private MenuBar menuBar;
  @FXML
  private Tab searchTab;
  @FXML
  private Tab mediaTab;
  @FXML
  private Tab chartTab;
  @FXML
  private Tab wishlistTab;
  @FXML
  private Tab listTab;
  @FXML
  private Tab achievementTab;
  @FXML
  private BorderPane anchor;
  @FXML
  private TabPane tabs;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    searchTab.setText(rb.getString(searchKey));
    mediaTab.setText(rb.getString(mediaKey));
    chartTab.setText(rb.getString(chartKey));
    wishlistTab.setText(rb.getString(wishlistKey));
    listTab.setText(rb.getString(listKey));
    achievementTab.setText(rb.getString(achievementKey));
    
    searchTab.setContent(searchView.getView());
    mediaTab.setContent(mediaView.getView());
  }  
}
