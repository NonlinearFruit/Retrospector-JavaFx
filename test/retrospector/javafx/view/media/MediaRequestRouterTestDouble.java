package retrospector.javafx.view.media;

import java.util.ArrayList;
import java.util.List;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.boundry.Request;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.CrudMediaRequest;
import static retrospector.core.interactor.CrudRequest.Crud.Create;
import retrospector.core.request.model.RequestableMedia;

public class MediaRequestRouterTestDouble implements RequestRouter {
  private CrudPresenter<RequestableMedia> presenter;
  private List<Request> list;
  
  public MediaRequestRouterTestDouble(CrudPresenter<RequestableMedia> presenter) {
    this.list = new ArrayList<>();
    this.presenter = presenter;
  }
  
  @Override
  public void disseminate(Request request) {
    list.add(request);
    process((CrudMediaRequest)request);
  }

  public List<Request> getSentRequests() {
    return list;
  }

  private void process(CrudMediaRequest request) {
    switch(request.getCrud()) {
      case Create:
        processCreate(request.getRequestable());
    }
  }

  private void processCreate(RequestableMedia media) {
    media.setId(15);
    presenter.added(media);
  }
}