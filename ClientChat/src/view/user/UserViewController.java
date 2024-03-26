package view.user;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.User;
import view.ViewHandler;
import viewmodel.UserViewModel;
import javafx.scene.control.Label;
import model.ChatModel;
import java.awt.*;




import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class UserViewController {
    @FXML
    private TextField textField;
    @FXML
    private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private UserViewModel viewModel;

    public void init(ViewHandler viewHandler, UserViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        errorLabel.textProperty().bind(viewModel.getErrorProperty());
        this.textField.textProperty().bindBidirectional(viewModel.getUsernameProperty());

    }

    public void reset() {
        viewModel.reset();
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void onSubmit() {
        String username = textField.getText();

        // Validate the username
        if (username.isEmpty()) {
            errorLabel.setText("Username cannot be empty.");
            return;
        }

        User newUser = new User(username);

        viewModel.setUsername(newUser);


        viewHandler.openView("Chat");
    }

    @FXML
    private void onCancel() {
        viewHandler.closeView();
    }
}
