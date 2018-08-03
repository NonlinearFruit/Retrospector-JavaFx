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
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.CrudMediaRequest;
import retrospector.core.interactor.CrudRequest.Crud;
import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.RequestableMedia;
import retrospector.core.request.model.RequestableReview;

public class CrudMediaViewController implements Initializable, Presenter {
    @FXML
    private ListView<Integer> mediaViewer;
    @FXML
    private TextField titleBox;
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
    
    private RequestRouter router;
    private ObjectProperty<RequestableMedia> currentMedia;
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
    }   
    
    public void setRequestRouter(RequestRouter router) {
        this.router = router;
        router.disseminate(new CrudMediaRequest(Crud.ReadAll));
    }

    @Override
    public void mediaAdded(RequestableMedia media) {
        setMediaView(media);
        mediaViewer.getItems().add(media.getId());
    }

    @Override
    public void mediaRetrieved(RequestableMedia media) {
        setMediaView(media);
    }

    @Override
    public void mediaUpdated(RequestableMedia media) {
        setMediaView(media);
        mediaViewer.getItems().add(media.getId());
    }
    
    @Override
    public void mediaDeleted(int mediaId) {
        mediaViewer.getItems().removeAll(mediaId);
        if (getViewedMedia().getId() == mediaId)
            setMediaView(new RequestableMedia("", "", ""));
    }

    @Override
    public void factoidAdded(RequestableFactoid factoid) {
        factoids.add(factoid);
    }

    @Override
    public void factoidRetrieved(RequestableFactoid factoid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void factoidUpdated(RequestableFactoid factoid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void factoidDeleted(int factoidId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reviewAdded(RequestableReview requestableReview) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reviewUpdated(RequestableReview requestableReview) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reviewRetrieved(RequestableReview requestableReview) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reviewDeleted(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private RequestableMedia getNewBlankMedia() {
        return new RequestableMedia("", "", "");
    }
        
    private void handleSave(ActionEvent event) {
        RequestableMedia media = getViewedMedia();
        if (media.getId() == null)
            router.disseminate(new CrudMediaRequest(Crud.Create, media));
        else
            router.disseminate(new CrudMediaRequest(Crud.Update, media));
    }
    
    private void handleDelete(ActionEvent event) {
        router.disseminate(new CrudMediaRequest(Crud.Delete, getViewedMedia().getId()));
    }
    
    private void handleNew(ActionEvent event) {
        setMediaView(new RequestableMedia("", "", ""));
    }

    private void setMediaView(RequestableMedia media) {
        titleBox.setText(media.getTitle());
        seasonBox.setText(media.getSeason());
        episodeBox.setText(media.getEpisode());
        typeBox.setValue(media.getType());
        categoryBox.setText(media.getCategory());
        descriptionBox.setText(media.getDescription());
    }

    private RequestableMedia getViewedMedia() {
        RequestableMedia media = new RequestableMedia("", "", "");
        media.setTitle(titleBox.getText());
        media.setSeason(seasonBox.getText());
        media.setEpisode(episodeBox.getText());
        media.setType(typeBox.getValue());
        media.setCategory(categoryBox.getText());
        media.setDescription(descriptionBox.getText());
        return media;
    }
}
