package gui_jar;

import javafx.beans.property.SimpleStringProperty;

public class ImageFx {

	    public String image;

	 
	   public ImageFx( String imagepath) {
	        this.image = imagepath;
	     
	    }
	   
       public String getImage() {
           return image;
       }

       public void setImage(String imagepath) {
    	   this.image=imagepath;

    	  
       }
}
