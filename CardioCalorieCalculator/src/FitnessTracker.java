import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;	

/**
 * 
 * @author Ezra
 * Uses Swing to create GUI
 * This class contains all methods needed for the Calorie Calculator
 * This class implements the ActionListener for the buttons in the GUI
 * 
 */
public class FitnessTracker implements ActionListener {
	
	private JPanel workoutPanel; //first window panel
	private JPanel timePanel; //second window panel
	private JPanel resultsPanel; //third window panel
	private JFrame workoutFrame; //first window frame
	private JFrame timeFrame; //second window frame
	private JFrame resultsFrame; //third window frame
	private JLabel centerLabel; //label serves as title of program
	private JLabel workoutPrompt; //asks user to select a button
	private JLabel timePrompt1; //asks user to input time
	private JLabel timePrompt2; //asks user to input time
	private JLabel timeError; //gets shown when user input is invalid
	private JLabel resultsLabel1; //displays result (calories burned) first line
	private JLabel resultsLabel2; //displays result (calories burned) second line
	private JLabel resultsLabel3; //displays result (calories burned) third line
	private JButton bikeLow; //button for biking at low intensity
	private JButton bikeModerate;//button for biking at moderate intensity
	private JButton bikeHigh; //button for biking at high intensity
	private JButton runLow; //button for running at low intensity
	private JButton runModerate; //button for running at moderate intensity
	private JButton runHigh; //button for running at high intensity
	private JButton walkLow; //button for walking at low intensity
	private JButton walkModerate; //button for walking at moderate intensity
	private JButton walkHigh; //button for walking at high intensity
	private JButton danceLow; //button for dancing at low intensity
	private JButton danceModerate; //button for dancing at moderate intensity
	private JButton danceHigh; //button for dancing at high intensity
	private JButton confirmButton; //button to confirm time input
	private JTextField timeInput; //user types input here
	private String workout; //stores the type of workout done
	private double intensity; //stores the value of intensity
	private int time; //stores the duration of the workout
	private final int myWeight = 75; //stores my height
	private final int myHeight = 172; //stores my weight
	private double caloriesBurned; //total number of calories burned
	 
	/**
	 * Creates an instance of the class for the Singleton Pattern
	 */
	private static FitnessTracker instance;
	
	/**
	 * 
	 * Constructs the first window of the GUI
	 * Displays buttons to let user(me) select their workout and corresponding intensity
	 */
	private FitnessTracker() {
		
		workoutPanel = new JPanel(); //creates a panel where labels and buttons are organized
		workoutPanel.setBackground(new Color(167, 191, 232)); //sets panel background color
		
		workoutFrame = new JFrame(); //new frame for first window
		workoutFrame.setSize(800, 350); //sets frame size
		workoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits program 
		workoutFrame.setVisible(true); //sets window as visible
		workoutFrame.add(workoutPanel); //adds panel to frame
		
		workoutPanel.setLayout(null); //enables .setBounds method to be used for custom layout
		
		centerLabel = new JLabel("Cardio Calorie Calculator"); //serves as title
		centerLabel.setBounds(275, 20, 300, 25); //position of label
		centerLabel.setFont(new Font("Silom", Font.BOLD, 18)); //sets font details
		workoutPanel.add(centerLabel); //adds label to the panel
		
		workoutPrompt = new JLabel("Select today's exercise and intensity."); //prompt to select a workout type and intensity
		workoutPrompt.setBounds(225, 50, 400, 25); //position of label
		workoutPrompt.setFont(new Font("Silom", Font.BOLD, 18)); //sets font details
		workoutPanel.add(workoutPrompt); //adds label to the panel
		
		bikeLow = new JButton("Bike - Low"); //creates a button for biking at low intensity
		bikeLow.setBounds(50, 100, 125, 25); //sets position of button
		workoutPanel.add(bikeLow); //adds the button to the panel
		bikeLow.addActionListener(this); //enables action when the button is clicked
		
		bikeModerate = new JButton("Bike - Moderate");
		bikeModerate.setBounds(50, 150, 125, 25);
		workoutPanel.add(bikeModerate);
		bikeModerate.addActionListener(this);
		
		bikeHigh = new JButton("Bike - High");
		bikeHigh.setBounds(50, 200, 125, 25);
		workoutPanel.add(bikeHigh);
		bikeHigh.addActionListener(this);
		
		runLow = new JButton("Run - Low");
		runLow.setBounds(225, 100, 125, 25);
		workoutPanel.add(runLow);
		runLow.addActionListener(this);
		
		runModerate = new JButton("Run - Moderate");
		runModerate.setBounds(225, 150, 125, 25);
		workoutPanel.add(runModerate);
		runModerate.addActionListener(this);
		
		runHigh = new JButton("Run - High");
		runHigh.setBounds(225, 200, 125, 25);
		workoutPanel.add(runHigh);
		runHigh.addActionListener(this);
		
		walkLow = new JButton("Walk - Low");
		walkLow.setBounds(400, 100, 125, 25);
		workoutPanel.add(walkLow);
		walkLow.addActionListener(this);
		
		walkModerate = new JButton("Walk - Moderate");
		walkModerate.setBounds(400, 150, 125, 25);
		workoutPanel.add(walkModerate);
		walkModerate.addActionListener(this);
		
		walkHigh = new JButton("Walk - High");
		walkHigh.setBounds(400, 200, 125, 25);
		workoutPanel.add(walkHigh);
		walkHigh.addActionListener(this);
		
		danceLow = new JButton("Dance - Low");
		danceLow.setBounds(575, 100, 150, 25);
		workoutPanel.add(danceLow);
		danceLow.addActionListener(this);
		
		danceModerate = new JButton("Dance - Moderate");
		danceModerate.setBounds(575, 150, 150, 25);
		workoutPanel.add(danceModerate);
		danceModerate.addActionListener(this);
		
		danceHigh = new JButton("Dance - High");
		danceHigh.setBounds(575, 200, 150, 25);
		workoutPanel.add(danceHigh);
		danceHigh.addActionListener(this);
		
		workoutFrame.setVisible(true); //sets the frame as visible
		
	}
	
