package retrospector.javafx.presenter;

import java.util.List;
import java.util.function.Consumer;
import retrospector.core.request.model.RequestableEntity;

public interface CrudPublisher<T extends RequestableEntity> {
    public void addAddedListener(Consumer<T> consumer);
    public void addRetrievedListener(Consumer<T> consumer);
    public void addUpdatedListener(Consumer<T> consumer);
    public void addDeletedListener(Consumer<Integer> consumer);
    public void addRetrievedAllListener(Consumer<List<T>> consumer);
    public void addRetrievedAllByMediaIdListener(Consumer<List<T>> consumer);
}
