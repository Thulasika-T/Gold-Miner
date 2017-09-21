package newIdeas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DialogBoxes extends JFrame{

	//Creating container
	Container contentPane = getContentPane();


	public DialogBoxes ()//constructor
	{
		super("New Ideas");
		setSize( 600,400 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		contentPane.setLayout(null);
		contentPane.setBackground(Color.black);
		setVisible(true);

		//create label for user to input a message in
		//Setting bounds for label and adding it to the frame
		JLabel message = new JLabel(" ");
		message.setBounds(100,100,500,300);
		contentPane.add(message);
		
		//*Dialog Boxes: showing a message, asking for a input and allowing for confirmation

		//Parameters are (Component, Object-String message)
		JOptionPane.showMessageDialog(contentPane, "Displays any message you desire."); 


		//Parameter (Component, Object-String message)
		JOptionPane.showConfirmDialog(null, "Confirming something that has happened in your game.\n"+
				"Examples: You have ___ points!!");

		//Parameter (String message)
		String input = JOptionPane.showInputDialog("Input a message you would like to have displayed on the black screen");
		message.setVisible(true);
		Font displayFont2 = new Font("Serif", Font.BOLD,50);
		message.setFont(displayFont2);
		message.setForeground(Color.white);
		//Setting the label to what the user inputed inside the Input Dialog Box
		message.setText(input);

		//*Dialog Boxes can be used in combinations for your liking
		//Using confirmDialog Box and then displaying what user picked in a message
		int response = JOptionPane.showConfirmDialog(null, "Choose one of the following");
		
		//If user picks Yes
		if(response==0)
			JOptionPane.showMessageDialog(contentPane, "You have picked Yes");

		//If user picks No
		if(response==1)
			JOptionPane.showMessageDialog(contentPane, "You have picked No"); 

		//Making user input something and then showing them their input
		String userinput =JOptionPane.showInputDialog("Enter a message");
		JOptionPane.showMessageDialog(contentPane, userinput);


		//*Giving user options and having output based on what was picked
		Object[] options = {"SEE MESSAGE","EXIT"};
		int option = JOptionPane.showOptionDialog(contentPane,
				"Would you like to see the message or exit?", "Choosing options", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, "Exit");
		
		//If user picks SEE MESSAGE
		if (option == JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(contentPane, "HI!!!");
		}
		
		//If user picks EXIT
		else if (option == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		DialogBoxes gui = new  DialogBoxes();	      
	}
}
