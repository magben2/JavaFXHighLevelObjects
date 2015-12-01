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
 
 @SuppressWarnings("unchecked")
 private void configureTable(StackPane root) {
   
  final ObservableList<MyDomain> data = FXCollections.observableArrayList(
     new MyDomain(new Imagefx("voiture.jpeg"),"Red","This is a fruit."),
     new MyDomain(new Imagefx("voiture.jpeg"),"Orange","This is also a fruit."),
     new MyDomain(new Imagefx("voiture.jpeg"),"Brown","This is a vegetable.")
     );
   
  CustomTableView<MyDomain> table = new CustomTableView<MyDomain>();
   
  CustomTableColumn<MyDomain,Imagefx> imageColumn = new CustomTableColumn<MyDomain,Imagefx>("Image");
  imageColumn.setPercentWidth(25);
  imageColumn.setCellValueFactory(new PropertyValueFactory<MyDomain,Imagefx>("image"));
  
  imageColumn.setCellFactory(new Callback<TableColumn<MyDomain, Imagefx>, TableCell<MyDomain, Imagefx>>() {

  	
      @Override
      public TableCell<MyDomain, Imagefx> call(TableColumn<MyDomain, Imagefx> param) {

          TableCell<MyDomain, Imagefx> cell = new TableCell<MyDomain, Imagefx>() {
          	@Override
          	public void updateItem( Imagefx objet,boolean empty) {
          	
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
   
  CustomTableColumn<MyDomain,String> titleColumn  = new CustomTableColumn<MyDomain,String>("Title");
  titleColumn.setPercentWidth(20);
  titleColumn.setCellValueFactory(new PropertyValueFactory<MyDomain,String>("name"));
   
  CustomTableColumn<MyDomain,String> descCol = new CustomTableColumn<MyDomain,String>("Description");
  descCol.setPercentWidth(55);
  descCol.setCellValueFactory(new PropertyValueFactory<MyDomain,String>("description"));
   
  table.getTableView().getColumns().addAll(imageColumn,titleColumn,descCol);
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
 
  public MyDomain(Imagefx img, String desc,String color){
   this.imagefx.set(img);
   this.name.set(desc);
   this.description.set(color);
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
     
  public void setImage(Imagefx img){
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