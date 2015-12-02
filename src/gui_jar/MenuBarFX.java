package gui_jar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MenuBarFX {
	private MenuBar menuBar;
	private TreeView<String> tree;
	public ImageView image = new ImageView(new Image(MenuBarFX.class.getResource("menu.png").toString()));
	public BorderPane root;
	public Scene scene;
	public Button button = new Button();
	public Menu menu[];
	public MenuItem subMenu[][];
	public TreeItem item[];
	public TreeItem subItem[][];
	
	/**
	 * 
	 * @param root 
	 * @param scene
	 * @param listeNomMenu
	 */
	public MenuBarFX(BorderPane root, Scene scene, String[] listeNomMenu){
		this.root=root;
		this.scene=scene;


		menuBar = new MenuBar();
		this.menu = new Menu[listeNomMenu.length];

		TreeItem<String> treeItem = new TreeItem<String> ("Menu");
		this.item=new TreeItem[listeNomMenu.length];
		treeItem.setExpanded(true);

		for (int i=0;i<listeNomMenu.length;i++){
			menu[i]= new Menu(listeNomMenu[i]);
			menuBar.getMenus().add(menu[i]);

			item[i] = new TreeItem<String> (listeNomMenu[i]);
			treeItem.getChildren().add(item[i]);
		}
		this.tree = new TreeView<String> (treeItem);
		image.setFitHeight(30);
		image.setFitWidth(30);
		button.setGraphic(image);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(tree.isVisible()){
					tree.setVisible(false);
				}else{
					tree.setVisible(true);
				}
			}
		});

		//Gestion de la "responsivite"
		scene.widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.intValue()>600){
				tree.setVisible(false);
				button.setVisible(false);
				root.setTop(menuBar);
			}
			else{
				root.setTop(button);
				button.setVisible(true);
			}

		});


		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		root.setLeft(tree);
		root.setTop(button);
		subMenu= new MenuItem[menu.length][10]; 
		subItem= new TreeItem[menu.length][10]; 
	}
	
	/**
	 * 
	 * @return menuBar
	 */
	public MenuBar getMenuBar(){
		return menuBar ;
	}
	
	/**
	 * 
	 * @return tree
	 */
	public TreeView<String> getTreeView(){
		return tree;
	}
	
	/**
	 * 
	 * @param menuPosition
	 * @param listeNomMenuItem
	 */
	public void createMenuItems(int menuPosition, String[] listeNomMenuItem){
		for (int i=0;i<listeNomMenuItem.length;i++){
			subMenu[menuPosition][i]=new MenuItem(listeNomMenuItem[i]);
			menu[menuPosition].getItems().add(subMenu[menuPosition][i]);
			subItem[menuPosition][i]=new TreeItem(listeNomMenuItem[i]);
			item[menuPosition].getChildren().add(subItem[menuPosition][i]);
		}
	}
}


