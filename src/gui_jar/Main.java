package gui_jar;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

        stage = new Stage();
        stage.setTitle("Hello Table");
        FXMLLoader loader = new FXMLLoader();
       loader.setLocation(Main.class.getResource("MainOverview.fxml"));
        
        GridPane root = new GridPane();
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        tableView_fx t = new tableView_fx();
        ObservableList<Node> children =root.getChildren();
        ArrayList<Objets> myArr = new ArrayList<Objets>();
        myArr.add(new Objets("voiture", "bon état",new Imagefx("voiture.jpeg")));
        myArr.add(new Objets("banane", "bon état",new Imagefx("voiture.jpeg")));
        ArrayList<Imagefx> myImg = new ArrayList<Imagefx>();
        myImg.add(new Imagefx("voiture.jpeg"));
        children.add(t.table(myArr,myImg));
        
        stage.show();
        
	}


}
