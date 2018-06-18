import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.event.*;
import java.util.Scanner;
import java.io.*;
/**
 * A class for telling jokes.
 *
 * @author   Tao Luo
 */
public class JokeGUI extends Application {
  public void start(Stage primaryStage) throws Exception {
    File sourceFile = new File("../../Files/Jokes.txt");
    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setStyle("-fx-background-color: #8db6cd;");//build a pane and set the colour
    
    Label label = new Label("Joke Time");
    label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 35));
    vBox.getChildren().add(label);//add a label
    int i = 0;
    int rn = (int)(Math.random()*4);
    try(Scanner input = new Scanner(sourceFile);){
    while(input.hasNext()){
    String s = input.nextLine();
    if (s.equals(""))
    i++;
    if (i == rn){
    label = new Label(s);
    label.setFont(Font.font("Harlow Solid Italic", FontWeight.BOLD, FontPosture.ITALIC, 25));
    vBox.getChildren().add(label);
    }
    }
    }//randomly select a paragraph of jokes to tell
    
     Button button1 = new Button("Stop telling Joke");//enable customer to stop the joke telling
     
     button1.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
       primaryStage.close();
       try{new OrderGUI().start(new Stage());}
       catch(Exception ex){}
     }
    });
    
     vBox.getChildren().add(button1);

     Scene scene = new Scene(vBox);
     primaryStage.setTitle("Jokes");
     primaryStage.setScene(scene);
     primaryStage.show();

  }
}