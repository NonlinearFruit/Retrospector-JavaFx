package retrospector.javafx.startup;

import retrospector.javafx.view.splash.SplashScreenView;
import retrospector.javafx.view.splash.SplashScreenController;
import com.airhacks.afterburner.injection.Injector;
import insidefx.undecorator.UndecoratorScene;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.CrudMediaUseCase;
import retrospector.core.interactor.CrudRequestRouter;
import retrospector.core.request.model.RequestableMedia;
import retrospector.hsqldb.datagateway.DbConnector;
import retrospector.hsqldb.datagateway.MediaGateway;
import retrospector.hsqldb.datagateway.PropertyGateway;
import retrospector.javafx.presenter.CrudRetaliator;
import retrospector.javafx.view.media.MediaView;

public class Bootstrapper extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);
    
    DbConnector connector = new DbConnector(PropertyGateway.connectionString);
    MediaGateway dataGateway = new MediaGateway(connector);
    CrudRetaliator<RequestableMedia> presenter = new CrudRetaliator<>();
    RequestRouter router = getRequestRouter(presenter, dataGateway);
    context.put("publisher", presenter);
    context.put("router", router);

    MediaView loader = new MediaView();
    showMainStage(loader);
  }

  private void showSplash(Stage stage) throws Exception {
    SplashScreenView loader = new SplashScreenView();
    SplashScreenController controller = (SplashScreenController) loader.getPresenter();
    controller.setTheStageAndShow(stage);
  }

  private void showMainStage(MediaView loader) {
    Parent root = loader.getView();
    Stage mainStage = new Stage();
    UndecoratorScene.setClassicDecoration();
    UndecoratorScene undecoratorScene = new UndecoratorScene(mainStage, (Region) root);
    mainStage.setScene(undecoratorScene);
    mainStage.setTitle("Retrospector");
    mainStage.getIcons().add(new Image(Bootstrapper.class.getResourceAsStream("/retrospector/javafx/resources/logo-128.png")));
    mainStage.show();
  }
  
  private RequestRouter getRequestRouter(CrudRetaliator<RequestableMedia> presenter, MediaGateway dataGateway) {
      CrudMediaUseCase mediaUseCase = new CrudMediaUseCase(dataGateway, presenter);
      return new CrudRequestRouter(mediaUseCase, null, null);
  }
  
  public static void main(String[] args) {
      launch(args);
  }
}
