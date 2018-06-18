import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.event.*;
import java.util.Scanner;
import java.io.*;
import javafx.scene.paint.*;
/**
 * A class for sleeping mode.
 *
 * @author   Tao Luo
 */
public class SleepingGUI extends Application {
 
   public void start(Stage primaryStage) throws Exception {
   GridPane pane = new GridPane();
   pane.setAlignment(Pos.CENTER);
   pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
   pane.setHgap(5.5);
   pane.setVgap(5.5);//build a pane and set the size

   
   Label label = new Label("I am sleeping! Zzzzzzzzzzzz...");
   label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
   pane.add(label, 1, 1);//add a label
   
   Button button2 = new Button("Wake Up!");
   
   button2.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
       primaryStage.close();
       try{new OrderGUI().start(new Stage());}
       catch(Exception ex){}
     }
    });
   pane.add(button2, 1, 2);//add a button for waking up the robot

   Scene scene = new Scene(pane,800,400 );
   pane.setStyle("-fx-background-color:#336699;");
   primaryStage.setTitle("Sleeping Mode");
   primaryStage.setScene(scene);
   primaryStage.show();


  }
}