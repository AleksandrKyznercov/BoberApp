import DAO.ConnectionPool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/MainForm.fxml"));
        //primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);

        ConnectionPool.getInstance();
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
