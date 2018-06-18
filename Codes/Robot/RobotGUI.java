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
import java.util.Timer;  
import java.util.TimerTask;
import javafx.application.Platform;

/**
 * A class for showing the Welcome message.
 *
 * @author   Tao Luo
 */
public class RobotGUI extends Application {
  Timer timer;
  public void start(Stage primaryStage) throws Exception {
   GridPane pane = new GridPane();
   pane.setAlignment(Pos.CENTER);
   pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
   pane.setHgap(5.5);
   pane.setVgap(5.5);//build a pane and set the size

   File sourceFile = new File("../../Files/WelcomeMessages.txt");
   int i = 0;
   try(
   Scanner input = new Scanner(sourceFile);
   ){
     while(input.hasNext()){
     String s1 = input.nextLine();
     i++;
     }
    }
   String[] s = new String[i];
   try(
   Scanner input = new Scanner(sourceFile);
   ){
     for(int j = 0; j < i; j++){
     s[j] = input.nextLine();
     }
    }//load the welcome messages from the file
   Label label = new Label(s[(int)(Math.random()*4)]);//randomly select a message to show
   label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
   pane.add(label, 1, 1);
   
   Button button2 = new Button("order");//set a button to enter the main order GUI
   
   button2.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
       primaryStage.close();
       timer.cancel();
       try{new OrderGUI().start(new Stage());}
       catch(Exception ex){}
     }
    });
   pane.add(button2, 1, 2);
   
   Button button1 = new Button("leave");//set a botton for directly leaving
  
    button1.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
       primaryStage.close();
       timer.cancel();
       try{new AccountGUI().start(new Stage());}
       catch(Exception ex5){}
     }
    });
   pane.add(button1, 2, 2);

   Label label5 = new Label("30s later entering sleeping mode");//set a label to show the seconds left to enter sleeping mode
   pane.add(label5, 1, 3);   

   Scene scene = new Scene(pane,800,400 );//set the scene's size and colour
   pane.setStyle("-fx-background-color:#336699;");
   primaryStage.setTitle("Robot Interface");
   primaryStage.setScene(scene);
   primaryStage.show();

    Timer timer = new Timer();//build a timer task to enter sleeping mode
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
       catch(Exception ex6){}
   }
   });
   timer.cancel();
    }
   }
  }, 0, 1000);

  }
}