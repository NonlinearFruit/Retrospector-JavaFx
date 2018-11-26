package retrospector.javafx.view.media;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javax.inject.Inject;
import org.controlsfx.control.Rating;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.CrudMediaRequest;
import retrospector.core.interactor.CrudRequest.Crud;
import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.RequestableMedia;
import retrospector.core.request.model.RequestableReview;
import retrospector.javafx.presenter.CrudPublisher;

public class MediaController implements Initializable {
  
  @Inject
  private RequestRouter router;
  @Inject
  private CrudPublisher<RequestableMedia> publisher;

  private ObjectProperty<RequestableMedia> currentMedia;
  private Integer currentMediaId;
  private ObservableList<RequestableFactoid> factoids;
  private ObservableList<RequestableReview> reviews;

  @FXML
  private TextField titleBox;
  @FXML
  private TextField creatorBox;
  @FXML
  private TextField seasonBox;
  @FXML
  private TextField episodeBox;
  @FXML
  private ChoiceBox<RequestableMedia.RequestableType> typeBox;
  @FXML
  private ChoiceBox<String> categoryBox;
  @FXML
  private TextArea descriptionBox;
  @FXML
  private Button saveButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Button newButton;
  @FXML
  private Rating stars;
  @FXML
  private HBox mediaTitleBox;
  @FXML
  private HBox mediaSeasonBox;
  @FXML
  private Button newSeasonButton;
  @FXML
  private HBox mediaEpisodeBox;
  @FXML
  private Button newEpisodeButton;
  @FXML
  private Text rating;
  @FXML
  private Text mediaMaxRating;
  @FXML
  private Button autofillBtn;
  @FXML
  private Button previousButton;
  @FXML
  private Button cancelButton;
  @FXML
  private Button nextButton;
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    reviews = FXCollections.observableArrayList();
    factoids = FXCollections.observableArrayList();
    currentMedia = new SimpleObjectProperty();
    typeBox.setItems(
        FXCollections.observableArrayList(
            RequestableMedia.RequestableType.SERIES,
            RequestableMedia.RequestableType.MINISERIES,
            RequestableMedia.RequestableType.SINGLE
        )
    );
    typeBox.setValue(RequestableMedia.RequestableType.SINGLE);
    saveButton.setOnAction(this::handleSave);
    deleteButton.setOnAction(this::handleDelete);
    newButton.setOnAction(this::handleNew);

    publisher.addAddedListener(this::mediaAdded);
  }   
  
  public void mediaAdded(RequestableMedia media) {
    setMediaView(media);
  }

  public void mediaRetrieved(RequestableMedia media) {
    setMediaView(media);
  }

  public void mediaUpdated(RequestableMedia media) {
    setMediaView(media);
  }
  
  public void mediaDeleted(int mediaId) {
    if (getViewedMedia().getId() == mediaId)
      setMediaView(getNewBlankMedia());
  }
  
  private RequestableMedia getNewBlankMedia() {
    return new RequestableMedia("", "", "");
  }
    
  private void handleSave(ActionEvent event) {
    RequestableMedia media = getViewedMedia();
    Crud crud = Crud.Update;
    if (media.getId() == null)
      crud = Crud.Create;
    router.disseminate(new CrudMediaRequest(crud, media));
  }
  
  private void handleDelete(ActionEvent event) {
    router.disseminate(new CrudMediaRequest(Crud.Delete, currentMediaId));
  }
  
  private void handleNew(ActionEvent event) {
    setMediaView(getNewBlankMedia());
  }

  private void setMediaView(RequestableMedia media) {
    currentMediaId = media.getId();
    titleBox.setText(media.getTitle());
    creatorBox.setText(media.getCreator());
    seasonBox.setText(media.getSeason());
    episodeBox.setText(media.getEpisode());
    typeBox.setValue(media.getType());
    categoryBox.setValue(media.getCategory());
    descriptionBox.setText(media.getDescription());
  }

  private RequestableMedia getViewedMedia() {
    RequestableMedia media = getNewBlankMedia();
    media.setId(currentMediaId);
    media.setTitle(titleBox.getText());
    media.setCreator(creatorBox.getText());
    media.setSeason(seasonBox.getText());
    media.setEpisode(episodeBox.getText());
    media.setType(typeBox.getValue());
    media.setCategory(categoryBox.getValue());
    media.setDescription(descriptionBox.getText());
    return media;
  }

  @FXML
  private void autofill(ActionEvent event) {
  }
}