package gui_jar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


public class Objets {

	    public final SimpleStringProperty nom;
	    public final SimpleStringProperty description;
	    private final ObjectProperty imagefx= new SimpleObjectProperty();
	   public Objets( String Lnom, String desc,Imagefx image ) {
		   setImage(image);
	        this.nom = new SimpleStringProperty(Lnom);
	        this.description = new SimpleStringProperty(desc);
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
