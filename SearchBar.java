package guiFX.view;

import guiFX.MainApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node ;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class SearchBar extends MainApplication {

	/**
	 * 	Barre de recherche
	 *	
	 *	@param entries
	 *				la liste des éléments parmis lesquels rechercher.
	 *	@param list
	 *				la liste à afficher, (sans recherche, tous les objets sont affichés).
	 *	@param root
	 *				la mise en page de la barre de recherche.
	 */
	public ObservableList<String> entries = FXCollections.observableArrayList();    
	public ListView<String> list = new ListView<String>();
	public VBox root = new VBox () ;

	/**
	 * renvoi la liste
	 * 
	 * @param list
	 * 				ListView
	 * @return list
	 * 				Node
	 */
	public Node searchBar(ListView list){
		//TODO 
		return list;
	}

	/**
	 * @deprecated 
	 * @param name
	 * @return 
	 */
	public Button addButton (String name) {
		
		Button btn = new Button();
		btn.setText(name);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		return btn;
	}
	
	/**
	 * Initialise le layout principale de la barre de recherche
	 * 
	 * @return root
	 */
	public VBox rootSearchBar(ObservableList<String> entries) {

		TextField txt = new TextField();
		txt.setPromptText("Search");
		txt.textProperty().addListener(
				new ChangeListener() {
					public void changed(ObservableValue observable, 
							Object oldVal, Object newVal) {
						handleSearchByKey((String)oldVal, (String)newVal);
					}
				});
		// Rajoute la liste view entrée en paramètre.
		this.entries = entries ;
		this.list.setItems (entries) ;

		VBox root = new VBox();
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(2);
		root.getChildren().addAll(txt, list);

		return root ;
	}

	/**
	 * Recherche automatique à chaque fois qu'une touche est préssée.
	 * 
	 * @param oldVal
	 * @param newVal
	 */
	public void handleSearchByKey(String oldVal, String newVal) {
		// Si le nombre de caractère dans le champ de texte est plus petit que la dernière fois
		// c'est parce-que l'utilisateur a presser supprimer.
		if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
			// restaure la liste originale des entrées
			// et commence depuis le début.
			list.setItems( entries );
		}

		// Différencie chaques parties du texte rentré par l'utilisateur
		// par les espace.
		String[] parts = newVal.toUpperCase().split(" ");

		// Filtre les entrées qui ne sont pas contenu dans le texte entré
		ObservableList<String> subentries = FXCollections.observableArrayList();
		for ( Object entry: list.getItems() ) {
			boolean match = true;
			String entryText = (String)entry;
			for ( String part: parts ) {
				// L'entrée a besoin de contenir toutes les portions 
				// du texte recherché mais dans n'importe quel ordre.
				if ( ! entryText.toUpperCase().contains(part) ) {
					match = false;
					break;
				}
			}
			if ( match ) {
				subentries.add(entryText);
			}
		}
		list.setItems(subentries);
	}
}