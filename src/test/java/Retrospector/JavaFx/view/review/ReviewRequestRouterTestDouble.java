package retrospector.javafx.view.review;

import java.util.ArrayList;
import java.util.List;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.boundry.Request;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.crud.CrudRequest;
import retrospector.core.interactor.crud.CrudRequest.Crud;
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
    doStuff((CrudRequest<RequestableReview>) request);
  }

  public List<Request> getSentRequests() {
    return list;
  }

  private void doStuff(CrudRequest<RequestableReview> request) {
    RequestableReview review = request.getRequestable();
    Crud crud = request.getCrud();
    switch(crud) {
      case Create:
        review.setId(15);
        presenter.added(review);
    }
  }
}