package retrospector.javafx.presenter;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.inject.Inject;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.CrudMediaRequest;
import retrospector.core.interactor.CrudRequest.Crud;
import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.RequestableMedia;
import retrospector.core.request.model.RequestableReview;

public class MediaController implements Initializable {
  @FXML
  private ListView<Integer> mediaViewer;
  @FXML
  private TextField titleBox;
  @FXML
  private TextField creatorBox;
  @FXML
  private TextField seasonBox;
  @FXML
  private TextField episodeBox;
  @FXML
  private ComboBox<RequestableMedia.RequestableType> typeBox;
  @FXML
  private TextField categoryBox;
  @FXML
  private TextArea descriptionBox;
  @FXML
  private Button saveButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Button newButton;
  
  @Inject
  private RequestRouter router;
  @Inject
  private MediaPublisher publisher;

  private ObjectProperty<RequestableMedia> currentMedia;
  private Integer currentMediaId;
  private ObservableList<RequestableFactoid> factoids;
  private ObservableList<RequestableReview> reviews;
  
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
    mediaViewer.setOnMouseClicked(e -> {
      if (e.getClickCount() == 2) {
        router.disseminate(new CrudMediaRequest(Crud.Read, mediaViewer.getSelectionModel().getSelectedItem()));
      }
    });
    saveButton.setOnAction(this::handleSave);
    deleteButton.setOnAction(this::handleDelete);
    newButton.setOnAction(this::handleNew);

    publisher.addMediaAddedListener(this::mediaAdded);
  }   
  
  public void mediaAdded(RequestableMedia media) {
    setMediaView(media);
    mediaViewer.getItems().add(media.getId());
  }

  public void mediaRetrieved(RequestableMedia media) {
    setMediaView(media);
  }

  public void mediaUpdated(RequestableMedia media) {
    setMediaView(media);
    mediaViewer.getItems().add(media.getId());
  }
  
  public void mediaDeleted(int mediaId) {
    mediaViewer.getItems().removeAll(mediaId);
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
    router.disseminate(new CrudMediaRequest(Crud.Delete, mediaViewer.selectionModelProperty().get().getSelectedItems().get(0)));
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
    categoryBox.setText(media.getCategory());
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
    media.setCategory(categoryBox.getText());
    media.setDescription(descriptionBox.getText());
    return media;
  }
}
