package retrospector.javafx.presenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import retrospector.core.request.model.RequestableReview;

public class CrudRetaliatorTest {

  private CrudRetaliator<RequestableReview> presenter;
  private int consumeReviewCount;
  private RequestableReview consumedReview;
  private int consumeIdCount;
  private int consumedId;
  private RequestableReview defaultReview;
  private int consumeListCount;
  private List<RequestableReview> consumeList;
  
  @Before
  public void before() {
    presenter = new CrudRetaliator<>();
    defaultReview = new RequestableReview(5, LocalDate.now(),"","");
  }
  
  @Test
  public void added_CallsListener() {
    presenter.addAddedListener(this::consumeReview);
    presenter.added(defaultReview);

    assertEquals(1, consumeReviewCount);
    assertEquals(defaultReview, consumedReview);
  }

  @Test
  public void added_CallsMultipleListeners() {
    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addAddedListener(this::consumeReview);
    presenter.added(defaultReview);

    assertEquals(numberOfListeners, consumeReviewCount);
  }

  @Test
  public void retrieved_CallsListener() {
    presenter.addRetrievedListener(this::consumeReview);
    presenter.retrieved(defaultReview);

    assertEquals(1, consumeReviewCount);
    assertEquals(defaultReview, consumedReview);
  }

  @Test
  public void retrieved_CallsMultipleListeners() {
    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addRetrievedListener(this::consumeReview);
    presenter.retrieved(defaultReview);

    assertEquals(numberOfListeners, consumeReviewCount);
  }

  @Test
  public void updated_CallsListener() {
    presenter.addUpdatedListener(this::consumeReview);
    presenter.updated(defaultReview);

    assertEquals(1, consumeReviewCount);
    assertEquals(defaultReview, consumedReview);
  }

  @Test
  public void updated_CallsMultipleListeners() {
    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addUpdatedListener(this::consumeReview);
    presenter.updated(defaultReview);

    assertEquals(numberOfListeners, consumeReviewCount);
  }

  @Test
  public void deleted_CallsListener() {
    int reviewId = 5;

    presenter.addDeletedListener(this::consumeId);
    presenter.deleted(reviewId);

    assertEquals(1, consumeIdCount);
    assertEquals(reviewId, consumedId);
  }

  @Test
  public void deleted_CallsMultipleListeners() {
    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addUpdatedListener(this::consumeReview);
    presenter.updated(defaultReview);

    assertEquals(numberOfListeners, consumeReviewCount);
  }

  @Test
  public void retrievedAllByMediaId_CallsListener() {
    List<RequestableReview> list = new ArrayList<>();

    presenter.addRetrievedAllByMediaIdListener(this::consumeList);
    presenter.retrievedAllByMediaId(list);

    assertEquals(1, consumeListCount);
    assertEquals(list, consumeList);
  }

  @Test
  public void retrievedAllByMediaId_CallsMultipleListeners() {
    List<RequestableReview> list = new ArrayList<>();

    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addRetrievedAllByMediaIdListener(this::consumeList);
    presenter.retrievedAllByMediaId(list);

    assertEquals(numberOfListeners, consumeListCount);
  }

  @Test
  public void retrievedAll_CallsListener() {
    List<RequestableReview> list = new ArrayList<>();

    presenter.addRetrievedAllListener(this::consumeList);
    presenter.retrievedAll(list);

    assertEquals(1, consumeListCount);
    assertEquals(list, consumeList);
  }

  @Test
  public void retrievedAll_CallsMultipleListeners() {
    List<RequestableReview> list = new ArrayList<>();

    int numberOfListeners = 10;
    for (int i = 0; i < numberOfListeners; i++)
      presenter.addRetrievedAllListener(this::consumeList);
    presenter.retrievedAll(list);

    assertEquals(numberOfListeners, consumeListCount);
  }

  private void consumeReview(RequestableReview review) {
    consumeReviewCount++;
    consumedReview = review;
  }

  private void consumeId(int id) {
    consumeIdCount++;
    consumedId = id;
  }

  private void consumeList(List<RequestableReview> list) {
    consumeListCount++;
    consumeList = list;
  }
}
