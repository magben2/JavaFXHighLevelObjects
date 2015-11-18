package gui_jar;




import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
 
public class tableView_fx  {
			
	public Node table(ArrayList listobjets,ArrayList listimages) {
    	TableView<Objets> table = new TableView<Objets>();
    	ObservableList<Objets> data = FXCollections.observableArrayList(listobjets);
        TableColumn<Objets, Imagefx> imagecol = new TableColumn<Objets,Imagefx>("Image");
        imagecol.setMinWidth(200);
        imagecol.setCellValueFactory(new PropertyValueFactory<Objets,Imagefx>("image"));
        
        imagecol.setCellFactory(new Callback<TableColumn<Objets, Imagefx>, TableCell<Objets, Imagefx>>() {

        	
            @Override
            public TableCell<Objets, Imagefx> call(TableColumn<Objets, Imagefx> param) {

                TableCell<Objets, Imagefx> cell = new TableCell<Objets, Imagefx>() {
                	@Override
                	public void updateItem( Imagefx objet,boolean empty) {
                	
                        if     (objet != null) {
                        VBox vb = new VBox();
                        ImageView imv = new ImageView(new Image(Objets.class.getResource("img").toString()+"/"+objet.getImage()));
                    	
                    	imv.setFitHeight(50);
                    	imv.setFitWidth(50);
                    	vb.getChildren().add(imv);
                    	setGraphic(vb);
                    }
               }
            };
                
            return cell;
            
        }
    });
       
       
        TableColumn nomcol = new TableColumn("Nom");
        nomcol.setMinWidth(200);
        
        nomcol.setCellValueFactory(
                new PropertyValueFactory<Objets, String>("nom"));
        
        TableColumn description = new TableColumn("Description");
        description.setCellValueFactory(
                new PropertyValueFactory<Objets, String>("desc"));
        
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(imagecol, nomcol, description);
        
		table.setItems(data);

		
		return table;
       
    }
}
