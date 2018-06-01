package telefonbuch;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class EntryArea {
    private TableView<TelefonNumber> tableView = new TableView<>();

    EntryArea(ObservableList<TelefonNumber> numbers) {
        Callback<TableColumn<TelefonNumber, String>, TableCell<TelefonNumber, String>> cellFactory = p -> new EditingCell();

        TableColumn<TelefonNumber, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(t -> getCurrentRow(t).setLastName(t.getNewValue()));

        TableColumn<TelefonNumber, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(cellFactory);
        firstNameCol.setOnEditCommit(t -> getCurrentRow(t).setFirstName(t.getNewValue()));

        TableColumn<TelefonNumber, String> emailCol = new TableColumn<>("Number");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(t -> getCurrentRow(t).setNumber(t.getNewValue()));

        tableView.getColumns().add(firstNameCol);
        tableView.getColumns().add(lastNameCol);
        tableView.getColumns().add(emailCol);
        tableView.setItems(numbers);
        tableView.setEditable(true);
    }
    public TelefonNumber getSelectedItem(){
    	return tableView.getSelectionModel().getSelectedItem();
    	
    }

    public Node getPane() {
        return tableView;
    }

    private static class EditingCell extends TableCell<TelefonNumber, String> {

        private TextField textField;

        private EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener((arg0, arg1, arg2) -> {
                if (!arg2) {
                    commitEdit(textField.getText());
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }

    private static TelefonNumber getCurrentRow(TableColumn.CellEditEvent<TelefonNumber, String> t) {
        return t.getTableView().getItems().get(t.getTablePosition().getRow());
    }

}
