package gui_jar;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TableColumn;

public class CustomTableColumn<S, t> extends TableColumn<S,t>{ 
		private SimpleDoubleProperty percentWidth = new SimpleDoubleProperty(); 
		
		public CustomTableColumn(String columnName){ 
			super(columnName); 
		} 
		
		public SimpleDoubleProperty percentWidth() { return percentWidth; } 
		
		public double getPercentWidth() { return percentWidth.get(); } 
		
		public void setPercentWidth(double percentWidth) { 
			this.percentWidth.set(percentWidth); 
		} 
} 
