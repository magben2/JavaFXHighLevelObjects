package gui_jar;

import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
 
public class TableViewFx extends Application {
	
 CustomTableView<MyDomain> table = new CustomTableView<MyDomain>();
 public static void main(String[] args) {
  Application.launch(args);
 }
  
 @Override
 public void start(Stage stage) throws Exception {
  StackPane root = new StackPane();
  root.autosize();
  Scene scene = new Scene(root);
  stage.setTitle("TableView Auto Size Demo");
  stage.setWidth(700);
     stage.setHeight(400);
     stage.setScene(scene);
     stage.show();
      
  configureTable(root);
 }
 

 public void configureTable(StackPane root) {
   
  final ObservableList<MyDomain> data = FXCollections.observableArrayList(
     new MyDomain(new ImageFx("voiture.jpeg"),"Red","This is a fruit."),
     new MyDomain(new ImageFx("voiture.jpeg"),"Orange","This is also a fruit."),
     new MyDomain(new ImageFx("voiture.jpeg"),"Brown","This is a vegetable.")
     );

  CustomTableColumn<MyDomain,String> title  = createTextColumn("name", 25);
   
  CustomTableColumn<MyDomain,String> descCol = createTextColumn("description", 25);
  
  table.getTableView().getColumns().addAll(createImage("image"),title,descCol);
  
  table.getTableView().setItems(data);
  root.getChildren().add(table);
 }

 
 /**
  * CustomTableView to hold the table and grid.
  */
 public class CustomTableView<s> extends StackPane{
  private TableView<s> table;
   
  @SuppressWarnings("rawtypes")
  public CustomTableView(){
   this.table = new TableView<s>();
   final GridPane grid = new GridPane();
 
   this.table.getColumns().addListener(new ListChangeListener<TableColumn>(){
    @Override
    public void onChanged(javafx.collections.ListChangeListener.Change<? extends TableColumn> arg0) {
	  
	     grid.getColumnConstraints().clear();
	     ColumnConstraints[] arr1 = new ColumnConstraints[CustomTableView.this.table.getColumns().size()];
	     StackPane[] arr2 = new StackPane[CustomTableView.this.table.getColumns().size()];
	 
	     int i=0;
	     for(TableColumn column : CustomTableView.this.table.getColumns()){
	      CustomTableColumn col = (CustomTableColumn)column;
	      ColumnConstraints consta = new ColumnConstraints();
	      consta.setPercentWidth(col.getPercentWidth());
	       
	      StackPane sp = new StackPane();
	      if(i==0){
	       // Quick fix for not showing the horizantal scroll bar.
	       NumberBinding diff = sp.widthProperty().subtract(3.75); 
	       column.prefWidthProperty().bind(diff);
	      }else{
	       column.prefWidthProperty().bind(sp.widthProperty());
	      }
	      arr1[i] = consta;
	      arr2[i] = sp;
	      i++;
	     }
	 
	     grid.getColumnConstraints().addAll(arr1);
	     grid.addRow(0, arr2);
	    }
   });
   getChildren().addAll(grid,table);
  }

  
  public TableView<s> getTableView(){
   return this.table;
  }
 }
 /**
  * create column text
  * @param nom
  * @param size / percentage
  * @return
  */
 public CustomTableColumn<MyDomain, String> createTextColumn(String nom,int size) {
		 CustomTableColumn<MyDomain,String> titleColumn  = new CustomTableColumn<MyDomain,String>(nom);
		 titleColumn.setPercentWidth(size);
		 titleColumn.setCellValueFactory(new PropertyValueFactory<MyDomain,String>(nom));
		return titleColumn;
 }
 /**
  * create column image
  * @param nom
  * @return
  */
 public CustomTableColumn<MyDomain,ImageFx> createImage(String nom) {
 CustomTableColumn<MyDomain,ImageFx> imageColumn = new CustomTableColumn<MyDomain,ImageFx>(nom);
 imageColumn.setPercentWidth(25);
 imageColumn.setCellValueFactory(new PropertyValueFactory<MyDomain,ImageFx>(nom));
 
 imageColumn.setCellFactory(new Callback<TableColumn<MyDomain, ImageFx>, TableCell<MyDomain, ImageFx>>() {

 	
     @Override
     public TableCell<MyDomain, ImageFx> call(TableColumn<MyDomain, ImageFx> param) {

         TableCell<MyDomain, ImageFx> cell = new TableCell<MyDomain, ImageFx>() {
         	@Override
         	public void updateItem( ImageFx objet,boolean empty) {
         	
                 if     (objet != null) {
                 VBox vb = new VBox();
                 ImageView imv = new ImageView(new Image(MyDomain.class.getResource("img").toString()+"/"+objet.getImage()));
             	
             	imv.setFitHeight(50);
             	imv.setFitWidth(50);
             	vb.setAlignment(Pos.CENTER);
             	vb.getChildren().add(imv);
             	setGraphic(vb);
             }
        }
     };
         
     return cell;
     
	  }
	});
return imageColumn;
 }
 /**
  * CustomTableColumn to hold the custom percentWidth property.
  */
 public class CustomTableColumn<S,T> extends TableColumn<S, T>{
  private SimpleDoubleProperty percentWidth = new SimpleDoubleProperty();
 
  public CustomTableColumn(String columnName){
   super(columnName);
  }
 
  public SimpleDoubleProperty percentWidth() {
   return percentWidth;
  }
 
  public double getPercentWidth() {
   return percentWidth.get();
  }
 
  public void setPercentWidth(double percentWidth) {
   this.percentWidth.set(percentWidth);
  }
 }
  
 /**
  * Domain Object.
  */
 public class MyDomain{
  private SimpleStringProperty name = new SimpleStringProperty();
  private SimpleStringProperty description = new SimpleStringProperty();
  private ObjectProperty imagefx= new SimpleObjectProperty();
 
  public MyDomain(ImageFx img, String param1,String param2){
   this.imagefx.set(img);
   this.name.set(param1);
   this.description.set(param2);
  }
 
  public String getDescription() {
      return description.get();
  }
       
  public SimpleStringProperty descriptionProperty(){
      return description;
  }
      
  public String getName() {
      return name.get();
  }
      
  public SimpleStringProperty nameProperty(){
      return name;
  }
     
  public void setImage(ImageFx img){
	imagefx.set(img);
 }
 public Object getImage(){
     return imagefx.get();
 }
 public ObjectProperty imageProperty(){
      return imagefx;
 }
 }
}