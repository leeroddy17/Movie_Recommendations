package contentView;

import java.sql.*;
import javax.swing.*;    
import java.awt.event.*;
import java.awt.*;
import connectDatabase.connect;

public class contentView
{
	public static void main(String[] args) // maybe pass the strings of the movies as arguments here
	{
        connect db = new connect();
        // String cus_lname = "";
        // try{
        //     //create a statement object
        //     Statement stmt = db.conn.createStatement();
        //     //create an SQL statement
        //     String sqlStatement = "SELECT rating FROM customer_ratings LIMIT 10";
        //     //send statement to DBMS
        //     ResultSet result = stmt.executeQuery(sqlStatement);
    
        //     //OUTPUT
        //     JOptionPane.showMessageDialog(null,"Customer rating from the Database.");
        //     //System.out.println("______________________________________");
        //     while (result.next()) {
        //         //System.out.println(result.getString("cus_lname"));
        //         cus_lname += result.getString("rating")+"\n";
        //     }
        // } catch (Exception e){
        //     JOptionPane.showMessageDialog(null,e.getClass().getName()+": "+e.getMessage());
        // }
        // JOptionPane.showMessageDialog(null,cus_lname);
        //closing the connection
       
		
		JFrame contentView = new JFrame();
		JLabel userTitle = new JLabel(); // a panel for a title
		JLabel TITLE = new JLabel(); // main title that should never be touched ever again
		
		
		String UserName = "Carlos"; // get the users name and put it here later
		
		
		userTitle.setBounds(100,-100, 1000, 300);
		userTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		userTitle.setText("Welcome Back " + UserName + "!");
		contentView.add(userTitle); // Add the name of the user to the 
		
		TITLE.setBounds(100, -200, 1000, 300);
		TITLE.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		TITLE.setText("Aggie Movies For Researchers");
		
		
		
		String movie1 = "avengers"; // name of the 3 most popular or relevant movies
		String movie2 = "inception";
		String movie3 = "Fast & Furious 6";
		
		String movie4 = "Interstellar"; // 3 movies based on what was recently watched
		String movie5 = "Tenet";
		String movie6 = "The Dark Knight";
		
		// Buttons for the user to click on the Featured movies
		JLabel recomended = new JLabel("Recomended for you");
		recomended.setBounds(100, 350, 1000, 200);
		recomended.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		contentView.add(recomended);
		
        JButton button1 = new JButton(movie1); //"place holder should be changed to movie name
        JButton button2 = new JButton(movie2);
        JButton button3 = new JButton(movie3);
        
        JLabel watched = new JLabel("Becasue you watched <place holder>");
        watched.setBounds(100, 50, 1000, 200);
        watched.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        contentView.add(watched);
        
        JButton button4 = new JButton(movie4); //place holder should be changed to movie name through database data
        JButton button5 = new JButton(movie5);
        JButton button6 = new JButton(movie6);
  
        // x axis, y axis, width, height for location on the page
        button1.setBounds(100, 500, 300, 200);
        button2.setBounds(500, 500, 300, 200);
        button3.setBounds(900, 500, 300, 200);
        button4.setBounds(100, 200, 300, 200);
        button5.setBounds(500, 200, 300, 200);
        button6.setBounds(900, 200, 300, 200);
  
        contentView.add(button1); // buttons added to the frame
        contentView.add(button2);
        contentView.add(button3);
        contentView.add(button4); 
        contentView.add(button5);
        contentView.add(button6);
        
       
        
          
        contentView.setSize(10000, 10000); // initial size of the frame can still be changed by the user
        contentView.setLayout(null);
        contentView.setVisible(true);
        
        try {
            db.conn.close();
            JOptionPane.showMessageDialog(null,"Connection Closed.");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
        }//end try catch


		
	}
}