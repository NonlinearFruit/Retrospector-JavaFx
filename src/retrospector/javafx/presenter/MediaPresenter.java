package retrospector.javafx.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.request.model.RequestableMedia;

public class MediaPresenter implements CrudPresenter<RequestableMedia>, MediaPublisher {

  private List<Consumer<RequestableMedia>> mediaAddedListeners; 
  private List<Consumer<RequestableMedia>> mediaRetrievedListeners; 
  private List<Consumer<RequestableMedia>> mediaUpdatedListeners; 
  private List<Consumer<Integer>> mediaDeletedListeners;

  public MediaPresenter() {
    mediaAddedListeners = new ArrayList<>();
    mediaRetrievedListeners = new ArrayList<>();
    mediaUpdatedListeners = new ArrayList<>();
    mediaDeletedListeners = new ArrayList<>();
  }

  @Override
  public void added(RequestableMedia media) {
    for (Consumer<RequestableMedia> mediaAddedListener : mediaAddedListeners)
      mediaAddedListener.accept(media);
  }

  @Override
  public void retrieved(RequestableMedia media) {
    for (Consumer<RequestableMedia> mediaRetrievedListener : mediaRetrievedListeners)
      mediaRetrievedListener.accept(media);
  }

  @Override
  public void updated(RequestableMedia media) {
    for (Consumer<RequestableMedia> mediaUpdatedListener : mediaUpdatedListeners)
      mediaUpdatedListener.accept(media);
  }

  @Override
  public void deleted(int id) {
    for (Consumer<Integer> mediaDeletedListener : mediaDeletedListeners)
      mediaDeletedListener.accept(id);
  }

  @Override
  public void retrievedAll(List<RequestableMedia> media) {
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
