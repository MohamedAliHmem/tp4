package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import application.Etudiant;
import application.EtudiantM;

public class ControllerEtudiant {
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField sexeField;
    @FXML
    private TextField filiereField;
    @FXML
    private TableView<Etudiant> tableView;
    @FXML
    private TableColumn<Etudiant, Integer> idColumn;
    @FXML
    private TableColumn<Etudiant, String> nomColumn;
    @FXML
    private TableColumn<Etudiant, String> prenomColumn;
    @FXML
    private TableColumn<Etudiant, String> sexeColumn;
    @FXML
    private TableColumn<Etudiant, String> filiereColumn;

    private EtudiantM etudiantM;
    private ObservableList<Etudiant> etudiantList;

    @FXML
    public void initialize() {
        etudiantM = new EtudiantM();
        etudiantList = FXCollections.observableArrayList(etudiantM.findAll());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeColumn.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        filiereColumn.setCellValueFactory(new PropertyValueFactory<>("filiere"));

        tableView.setItems(etudiantList);
    }

    @FXML
    private void handleAdd() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String sexe = sexeField.getText();
        String filiere = filiereField.getText();

        Etudiant etudiant = new Etudiant(nom, prenom, sexe, filiere);
        if (etudiantM.create(etudiant)) {
            etudiantList.add(etudiant);
            clearFields();
        } else {
            showAlert("Erreur", "Impossible d'ajouter l'étudiant", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleDelete() {
        Etudiant selectedEtudiant = tableView.getSelectionModel().getSelectedItem();
        if (selectedEtudiant != null) {
            if (etudiantM.delete(selectedEtudiant)) {
                etudiantList.remove(selectedEtudiant);
            } else {
                showAlert("Erreur", "Impossible de supprimer l'étudiant", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Aucune sélection", "Veuillez sélectionner un étudiant dans la table", Alert.AlertType.WARNING);
        }
    }

    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        sexeField.clear();
        filiereField.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
