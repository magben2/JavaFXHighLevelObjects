package gui_jar;

import javafx.beans.property.SimpleStringProperty;

public class Imagefx {

	    public String image;

	 
	   public Imagefx( String imagepath) {
	        this.image = imagepath;
	     
	    }
	   
       public String getImage() {
           return image;
       }

       public void setImage(String imagepath) {
    	   this.image=imagepath;

    	  
       }
}
