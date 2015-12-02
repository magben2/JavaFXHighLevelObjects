package gui_jar;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class MainMenu extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			String[] liste = {"file","edit","autre"};
			MenuBarFX menu = new MenuBarFX(root,scene,liste);
			menu.getMenuBar().prefWidthProperty().bind(primaryStage.widthProperty());
			
			
			String[] listeItemsFile = {"item1","item2"};
			menu.createMenuItems(0, listeItemsFile);			
			String[] listeItemsAutre = {"autre1","autre2","autre3"};
			menu.createMenuItems(1, listeItemsAutre);
			
			//implémentation des fonctions
			menu.subMenu[0][0].setOnAction(actionEvent -> System.out.println("ok"));
			menu.subItem[0][0].addEventHandler(ActionEvent.ANY, menu.subMenu[0][0].getOnAction());
			

			scene.widthProperty().addListener((observable, oldValue, newValue) -> {
				menu.resizeMe(newValue.doubleValue());

			});
			
			primaryStage.setTitle("NavBar");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
