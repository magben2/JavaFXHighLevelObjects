package application;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Sample {

    final ObservableValue<Integer> id;
    final SimpleStringProperty objet;
    final SimpleStringProperty nobjet;

    Sample(int id, String objet, String nobjet) {
        this.id = new SimpleObjectProperty<>(id);
        this.objet = new SimpleStringProperty(objet);
        this.nobjet = new SimpleStringProperty(nobjet);
    }
}