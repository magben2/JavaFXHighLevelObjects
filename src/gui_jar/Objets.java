package gui_jar;

import javafx.beans.property.SimpleStringProperty;

public class Objets {
	    public final SimpleStringProperty image;
	    public final SimpleStringProperty nom;
	    public final SimpleStringProperty description;
	 
	   public Objets( String imagepath, String Lnom, String desc) {
	        this.image = new SimpleStringProperty(imagepath);
	        this.nom = new SimpleStringProperty(Lnom);
	        this.description = new SimpleStringProperty(desc);
	    }
	   
       public String getImage() {
           return image.get();
       }

       public void setImage(String imagepath) {
           image.set(imagepath);
       }

       public String getNom() {
           return nom.get();
       }

       public void setNom(String Lnom) {
           nom.set(Lnom);
       }

       public String getDesc() {
           return description.get();
       }

       public void setDesc(String desc) {
           description.set(desc);
       }
   
}
