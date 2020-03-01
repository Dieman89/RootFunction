package rootfunction;

//IMPORTS
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//IMPORTS END

public class Controller implements EventHandler, Initializable {
 
    //FXML VAR
@FXML private TextArea Result; @FXML private ComboBox<String> comboFunction; @FXML private ComboBox<String> comboPre; @FXML private Button btnNewton; @FXML private Button btnSecant; @FXML private Button btnClose;
@FXML private RadioButton rbPre; @FXML private RadioButton rbUser; @FXML private TextField txtPoint;  @FXML private TextField txtSecant; @FXML private TextField txtResult; 
@FXML private ComboBox comboDecimal; @FXML private Button btnBisection; @FXML private Button btnFalse; @FXML private AnchorPane graphPane; @FXML private TableView resultTable;
@FXML private TableColumn x0; @FXML private TableColumn x1; @FXML private TableColumn x2;
@FXML private TableColumn fx0; @FXML private TableColumn fx1; @FXML private TableColumn fx2;
    //FXML VAR END

//LIST OF FUNCTIONS IN ARRAY
List<String> f1InitValues = Arrays.asList("x = -1","x = 0.5","x=0.50001","x=0.49999");
List<String> f2InitValues = Arrays.asList("x = 0","x = -1","x = -1.0e-10");
List<String> f3InitValues = Arrays.asList("x = 1.09","x = 1.1","x = 1.11");


//VARS
private static int resultCount = 0;
private static int decimalPlace = 0; 
private static String zero = "";
static DecimalFormat df = new DecimalFormat("#");
Group root;
LineChart linechart;
XYChart.Series series;



    @Override
    public void initialize(URL url, ResourceBundle rbs) {
        comboFunction.getItems().addAll("f(x) = x-x^2","f(x) = ln(x+1) + 1","f(x) = e^x - 3x"); //ADD ITEMS IN COMBO
        comboFunction.getSelectionModel().select(0);  // SELECT FIRST ITEM

        comboPre.getItems().addAll(f1InitValues);  // ADD ITEMS FROM ARRAY
        comboPre.getSelectionModel().select(0);    // SELECT FIRST ITEM

        comboDecimal.getItems().addAll("0", "1", "2", "3"); // ADD DECIMAL POINTS IN COMBO
        comboDecimal.getSelectionModel().select(3);  // SELECT 2

        df.setRoundingMode(RoundingMode.CEILING); // DECIMAL POINT ROUND
        df.setMaximumFractionDigits(3); // MAXIMUM DECIMAL POINT
        
        // VALUES FOR LIST
        x0.setCellValueFactory(new PropertyValueFactory<>("x0"));
        x1.setCellValueFactory(new PropertyValueFactory<>("x1"));
        x2.setCellValueFactory(new PropertyValueFactory<>("x2"));
        fx0.setCellValueFactory(new PropertyValueFactory<>("fx0"));
        fx1.setCellValueFactory(new PropertyValueFactory<>("fx1"));
        fx2.setCellValueFactory(new PropertyValueFactory<>("fx2"));
        
        //RANGE FOR GRAPH
        NumberAxis xAxis = new NumberAxis(-10, 10, 1); 
            xAxis.setLabel("X"); 

            //Defining the y axis   
            NumberAxis yAxis = new NumberAxis(-10, 10, 1); 
            yAxis.setLabel("F(X)");
            
            //Creating the line chart 
            linechart = new LineChart(xAxis, yAxis);
            linechart.setPrefSize(700 , 500);

            //Prepare XYChart.Series objects by setting data 
            series = new XYChart.Series(); 
            series.setName("x vs f(x)"); 

            

            //Setting the data to Line chart    
            linechart.getData().add(series);        

            //Creating a Group object  
            root = new Group(linechart); 
            
            // add group to chart [show data to chart] (just defined previously)
            graphPane.getChildren().add(root);
            
            // STYLE WITH CSS
            graphPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            // get function number selected from dropdown
          int fNo = comboFunction.getSelectionModel().getSelectedIndex() + 1;
           
            // loop to find (x,y) points for different x values as i here
          for(double i=-10;i<20;i+=1){
              try{
                  series.getData().add(new XYChart.Data(i, Function.funcVal(i,fNo,"f")));
              }catch(Exception exc){}
          }
          
          
          
          
   ///// Textbox Filters       
//txtSecant.textProperty().addListener((observable, oldValue, newValue) -> {
   //if(!newValue.matches("^(-?)\\d{0,7}([\\.]\\d{0,4})?")){

    //    txtSecant.setText(oldValue);
  //  }
    
//})  OLD

//FILTER EDITBOXES
txtSecant.textProperty().addListener(new MyChangeListener(txtSecant));
txtPoint.textProperty().addListener(new MyChangeListener(txtPoint));
//FILTER EDITBOXES


//FOCUS SECANT
txtSecant.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
 if (newPropertyValue) // 
 {
     System.out.println("Textfield on focus");
     txtSecant.setText(""); // delete whatever inside the editbox on click (focus)
   }
 else
  {
  if (txtSecant.getText() == null || txtSecant.getText().trim().isEmpty()) { // if there is nothing out of focus
     txtSecant.setText("0"); // set text as 0
    }     
}
     });
