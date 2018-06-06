package Effort_Log;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
* @author  Sanchit & Pratik
 
*  @version 1.2	 7:24pm          2018-04-20
* The JavaFX-based GUI for the implementation of a effort Log project
*
*/

public class UserInterface {
   
	// Application values required by the Userinterface.............
   
	private Label lbl_Title = new Label("Effort Log Analysis Team Version");
   private Label lbl_SrcDirectory = new Label("Source Directory: "); 
   private TextField txt_SrcDirectory = new TextField();  
   private Button button_Browse = new Button("Browse");
   private Button bttn_AR = new Button("Generate Analysis Report");
   private Button bttn_SR = new Button("Generate Summary Report");
   private Button bttn_Q = new Button("Quit");
   CheckBox box_Label= new CheckBox("Select All");
 
   VBox VBox = new VBox(box_Label);
   Stage primaryStage;
   String absolutePath ="";
   
   final ObservableList<FileList> data = FXCollections.observableArrayList();
      final ArrayList<String> names = new ArrayList<String>();
      ListView<FileList> LV = new ListView<FileList>();
   
 // Contain all the names of the selected files...............
   
   String str[]; 
			
  
  public UserInterface(Pane theRoot) {
  VBox.setLayoutX(150);
  VBox.setLayoutY(125);                        
  LV.setLayoutX(200);
  LV.setLayoutY(150);
  LV.setMaxSize(700, 300);
  box_Label.selectedProperty().addListener(new ChangeListener<Boolean>() {
 
  public void changed(ObservableValue<? extends Boolean> ObservableValue, Boolean Sanchit, Boolean sanchit) {
        if(sanchit){  
          for (FileList fList : data)                                                                                              
              fList.setSelected(true);
                   }
         else{
            for (FileList fList : data)
            fList.setSelected(false);                                                                                             
                }
              }
           });
         for (FileList file : data) {
         InvalidationListener listener = null;
         file.selectedProperty().addListener(listener );
              }

         LV.setItems(data);
         Callback<FileList, ObservableValue<Boolean>> getProperty = new Callback<FileList, ObservableValue<Boolean>>() {
    
         public BooleanProperty call(FileList layer) {
             return layer.selectedProperty();
                }
             };
             Callback<ListView<FileList>, ListCell<FileList>> forListView = CheckBoxListCell.forListView(getProperty);
      
         LV.setCellFactory(forListView);
        
      //Label the title of the project on the window pane..................
         
         setupLabelUI(lbl_Title, "Arial", 24, ExcelSheet.WINDOW_WIDTH, Pos.CENTER, 0, 10);

      // Label the source directory on the window pane......................
         
         setupLabelUI(lbl_SrcDirectory, "Arial", 22, ExcelSheet.WINDOW_WIDTH, Pos.BASELINE_LEFT, 10, 80);

      // label the text source directory on the window pane.................................
         
         setupTextUI(txt_SrcDirectory, "Arial", 17, 300, Pos.BASELINE_LEFT, 180, 80, true);
         txt_SrcDirectory.textProperty().addListener((observable, oldValue, newValue) -> { });

      // Establish the "Browse Button" and specify its position....................
         
         setupButtonUI(button_Browse, "Symbol", 18, 21, Pos.BASELINE_LEFT, 500, 80);
         button_Browse.setOnAction(new EventHandler<ActionEvent>() {

          public void handle(ActionEvent event) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(primaryStage);
            
                if(selectedDirectory != null){
                     txt_SrcDirectory.setText(selectedDirectory.getAbsolutePath());
                     absolutePath = selectedDirectory.getAbsolutePath();
                   }
                
           List <String> fileNamesList = new ArrayList <String> ();
               try {
                  Files.newDirectoryStream(Paths.get(absolutePath),
                   path -> path.toString().endsWith(".xlsx")).forEach(filePath -> fileNamesList.add(filePath.toString()));
                   }
               catch (IOException e) {
                       e.printStackTrace();
                        }

               for (String name : fileNamesList) {
                   String facultyName = name.substring(absolutePath.length()+1);
                      data.add(new FileList(false, facultyName.substring(0, facultyName.indexOf("v2"))));
                           }
                    }
              });
          
