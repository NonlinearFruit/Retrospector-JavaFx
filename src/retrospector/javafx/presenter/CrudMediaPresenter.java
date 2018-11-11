package retrospector.javafx.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import retrospector.core.boundry.Presenter;
import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.RequestableMedia;
import retrospector.core.request.model.RequestableReview;

public class CrudMediaPresenter implements Presenter, CrudMediaPublisher {

  private List<Consumer<RequestableMedia>> mediaAddedListeners; 
  private List<Consumer<RequestableMedia>> mediaRetrievedListeners; 
  private List<Consumer<RequestableMedia>> mediaUpdatedListeners; 
  private List<Consumer<Integer>> mediaDeletedListeners;

  public CrudMediaPresenter() {
    mediaAddedListeners = new ArrayList<>();
    mediaRetrievedListeners = new ArrayList<>();
    mediaUpdatedListeners = new ArrayList<>();
    mediaDeletedListeners = new ArrayList<>();
  }

  @Override
  public void mediaAdded(RequestableMedia media) {
    for (Consumer<RequestableMedia> mediaAddedListener : mediaAddedListeners)
      mediaAddedListener.accept(media);
  }

  @Override
  public void mediaRetrieved(RequestableMedia media) {
    for (Consumer<RequestableMedia> mediaRetrievedListener : mediaRetrievedListeners)
      mediaRetrievedListener.accept(media);
  }

  @Override
  public void mediaUpdated(RequestableMedia media) {
    for (Consumer<RequestableMedia> mediaUpdatedListener : mediaUpdatedListeners)
      mediaUpdatedListener.accept(media);
  }

  @Override
  public void mediaDeleted(int id) {
    for (Consumer<Integer> mediaDeletedListener : mediaDeletedListeners)
      mediaDeletedListener.accept(id);
  }

  @Override
  public void mediaRetrievedAll(List<RequestableMedia> media) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void factoidAdded(RequestableFactoid factoid) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void factoidRetrieved(RequestableFactoid factoid) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void factoidUpdated(RequestableFactoid factoid) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void factoidDeleted(int factoidId) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void reviewAdded(RequestableReview requestableReview) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void reviewRetrieved(RequestableReview requestableReview) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void reviewUpdated(RequestableReview requestableReview) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void reviewDeleted(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }
  
  @Override
  public void addMediaAddedListener(Consumer<RequestableMedia> consumer) {
    mediaAddedListeners.add(consumer);
  }

  @Override
  public void addMediaRetrievedListener(Consumer<RequestableMedia> consumer) {
    mediaRetrievedListeners.add(consumer);
  }

  @Override
  public void addMediaUpdatedListener(Consumer<RequestableMedia> consumer) {
    mediaUpdatedListeners.add(consumer);
  }

  @Override
  public void addMediaDeletedListener(Consumer<Integer> consumer) {
    mediaDeletedListeners.add(consumer);
  }
}
