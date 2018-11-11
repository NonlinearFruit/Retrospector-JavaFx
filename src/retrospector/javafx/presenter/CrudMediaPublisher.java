package retrospector.javafx.presenter;

import java.util.function.Consumer;
import retrospector.core.request.model.RequestableMedia;

public interface CrudMediaPublisher {
  
  public void addMediaAddedListener(Consumer<RequestableMedia> consumer);

  public void addMediaRetrievedListener(Consumer<RequestableMedia> consumer);

  public void addMediaUpdatedListener(Consumer<RequestableMedia> consumer);

  public void addMediaDeletedListener(Consumer<Integer> consumer);
}
