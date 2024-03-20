package view;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import utility.IntStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewmodel.ChatTabsViewModel;
public class ChatTabsController {
    @FXML
private TextField globalMessage;
    @FXML
    private ListView globalList;
    @FXML
    private ListView onlineUserList;
    @FXML
    private ListView messagesList;
@FXML
private TextField selected;
}