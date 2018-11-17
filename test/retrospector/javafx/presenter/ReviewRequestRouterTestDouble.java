package retrospector.javafx.presenter;

import java.util.ArrayList;
import java.util.List;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.boundry.Request;
import retrospector.core.boundry.RequestRouter;
import static retrospector.core.interactor.CrudRequest.Crud.Create;
import retrospector.core.interactor.CrudReviewRequest;
import retrospector.core.request.model.RequestableReview;

public class ReviewRequestRouterTestDouble implements RequestRouter {
  private CrudPresenter<RequestableReview> presenter;
  private List<Request> list;
  
  public ReviewRequestRouterTestDouble(CrudPresenter<RequestableReview> presenter) {
    this.list = new ArrayList<>();
    this.presenter = presenter;
  }
  
  @Override
  public void disseminate(Request request) {
    list.add(request);
  }

  public List<Request> getSentRequests() {
    return list;
  }
}