	/**
	 * Calculator for biking
	 * @param intensity -- the selected intensity of the exercise
	 * @param time -- the duration of the exercise
	 * @return the calories burned
	 * Uses the formula for calories burned per minute while running
	 * Formula taken from 
	 * https://captaincalculator.com/health/calorie/calories-burned-cycling-calculator/
	 */
	public double calculateBike(double intensity, int time) {
		return ((intensity * myWeight * 3.5) / 200) * time ;
	}

	/**
	 * Calculator for running
	 * @param intensity
	 * @param time
	 * @return the calories burned 
	 * Formula taken from 
	 * https://fitness.stackexchange.com/questions/15608/energy-expenditure-calories-burned-equation-for-running#:~:text=Men%20use%20the%20following%20formula,20.4022%5D%20x%20Time%20%2F%204.184.
	 */
	public double calculateRun(double intensity, int time) {
		return ((20 * 0.2017) + (myWeight * 0.09036) + (intensity * 0.6309) - 55.0969) * time / 4.184 ;
	}
	
	/**
	 * Calculator for walking
	 * @param intensity
	 * @param time
	 * @return the calories burned
	 * Formula taken from
	 * https://www.verywellfit.com/walking-calories-and-distance-calculators-3432711#:~:text=Calories%20Per%20Minute&text=The%20number%20varies%20based%20on,of%20three%20miles%20per%20hour.
	 */
	public double calculateWalk(double intensity, int time) {
		return ((0.35 * myWeight) + (intensity/myHeight) * 0.029 * myWeight) * time /3 ;
	}
	
	/**
	 * Calculator for walking
	 * @param intensity
	 * @param time
	 * @return the calories burned
	 * Formula derived from 
	 * https://captaincalculator.com/health/calorie/calories-burned-dancing-calculator/#:~:text=The%20average%20person%20will%20burn,in%20an%20aerobic%20dance%20class.
	 */
	public double calculateDance(double intensity, int time) {
		return (myWeight/intensity) * time ;
	}
	
	/**
	 * Gets called when "Confirm" button is clicked on second window
	 * Creates a new results window which displays the result of the calculation
	 * Multiple if blocks to check the kind of workout done
	 * 
	 */
	public void resultsWindow() {
		
		resultsPanel = new JPanel(); //creates panel for the second window
		resultsPanel.setBackground(new Color(167, 191, 232)); //sets background color
		resultsPanel.setLayout(null); //enables custom layout
		
		resultsFrame = new JFrame(); //creates frame for the second window
		resultsFrame.setSize(500, 350); //sets frame size
		resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //enables window to be closed
		resultsFrame.setVisible(true); //makes window visible
		resultsFrame.add(resultsPanel); //adds panel to the frame
		
		if (workout == "bike"){
			caloriesBurned = calculateBike(intensity, time); //calls the method that uses the biking formula
		}

		if (workout == "run"){
			caloriesBurned = calculateRun(intensity, time); //calls the method that uses the running formula
		}

		if (workout == "walk"){
			caloriesBurned = calculateWalk(intensity, time); //calls the method that uses walking formula
		}

		if (workout == "dance"){
			caloriesBurned = calculateDance(intensity, time); //calls the method that uses dancing formula
		}

		

		resultsLabel1 = new JLabel("You burned "); 
		resultsLabel1.setBounds(175, 80, 200, 25);
		resultsLabel1.setFont(new Font("Silom", Font.BOLD, 25));
		resultsPanel.add(resultsLabel1);
		
		resultsLabel2 = new JLabel(String.valueOf(Math.round(caloriesBurned*100)/100) + " calories today."); //rounds off the double and converts it to string
		resultsLabel2.setBounds(125, 130, 400, 25);
		resultsLabel2.setFont(new Font("Silom", Font.BOLD, 25));
		resultsPanel.add(resultsLabel2);
		
		resultsLabel3 = new JLabel("Keep it up!"); 
		resultsLabel3.setBounds(175, 180, 400, 25);
		resultsLabel3.setFont(new Font("Silom", Font.BOLD, 25));
		resultsPanel.add(resultsLabel3);
		
		
	}
	
