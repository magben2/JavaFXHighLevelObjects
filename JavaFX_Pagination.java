package application;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JavaFX_Pagination extends Application {

    private final static int dataSize = 100;
    private final static int rowsPerPage = 10;

    private final TableView<Sample> table = createTable();
    private final List<Sample> data = createData();

    private List<Sample> createData() {
        List<Sample> data = new ArrayList<>(dataSize);

        for (int i = 0; i < dataSize; i++) {
            data.add(new Sample(i, "Objet " + i, "nObjet " + i));
        }

        return data;
    }

    private TableView<Sample> createTable() {

        TableView<Sample> table = new TableView<>();

        TableColumn<Sample, Integer> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(param -> param.getValue().id);
        column1.setPrefWidth(150);

        TableColumn<Sample, String> column2 = new TableColumn<>("Objet");
        column2.setCellValueFactory(param -> param.getValue().objet);
        column2.setPrefWidth(250);

        TableColumn<Sample, String> column3 = new TableColumn<>("nObjet");
        column3.setCellValueFactory(param -> param.getValue().nobjet);
        column3.setPrefWidth(250);

        table.getColumns().addAll(column1, column2, column3);

        return table;
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
        table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        return new BorderPane(table);
    }

    @Override
    public void start(final Stage stage) throws Exception {

        Pagination pagination = new Pagination((data.size() / rowsPerPage + 1), 0);
        pagination.setPageFactory(this::createPage);

        Scene scene = new Scene(new BorderPane(pagination), 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Pagination");
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}