package gui_jar;

import javafx.scene.control.TextField;

public class SubscribeFormFX extends FormFX {

	public SubscribeFormFX(String title) {
		super (title, "Username");
		this.addPasswordField("Password");
		this.addTextField("Name", "FirstName", "Email", "Phone");
	}//SubscribeFormFX()
	
	
}//SubscribeFormFX