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
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.interactor.CrudFactoidUseCase;
import retrospector.core.interactor.CrudMediaUseCase;
import retrospector.core.interactor.CrudRequestRouter;
import retrospector.core.interactor.CrudReviewUseCase;
import retrospector.hsqldb.datagateway.CrudGateway;
import retrospector.hsqldb.datagateway.DbConnector;
import retrospector.hsqldb.datagateway.FactoidGateway;
import retrospector.hsqldb.datagateway.MediaGateway;
import retrospector.hsqldb.datagateway.PropertyGateway;
import retrospector.hsqldb.datagateway.ReviewGateway;
import retrospector.javafx.presenter.CrudMediaPresenter;
import retrospector.javafx.presenter.CrudMediaView;

public class Bootstrapper extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    Map<Object, Object> context = new HashMap<>(); 
    Injector.setConfigurationSource(context::get);
    
    DbConnector connector = getConnector();
    DataGateway dataGateway = getDataGateway(connector);
    Presenter presenter = getPresenter();
    RequestRouter router = getRequestRouter(presenter, dataGateway);
    context.put("connector", connector);
    context.put("presenter", presenter);
    context.put("router", router);

    CrudMediaView loader = getFXMLLoader();
    showMainStage(loader);
  }

  private void showSplash(Stage stage) throws Exception {
    SplashScreenView loader = new SplashScreenView();
    SplashScreenController controller = (SplashScreenController) loader.getPresenter();
    controller.setTheStageAndShow(stage);
  }

  private void showMainStage(CrudMediaView loader) {
    Parent root = loader.getView();
    Stage mainStage = new Stage();
    UndecoratorScene.setClassicDecoration();
    UndecoratorScene undecoratorScene = new UndecoratorScene(mainStage, (Region) root);
    mainStage.setScene(undecoratorScene);
    mainStage.setTitle("Retrospector");
    mainStage.getIcons().add(new Image(Bootstrapper.class.getResourceAsStream("/retrospector/javafx/resources/logo-128.png")));
    mainStage.show();
  }
  
  private CrudMediaView getFXMLLoader() throws Exception {
      CrudMediaView loader = new CrudMediaView();
      return loader;
  }
  
  private Presenter getPresenter() {
    return new CrudMediaPresenter();
  }
  
  private RequestRouter getRequestRouter(Presenter presenter, DataGateway dataGateway) {
      CrudMediaUseCase mediaUseCase = new CrudMediaUseCase(dataGateway, presenter);
      CrudReviewUseCase reviewUseCase = new CrudReviewUseCase(dataGateway, presenter);
      CrudFactoidUseCase factoidUseCase = new CrudFactoidUseCase(dataGateway, presenter);
      
      return new CrudRequestRouter(mediaUseCase, reviewUseCase, factoidUseCase);
  }
  
  private DataGateway getDataGateway(DbConnector connector) {
      MediaGateway mediaGateway = new MediaGateway(connector);
      ReviewGateway reviewGateway = new ReviewGateway(connector);
      FactoidGateway factoidGateway = new FactoidGateway(connector);
      
      return new CrudGateway(mediaGateway, reviewGateway, factoidGateway);
  }
  
  private DbConnector getConnector() {
      return new DbConnector(PropertyGateway.connectionString);
  }

  public static void main(String[] args) {
      launch(args);
  }
    
}
