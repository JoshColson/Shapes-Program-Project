import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

	public class Shapes extends Application
	{	
		ArrayList <Shape> shapesList = new ArrayList<Shape>();
		String[][] shapesColour = new String[3][2];
		String currentColour;
		String currentShape;
		String shapeInput;
		Rectangle rectangle = new Rectangle();
		Circle circle = new Circle();
		Polyline octagon = new Polyline();

		public boolean createShape(String input)
		{
			switch (input)
			{
				case "rectangle":
					if (shapesColour[0][0] == null)
					{
						rectangle.setWidth(250);
						rectangle.setHeight(150);
						rectangle.setFill(Color.WHITE);
						rectangle.setStroke(Color.BLACK);
						shapesColour[0][0]= "White";
						shapesColour[0][1]= "Rectangle";
						shapesList.add(rectangle);
					}
					else
					{
						shapesList.add(rectangle);
					}
					return true;
				case "circle":
					if (shapesColour[1][0] == null)
					{
						circle.setRadius(100);
						circle.setFill(Color.WHITE);
						circle.setStroke(Color.BLACK);
						shapesColour[1][0]= "White";
						shapesColour[1][1]= "Circle";
						shapesList.add(circle);
					}
					else 
					{
						shapesList.add(circle);
					}
					return true;
				case "octagon":
					if (shapesColour[2][0] == null)
					{
						octagon.getPoints().addAll(
								165.0, 45.0,
								165.0, 135.0,
								105.0, 195.0,
								15.0, 195.0,
								-45.0, 135.0,
								-45.0, 45.0,
								15.0, -15.0,
								105.0, -15.0,
								165.0, 45.0); //Coordinates for octagon, which does not exist as a preset
						octagon.setFill(Color.WHITE);
						octagon.setStroke(Color.BLACK);
						shapesColour[2][0]= "White";
						shapesColour[2][1]= "Octagon";
						shapesList.add(octagon);
					}
					else 
					{
						shapesList.add(octagon);
					}
					return true;
				default:
					return false;
			}
		}

		public Paint painter(String input)
		{
			Color paint = Color.WHITE;
			switch (input)
			{
				case "blue":
					paint = Color.BLUE;
					break;
				case "green":
					paint = Color.GREEN;
					break;
				case "yellow":
					paint = Color.YELLOW;
					break;
				default:
					paint = null;
					break;				
			}
			return paint;
		}
			
//		create a method which creates a shape and colour, call this each time the create button is called, but inside
//		a try catch statement and if theres an error, display the one which already exists.
		
		public void start(Stage stage)
		{
			//Title Text
			Label mainTitle = new Label("Shapes");       
			mainTitle.setFont(Font.font("Helvetica", 30));   
			mainTitle.setAlignment(Pos.CENTER);
			mainTitle.setPadding(new Insets(0,0,40,0));
			mainTitle.setUnderline(true);
			
			Label colourTitle = new Label("Shapes");       
			colourTitle.setFont(Font.font("Helvetica", 30));   
			colourTitle.setAlignment(Pos.CENTER);
			colourTitle.setPadding(new Insets(10,0,0,0)); 
			colourTitle.setUnderline(true);
			
			Label clearTitle = new Label("Shapes");       
			clearTitle.setFont(Font.font("Helvetica", 30));   
			clearTitle.setAlignment(Pos.CENTER);
			clearTitle.setPadding(new Insets(0,0,40,0));
			clearTitle.setUnderline(true);
			
			//Intro Text
			Label mainIntro = new Label("To generate a shape, enter its name below\nthen press the 'ENTER' key on the keyboard, or press the 'GO' button"
					+ ".\n\n\nAvailable Options:\n\n- Rectangle\n- Circle\n- Octagon");
			mainIntro.setFont(Font.font("Helvetica", 15));
			mainIntro.setTextAlignment(TextAlignment.CENTER);
			mainIntro.setPadding(new Insets(0,0,30,0));
			
			Label colourIntro = new Label("Type a colour to fill your shape\nthen press "
					+ "the 'ENTER' key on the keyboard, or press the 'GO' button.\n\nAvailable Colours:\n\n- Blue\n- Green\n- Yellow");
			colourIntro.setFont(Font.font("Helvetica", 15));
			colourIntro.setTextAlignment(TextAlignment.CENTER);
			colourIntro.setPadding(new Insets(0,0,10,0));

			//Clear Text
			Label clearText = new Label();
			clearText.setFont(Font.font("Helvetica", 15));
			clearText.setTextAlignment(TextAlignment.CENTER);
			clearText.setAlignment(Pos.CENTER);
			
			//Updated Text
			Label updatedText = new Label("Shape Updated");
			updatedText.setFont(Font.font("Helvetica", 17));
			updatedText.setTextAlignment(TextAlignment.CENTER);
			updatedText.setTextFill(Color.GREEN);
		     //Create an animation to fade the updatedText label
			
			//Shape Error Messages
			Label shapeError1 = new Label("ERROR - NO SHAPE SELECTED - TYPE AN AVAILABLE SHAPE");
			shapeError1.setFont(Font.font("Helvetica", 15));
			shapeError1.setTextFill(Color.RED);
			Label shapeError2 = new Label("ERROR - INCORRECT SHAPE SELECTED\nTYPE AN AVAILABLE SHAPE FROM THE LIST ABOVE");
			shapeError2.setFont(Font.font("Helvetica", 15));
			shapeError2.setTextFill(Color.RED);
			shapeError2.setTextAlignment(TextAlignment.CENTER);	
			
			//Colour Error Messages
			Label colourError1 = new Label("ERROR - NO COLOUR SELECTED - TYPE AN AVAILABLE COLOUR");
			colourError1.setFont(Font.font("Helvetica", 15));
			colourError1.setTextFill(Color.RED);
			Label colourError2 = new Label("ERROR - INCORRECT COLOUR SELECTED\nTYPE AN AVAILABLE COLOUR FROM THE LIST ABOVE");
			colourError2.setFont(Font.font("Helvetica", 15));
			colourError2.setTextFill(Color.RED);
			colourError2.setTextAlignment(TextAlignment.CENTER);
			Label colourError3 = new Label();
			colourError3.setFont(Font.font("Helvetica", 15));
			colourError3.setTextFill(Color.RED);
			colourError3.setTextAlignment(TextAlignment.CENTER);

			//Text Input Fields 
			TextField shapeChoiceField = new TextField();    
			shapeChoiceField.setMinSize(300, 60);
			shapeChoiceField.setFont(Font.font("Helvetica", 19));
			shapeChoiceField.setStyle("-fx-background-color: white;" + "-fx-border-color: #eb6200;" + "-fx-focus-color: none");
			TextField colourChoiceField = new TextField();   
			colourChoiceField.setMinSize(230, 46);
			colourChoiceField.setStyle("-fx-background-color: white;" + "-fx-border-color: #eb6200;" + "-fx-focus-color: none");

			//Initial Go Button
			Button createButton = new Button();         
			createButton.setText("GO"); 	
			createButton.setMinSize(60, 60);
			createButton.setFont(Font.font("Helvetica", 19));
	        createButton.setStyle("-fx-background-color: #E3E3E3;"+"-fx-border-color: #eb6200;" + "-fx-focus-color: none"); 
	        //Event Handler to allow the button to change when the mouse is hovered over it.
			DropShadow shadow = new DropShadow(); // create Dropshadow object for mouseover events
			shadow.setColor(Color.web("rgba(235, 98, 0,1 )"));
	        createButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
	                new EventHandler<MouseEvent>() {
	                  public void handle(MouseEvent e) {
	                    createButton.setEffect(shadow);
	                  }
	                });
	        createButton.addEventHandler(MouseEvent.MOUSE_EXITED,
	                new EventHandler<MouseEvent>() {
	                  public void handle(MouseEvent e) {
	                    createButton.setEffect(null);
	                  }
	                });
	        //Colour Button
			Button colourButton = new Button();         
			colourButton.setText("GO");
			colourButton.setMinSize(46, 46);
			colourButton.setFont(Font.font("Helvetica", 15));
	        colourButton.setStyle("-fx-background-color: #E3E3E3;"+"-fx-border-color: #eb6200;" + "-fx-focus-color: none");  
	        //Event Handler to allow the button to change when the mouse is hovered over it.
	        colourButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
	        	new EventHandler<MouseEvent>() {
                	public void handle(MouseEvent e) {
                		colourButton.setEffect(shadow);
                }
              });
	        colourButton.addEventHandler(MouseEvent.MOUSE_EXITED,
              new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                  colourButton.setEffect(null);
                }
              });
	        
	        //Back Button
			Button backButton = new Button();         
			backButton.setText("BACK"); 
			backButton.setMinSize(46, 46);
			backButton.setFont(Font.font("Helvetica", 15));
	        backButton.setStyle("-fx-background-color: #E3E3E3;"+"-fx-border-color: #eb6200;" + "-fx-focus-color: none");  
	        //Event Handler to allow the button to change when the mouse is hovered over it.
			backButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
	          new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                	backButton.setEffect(shadow);
                }
              });
			backButton.addEventHandler(MouseEvent.MOUSE_EXITED,
              new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                	backButton.setEffect(null);
                }
              });
			
			//Yes Button
			Button yesButton = new Button();         
			yesButton.setText("YES"); 
			yesButton.setMinSize(46, 46);
			yesButton.setFont(Font.font("Helvetica", 15));
			yesButton.setStyle("-fx-background-color: #E3E3E3;"+"-fx-border-color: #eb6200;" + "-fx-focus-color: none");  
	        //Event Handler to allow the button to change when the mouse is hovered over it.
			yesButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
	          new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                	yesButton.setEffect(shadow);
                }
              });
			yesButton.addEventHandler(MouseEvent.MOUSE_EXITED,
              new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                	yesButton.setEffect(null);
                }
              });
			
			//No Button
			Button noButton = new Button();         
			noButton.setText("NO"); 
			noButton.setMinSize(46, 46);
			noButton.setFont(Font.font("Helvetica", 15));
			noButton.setStyle("-fx-background-color: #E3E3E3;"+"-fx-border-color: #eb6200;" + "-fx-focus-color: none");  
	        //Event Handler to allow the button to change when the mouse is hovered over it.
			noButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
	          new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                	noButton.setEffect(shadow);
                }
              });
			noButton.addEventHandler(MouseEvent.MOUSE_EXITED,
              new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                	noButton.setEffect(null);
                }
              });
			
			//Clear Button
			Button clearButton = new Button();
			clearButton.setText("CLEAR COLOURS");
			clearButton.setMinSize(120, 60);
			clearButton.setFont(Font.font("Helvetica", 19));
			clearButton.setStyle("-fx-background-color: #E3E3E3;"+"-fx-border-color: #eb6200;" + "-fx-focus-color: none"); 
	        //Event Handler to allow the button to change when the mouse is hovered over it.
			clearButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
	  	          new EventHandler<MouseEvent>() {
	                  public void handle(MouseEvent e) {
	                	  clearButton.setEffect(shadow);
	                  }
	                });
			clearButton.addEventHandler(MouseEvent.MOUSE_EXITED,
	                new EventHandler<MouseEvent>() {
	                  public void handle(MouseEvent e) {
	                	  clearButton.setEffect(null);
	                  }
	                });	

	        //Three scenes which hold the relevent components for three major state-changes
	
			//mainScene creation and contents
			HBox mainMenuHorizontal = new HBox(20); //Horizontal group of objects to allow for organisation of GUI
			mainMenuHorizontal.setAlignment(Pos.CENTER); 
			mainMenuHorizontal.getChildren().addAll(shapeChoiceField, createButton);
			HBox shapesErrors = new HBox(20);
			shapesErrors.setAlignment(Pos.CENTER);
			VBox mainMenu = new VBox(20);//Vertical group of objects (which holds horizontal group also)
			mainMenu.setAlignment(Pos.CENTER);      
			mainMenu.getChildren().addAll(mainTitle, mainIntro, mainMenuHorizontal, shapesErrors);
	        mainMenu.setStyle("-fx-background-color: #fafcff");//Recolour background
	        Scene mainScene = new Scene(mainMenu, 650, 650);//objects(now within root), followed by the size of window needed
	        
			//colourScene creation and contents
			//Horizontal group of objects to allow for organisation of GUI
	        HBox colourMenuHorizontal = new HBox(20);
	        colourMenuHorizontal.setAlignment(Pos.CENTER);  
	        colourMenuHorizontal.getChildren().addAll(backButton, colourChoiceField, colourButton);
	        //Vertical group of objects (which holds horizontal group also)
	        HBox shapes = new HBox(20);
	        shapes.setAlignment(Pos.CENTER);
	        shapes.getChildren().addAll(shapesList);
	        HBox colourErrors = new HBox(20);
	        colourErrors.setAlignment(Pos.CENTER);  
			VBox colourMenu = new VBox(20);
			colourMenu.setAlignment(Pos.CENTER);
			colourMenu.getChildren().addAll(colourTitle, colourIntro, colourMenuHorizontal,shapes, colourErrors);
	        colourMenu.setStyle("-fx-background-color: #fafcff");//Recolour background
	        Scene colourScene = new Scene(colourMenu, 650, 650);

			//clearScene creation and contents
			//Horizontal group of objects to allow for organisation of GUI
	        HBox clearMenuHorizontal = new HBox(20);
	        clearMenuHorizontal.setAlignment(Pos.CENTER);
	        clearMenuHorizontal.getChildren().addAll(yesButton,noButton);
	        //Vertical group of objects (which holds horizontal group also)
	        VBox clearMenu = new VBox(20);
	        clearMenu.setAlignment(Pos.CENTER);
	        clearMenu.getChildren().addAll(clearTitle, clearText, clearMenuHorizontal);
	        clearMenu.setStyle("-fx-background-color: #fafcff");//Recolour background
	        Scene clearScene = new Scene(clearMenu, 650, 650);
			
	        //Final scene settings
	        stage.setTitle("Shapes");
	        stage.setScene(mainScene); //the default state should be the mainScene menu scene
	        stage.show();
	    
	        //When 'Enter' key is pressed, trigger the 'GO' button:
	        shapeChoiceField.setOnKeyPressed(e -> 
	        {
	        	if((e.getCode() == KeyCode.ENTER) && (stage.getScene()==mainScene))
	        	{
	        		createButton.fire();
	        	}	
	        });
	        //When 'GO' button is pressed, do this:
	        createButton.setOnAction(e -> 
	        {
	        	//Remove errors from screen if they are already there to avoid errors by adding something which already exists
        		shapeInput = shapeChoiceField.getText().toLowerCase().trim();
	        	shapesErrors.getChildren().clear();

        		if (createShape(shapeInput)) 
        		{
    				shapes.getChildren().addAll(shapesList);
    				for (int i=0; i<shapesColour.length; i++)
    				{
						if (shapeInput.equalsIgnoreCase(shapesColour[i][0]))
						{
	    					for (Node node : shapes.getChildren())
	    					{
	    						((Shape) node).setFill(painter(shapesColour[i][0]));
	    					}
						}
    				}
    	        	
    	        	shapesErrors.getChildren().clear();
    	        	shapeChoiceField.setText(""); //Wipe the text field so that it's ready for new input
    		        colourChoiceField.requestFocus(); //focus on the new text field - This improves functionality from the 'Enter' key functionality
    		        stage.setScene(colourScene);
        		}
        		else if (shapeInput.equals(""))
        		{
        			shapesErrors.getChildren().addAll(shapeError1);
        		}
        		else
        		{
        			shapesErrors.getChildren().addAll(shapeError2);
        		}
	        });
	        
	        FadeTransition updatedAnimation = new FadeTransition(Duration.seconds(1.5), updatedText);//set the animation speed and link to Label
	        updatedAnimation.setFromValue(1.0);//set the fade values
	        updatedAnimation.setToValue(.20);
	        updatedAnimation.setCycleCount(5);//set the animation length
	        updatedAnimation.setAutoReverse(true);//set to fade in AND out
	        updatedAnimation.setOnFinished(new EventHandler<ActionEvent>(){ //set a listener to check whether the animation is playing or not
	            public void handle(ActionEvent AE){
	            	boolean isAnimationPlaying;
	                isAnimationPlaying = false; 
	    	        if(!(isAnimationPlaying))
	    	        {
	    	        	colourErrors.getChildren().removeAll(updatedText);//remove the label if the animation has stopped
	    	        }
	            }});
	         
	        //When 'Enter' key is pressed, trigger the colour 'GO' button:
	        colourChoiceField.setOnKeyPressed(e -> 
	        {
	        	if((e.getCode() == KeyCode.ENTER) && (stage.getScene()==colourScene)) //only trigger if item is in scene
	        	{
	        		colourButton.fire();
	        	}	
	        });
	        
	        //When colour 'GO' button is pressed, do this:
	        colourButton.setOnAction(e ->{ 
	        	String colourInput = colourChoiceField.getText().toLowerCase().trim(); //Place modified input into variable to avoid need to repeat  
        		Paint paint = painter(colourInput); // Turn user input into a valid colour that can be applied to shapes, or null if not valid
    			
    			if (colourMenu.getChildren().contains(updatedText)) //stop any update animation if present, as a new selection has now been made
    			{
            		updatedAnimation.stop();
            		colourMenu.getChildren().removeAll(updatedText);
    			}

	        	//remove errors as they are now outdated, ready to react to the new selection
    			colourErrors.getChildren().clear();

    			if (paint==null) //If input is invalid, output a reason why to the user
    			{
    				if (colourInput.equals(""))
    				{
    					colourErrors.getChildren().addAll(colourError1);
    				}
    				else
    				{
    					colourErrors.getChildren().addAll(colourError2);
    				}
    			}
    			//Otherwise if the shape is already this colour...
    			else if (paint.equals(shapesList.get(0).getFill()))
				{
    				colourErrors.getChildren().addAll(colourError3);
    				colourError3.setText("THE SHAPE IS ALREADY "+ colourInput.toUpperCase()); //This needs to be updated 'in the moment' so that it outputs up-to-date information
				}
    			//Otherwise, run the input through a switch for possible outcomes
    			else if (!((paint) == null))
    			{
    				for (Node node : shapes.getChildren()) //Search through current shapes within the scene
    				{
    					((Shape) node).setFill(paint);// Fill these shapes with paint, chosen by user input					
    				}
        			for (int i=0; i<shapesColour.length; i++) //Search through first array in shapesColour
        			{
        				for (int x=0; x<shapesColour[i].length; x++) //Search through second layer of Array
        				{
        					if (shapeInput.equalsIgnoreCase(shapesColour[i][x])) //If current shape is already created (which it always will be as it is on screen)
        					{	
        						shapesColour[i][x-1] = colourInput.substring(0, 1).toUpperCase() + colourInput.substring(1); //Update that shape with the new colour, and capitalise the first letter
        					}
        				}
        			}
        			if (!(colourErrors.getChildren().contains(updatedText))) //avoid repetition if the label is already present
	        		{
	        			colourErrors.getChildren().addAll(updatedText);
	        		}
	        		updatedAnimation.play(); //Play the animation
        		}});
	        
	        //When 'Back' button is pressed, remove all components related to colouring, in order to revert to previous state, do this:
	        backButton.setOnAction(e ->{ 

	        	if (colourErrors.getChildren().contains(updatedText))
	        	{
	        		updatedAnimation.stop();//Stop the animation
	        	}
	        	shapes.getChildren().clear(); //Remove any shapes and errors as when the user returns to this scene, they will be out-of-date
	        	colourErrors.getChildren().clear();

	        	if (!(mainMenu.getChildren().contains(clearButton))) //if clearButton is not on screen, count how many shapes are coloured and add it if it is above 0
	        	{
					int colourCount=0;
					for (int i=0; i<shapesColour.length; i++)
					{
						if (!("white".equalsIgnoreCase(shapesColour[i][0])) && (shapesColour[i][0] != null)) 
						{
							colourCount++;
						}
					}
					if (colourCount>=1)
					{
						mainMenu.getChildren().addAll(clearButton);
					}
	        	}

		        stage.setScene(mainScene);//change scene
		        shapesList.clear();// clear shapes
		        currentShape = "";
	        	colourChoiceField.setText("");
		        shapeChoiceField.requestFocus();
	         });
	        
	        //When "Clear Colours" button is pressed, ask the user to reset shape colours
	        clearButton.setOnAction(e ->{;
        	shapesErrors.getChildren().clear();
	        	/*Set clear text to display correct and up to date shape colours, needs to be called only when user requests
	        	 information, so that information is up to date.*/
        	String setClearText="";
        	for (int i=0;i<shapesColour.length;i++) //count through shape names
        	{ //if shape colour is not null && check theres no duplicates && check that its not "white"
        		if ((shapesColour[i][0] != null) && (!(setClearText.contains(shapesColour[i][0]))) && (!("white".equalsIgnoreCase(shapesColour[i][0]))))
        		{
        			setClearText += "\n" + shapesColour[i][1] + " = " + shapesColour[i][0];//then add these to the string to output to the user
        		}
        	}
        	
        	setClearText += "\n\nWould you like to clear this paint?";
	        	clearText.setText(setClearText);
	            stage.setScene(clearScene);
	        	shapeChoiceField.setText("");
	        });
	          
	        //When "Yes" button is pressed, clear all shape colours, reset all colourcolourSceneto "White", and change screen objects back to shape creation
	        yesButton.setOnAction(e ->{
	        	
	        	for (int i = 0; i<shapesColour.length;i++) // check through double array and set each colour to null
	    		{
	    			shapesColour[i][0] = null;
	    		}
		        shapeChoiceField.requestFocus();
	        	mainMenu.getChildren().removeAll(clearButton);
	            stage.setScene(mainScene);
	        });
	        
//	        //When "No" button is pressed, change back to shape creation scene without adjustments being made
	        	noButton.setOnAction(e ->{
	            stage.setScene(mainScene);
		        shapeChoiceField.requestFocus();
	        });
		}
	    public static void begin(String[] args)     
	    {
			launch(args);//Start
	    }
	}