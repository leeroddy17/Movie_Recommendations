package analysisView;

import javax.swing.*;    
import java.awt.event.*;
import java.awt.*;

public class AnalysisView
{
	
	
	
	public static void main(String[] args) // maybe pass the strings of the movies as arguments here
	{
		JFrame analysistView = new JFrame();
		
		 JLabel userTitle = new JLabel(); // a panel for a title
		 JLabel TITLE = new JLabel(); // main title that should never be touched ever again
		
		 JCheckBox horror = new JCheckBox("Horror");
		 JCheckBox thriller = new JCheckBox("Thriller");
		 JCheckBox action = new JCheckBox("Action");
		 JCheckBox adventure = new JCheckBox("Adventure");
		 JCheckBox romance = new JCheckBox("Romance");
		 JCheckBox comedy = new JCheckBox("Comedy");
		 JCheckBox sciFi = new JCheckBox("Sci-Fi");
		 JCheckBox documentary = new JCheckBox("Documentary");
		 JCheckBox mystery = new JCheckBox("Mystery");
		
		
		 JLabel boxTitle = new JLabel("Filter By Genre:");
		 boxTitle.setBounds(300, 440, 100, 100);
		 analysistView.add(boxTitle);
		
		 Box CheckBoxes = Box.createVerticalBox();
		 CheckBoxes.setBounds(300, 500, 100, 300);
		 CheckBoxes.add(horror);
		 CheckBoxes.add(thriller);
		 CheckBoxes.add(action);
		 CheckBoxes.add(adventure);
		 CheckBoxes.add(romance);
		 CheckBoxes.add(comedy);
		 CheckBoxes.add(sciFi);
		 CheckBoxes.add(documentary);
		 CheckBoxes.add(mystery);
		 analysistView.add(CheckBoxes);
		
		
		 ///////////////////////////////////////////////
		 /*
		 JCheckBox studio1 = new JCheckBox("Warner Bros");
		 JCheckBox studio2 = new JCheckBox("Universal");
		 JCheckBox studio3 = new JCheckBox("Marvel");
		 JCheckBox studio4 = new JCheckBox("DreamWorks");
		 JCheckBox studio5 = new JCheckBox("Sony");
		 JCheckBox studio6 = new JCheckBox("Paramount");
		 JLabel studioTitle = new JLabel("Filter By Studio:");
		 studioTitle.setBounds(100, 440, 100, 100);
		 analysistView.add(studioTitle);
		
		 Box studioBoxes = Box.createVerticalBox();
		 studioBoxes.setBounds(100, 500, 100, 300);
		 studioBoxes.add(studio1);
		 studioBoxes.add(studio2);
		 studioBoxes.add(studio3);
		 studioBoxes.add(studio4);
		 studioBoxes.add(studio5);
		 studioBoxes.add(studio6);
		
		 analysistView.add(studioBoxes);*/
		 ////////////////////////////////////////////////////
		
		 String UserName = "Carlos"; // get the users name and put it here later
		
		
		 userTitle.setBounds(100,-100, 1000, 300);
		 userTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		 userTitle.setText("Welcome Back " + UserName + "!");
		 analysistView.add(userTitle); // Add the name of the user to the 
		
		 TITLE.setBounds(100, -200, 1000, 300);
		 TITLE.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		 TITLE.setText("Aggie Movies For Researchers");
		
        
        JLabel dateRangeLabel = new JLabel();
        dateRangeLabel.setText("Top Ten Movies From (mm/dd/yyyy): ");
        dateRangeLabel.setBounds(100, 100, 300, 30);
        analysistView.add(dateRangeLabel);
        
        JTextField fromDate = new JTextField(10);
        fromDate.setBounds(310, 100, 100, 30);
        analysistView.add(fromDate);

        JLabel dateRangeLabel2 = new JLabel();
        dateRangeLabel2.setText("To (mm/dd/yyyy): ");
        dateRangeLabel2.setBounds(470, 100, 300, 30);
        analysistView.add(dateRangeLabel2);

        JTextField toDate = new JTextField(10);
        toDate.setBounds(575, 100, 100, 30);
        analysistView.add(toDate);

        JButton calculate = new JButton("Calculate");
        calculate.setBounds(750, 100, 100, 30);
        analysistView.add(calculate);


         String movie1 = "avengers"; // name of the 3 most popular or relevant movies
		 String movie2 = "inception";
		 String movie3 = "Fast & Furious 6"; // place holders
		 
		 int date1 = 2001;
		 int date2 = 2021;
		 
		 JLabel Title2 = new JLabel("Top 10 Most Watched Movies between " + date1 + "-" + date2);
		 Title2.setBounds(100, 50, 500, 200);
		 analysistView.add(Title2);

        JButton[] TopMovies = new JButton[10];
        for (int i=0; i<5; i++)
        {
            TopMovies[i] = new JButton(movie1);
            TopMovies[i].setBounds(100+ 225*i, 175, 200, 60); // x, y, width, height
            analysistView.add(TopMovies[i]);				  
        } 
        for (int i=5; i<10; i++)
        {
            TopMovies[i] = new JButton(movie2);
            TopMovies[i].setBounds(100+ 225*(i-5), 255, 200, 60);
            analysistView.add(TopMovies[i]);
        }
                  
        analysistView.setSize(10000, 10000); // initial size of the frame can still be changed by the user
        analysistView.setLayout(null);
        analysistView.setVisible(true);
	}
}
