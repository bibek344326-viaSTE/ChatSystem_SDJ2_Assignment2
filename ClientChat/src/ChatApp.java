import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelFactory;
import view.ViewHandler;
import viewmodel.ClientFactory;
import viewmodel.ViewModelFactory;

public class ChatApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ClientFactory clientFactory = new ClientFactory();
        ModelFactory modelFactory = new ModelFactory(clientFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory.getChatModel());
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(stage);
    }
}
