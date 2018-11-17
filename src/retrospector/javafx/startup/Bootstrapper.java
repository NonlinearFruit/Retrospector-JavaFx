package retrospector.javafx.startup;

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
import retrospector.hsqldb.datagateway.DbConnector;
import retrospector.hsqldb.datagateway.MediaGateway;
import retrospector.hsqldb.datagateway.PropertyGateway;
import retrospector.javafx.presenter.MediaPresenter;
import retrospector.javafx.presenter.MediaView;

public class Bootstrapper extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);
    
    DbConnector connector = getConnector();
    MediaGateway dataGateway = getDataGateway(connector);
    MediaPresenter presenter = getPresenter();
    RequestRouter router = getRequestRouter(presenter, dataGateway);
    context.put("connector", connector);
    context.put("publisher", presenter);
    context.put("router", router);

    MediaView loader = getFXMLLoader();
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
  
  private MediaView getFXMLLoader() throws Exception {
      MediaView loader = new MediaView();
      return loader;
  }
  
  private MediaPresenter getPresenter() {
    return new MediaPresenter();
  }
  
  private RequestRouter getRequestRouter(MediaPresenter presenter, MediaGateway dataGateway) {
      CrudMediaUseCase mediaUseCase = new CrudMediaUseCase(dataGateway, presenter);
      return new CrudRequestRouter(mediaUseCase, null, null);
  }
  
  private MediaGateway getDataGateway(DbConnector connector) {
      MediaGateway mediaGateway = new MediaGateway(connector);
      return mediaGateway;
  }
  
  private DbConnector getConnector() {
      return new DbConnector(PropertyGateway.connectionString);
  }

  public static void main(String[] args) {
      launch(args);
  }
    
}
