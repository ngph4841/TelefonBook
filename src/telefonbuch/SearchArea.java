package telefonbuch;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SearchArea {

    private final AnchorPane pane = new AnchorPane();
    private final TextField tf = new TextField();

    SearchArea() {
        Button search = new Button("Suchen");
        AnchorPane.setRightAnchor(search, 10.);
        AnchorPane.setTopAnchor(search, 10.);
        AnchorPane.setBottomAnchor(search, 10.);
        search.setPrefWidth(70);

        AnchorPane.setLeftAnchor(tf, 10.);
        AnchorPane.setTopAnchor(tf, 10.);
        AnchorPane.setBottomAnchor(tf, 10.);
        AnchorPane.setRightAnchor(tf, 90.);

        pane.getChildren().add(search);
        pane.getChildren().add(tf);
    }

    public Node getPane() {
        return pane;
    }

    public void focusTextField() {
        tf.requestFocus();
    }
}