///FOCUS SECANT

 //// FOCUS POINT
txtPoint.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
 if (newPropertyValue)
 {
     System.out.println("Textfield on focus"); 
     txtSecant.setText(""); // delete whatever inside the editbox on click (focus)
   }
 else
  {
  if (txtSecant.getText() == null || txtSecant.getText().trim().isEmpty()) { // if there is nothing out of focus
     txtSecant.setText("0"); // set text as 0
    }     
}
     });
/// FOCUS POINT
}
    // Used to change values when changing function in the comboBoxes
   public void ChangeFunction() {
       // event
       comboFunction.setOnAction((ActionEvent e) -> {
           // get index
          int index = comboFunction.getSelectionModel().getSelectedIndex();
          // clear previous data
          comboPre.getItems().clear();
          // switch and cases for new values
           switch (index) {
               case 0:
                   comboPre.getItems().addAll(f1InitValues); // n1
                   break;
               case 1:
                   comboPre.getItems().addAll(f2InitValues); // n2
                   break;
               case 2:
                   comboPre.getItems().addAll(f3InitValues); //n3
                   break;
               default:
                   break;
           }
          comboPre.getSelectionModel().select(0);
          
          series.getData().clear();
          int fNo = comboFunction.getSelectionModel().getSelectedIndex() + 1;
          for(double i=-10;i<20;i+=1){
              try{
                  series.getData().add(new XYChart.Data(i, Function.funcVal(i,fNo,"f")));
              }catch(Exception exc){}
          }
       });
   }
   
   // to let the radio work properly
   public void ChangeRadio() {
       txtPoint.setDisable(false);  // To fix first click
       rbPre.setOnAction((ActionEvent e) -> { // when clicking on Preselected Radio
       comboPre.setDisable(false); // disable the combobox for Preselected
       txtPoint.setDisable(true); // enable Starting Point Editbox
       rbUser.setSelected(false); // Disable User Rombo
       });
       rbUser.setOnAction((ActionEvent e) -> { // when clicking on User Radio
       comboPre.setDisable(true); // enable combobox for Preselected
       txtPoint.setDisable(false); // disable Starting Point (for user)
       rbPre.setSelected(false); // deselect Preselected rombo
       });
   }
   
   public void ChangeDecimal() {
       comboDecimal.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent e) {
           decimalPlace = Integer.parseInt((String) comboDecimal.getSelectionModel().getSelectedItem());
           System.out.println(decimalPlace);
           
           zero = "";
           for (int i=0;i<decimalPlace;++i) {
           zero += "#";
            }
           //df = new DecimalFormat("#." + zero);
           
            df.setMaximumFractionDigits(decimalPlace); //
      }
   });
   }

   public void Newton() { 
        
       //event: when clicking the button
        btnNewton.setOnAction((ActionEvent e) -> {
          int fNo = comboFunction.getSelectionModel().getSelectedIndex() + 1;  // get function 
          
            // get initial value(x0) from dropdown for newton - split and get [1]
           double initialVal = Double.parseDouble(comboPre.getSelectionModel().getSelectedItem().split("=")[1]);
           
           if(rbUser.isSelected()){
              initialVal = Double.parseDouble(txtPoint.getText());
          }
          
           double sqroot = 0;
           try{
               
               // get square root value after iterations
               sqroot = NewtonRaphson.NewtonRaphson(initialVal, fNo);
               // increament how many times calculation occurs with [Newton Raphson, Secant, Bisection & False Position Methods]
                ++resultCount;
                //get text as value from textarea
                String txt = Result.getText();
                //set result to textarea
                Result.setText(txt);
                //set iteration data to data-table
                setResultToTableForLinkList(NewtonRaphson.list);
           }catch(Exception exc){
               //for any kind of exception handling
                showExceptionMessage(sqroot,exc);
           }
        
        });
   }
   
   public void Secant() { 
        
       // when clicking the Secant button
        btnSecant.setOnAction((ActionEvent e) -> {
            
            int fNo = comboFunction.getSelectionModel().getSelectedIndex() + 1;  // get function
            
            // get initial value(x0) from dropdown for secant - split and get [1]
            double initialVal = Double.parseDouble(comboPre.getSelectionModel().getSelectedItem().split("=")[1]);
            
            // if user is selected then
            if(rbUser.isSelected()){
                //change initial value to the one written by him
                initialVal = Double.parseDouble(txtPoint.getText());
            }
             // get second value(x1) from dropdown for secant
            double secondInitial = Double.parseDouble(txtSecant.getText());
            
            // define var
            double sqroot = 0;
            try{
                // get square root value after iterations
                sqroot = Secant.Secant(initialVal, secondInitial, fNo);
              // increament how many times calculation occurs with [Newton Raphson, Secant, Bisection & False Position Methods]
                ++resultCount;
                // store in a var
                String txt = Result.getText();
                 // set it in the editbox
                Result.setText(txt);
                
                setResultToTable(Secant.resultArr);
            }catch(Exception exc){
                showExceptionMessage(sqroot,exc);
            }

        });
    }
  
   public void Bisection() { 
        
       // when clicking the Bisection Button
        btnBisection.setOnAction((ActionEvent e) -> {
            int fNo = comboFunction.getSelectionModel().getSelectedIndex() + 1; // get function
             // get initial value(x0) from dropdown for secant - split and get [1]
            double initialVal = Double.parseDouble(comboPre.getSelectionModel().getSelectedItem().split("=")[1]);
            // if user is selected then
            if(rbUser.isSelected()){
                //change initial value to the one written by him
                initialVal = Double.parseDouble(txtPoint.getText());
            }
             // get second value(x1) from dropdown for Bisection
            double secondInitial = Double.parseDouble(txtSecant.getText());
             // define var            
            double sqroot = 0;
            try{
                // get square root value after iterations
                sqroot = BiSection.BiSection(initialVal, secondInitial, fNo);
                 // increament how many times calculation occurs with [Newton Raphson, Secant, Bisection & False Position Methods]
                ++resultCount;
                // store in a var
                String txt = Result.getText();
                // set it in the editbox
                Result.setText(txt);
                txtResult.setText(Double.toString(sqroot)); 
                setResultToTable(BiSection.resultArr);
            }catch(Exception exc){
                showExceptionMessage(sqroot,exc);
            }
        });
    }
   
      public void falsePosition() {
        
        btnFalse.setOnAction((ActionEvent e) -> { 
            int fNo = comboFunction.getSelectionModel().getSelectedIndex() + 1;
            
            double initialVal = Double.parseDouble(comboPre.getSelectionModel().getSelectedItem().split("=")[1]);
            if(rbUser.isSelected()){
                initialVal = Double.parseDouble(txtPoint.getText());
            }
            
            double secondInitial = Double.parseDouble(txtSecant.getText());
                        
            double sqroot = 0;
            try{
                sqroot = FalsePosition.FalsePosition(initialVal, secondInitial, fNo);  
                ++resultCount;
                String txt = Result.getText();
                Result.setText(txt);
                txtResult.setText(Double.toString(sqroot)); 
                setResultToTableForLinkList(FalsePosition.list);
            }catch(Exception exc){
                showExceptionMessage(sqroot,exc);
            }

        });
    }
    //TABLE RESULTS INSERT [FOR ARRAY]
    private void setResultToTable(SqrootArray resultArr){
        resultTable.getItems().clear(); //clear table before insert new values
        //loop based on length of result array 
        for(int i = 0; i < resultArr.xVals.length; i++){
            // add each iteration values to data-table as row
            resultTable.getItems().add(new Result(resultArr.xVals[i],resultArr.fxVals[i],resultArr.x1Vals[i],resultArr.fx1Vals[i],resultArr.x2Vals[i],resultArr.fx2Vals[i]));
        }
    }
    
    //TABLE RESULTS INSERT [FOR LINK-LIST]
    private void setResultToTableForLinkList(SqrootLinkList result){
        //clear table-data
        resultTable.getItems().clear();
        //get head node of link-list
        SqrootResult s = result.head;
        //if head exists
        if(s!=null){
            // until next node exists
            while(s.next != null){
                // set data to data-table
                resultTable.getItems().add(new Result(s.x,s.fx,s.x1,s.fx1,s.x2,s.fx2)); 
                // go to next node
                s = s.next;
            }
          
            resultTable.getItems().add(new Result(s.x,s.fx,s.x1,s.fx1,s.x2,s.fx2));
        }
    }
      
    private void showExceptionMessage(Double sqroot,Exception exc){
        //clear data-table , if exception occurs
        resultTable.getItems().clear();
        //define variable for message
        String msg = "";
        //check exception message type to determine which message to show
        switch(exc.getMessage()){
            case "Infinite":
                msg = "Infinite"; //set message
                txtSecant.setText("0"); //set result textarea value as zero
                break;
            case "notpossible":
                msg = "Solution not possible!";
                txtSecant.setText("0");
                break;
            case "NaN":
                msg = "NaN";
                txtSecant.setText("0");
                break;
            case "itsroot":
                msg = "The starting point " + "is already a root";
                txtSecant.setText("0");
                break;
            case "fxis0":
                msg = "Since the derivate is 0, the method will now stop.";
                txtSecant.setText("0");
                break;
            default:
                msg = "Functional value at given value couldn't be evaluated."; // default message
                txtSecant.setText("0"); //set zero as result value

        }
        //set result to textarea to show result as zero
        txtResult.setText(Double.toString(sqroot));  
        // new alert object
        Alert alert = new Alert(AlertType.ERROR, msg,ButtonType.CLOSE);
        //show alert
        alert.showAndWait();
    }
        // Code for Close button
        public void closeApp() {
        btnClose.setOnAction((ActionEvent e) -> {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
        });
    }
    
     
    @Override
    public void handle(Event event) {
        System.out.println("$(method_name) not coded yet.");
    }
}

class MyChangeListener implements ChangeListener<String> {

    private final TextField myTextField;

    public MyChangeListener(TextField myTextField) {
        this.myTextField = myTextField;
    }
       // RegEx code for filtering editboxes. In this case only integers and decimal numbers can be written and words cannot be typed in the boxes.
       // Also there is a set of rules such as only a "-" before any number can be typed and only a "." can be typed as well.
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(!newValue.matches("^(-?)\\d{0,7}([\\.]\\d{0,4})?")){

            myTextField.setText(oldValue);
        }
    }

}


