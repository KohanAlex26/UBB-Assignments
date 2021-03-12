import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;





public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root= FXMLLoader.load(getClass().getResource("example.fxml"));*/
        primaryStage.setTitle("Convet to euro");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Button btn = new Button("Convert");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        Label pw = new Label("Lei:");
        grid.add(pw, 0, 1);

        TextField userTextField2 = new TextField();
        grid.add(userTextField2, 1, 2);
        Label pw2 = new Label("Euro:");
        grid.add(pw2, 0, 2);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String str=userTextField.getText();
                boolean isNumeric = str.chars().allMatch( Character::isDigit );
                if (isNumeric)
                {

                    Float a = Float.valueOf(userTextField.getText());
                    float b = (float) 4.8;
                    a = a * b;
                    userTextField2.setText(a.toString());
                }
                else
                {
                    Alert ab = new Alert(Alert.AlertType.ERROR);
                    ab.setTitle("Error Dialog");
                    ab.setHeaderText("Look, an Error Dialog");
                    ab.setContentText("Ooops, there was an error!");

                    ab.showAndWait();
                }
            }
        });


        Scene scene=new Scene(grid,400,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
