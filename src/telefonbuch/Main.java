package telefonbuch;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
		// main
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		SearchArea sa = new SearchArea();
		
		root.setTop(sa.getPane());
	
		TelefonBook tb = TelefonBook.loadFromFile(Paths.get("numbers.json"));
		TelefonBook neueSeiten = TelefonBook.loadFromFile(Paths.get("seiten.json"));
		
		EntryArea ea = new EntryArea(tb.getObservableList());
		root.setLeft(ea.getPane());

		EntryArea entrySeiten = new EntryArea(neueSeiten.getObservableList());
		root.setRight(entrySeiten.getPane());


		DeleteArea da = new DeleteArea(() -> {
			tb.getObservableList().remove(ea.getSelectedItem());
		},()-> {
			tb.getObservableList().add(new TelefonNumber("last Name", "first Name", "number"));
		},()->{
			tb.save();
		},()->{
            tb.getObservableList().add(neueSeiten.get(entrySeiten.getSelectedItem()));
        });
		
		root.setBottom(da.getPane());

		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setTitle("Telefonbuch");
		sa.focusTextField();
		primaryStage.show();
		
//		BorderPane toor = new BorderPane();
//		
//		SearchArea as = new SearchArea();
//		
//		toor.setTop(as.getPane());
//		
//		TelefonBook neueSeiten = TelefonBook.loadFromFile(Paths.get("neueSeiten.json"));
//		
//		EntryArea ae = new EntryArea(neueSeiten.getObservableList());
//		toor.setCenter(ae.getPane());
//		DeleteArea ad = new DeleteArea(() -> {
//			neueSeiten.getObservableList().remove(ae.getSelectedItem());
//		},()-> {
//			neueSeiten.getObservableList().add(new TelefonNumber("first Name", "last Name", "number"));
//		},()->{
//			neueSeiten.save();
//		});
//		
//		toor.setBottom(ad.getPane());
//
//		primaryStage.setScene(new Scene(root, 800, 600));
//		primaryStage.setTitle("Telefonbuch");
//		as.focusTextField();
//		
	}
}
