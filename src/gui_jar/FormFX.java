package gui_jar;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * Classe permettant de créer un formulaire JavaFX responsive.
 * @author Benjamin Magron
 */
public class FormFX extends Parent {

	private GridPane  		 grid         = new GridPane();
	private ArrayList<Label> formLabels   = new ArrayList<Label>();
	private ArrayList<Node>  formElements = new ArrayList<Node>(); 

	private int nbRows = 0, sizeForm = 1;

	
	/**
	 * Constructeur d'un formulaire définit par son titre
	 * @param title
	 */
	public FormFX (String title) {
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text formTitle = new Text(title);
		formTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

		grid.add(formTitle, 0, 0, 2, 1);

		this.getChildren().add(grid);
	}//FormFX()
	

	/**
	 * Constructeur d'un formulaire définit par son titre,
	 * et pouvant ajouter un nombre variable de TextField.
	 * @param title
	 * @param fields
	 */
	public FormFX (String title, String... textFields) {
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));


		Text formTitle = new Text(title);
		formTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

		formLabels.add (0, new Label (title)); 
		formElements.add(0, formTitle);

		grid.add(formTitle, 0, 0, 2, 1);

		addTextField(textFields);

		this.getChildren().add(grid);
	}//FormFX()

	/**
	 * Retourne la GridPane qui contient le formulaire
	 * @return grid
	 */
	public GridPane getGrid() { return grid; }

	/**
	 * Retourne le nombre de lignes occupée par le formulaire
	 * @return nbRows
	 */
	public int getRows () { return nbRows; }

	
	/**
	 * add() permet d'ajouter un element à la Grid du formulaire
	 * @param child
	 * @param columnIndex
	 * @param rowIndex
	 */
	public void add(Node child, int columnIndex, int rowIndex) {
		grid.add(child, columnIndex, rowIndex);
	}//add()
	
	/**
	 * add() permet d'ajouter un element à la Grid du formulaire
	 * @param child
	 * @param columnIndex
	 * @param rowIndex
	 * @param colspan
	 * @param rowspan
	 */
	public void add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan) {
		grid.add(child, columnIndex, rowIndex, colspan, rowspan);
	}//add()


	/**
	 * Ajout d'un ou plusieurs TextField en donnant le nom (Label)
	 * de ces TextField
	 * L'ajout sera de la forme [Label : Element]
	 * @param labelField(s)
	 */
	public void addTextField (String... labelFields) {
		for (int i = 0; i < labelFields.length; ++i) {
			++nbRows;
			Label label = new Label(labelFields[i]);
			formLabels.add(nbRows, label);
			grid.add(label, 0, nbRows);

			TextField textField = new TextField();
			formElements.add(nbRows, textField);
			grid.add(textField, 1, nbRows, 2, 1);
		}
	}//addField()

	
	/**
	 * Permet de récupérer un TextField par son nom.
	 * @param name
	 * @return
	 */
	public TextField getTextField(String name) {
		for(int i = 0; i <= nbRows; i++)
			if (formLabels.get(i).getText().equals(name)) 
				return (TextField) formElements.get(i);
		return new TextField();
	}//getTextField()



	/**
	 * Possibilité d'ajout d'un PasswordField avec son Label
	 * L'ajout sera de la forme [Label : Element]
	 * @param label
	 */
	public void addPasswordField(String label) {
		++nbRows;
		Label lab = new Label(label);
		formLabels.add(nbRows, lab);
		grid.add(lab, 0, nbRows);

		PasswordField passwordField = new PasswordField();
		formElements.add(nbRows, passwordField);
		grid.add(passwordField, 1, nbRows);
	}//addPasswordField()

	
	/**
	 * Permet de récupérer un PasswordField par son nom.
	 * @param name
	 * @return
	 */
	public PasswordField getPasswordField(String name) {
		for(int i = 0; i <= nbRows; i++)
			if (formLabels.get(i).getText().equals(name)) 
				return (PasswordField) formElements.get(i);
		return new PasswordField();
	}//getTextField()



	/**
	 * Possibilité d'ajout d'un TextArea avec son Label
	 * L'ajout sera de la forme [Label : Element]
	 * @param label
	 */
	public void addTextArea (String label) {
		++nbRows;
		Label lab = new Label(label);
		formLabels.add(nbRows, lab);
		grid.add(lab, 0, nbRows);

		TextArea textArea = new TextArea();
		formElements.add(nbRows, textArea);
		grid.add(textArea, 0, ++nbRows, 2, 1);
	}//addFieldView()

	/**
	 * Permet de récupérer un TextArea par son nom.
	 * @param name
	 * @return
	 */
	public TextArea getTextArea(String name) {
		for(int i = 0; i <= nbRows; i++)
			if (formLabels.get(i).getText().equals(name)) 
				return (TextArea) formElements.get(i);
		return new TextArea();
	}//getTextField()



	/**
	 * Possibilité d'ajout d'une ou plusieurs CheckBox avec son Label
	 * L'ajout sera de la forme [Label : Element]
	 * @param label(s)
	 */
	public void addCheckBox (String... labels) {
		for (int i = 0; i < labels.length; ++i) {
			++nbRows;
			Label label = new Label(labels[i]);
			formLabels.add(nbRows, label);
			grid.add(label, 0, nbRows);

			CheckBox checkBox = new CheckBox();
			formElements.add(nbRows, checkBox);
			grid.add(checkBox, 1, nbRows);
		}
	}//addCheckBox()

	/**
	 * Permet de récupérer une CheckBox par son nom.
	 * @param name
	 * @return
	 */
	public CheckBox getCheckBox(String name) {
		for(int i = 0; i <= nbRows; i++)
			if (formLabels.get(i).getText().equals(name)) 
				return (CheckBox) formElements.get(i);
		return new CheckBox();
	}//getTextField()


	/**
	 * Possibilité d'ajout d'une ComboBox<String> en spécifiant 
	 * son Label et les éléments à l'interieur.
	 * L'ajout sera de la forme [Label : Element]
	 * @param label
	 * @param list
	 */
	public void addComboBox (String label, String... list) {
		++nbRows;
		Label lab = new Label(label);
		formLabels.add(nbRows, lab);
		grid.add(lab, 0, nbRows);

		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setValue("-- " + label + " --");
		comboBox.getItems().addAll(list);
		formElements.add(nbRows, comboBox);
		grid.add(comboBox, 1, nbRows);

	}//addComboBox()

	/**
	 * Permet de récupérer un ComboBox<String> par son nom.
	 * @param name
	 * @return
	 */
	public ComboBox<String> getComboBox(String name) {
		for(int i = 0; i <= nbRows; i++)
			if (formLabels.get(i).getText().equals(name)) 
				return (ComboBox<String>) formElements.get(i);
		return new ComboBox<String>();
	}//getTextField()


	
	/**
	 * Possibilité d'ajout d'un groupe de RadioButtons avec leur Label
	 * L'ajout sera de la forme [Label : Element]
	 * @param label
	 * @param names
	 */
	public void addRadioButtons (String label, String... names) {
		++nbRows;
		VBox        radioBBox = new VBox();
		ToggleGroup group     = new ToggleGroup();
		
		radioBBox.setSpacing(10);
		
		for (int i = 0; i < names.length; ++i) {
			RadioButton radioButton = new RadioButton(names[i]);
		    radioButton.setToggleGroup(group);
		    radioBBox.getChildren().add(radioButton);
		}
		formLabels.add(nbRows, new Label(label));
		formElements.add(nbRows, radioBBox);
		grid.add(radioBBox, 0, nbRows);
	}//addRadioButtons()
	
	public RadioButton getRadioButton(String name) {
		for(int i = 0; i <= nbRows; i++)
			if (formLabels.get(i).getText().equals(name)) 
				return (RadioButton) formElements.get(i);
		return new RadioButton();
	}//getTextField()
	

	/**
	 * Possibilité d'ajout d'un champs DatePicker avec son Label
	 * L'ajout sera de la forme [Label : Element]
	 * @param name
	 */
	public void addDatePicker(String label) {
		++nbRows;
		Label lab = new Label(label);
		formLabels.add(nbRows, lab);
		grid.add(lab, 0, nbRows);

		DatePicker datePicker = new DatePicker();
		formElements.add(nbRows, datePicker);
		grid.add(datePicker, 1, nbRows);
	}//addDatePicker()

	/**
	 * Permet de récupérer un DatePicker par son nom.
	 * @param name
	 * @return
	 */
	public DatePicker getDatePicker(String name) {
		for(int i = 0; i <= nbRows; i++)
			if (formLabels.get(i).getText().equals(name)) 
				return (DatePicker) formElements.get(i);
		return new DatePicker();
	}//getTextField()

	
	/* Utile a la fonction moveElements() */
	private void changePlaceElem(Node elem, int col, int row) {
		grid.getChildren().remove(elem);
		grid.add(elem, col, row);
		grid.getChildren().remove(elem);
		grid.add(elem, col, row);
	}//changePlaceElem


	/* Utile a la fonction resizeMe() */
	private void moveElements (String size) {
		if (size.equals("SMALL")) {
			if (sizeForm == 1) {
				int i = 1;
				for (; i < formElements.size() ; ++i) {
					if (formElements.get(i).getClass().equals(CheckBox.class)) {
						changePlaceElem(formLabels.get(i),   0, i*2 - 1);
						changePlaceElem(formElements.get(i), 0, i*2 - 1);
						formElements.get(i).setTranslateX(formLabels.get(i).getWidth() + 10);
					}
					else {
						changePlaceElem(formLabels.get(i),   0, i*2 - 1);
						changePlaceElem(formElements.get(i), 0, i*2    );
						if (formElements.get(i).getClass().equals(TextArea.class))
							((TextArea) formElements.get(i)).setPrefSize(350, 30);;
					}
				}
				nbRows = i * 2;
				sizeForm = 0;
			}
		}

		if (size.equals("LARGE")) {
			if (sizeForm == 0) {
				int i = 1;
				for (; i < formElements.size() ; ++i) {
					changePlaceElem(formLabels.get(i),   0, i);
					changePlaceElem(formElements.get(i), 1, i);

					if (formElements.get(i).getClass().equals(CheckBox.class))
						formElements.get(i).setTranslateX(0);
					if (formElements.get(i).getClass().equals(TextArea.class))
						((TextArea) formElements.get(i)).setPrefSize(400, 100);
				}
				nbRows = i;
				sizeForm = 1;
			}
		}
	}//moveElem()
	
	
	
	/**
	 * Cette fonction permet de redisposer le formulaire
	 * en fonction de la largeur de la fenetre qui le contient
	 * @param width
	 */
	public void resizeMe(double width) {
		//		System.out.println(W);
		if (width < 480) {
			moveElements("SMALL");
		}
		else {
			moveElements("LARGE");
		}
	}//resizeMe()
}//FormFX