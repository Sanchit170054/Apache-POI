package Effort_Log;

import javafx.application.Application; 
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*******
 * @author Sanchit & Pratik
 *
 * @version 1.2	 7:11 pm          2018-04-20
 * The mainline of a JavaFX-based GUI implementation of a Effort Log Project
 */

public class ExcelSheet extends Application {

	public final static double WINDOW_WIDTH = 600;
	public final static double WINDOW_HEIGHT = 380;
	
	public UserInterface theGUI;

	public ExcelSheet(Pane theRoot) {
		// TODO Auto-generated constructor stub
	}

	/**********
	 * This is the start method that is called once the application has been loaded into memory and is 
	 * ready to get to work.
	 * 
	 
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		
		// Label the stage (a window)............
		
		theStage.setTitle("Sanchit & Pratik's:   Project 3");	
		 new Pane(new Group());
			
		// Create a pane within the window..............
		 
		 Pane theRoot = new Pane();						
		ListView<String> lv= new ListView<>();
		Pane root = new Pane(lv);
		
		// Create the Graphical User Interface................
		
		theGUI = new UserInterface(theRoot);				
		
		// Create the scene....................
		
		Scene theScene = new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT);	
		  Scene sc= new Scene(root, 700, 600);
	   
		// Set the scene on the stage................
		  
		  theStage.setScene(sc);						
		((Pane) sc.getRoot()).getChildren().add(theScene.getRoot());
		
		// Show the stage to the user...................
		
		theStage.show();										
		
		// When the stage is shown to the user, the pane within the window is visible.  This means that the
		// labels, fields, and buttons of the Graphical User Interface (GUI) are visible//
	}

	/*******************************************************************************************************
	 * This is the method that launches the JavaFX application
	 * 
	 */
	public static void main(String[] args) {						// This method may not be required
		launch(args);											// for all JavaFX applications using
	}															// other IDEs.
}