	/**
	 * Gets called when a button is clicked on the first window
	 * Creates a new window with a textField to input number of minutes
	 * @param t - passes the value of the workout variable on
	 * Creates an anonymous class that implements ActionListener
	 * Anonymous class is needed to be able to use the button on the second window
	 * Try-catch block inside anonymous class to catch invalid input (non integers)`
	 * 
	 */
	public void timeWindow(String t) {
		
		timePanel = new JPanel();
		timeFrame = new JFrame();
		timeFrame.setSize(500, 350);
		timeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timeFrame.getContentPane().setBackground(new Color(101, 109, 138));
		timeFrame.setVisible(true);
		timeFrame.add(timePanel);
		 
		timePanel.setLayout(null);
		timePanel.setBackground(new Color(167, 191, 232));
		
		timePrompt1 = new JLabel("How many minutes did you exercise for?");
		timePrompt1.setBounds(65, 20, 400, 25);
		timePrompt1.setFont(new Font("Silom", Font.BOLD, 18));
		timePanel.add(timePrompt1);
		
		timePrompt2 = new JLabel("Please enter a number.");
		timePrompt2.setBounds(145, 50, 400, 25);
		timePrompt2.setFont(new Font("Silom", Font.BOLD, 18));
		timePanel.add(timePrompt2);
		
		timeError = new JLabel("Invalid input. Please enter a number");
		timeError.setBounds(85, 200, 400, 25);
		timeError.setFont(new Font("Silom", Font.BOLD, 18));
		timeError.setForeground(Color.RED);
		timeError.setVisible(false);
		timePanel.add(timeError);
		
		timeInput = new JTextField();
		timeInput.setBounds(150, 125, 180, 25);
		timePanel.add(timeInput);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(150, 150, 180, 25);
		timePanel.add(confirmButton);
		confirmButton.addActionListener(new ActionListener(){
			/**
			 * Enables the button on the second window to be used\
			 * Try-catch block catches invalid input
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				time = Integer.parseInt(timeInput.getText()); //converts string to int
				timeFrame.dispose(); //closes the window 
				resultsWindow(); //opens results window
			}
				catch(Exception ex) {
					timeError.setVisible(true); //reveals error message when input is invalid
				}
			}
		});
	
	}
	
	
	/**
	 * Observer design pattern
	 * Method that listens for  button click
	 * Multiple if statements to identify which button was clicked
	 * Updates the values of workout and intensity 
	 * Closes current window and opens timeWindow
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==bikeLow) {
			workout = "bike";
			intensity = 4;
		}
		if(e.getSource()==bikeModerate) {
			workout = "bike";
			intensity = 8.5;
		}
		if(e.getSource()==bikeHigh) {
			workout = "bike";
			intensity = 14;
		}
		if(e.getSource()==runLow) {
			workout = "run";
			intensity = 120;
		}
		if(e.getSource()==runModerate) {
			workout = "run";
			intensity = 145;
		}
		if(e.getSource()==runHigh) {
			workout = "run";
			intensity = 170;
		}
		if(e.getSource()==walkLow) {
			workout = "walk";
			intensity = 1.4;
		}
		if(e.getSource()==walkModerate) {
			workout = "walk";
			intensity = 2;
		}
		if(e.getSource()==walkHigh) {
			workout = "walk";
			intensity = 2.5;
		}
		if(e.getSource()==danceLow) {
			workout = "dance";
			intensity = 22;
		}
		if(e.getSource()==danceModerate) {
			workout = "dance";
			intensity = 22.5;
		}
		if(e.getSource()==danceHigh) {
			workout = "dance";
			intensity = 23.25;
		}
		
		workoutFrame.dispose(); //closes the first window
		timeWindow(workout); //opens the second window

	}
	
	/**
	 * 
	 * @return instance of class
	 * for Singleton pattern
	 */
	public static FitnessTracker getInstance() {
		if(instance == null) {
			instance = new FitnessTracker();
		}
		return instance;
	}
}
