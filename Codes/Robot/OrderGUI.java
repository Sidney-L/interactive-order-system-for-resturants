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
import javafx.scene.paint.*;
import java.util.Timer;  
import java.util.TimerTask;
import javafx.application.Platform;
/**
 * A class for showing the main order.
 *
 * @author   Tao Luo
 */
public class OrderGUI extends Application {
   Timer timer;
   public void start(Stage primaryStage) throws Exception {

   BorderPane borderpane = new BorderPane();
   borderpane.setStyle("-fx-background-color: #8db6cd;");//build a pane and set the background colour
   

   Label label1 = new Label("Please select an option:");
   label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
   borderpane.setTop(label1);//add a label
   

   GridPane pane = new GridPane();
   
   BorderPane borderpane1 = new BorderPane();
   borderpane1.setStyle("-fx-background-color: #b4cdcd;");
   borderpane1.setPrefSize(300,200);
   borderpane1.setTop(new Label("1"));
   borderpane1.setCenter(new Label("Fish"));
   pane.add(borderpane1, 0, 0);

   BorderPane borderpane2 = new BorderPane();
   borderpane2.setStyle("-fx-background-color: #ff7f24;");
   borderpane2.setPrefSize(300,200);
   borderpane2.setTop(new Label("2"));
   borderpane2.setCenter(new Label("Meat"));
   pane.add(borderpane2, 1, 0);

   BorderPane borderpane3 = new BorderPane();
   borderpane3.setStyle("-fx-background-color: #bbffff;");
   borderpane3.setPrefSize(300,200);
   borderpane3.setTop(new Label("3"));
   borderpane3.setCenter(new Label("Rice"));
   pane.add(borderpane3, 2, 0);

   
   BorderPane borderpane4 = new BorderPane();
   borderpane4.setStyle("-fx-background-color: #fff68f;");
   borderpane4.setPrefSize(300,200);
   borderpane4.setTop(new Label("4"));
   borderpane4.setCenter(new Label("Noodle"));
   pane.add(borderpane4, 0, 1);

   BorderPane borderpane5 = new BorderPane();
   borderpane5.setStyle("-fx-background-color: #ff82ab;");
   borderpane5.setPrefSize(300,200);
   borderpane5.setTop(new Label("5"));
   borderpane5.setCenter(new Label("Drink"));
   pane.add(borderpane5, 1, 1);
   
   BorderPane borderpane6 = new BorderPane();
   borderpane6.setStyle("-fx-background-color: #90ee90;");
   borderpane6.setPrefSize(300,200);
   pane.add(borderpane6, 2, 1);//all the colourful rectangles to show the dishes

   borderpane.setCenter(pane);
   
   GridPane bottom = new GridPane();

   Label label2 = new Label("Option selected:");
   label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
   bottom.add(label2, 0, 0);
   TextField textField = new TextField();
   bottom.add(textField, 1, 0);
   Button button1 = new Button("leave");//add a button for paying
  
    button1.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
     primaryStage.close();
     timer.cancel();
     try{new AccountGUI().start(new Stage());}
     catch(Exception ex6){}
     }
    });
   bottom.add(button1, 0, 1);
   
   
   Button button2 = new Button("confirm");//add a button to confirm the input
   
   button2.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
       int opt =Integer.parseInt(textField.getText().trim());
       switch(opt){
       case 1:
       primaryStage.close();
       timer.cancel();
       try{new FishListGUI().start(new Stage());}
       catch(Exception ex1){}
       break;
       case 2:
       primaryStage.close();
       timer.cancel();
       try{new MeatListGUI().start(new Stage());}
       catch(Exception ex2){}
       break;
       case 3:
       primaryStage.close();
       timer.cancel();
       try{new RiceListGUI().start(new Stage());}
       catch(Exception ex3){}
       break;
       case 4:
       primaryStage.close();
       timer.cancel();
       try{new NoodleListGUI().start(new Stage());}
       catch(Exception ex4){}
       break;
       case 5:
       primaryStage.close();
       timer.cancel();
       try{new DrinkListGUI().start(new Stage());}
       catch(Exception ex5){}
       break;//different inputs to open different GUI
       default:
       Label error = new Label("Error, choice must between: 1 to 5");
       error.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
       pane.add(error, 1, 5);
       break;//input checking
        }
     }
    });
   bottom.add(button2, 1, 1);

    Label label5 = new Label("30s later entering sleeping mode");
    pane.add(label5, 1, 6);
   
   Button button3 = new Button("serving");//ask for serving and telling jokes
   button3.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
      primaryStage.close();
      timer.cancel();
      try{new JokeGUI().start(new Stage());}
      catch(Exception ex7){}
       
      }
    });
     bottom.add(button3, 2, 1);
     bottom.setHgap(250);

   borderpane.setBottom(bottom);
   
   Scene scene = new Scene(borderpane);
   primaryStage.setTitle("Main order");
   primaryStage.setScene(scene);
   primaryStage.show();
   
   Timer timer = new Timer();
   this.timer = timer;
   timer.schedule(new TimerTask(){
   int t = 30;
   public void run(){
   t--;
   Platform.runLater(new Runnable(){
   @Override
   public void run(){
   label5.setText(t + "s later entering sleeping mode");
   }
   });
   if(t < 0){
   Platform.runLater(new Runnable(){
   @Override
   public void run(){
   primaryStage.close();
   try{new SleepingGUI().start(new Stage());}
       catch(Exception ex5){}
   }
   });
   timer.cancel();
    }
   }
  }, 0, 1000);//enter sleeping mode when there is no operation in 30 seconds
   
  
  }
}

