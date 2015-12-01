package application;

import javafx.scene.control.*;


public class LoginFormFX extends FormFX {

	public LoginFormFX (String title) {
		super(title, "User Name");

		this.addPasswordField("Password");

		Button okBut = new Button("OK");
		this.add(okBut, 0, 3);
		Button CancelBut = new Button("Cancel");
		this.add(CancelBut, 1, 3);

		//this.getChildren().add(grid);
	}//Form()


}//LoginForm
