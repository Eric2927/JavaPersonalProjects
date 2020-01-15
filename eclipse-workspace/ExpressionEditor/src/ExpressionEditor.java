import javafx.application.Application;
import java.util.*;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ExpressionEditor extends Application {
	public static void main (String[] args) {
		launch(args);
	}

	/**
	 * Mouse event handler for the entire pane that constitutes the ExpressionEditor
	 */
	private static class MouseEventHandler implements EventHandler<MouseEvent> {
		Pane _pane;
		CompoundExpression _rootExpression;
		Point2D _initialMouseLocation;
		Expression _draggableExpCopy;
		Expression _currentFocus;

		MouseEventHandler (Pane pane_, CompoundExpression rootExpression_) {
			_pane = pane_;
			_rootExpression = rootExpression_;
		}

		public void handle (MouseEvent event) {
			if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				_initialMouseLocation = new Point2D(event.getSceneX(), event.getSceneY());
				Expression mostSpecificFocus = _rootExpression.findMostSpecificFocus(_initialMouseLocation, true);
				_currentFocus = _rootExpression.findHighlighted();
				if (mostSpecificFocus != null) {
					Expression cursor = mostSpecificFocus;
					if (_currentFocus == null) {
						while (cursor.getParent().getParent() != null) {
							Expression exp = cursor.getParent().getParent();
							cursor = cursor.getParent();
						}
						((Region) cursor.getNode()).setBorder(cursor.RED_BORDER);
						_currentFocus = cursor;
					}
					else {
						if (mostSpecificFocus == _currentFocus) {
							((Region) _currentFocus.getNode()).setBorder(_currentFocus.NO_BORDER);
						}
						else {
							boolean clickToFocus = false;
							Expression temp = cursor;
							cursor = cursor.getParent();
							while (cursor.getParent() != null) {
								if (cursor == _currentFocus) {
									clickToFocus = true;
									break;
								}
								temp = cursor;
								cursor = cursor.getParent();
							}
							if (clickToFocus) {
								((Region) temp.getNode()).setBorder(temp.RED_BORDER);
								((Region) cursor.getNode()).setBorder(cursor.NO_BORDER);
								_currentFocus = temp;
							}
							else {
								((Region) _currentFocus.getNode()).setBorder(_currentFocus.NO_BORDER);
								((Region) temp.getNode()).setBorder(temp.RED_BORDER);
								_currentFocus = temp;
							}
						}
					}
				}
				else if (_currentFocus != null) {
					((Region) _currentFocus.getNode()).setBorder(_currentFocus.NO_BORDER);
				}
			}
			else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				Point2D mouseDragLocation = new Point2D(event.getSceneX(), event.getSceneY());
				Expression mostSpecificFocus = _rootExpression.findMostSpecificFocus(_initialMouseLocation, true);
				if (_draggableExpCopy == null) {
					boolean clickToDrag = false;
					if (mostSpecificFocus != null) {
						Expression temp = mostSpecificFocus;
						while (temp.getParent() != null) {
							if (temp == _currentFocus) {
								clickToDrag = true;
								break;
							}
							temp = temp.getParent();
						}	
					}
					if (clickToDrag) {
						_draggableExpCopy = _currentFocus.deepCopy();
						_currentFocus.convertToGhost(false);
						Expression temp3 = _currentFocus.getParent();
						Point2D originalExpLocation = new Point2D(_currentFocus.getNode().getLayoutX(), _currentFocus.getNode().getLayoutY());
						while (temp3.getParent() != null) {
							originalExpLocation = temp3.getNode().localToParent(originalExpLocation);
							temp3 = temp3.getParent();
						}
						originalExpLocation = temp3.getNode().localToParent(originalExpLocation);
						_pane.getChildren().add(_draggableExpCopy.getNode());
						_draggableExpCopy.getNode().setLayoutX(originalExpLocation.getX());
						_draggableExpCopy.getNode().setLayoutY(originalExpLocation.getY());
					}
				}
				if (_draggableExpCopy != null) {
					_draggableExpCopy.getNode().setTranslateX(mouseDragLocation.getX() - _initialMouseLocation.getX());
					_draggableExpCopy.getNode().setTranslateY(mouseDragLocation.getY() - _initialMouseLocation.getY());
				}
				CompoundExpression parentToFocus = _currentFocus.getParent();
				if (!(parentToFocus instanceof ParentheticalExpression)) {
					HashMap<Double, List<Expression>> configurations = new HashMap<Double, List<Expression>>();
					List<Expression> temporaryConfiguration = new ArrayList<Expression>();
					int indexOfFocus = parentToFocus.getChildren().indexOf(_currentFocus);
					int cursorIndex = 0;
					while (cursorIndex < parentToFocus.getChildren().size()) {
						temporaryConfiguration.addAll(parentToFocus.getChildren());
						temporaryConfiguration.add(cursorIndex, temporaryConfiguration.remove(indexOfFocus));
						HBox tempLayout = new HBox();
						double layoutX = -1;
						for (int i = 0; i < temporaryConfiguration.size(); i ++) {
							tempLayout.getChildren().add(temporaryConfiguration.get(i).getNode());
							if (i == cursorIndex) {
								layoutX = tempLayout.getChildren().get(tempLayout.getChildren().size() - 1).getLayoutX();
							}
							if (i != temporaryConfiguration.size() - 1) {
								if (parentToFocus instanceof MultiplicativeExpression) {
									tempLayout.getChildren().add(new Label("*"));
								}
								else if (parentToFocus instanceof AdditiveExpression) {
									tempLayout.getChildren().add(new Label("+"));
								}
							}
						}
						configurations.put(layoutX, temporaryConfiguration);
						temporaryConfiguration.clear();
						cursorIndex ++;
					}
					double minDifference = Double.MAX_VALUE;
					List<Expression> closestConfiguration = null;
					Iterator it = configurations.keySet().iterator();
					while (it.hasNext()) {
						double key = (double) it.next();
						if (_currentFocus.getNode().getLayoutX() + _draggableExpCopy.getNode().getTranslateX() - key < minDifference) {
							minDifference = _currentFocus.getNode().getLayoutX() + _draggableExpCopy.getNode().getTranslateX() - key;
							closestConfiguration = configurations.get(key);
						}
					}
					
					
				}
				

			}
			else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				if (_draggableExpCopy != null) {
					_pane.getChildren().remove(_draggableExpCopy.getNode());
					_draggableExpCopy = null;
				}
				_rootExpression.convertToGhost(true);
			}
		}
	}

	/**
	 * Size of the GUI
	 */
	private static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 250;

	/**
	 * Initial expression shown in the textbox
	 */
	private static final String EXAMPLE_EXPRESSION = "2*x+3*y+4*z+(7+6*z)";

	/**
	 * Parser used for parsing expressions.
	 */
	private final ExpressionParser expressionParser = new SimpleExpressionParser();

	@Override
	public void start (Stage primaryStage) {
		primaryStage.setTitle("Expression Editor");

		// Add the textbox and Parser button
		final Pane queryPane = new HBox();
		final TextField textField = new TextField(EXAMPLE_EXPRESSION);
		final Button button = new Button("Parse");
		queryPane.getChildren().add(textField);

		final Pane expressionPane = new Pane();

		// Add the callback to handle when the Parse button is pressed	
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				// Try to parse the expression
				try {
					// Success! Add the expression's Node to the expressionPane
					final Expression expression = expressionParser.parse(textField.getText(), true);
					System.out.println(expression.convertToString(0));
					expressionPane.getChildren().clear();
					expressionPane.getChildren().add(expression.getNode());
					expression.getNode().setLayoutX(WINDOW_WIDTH/4);
					expression.getNode().setLayoutY(WINDOW_HEIGHT/2);
					// If the parsed expression is a CompoundExpression, then register some callbacks
					if (expression instanceof CompoundExpression) {
						((Pane) expression.getNode()).setBorder(Expression.NO_BORDER);
						final MouseEventHandler eventHandler = new MouseEventHandler(expressionPane, (CompoundExpression) expression);
						expressionPane.setOnMousePressed(eventHandler);
						expressionPane.setOnMouseDragged(eventHandler);
						expressionPane.setOnMouseReleased(eventHandler);
					}
				} catch (ExpressionParseException epe) {
					// If we can't parse the expression, then mark it in red
					textField.setStyle("-fx-text-fill: red");
				}
			}
		});
		queryPane.getChildren().add(button);

		// Reset the color to black whenever the user presses a key
		textField.setOnKeyPressed(e -> textField.setStyle("-fx-text-fill: black"));
		
		final BorderPane root = new BorderPane();
		root.setTop(queryPane);
		root.setCenter(expressionPane);

		primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
		primaryStage.show();
	}
}
