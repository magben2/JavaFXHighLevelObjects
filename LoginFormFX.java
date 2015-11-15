package application;

import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.GridPane;

public class LoginFormFX extends Parent {

	public LoginFormFX (String title) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		//grid.setGridLinesVisible(true);

		Text scenetitle = new Text(title);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);
		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button okBut = new Button("OK");
		grid.add(okBut, 1, 3);
		Button CancelBut = new Button("Cancel");
		grid.add(CancelBut, 2, 3);

		this.getChildren().add(grid);
	}//Form()


}//LoginForm
