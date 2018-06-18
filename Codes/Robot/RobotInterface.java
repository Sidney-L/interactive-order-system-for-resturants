import javafx.stage.Stage;
import javafx.application.Application;
/**
 * This is the class containing the main method.
 *
 * @author   Tao Luo
 */
public class RobotInterface extends Application{
  public void start(Stage priaryStage) throws Exception{
    new RobotGUI().start(new Stage());//open the GUI
   }
}



















