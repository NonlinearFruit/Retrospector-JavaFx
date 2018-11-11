package retrospector.javafx.presenter;

import java.util.ArrayList;
import java.util.List;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.Request;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.CrudMediaRequest;
import static retrospector.core.interactor.CrudRequest.Crud.Create;
import retrospector.core.request.model.RequestableMedia;

public class RequestRouterTestDouble implements RequestRouter {
  private Presenter presenter;
  private List<Request> list;
  
  public RequestRouterTestDouble(Presenter presenter) {
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
    presenter.mediaAdded(media);
  }
}