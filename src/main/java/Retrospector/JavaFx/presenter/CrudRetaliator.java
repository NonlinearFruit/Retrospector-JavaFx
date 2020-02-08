package retrospector.javafx.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.request.model.RequestableEntity;

public class CrudRetaliator<T extends RequestableEntity> implements CrudPresenter<T>, CrudPublisher<T> {

  private List<Consumer<T>> addedListeners; 
  private List<Consumer<T>> retrievedListeners; 
  private List<Consumer<T>> updatedListeners; 
  private List<Consumer<Integer>> deletedListeners;
  private List<Consumer<List<T>>> retrievedAllByMediaIdListeners;
  private List<Consumer<List<T>>> retrievedAllListeners;

  public CrudRetaliator() {
    addedListeners = new ArrayList<>();
    retrievedListeners = new ArrayList<>();
    updatedListeners = new ArrayList<>();
    deletedListeners = new ArrayList<>();
    retrievedAllByMediaIdListeners = new ArrayList<>();
    retrievedAllListeners = new ArrayList<>();
  }
  
  @Override
  public void added(T entity) {
    for (Consumer<T> addedListener : addedListeners)
      addedListener.accept(entity);
  }

  @Override
  public void retrieved(T entity) {
    for (Consumer<T> retrievedListener : retrievedListeners)
      retrievedListener.accept(entity);
  }

  @Override
  public void updated(T entity) {
    for (Consumer<T> updatedListener : updatedListeners)
      updatedListener.accept(entity);
  }

  @Override
  public void deleted(int id) {
    for (Consumer<Integer> deletedListener : deletedListeners)
      deletedListener.accept(id);
  }

  @Override
  public void retrievedAll(List<T> entity) {
    for (Consumer<List<T>> retrievedAllListener : retrievedAllListeners)
      retrievedAllListener.accept(entity);
  }

  @Override
  public void retrievedAllByMediaId(List<T> entity) {
    for (Consumer<List<T>> retrievedAllByMediaIdListener : retrievedAllByMediaIdListeners)
      retrievedAllByMediaIdListener.accept(entity);
  }

  @Override
  public void addAddedListener(Consumer<T> consumer) {
    addedListeners.add(consumer);
  }

  @Override
  public void addRetrievedListener(Consumer<T> consumer) {
    retrievedListeners.add(consumer);
  }

  @Override
  public void addUpdatedListener(Consumer<T> consumer) {
    updatedListeners.add(consumer);
  }

  @Override
  public void addDeletedListener(Consumer<Integer> consumer) {
    deletedListeners.add(consumer);
  }

  @Override
  public void addRetrievedAllListener(Consumer<List<T>> consumer) {
    retrievedAllListeners.add(consumer);
  }

  @Override
  public void addRetrievedAllByMediaIdListener(Consumer<List<T>> consumer) {
    retrievedAllByMediaIdListeners.add(consumer);
  }
}
