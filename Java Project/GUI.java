import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.StringTokenizer;

import acm.gui.TableLayout;
import acm.program.Program;

public class GUI extends Program implements ChangeListener, ActionListener {

	/**
	 * 
	 */
	String previous = "";
	String prefix = "";
	JTextField input = new JTextField("");  //creating 3 JTextField one for the input
	JTextField output = new JTextField(""); //one for the output
	JTextField prec_tf = new JTextField("");//one for the precision of the slider

	JSlider slider; //creating a slider
	//creating the variables needed for the calculation
	String precidecimal = "";  // the decimal part that will be displayed
	String decimal = "0"; //decimal part of the answer
	String result = null;//the answer
	String integer = null;// the integer part of the answer

	public void init() {
		setSize(400, 800); //set the size of the UI (400x800)
		setLayout(new TableLayout(9, 4)); // Create a new table layou of 9 lines and 4 columns
		output.setFont(new Font("ARIAL", Font.ITALIC, 35)); //change the font of the output
		output.setEditable(false);//make it non editable
		input.setFont(new Font("ARIAL", Font.PLAIN, 35)); //change the font of the input
		add(input, "gridwidth=4 height=50 ");//adding the input and output (of type JTextfield) to the UI  
		add(output, "gridwidth=4 height=50");
		String Array[] = { "C", "", "prev", "<-", "7", "8", "9", "/", "4",
				"5", "6", "x", "1", "2", "3", "+", ".", "0", "^", "-", "", "(",
				")", "=" };//storing the different Label of buttons in a Array
		String sizeOfbutton = "height = 100 width = 100"; //specifying the size of the button
		for (int i = 0; i < Array.length; i++) {
			JButton create = new JButton(Array[i]);//creating a button of each label
			create.setFont(new Font("Arial", Font.PLAIN, 35));//change the font and the color
			create.setBackground(Color.white);
			add(create, sizeOfbutton);//adding the button to the UI with the appropriate size
			create.addActionListener(this);// add action listener to detect the pressing of the button
		}

	
		JLabel preci = new JLabel("Precision"); //creating a label for the precision of the slider
		preci.setFont(new Font("Arial", Font.PLAIN, 20));//changing the font and adding it to the UI
		add(preci);

		slider = new JSlider(0, 10, 6); //create a new slider that goes to 0 to 10 and the default is 6
		slider.addChangeListener(this);//add change listener to check changes
		add(slider, "gridwidth = 2 height = 25");//add the slider to the UI 
		prec_tf.setText("6");//initialise the precision counter to 6
		prec_tf.setFont(new Font("Arial", Font.PLAIN, 20));//set its font
		prec_tf.setBackground(Color.WHITE);//change background color
		add(prec_tf);//add it to the UI

	}

	@Override
	public void stateChanged(ChangeEvent arg0) { //detecting the statechange of the slider
		// TODO Auto-generated method stub
		
		prec_tf.setText(slider.getValue() + "");//set the precision text to the new value of the slider
		boolean noException = true;// a boolean checking the presence of a exception
		try {
			result = JCalc.main(previous.replace("x", "*"));//try to calculate the output after replacing x by *
		} catch (Exception e) {//if you catch an exception set the boolean to false and set the ouput to error
			noException = false;
			output.setText("ERROR");
		}
		if (noException) { //if we didn't get a error continue
			StringTokenizer token = new StringTokenizer(result, ".", true); //split the result into 2 string according to the .
			integer = token.nextToken();//store the integer part
			token.nextToken();//do not store the "."
			decimal = token.nextToken();//store the decimal part
			
			precidecimal = ".";
			int i;
			for (i = 0; i < slider.getValue(); i++) {//adding  the missing digits to the precidimal
				if (i < decimal.length())//for i going from 0 to the slider value add
					precidecimal += decimal.charAt(i);//check if there is a decimal at the place i if yes add it
				else
					precidecimal += "0";//else add an 0
			}
			if(i == 0)//check if the for loop didn't ran
				output.setText(integer);// just print the integer if yes
			else 
				output.setText(integer + precidecimal);//set the output to the integer plus a point and the predecimal part
			
			decimal = integer = result = precidecimal = "";//set the variables back to 0
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {//actions to perform when the button is pressed
		if (e.getActionCommand() == "+/-") {//if for each non numeral button action command
			output.setText("NOT IMPLEMENTED");//for "+/-" the function isn't implemented
		} else {
			boolean noException = true;//a boolean to check the presence of an Exception
			if (e.getActionCommand() == "=") { //for action command "="
				previous = input.getText();
				try {
					result = JCalc.main(input.getText().replace("x", "*"));//try to calculate
				} catch (Exception f) {
					output.setText("ERROR");//if you get an exception print ERROR in the ouput 
					noException = false;
				}
				prefix = input.getText() + "=";// add "=" to the input
				input.setText(prefix);
				if (noException) { //if there was no Exception continue
					StringTokenizer token = new StringTokenizer(result, ".",true); // split the result occording to the "."
					integer = token.nextToken();// take the integer part of the answer
					token.nextToken();//ignore the "."
					decimal = token.nextToken();//take the decimal part of the question
					precidecimal = ".";
					int i;
					for (i = 0; i < slider.getValue(); i++) {//adding  the missing digits to the precidimal
						if (i < decimal.length())//for i going from 0 to the slider value add
							precidecimal += decimal.charAt(i);//check if there is a decimal at the place i if yes add it
						else
							precidecimal += "0";//else add an 0
					}
					if(i == 0)//check if the for loop ran
						output.setText(integer);// just print the integer if no
					else 
						output.setText(integer + precidecimal);//set the output to the integer plus a point and the predecimal part
					decimal = integer = result = precidecimal = "";//set the variables back to 0
				}
			} else if (e.getActionCommand() == "C") { //if the action command is C
				input.setText("");//clear the input
				output.setText("");//clear the output
				prefix = "";//clear the internal prefix
				decimal = integer = result = "";//reset the variables
			} else if (e.getActionCommand() == "prev") {
				input.setText(previous);// if you get prev set the input text to your previous expression
			} else {
				if (e.getActionCommand() == "<-") {//if you get "<-"
					try{//try to delete the last character of the string
						StringBuilder temp = new StringBuilder(prefix);
						temp.deleteCharAt(temp.length() - 1);
						prefix = temp.toString();
					}catch(Exception j){
						System.out.println("prefix empty");
						//else print that the prefix is empty
					}
				}

				else
					prefix += e.getActionCommand();//if it is not one of the above then it is a number so add it to the prefix
				previous = prefix;//store the previous expression
				input.setText(prefix);//refresh the input
			}
		}
	}
}
