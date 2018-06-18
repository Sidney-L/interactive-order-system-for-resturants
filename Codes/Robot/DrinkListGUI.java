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
public class DrinkListGUI extends Application {
   Timer timer;
   public void start(Stage primaryStage) throws Exception {
   BorderPane mborderpane = new BorderPane();
   mborderpane.setStyle("-fx-background-color: #8db6cd;");//build a main borderpane and set its colour
   
   Label label1 = new Label("Please select a dish to order:");
   label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
   mborderpane.setTop(label1);//add a label

   File sourceFile = new File("../../Files/DrinkDishes.txt");
   File orderFile = new File ("../../Files/CustomerOrder.txt");
   int i = 0;
   try(
   Scanner input = new Scanner(sourceFile);
   ){
     while(input.hasNext()){
     String s1 = input.nextLine();
     i++;
     }
    }
   
   String[] name = new String[i];
   String[] price = new String[i];
   int[] left = new int[i];
   
   try(
   Scanner input = new Scanner(sourceFile);
   ){
     for(int j = 0; j < i; j++){
     name[j] = input.next();
     price[j] = input.next();
     left[j] = input.nextInt();
     }
    }//read from the storage file to get the dishes information
   
   GridPane pane = new GridPane();
   BorderPane[] borderpane = new BorderPane[6];
   
   borderpane[0] = new BorderPane();
   borderpane[0].setStyle("-fx-background-color: #b4cdcd;");
   borderpane[0].setPrefSize(300,200);
   borderpane[0].setTop(new Label("1"));
   pane.add(borderpane[0], 0, 0);

   borderpane[1] = new BorderPane();
   borderpane[1].setStyle("-fx-background-color: #ff7f24;");
   borderpane[1].setPrefSize(300,200);
   borderpane[1].setTop(new Label("2"));
   pane.add(borderpane[1], 1, 0);

   borderpane[2] = new BorderPane();
   borderpane[2].setStyle("-fx-background-color: #bbffff;");
   borderpane[2].setPrefSize(300,200);
   borderpane[2].setTop(new Label("3"));
   pane.add(borderpane[2], 2, 0);

   
   borderpane[3] = new BorderPane();
   borderpane[3].setStyle("-fx-background-color: #fff68f;");
   borderpane[3].setPrefSize(300,200);
   borderpane[3].setTop(new Label("4"));
   pane.add(borderpane[3], 0, 1);

   borderpane[4] = new BorderPane();
   borderpane[4].setStyle("-fx-background-color: #ff82ab;");
   borderpane[4].setPrefSize(300,200);
   borderpane[4].setTop(new Label("5"));
   pane.add(borderpane[4], 1, 1);
   
   borderpane[5] = new BorderPane();
   borderpane[5].setStyle("-fx-background-color: #90ee90;");
   borderpane[5].setPrefSize(300,200);
   borderpane[5].setTop(new Label("6"));
   pane.add(borderpane[5], 2, 1);//all the colourful rectangles to show the dishes

   Label label5 = new Label("30s later entering sleeping mode");
   pane.add(label5, 1, 2);//add a label to remind customer
   
   Label temp;
   for(int n =0; n<i; n++){
   temp = new Label(name[n] + "\n" + price[n]);
   temp.setFont(Font.font("ºÚÌå", FontWeight.BOLD, FontPosture.ITALIC, 15));
   borderpane[n].setCenter(temp);
   }//labels showing the dishes information

   mborderpane.setCenter(pane);
   GridPane bottom = new GridPane();   

   Label label2 = new Label("Option selected:");
   label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
   bottom.add(label2, 0, 0);//add a label

   TextField textField = new TextField();
   bottom.add(textField, 1, 0);//add a text field to hold the input

   Button button2 = new Button("confirm");
   button2.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
     int opt = Integer.parseInt(textField.getText().trim());
     
     int k = 0;
     try{Scanner input = new Scanner(sourceFile);
          while(input.hasNext()){
          String s1 = input.nextLine();
          k++;
      }
     input.close();
     }
     catch(Exception ex){}
    
     
    
     if (opt < 1 ||  opt > k || left[opt-1] < 1){
     Label label = new Label("Choice not available, please select again:");
     label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
     mborderpane.getChildren().remove(label1);
     mborderpane.setTop(label);
      }//make sure the input is valid and the dish is available
     else{
     left[opt-1]--;
     
     try{PrintWriter output = new PrintWriter(sourceFile);
     for( int j = 0; j < k; j++){
       output.print(name[j] + " ");
       output.print(price[j] + " ");
       output.println(left[j]);
     }
     output.close();
     } 
     catch(Exception ex1){}//update the information in the storage file
     
     
     int l = 0;
     try{Scanner input = new Scanner(orderFile);
     while(input.hasNext()){
        String s1 = input.nextLine();
        l++;
     }
     input.close();
     }
     catch(Exception ex2){}
   
     String[] oname = new String[l];
     String[] oprice = new String[l];

   
     try{Scanner input = new Scanner(orderFile);
     for(int j = 0; j < l; j++){
     oname[j] = input.next();
     oprice[j] = input.next();
     }
     input.close();
     }
     catch(Exception ex3){}

     try{PrintWriter output = new PrintWriter(orderFile);
     for( int j = 0; j < l; j++){
       output.print(oname[j] + " ");
       output.println(oprice[j]);
     }
       output.print(name[opt-1] + " ");
       output.println(price[opt-1]);
     output.close();
     } 
     catch(Exception ex4){}//update the customer order file
     
     primaryStage.close();
     timer.cancel();
     try{new OrderGUI().start(new Stage());}
     catch(Exception ex5){}
     
      }
  


      }
    });
     bottom.add(button2, 1, 1);

     Button button3 = new Button("back");
   button3.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
       primaryStage.close();
       timer.cancel();
       try{new OrderGUI().start(new Stage());}
       catch(Exception ex6){}
       
      }
    });
     bottom.add(button3, 0, 1);//add a button for returning to the main order
     bottom.setHgap(250);
     mborderpane.setBottom(bottom);
       
     Scene scene = new Scene(mborderpane);
     primaryStage.setTitle("Drinks");
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