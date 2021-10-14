import javax.swing.*;    
import java.awt.event.*;
import java.awt.*;
import java.sql.*;



public class contentView 
{
    private static String userId;
    public contentView(String userId) {
        this.userId = userId;
        System.out.println(userId);
    }

	public static void main(String[] args) // maybe pass the strings of the movies as arguments here
	{

        
        JScrollPane scrollpane;
        JFrame contentView = new JFrame(); // Make the frame
        query sqlQuery = new query();

         //Set up to call more queries
         //Queries are already set up in query.java
		
        
		JPanel scroll = new JPanel();
		int n = 50; // default value that should change
		JButton[] History = new JButton[n];
        scroll.setSize(600, 400);
        scroll.setLayout(new GridLayout(n, 10, 10, 0));
 
        for (int i=0; i<n; i++)
        {
            History[i] = new JButton("");
            scroll.add(History[i]);
        } 
        
        scrollpane = new JScrollPane(scroll);
        scrollpane.setBounds(100,150,1000,200);
        contentView.add(scrollpane);
        

        JLabel dateRangeLabel = new JLabel();
        dateRangeLabel.setText("Watch History From (mm-dd-yyyy): ");
        dateRangeLabel.setBounds(100, 130, 300, 30);
        contentView.add(dateRangeLabel);
        
        JTextField fromDate = new JTextField(10);
        fromDate.setBounds(310, 130, 100, 30);
        contentView.add(fromDate);

        JLabel dateRangeLabel2 = new JLabel();
        dateRangeLabel2.setText("To (mm-dd-yyyy): ");
        dateRangeLabel2.setBounds(470, 130, 300, 30);
        contentView.add(dateRangeLabel2);

        JTextField toDate = new JTextField(10);
        toDate.setBounds(575, 130, 100, 30);
        contentView.add(toDate);

        
        JButton calculate = new JButton("Calculate");
        calculate.setBounds(750, 130, 100, 30);
        contentView.add(calculate);
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startDate = fromDate.getText();
                String endDate = toDate.getText();
                ResultSet watchHistory = sqlQuery.WatchHistory(customerId, startDate, endDate); 
                try {
                    int i=0;
                    while (watchHistory.next()) {
                        History[i].setText(watchHistory.getString(1));
                        i++;
                    }
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
         });
		
		
		JLabel userTitle = new JLabel(); // a panel for a title
		JLabel TITLE = new JLabel(); // main title that should never be touched ever again
		
		userTitle.setBounds(100,-100, 1000, 300);
		userTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		userTitle.setText("Hi User: " + userId);
		contentView.add(userTitle); // Add the name of the user to the 
		
		TITLE.setBounds(100, -200, 1000, 300);
		TITLE.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		TITLE.setText("Aggie Movies For Researchers");

		
		 //Buttons for the user to click on the Featured movies
		JLabel recomended = new JLabel("Recomended for you");
		recomended.setBounds(100, 380, 1000, 200);
		recomended.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		contentView.add(recomended);
        
        JButton[] RecMovies = new JButton[10];
        
        for (int i=0; i<5; i++)
        {
            RecMovies[i] = new JButton("");
            RecMovies[i].setBounds(100 + 225*i, 485, 200, 60);
            contentView.add(RecMovies[i]);
        } 
        for (int i=5; i<10; i++)
        {
            RecMovies[i] = new JButton("");
            RecMovies[i].setBounds(100 + 225*(i-5), 565, 200, 60);
            contentView.add(RecMovies[i]);
        }
          
        contentView.setSize(10000, 10000); // initial size of the frame can still be changed by the user
        contentView.setLayout(null);
        contentView.setVisible(true);
	}

}