       //Establish the Button for the "Analysis Report" on the window pane and specify its position.................
          
         setupButtonUI(bttn_AR, "Symbol", 20, 20, Pos.BASELINE_LEFT, 10, ExcelSheet.WINDOW_HEIGHT- -100);
          bttn_AR.setOnAction(new EventHandler<ActionEvent>() {
          
           public void handle(ActionEvent arg0) {
           File File = new File("D:\\Semester - 2\\App Development\\Zip files\\Effort Log Analysis\\sanchit.xlsx");
           
           if (File.exists())
             {
              if (Desktop.isDesktopSupported())
               {
          
           try {
              Desktop.getDesktop().open(File);
               }
           catch (IOException e)
                 {
                  e.printStackTrace();
                      }
                    }
                  }
                  else
                       {
               System.out.println("File is not exists!");
                    }
                  }
               });
 
          //Establish the Button for the "Summary Report" on the window pane and specify its position..............
            
            setupButtonUI(bttn_SR, "Symbol", 20, 20, Pos.BASELINE_LEFT,ExcelSheet.WINDOW_WIDTH-230, ExcelSheet.WINDOW_HEIGHT- -100);
            bttn_SR.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent arg0) {
            File File = new File("D:\\Semester - 2\\App Development\\Zip files\\Effort Log Analysis\\Summary Sheet.xlsx");
             
             if (File.exists())
                {
               	 if (Desktop.isDesktopSupported())
                    {
     
             try {
                Desktop.getDesktop().open(File);
               }
              catch (IOException e)
                   {
                    e.printStackTrace();
                        }
                      }
                    }
                    else
                         {
                 System.out.println("File is not exists!");
                          }
                       }
                   });
   
           // Establish the button for the "Quit" on the window pane and specify its position...........
              
              setupButtonUI(bttn_Q, "Symbol", 20, 20, Pos.BASELINE_LEFT, ExcelSheet.WINDOW_WIDTH -50, ExcelSheet.WINDOW_HEIGHT- -160);
              bttn_Q.setOnAction((event) -> { System.exit(0); });

           // Place all of the essential GUI elements into the Pane............
              
              theRoot.getChildren().addAll(lbl_Title, lbl_SrcDirectory, txt_SrcDirectory, button_Browse, bttn_AR, bttn_SR, bttn_Q, LV, VBox);    

                   }
           
          // Private local method to initialize the standard fields for a label......................
                       private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
                           l.setFont(Font.font(ff, f));
                           l.setMinWidth(w);
                           l.setAlignment(p);
                           l.setLayoutX(x);
                           l.setLayoutY(y);                          
              }

           //Private local method to initialize the standard fields for a text field....................
                        private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
                           t.setFont(Font.font(ff, f));
                           t.setMinWidth(w);
                           t.setMaxWidth(w);
                           t.setAlignment(p);
                           t.setLayoutX(x);
                           t.setLayoutY(y);                         
                           t.setEditable(e);
              }

          //Private local method to initialize the standard fields for a button.............
                          private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
                           b.setFont(Font.font(ff, f));
                           b.setMinWidth(w);
                           b.setAlignment(p);
                           b.setLayoutX(x);
                           b.setLayoutY(y);                        
              }
          
          // Create a new class name as "FileList" ...............
             public class FileList {
               private final SimpleBooleanProperty selected;
               private final SimpleStringProperty name;
               public FileList(boolean id, String name)
               {
                     this.selected = new SimpleBooleanProperty(id);
                     this.name = new SimpleStringProperty(name);
                           }
               public boolean getSelected() 
                  {
                     return selected.get();
                  }
        
               public void setSelected(boolean selected)
                  {
                      this.selected.set(selected);
                   }
               
               public String getName()
                   {
                       return name.get();
                   }
               public void setName(String fName) 
                   {
                       name.set(fName);
                   }
                           
                public SimpleBooleanProperty selectedProperty() 
                   {
                       return selected;
                    }

                public String toString()
                    {
                        return getName();
                    }
          }
    }
