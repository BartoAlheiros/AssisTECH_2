package br.ufrpe.assistec.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.assistec.exceptions.NomeDeUsuarioOuSenhaInvalidaException;
import br.ufrpe.assistec.negocio.ServidorAssisTech;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField pswdF;
    @FXML
    private Button btnAcessar;
    @FXML
    private ComboBox<String> tipoUsuario;
    ServidorAssisTech svr = ServidorAssisTech.getInstance();

    @FXML
    public void ckLoginData(ActionEvent event) throws IOException {
        String usrName = new String(txtUserName.getText());
        String pswd = new String(pswdF.getText());

        try {

            tipoUsuario.getValue();

            if (tipoUsuario.getValue().equals("Cliente")) {
                try {

                    svr.buscarClientePorLogin(usrName, pswd);
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Parent parent = FXMLLoader.load(getClass().getResource("Janela1.fxml")); //carrega janela1
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("AssisTech");
                    stage.setResizable(false);
                    stage.setWidth(1222);
                    stage.setHeight(670);
                    stage.show();
                } catch (NomeDeUsuarioOuSenhaInvalidaException e) {
                    Alert err = new Alert(AlertType.ERROR);
                    err.setContentText(e.getMessage());
                    err.showAndWait();
                }

            } else if (tipoUsuario.getValue().equals("Técnico")) {
                try {
                    svr.buscarTecnicoPorLogin(usrName, pswd);
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Parent parent = FXMLLoader.load(getClass().getResource("Janela1.fxml")); //carrega janela1
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("AssisTech");
                    stage.setResizable(false);
                    stage.show();
                } catch (NomeDeUsuarioOuSenhaInvalidaException e) {
                    Alert err = new Alert(AlertType.ERROR);
                    err.setContentText(e.getMessage());
                    err.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert err = new Alert(AlertType.ERROR);
            err.setContentText("Escolha um tipo de Usuário!");
            err.showAndWait();
        }

    }//Fim do m�todo chkLoginData

    ObservableList<String> list = FXCollections.observableArrayList("Cliente", "Técnico");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoUsuario.setItems(list);

    }
}
