package retrospector.javafx.presenter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import retrospector.core.request.model.RequestableMedia;

public class MediaPresenterTest {

  private MediaPresenter presenter;
  private int consumeMediaCount;
  private RequestableMedia consumedMedia;
  private int consumeIdCount;
  private int consumedId;
  
  @Before
  public void before() {
    presenter = new MediaPresenter();
    consumeMediaCount = 0;
  }
  
  @Test
  public void mediaAdded_CallsListener() {
    RequestableMedia media = new RequestableMedia("Hound of the Baskervilles", "Arthor Doyle", "Book");

    presenter.addMediaAddedListener(this::consumeMedia);
    presenter.added(media);

    assertEquals(1, consumeMediaCount);
    assertEquals(media, consumedMedia);
  }

  @Test
  public void mediaAdded_CallsMultipleListeners() {
    RequestableMedia media = new RequestableMedia("Hound of the Baskervilles", "Arthor Doyle", "Book");

    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addMediaAddedListener(this::consumeMedia);
    presenter.added(media);

    assertEquals(numberOfListeners, consumeMediaCount);
  }

  @Test
  public void mediaRetrieved_CallsListener() {
    RequestableMedia media = new RequestableMedia("Hound of the Baskervilles", "Arthor Doyle", "Book");

    presenter.addMediaRetrievedListener(this::consumeMedia);
    presenter.retrieved(media);

    assertEquals(1, consumeMediaCount);
    assertEquals(media, consumedMedia);
  }

  @Test
  public void mediaRetrieved_CallsMultipleListeners() {
    RequestableMedia media = new RequestableMedia("Hound of the Baskervilles", "Arthor Doyle", "Book");

    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addMediaRetrievedListener(this::consumeMedia);
    presenter.retrieved(media);

    assertEquals(numberOfListeners, consumeMediaCount);
  }

  @Test
  public void mediaUpdated_CallsListener() {
    RequestableMedia media = new RequestableMedia("Hound of the Baskervilles", "Arthor Doyle", "Book");

    presenter.addMediaUpdatedListener(this::consumeMedia);
    presenter.updated(media);

    assertEquals(1, consumeMediaCount);
    assertEquals(media, consumedMedia);
  }

  @Test
  public void mediaUpdated_CallsMultipleListeners() {
    RequestableMedia media = new RequestableMedia("Hound of the Baskervilles", "Arthor Doyle", "Book");

    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addMediaUpdatedListener(this::consumeMedia);
    presenter.updated(media);

    assertEquals(numberOfListeners, consumeMediaCount);
  }

  @Test
  public void mediaDeleted_CallsListener() {
    int mediaId = 5;

    presenter.addMediaDeletedListener(this::consumeId);
    presenter.deleted(mediaId);

    assertEquals(1, consumeIdCount);
    assertEquals(mediaId, consumedId);
  }

  @Test
  public void mediaDeleted_CallsMultipleListeners() {
    RequestableMedia media = new RequestableMedia("Hound of the Baskervilles", "Arthor Doyle", "Book");

    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addMediaUpdatedListener(this::consumeMedia);
    presenter.updated(media);

    assertEquals(numberOfListeners, consumeMediaCount);
  }

  private void consumeMedia(RequestableMedia media) {
    consumeMediaCount++;
    consumedMedia = media;
  }

  private void consumeId(int id) {
    consumeIdCount++;
    consumedId = id;
  }
}
