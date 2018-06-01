package telefonbuch;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeleteArea {
    private AnchorPane pane = new AnchorPane();

    DeleteArea(Runnable toDelete, Runnable toAdd, Runnable toSafe, Runnable toCopy) {
        Button delete = new Button("Entfernen");
        Button add = new Button("Neue Telefonnummer anlegen");
        Button safe = new Button("Sichern");
        Button copy = new Button("Kopieren");
        

        AnchorPane.setRightAnchor(delete, 30.);
        AnchorPane.setTopAnchor(delete, 10.);
        AnchorPane.setBottomAnchor(delete, 10.);
        delete.setPrefWidth(140);
        
        AnchorPane.setRightAnchor(add, 190.);
        AnchorPane.setTopAnchor(add, 10.);
        AnchorPane.setBottomAnchor(add, 10.);
        add.setPrefWidth(280);
        
        AnchorPane.setRightAnchor(safe, 490.);
        AnchorPane.setTopAnchor(safe, 10.);
        AnchorPane.setBottomAnchor(safe, 10.);
        safe.setPrefWidth(140);

        AnchorPane.setRightAnchor(copy, 650.);
        AnchorPane.setTopAnchor(copy, 10.);
        AnchorPane.setBottomAnchor(copy, 10.);
        copy.setPrefWidth(140);

        pane.getChildren().addAll(delete, add, safe, copy);
        copy.setOnAction(e->{toCopy.run();});
        safe.setOnAction(e->{toSafe.run();});
        delete.setOnAction(e->{toDelete.run();});
        add.setOnAction(e->{toAdd.run();});
    }
    

    public Node getPane() {
        return pane;
    }
}
