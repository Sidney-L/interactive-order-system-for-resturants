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
import java.util.Timer;  
import java.util.TimerTask;
import javafx.application.Platform;

/**
 * A class for printing the farewell message and total price.
 *
 * @author   Tao Luo
 */
public class AccountGUI extends Application {
    Timer timer;
    public void start(Stage primaryStage) throws Exception {
    int total = 0;
    
    File orderFile = new File ("../../Files/CustomerOrder.txt");
    File messageFile = new File("../../Files/FarewellMessage.txt");
    VBox vBox = new VBox(10);
    vBox.setAlignment(Pos.CENTER);
    vBox.setStyle("-fx-background-color: #8db6cd;");//build a pane and set the size
    
    Label label1= new Label("Dishes Ordered");
    label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 35));
    vBox.getChildren().add(label1);// add a label
    int l = 0;
     try{Scanner input = new Scanner(orderFile);
     while(input.hasNext()){
        String s1 = input.nextLine();
        Label label = new Label(s1);
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
        vBox.getChildren().add(label);
        l++;
     }
     input.close();
     }
     catch(Exception ex1){}
   
     String[] name = new String[l];
     String[] price = new String[l];

   
     try{Scanner input = new Scanner(orderFile);
     for(int j = 0; j < l; j++){
     name[j] = input.next();
     price[j] = input.next();
     }
     input.close();
     }
     catch(Exception ex2){}//read data from the CustomerOrder file

    Label label2= new Label("Total Price:");
    label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 35));
    vBox.getChildren().add(label2);//add a label
    
    for(String s: price){
    total += Integer.parseInt(s.split("R")[0]);
    }//compute the total price
    
    Label label3 = new Label(total + "RMB");
    label3.setFont(Font.font("Cooper Black", FontWeight.BOLD, FontPosture.ITALIC, 20));
    vBox.getChildren().add(label3);//add another label
    
    try{PrintWriter output = new PrintWriter(orderFile);
     output.close();
     }
     catch(Exception ex3){}//clear the information in the CustomerOrder file which stores the dishes ordered
    
    int i = 0;
    try(
    Scanner input = new Scanner(messageFile);
    ){
     while(input.hasNext()){
     String s1 = input.nextLine();
     i++;
     }
    }
   String[] s = new String[i];
   try(
   Scanner input = new Scanner(messageFile);
   ){
     for(int j = 0; j < i; j++){
     s[j] = input.nextLine();
     }
    }
   Label label4 = new Label(s[(int)(Math.random()*4)]);
   label4.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
   vBox.getChildren().add(label4);//randomly print a farewell message

   
     Button button1 = new Button("new order");//add a button to 
     
     button1.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
       primaryStage.close();
       timer.cancel();
       try{new OrderGUI().start(new Stage());}
       catch(Exception ex4){}
     }
    });
     vBox.getChildren().add(button1);
     Label label5 = new Label("30s later entering sleeping mode");
     vBox.getChildren().add(label5);//add a label to count down
   


     Scene scene = new Scene(vBox);
     primaryStage.setTitle("Customer Order");
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