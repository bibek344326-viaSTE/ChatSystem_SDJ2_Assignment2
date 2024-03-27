package view.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.User;
import view.ViewController;
import view.ViewHandler;
import viewmodel.UserViewModel;
import viewmodel.ViewModelFactory;

import java.awt.event.ActionEvent;

public class UserViewController implements ViewController {
    @FXML
    private TextField textField;
    @FXML
    private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private UserViewModel viewModel;

    public void init(ViewHandler viewHandler, UserViewModel viewModel, Region root) {

    }

    public void reset() {
        viewModel.reset();
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void onSubmit() {
        if (viewModel.onLogin(textField.getText())) {
            viewHandler.openChat();
        }
    }

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModelFactory.getUserViewModel();
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
        this.textField.textProperty().bindBidirectional(viewModel.getUsernameProperty());

    }

//    @FXML
//    private void onCancel() {
//        viewHandler.closeView();
//    }
}
