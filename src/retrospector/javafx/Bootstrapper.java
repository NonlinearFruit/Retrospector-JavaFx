package retrospector.javafx;

import retrospector.javafx.presenter.CrudMediaViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class Bootstrapper extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = getFXMLLoader();
        DbConnector connector = getConnector();
        DataGateway dataGateway = getDataGateway(connector);
        Presenter presenter = getPresenter(loader);
        RequestRouter router = getRequestRouter(presenter, dataGateway);
        ((CrudMediaViewController) presenter).setRequestRouter(router); // TODO
        showStage(stage, loader);
    }
    
    private FXMLLoader getFXMLLoader() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("presenter/CrudMediaView.fxml"));
        loader.load();
        return loader;
    }
    
    private void showStage(Stage stage, FXMLLoader loader) {
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private Presenter getPresenter(FXMLLoader loader) {
        Parent root = loader.getRoot();
        CrudMediaViewController presenter = loader.getController();
        return presenter;
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
