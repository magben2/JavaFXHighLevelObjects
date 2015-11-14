package gui_jar;




import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
 
public class tableView_fx  {
	

    @SuppressWarnings("unchecked")
	public Node table(ArrayList listobjets) {
    TableView<Objets> table = new TableView<Objets>();
    ObservableList<Objets> data = FXCollections.observableArrayList(listobjets);
        TableColumn imagecol = new TableColumn("Image");
        imagecol.setMinWidth(200);
        imagecol.setCellValueFactory(
                new PropertyValueFactory<Objets, String>("image"));
        
        TableColumn nomcol = new TableColumn("Nom");
        nomcol.setMinWidth(200);
       // nomcol.getStyleClass().addAll("visible-lg", "visible-md");
        
        nomcol.setCellValueFactory(
                new PropertyValueFactory<Objets, String>("nom"));
        
        TableColumn description = new TableColumn("Description");
        
      /*  nomcol.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<Objets, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getString());
                        setGraphic(null);
                    }

                    private String getString() {
                        return getItem() == null ? "" : getItem().toString();
                    }
                };

                
                return cell;
            }
        });*/
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(imagecol, nomcol, description);
        
		table.setItems(data);
		return table;
       
    }
}
