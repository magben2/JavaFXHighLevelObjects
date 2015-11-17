package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FormFX extends Parent {

	private GridPane grid         = new GridPane();
	private Label[]  formLabels   = new Label[100];
	private Node[]   formElements = new Node[100];
	private int      nbElem = 0;

	/**
	 * Constructeur FormFX
	 * 
	 * @author Benjamin Magron
	 * @param title
	 * @param fields
	 */
	public FormFX (String title, String... fields) {
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		//grid.setGridLinesVisible(true);

		Text formTitle = new Text(title);
		formTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		formLabels[0] = new Label (title);
		formElements[0] = formTitle;
		grid.add(formTitle, 0, 0, 2, 1);

		addTextField(fields);

		this.getChildren().add(grid);
	}//FormFX()


	/**
	 * add() permet d'ajouter un element à la Grid du formulaire
	 * @param child
	 * @param columnIndex
	 * @param rowIndex
	 * @param colspan
	 * @param rowspan
	 */
	public void add(Node child, int columnIndex, int rowIndex) {
		grid.add(child, columnIndex, rowIndex);
	}//add()
	public void add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan) {
		grid.add(child, columnIndex, rowIndex, colspan, rowspan);
	}//add()
	

	/**
	 * Ajout d'un ou plusieurs champs en donnant le nom
	 * de ces champs
	 * @param field
	 */
	public void addTextField (String... fields) {
		for (int i = 0; i < fields.length; ++i) {
			++nbElem;
			Label label = new Label(fields[i]);
			formLabels[nbElem] = label;
			grid.add(label, 0, nbElem);
			
			TextField textField = new TextField();
			formElements[nbElem] = textField;
			grid.add(textField, 1, nbElem);
		}
	}//addField()
	
	public TextField getTextField(String name) {
		for(int i = 0; i <= nbElem; i++)
			if (formLabels[i].getText().equals(name)) 
				return (TextField) formElements[i];
		return new TextField();
	}//getTextField()

	

	/**
	 * Possibilité d'ajout d'un PasswordField
	 * @param name
	 */
	public void addPasswordField(String name) {
		++nbElem;
		Label label = new Label(name);
		formLabels[nbElem] = label;
		grid.add(label, 0, nbElem);

		PasswordField passwordField = new PasswordField();
		formElements[nbElem] = passwordField;
		grid.add(passwordField, 1, nbElem);
	}//addPasswordField()
	
	public PasswordField getPasswordField(String name) {
		for(int i = 0; i <= nbElem; i++)
			if (formLabels[i].getText().equals(name)) 
				return (PasswordField) formElements[i];
		return new PasswordField();
	}//getTextField()
	
	
	
	/**
	 * Possibilité d'ajout d'un TextArea avec son Label
	 * @param name
	 */
	public void addTextArea (String name) {
		++nbElem;
		Label label = new Label(name);
		formLabels[nbElem] = label;
		grid.add(label, 0, nbElem);
		
		TextArea textArea = new TextArea();
		formElements[nbElem] = textArea;
		grid.add(textArea, 0, ++nbElem, 2, 1);
	}//addFieldView()

	public TextArea getTextArea(String name) {
		for(int i = 0; i <= nbElem; i++)
			if (formLabels[i].getText().equals(name)) 
				return (TextArea) formElements[i];
		return new TextArea();
	}//getTextField()

	
	
	/**
	 * Possibilité d'ajout d'une ou plusieurs CheckBox
	 * @param label
	 */
	public void addCheckBox (String... labels) {
		for (int i = 0; i < labels.length; ++i) {
			++nbElem;
			Label label = new Label(labels[i]);
			formLabels[nbElem] = label;
			grid.add(label, 0, nbElem);
			
			CheckBox checkBox = new CheckBox();
			formElements[nbElem] = checkBox;
			grid.add(checkBox, 1, nbElem);
		}
	}//addCheckBox()
	
	public CheckBox getCheckBox(String name) {
		for(int i = 0; i <= nbElem; i++)
			if (formLabels[i].getText().equals(name)) 
				return (CheckBox) formElements[i];
		return new CheckBox();
	}//getTextField()


	/**
	 * Possibilité d'ajout d'une ComboBox en spécifiant 
	 * son Label et les éléments à l'interieur.
	 * @param name
	 * @param list
	 */
	public void addComboBox (String name, String... list) {
		++nbElem;
		Label label = new Label(name);
		formLabels[nbElem] = label;
		grid.add(label, 0, nbElem);

		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setValue("-- " + name + " --");
		comboBox.getItems().addAll(list);
		formElements[nbElem] = comboBox;
		grid.add(comboBox, 1, nbElem);
		
	}//addComboBox()
	
	public ComboBox<String> getComboBox(String name) {
		for(int i = 0; i <= nbElem; i++)
			if (formLabels[i].getText().equals(name)) 
				return (ComboBox<String>) formElements[i];
		return new ComboBox<String>();
	}//getTextField()

	
	
	/**
	 * Possibilité d'ajout d'un champs DatePicker
	 * @param name
	 */
	public void addDatePicker(String name) {
		++nbElem;
		Label label = new Label(name);
		formLabels[nbElem] = label;
		grid.add(label, 0, nbElem);

		DatePicker datePicker = new DatePicker();
		formElements[nbElem] = datePicker;
		grid.add(datePicker, 1, nbElem);
	}//addDatePicker()
	
	public DatePicker getDatePicker(String name) {
		for(int i = 0; i <= nbElem; i++)
			if (formLabels[i].getText().equals(name)) 
				return (DatePicker) formElements[i];
		return new DatePicker();
	}//getTextField()
	
}//FormFX