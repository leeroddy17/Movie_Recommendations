import javax.swing.*;    
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;



public class contentView 
{
    private static String userId;
    public contentView(String userId) {
        this.userId = userId;
        System.out.println(userId);
    }

	public static void main(String[] args) // maybe pass the strings of the movies as arguments here
	{
        
        //Frame in which everything is held
        JFrame frame = new JFrame("Content View");
        //Panel that organizes the objects
        JPanel contentView = new JPanel();
        contentView.setLayout(new BoxLayout(contentView, BoxLayout.PAGE_AXIS));
        //Setting ip the queries
        query sqlQuery = new query();
        JLabel userTitle = new JLabel(); // a panel for a title
		
		userTitle.setBounds(100,-100, 1000, 300);
		userTitle.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		userTitle.setText("Hello User: " + userId+"! ");
        userTitle.setBorder(BorderFactory.createLineBorder(Color.black));
		contentView.add(userTitle); // Add the name of the user to the 
        //////////////////////////////////////////////////////////////////
        
        //Creating the Date Labels and ranges for the watch history command
        JPanel CalcWatchHistory = new JPanel();
        CalcWatchHistory.setAlignmentX(Component.LEFT_ALIGNMENT);
        CalcWatchHistory.setLayout(new BoxLayout(CalcWatchHistory, BoxLayout.LINE_AXIS));
        
        JLabel WatchHistoryTitle = new JLabel(); // a panel for a title

		WatchHistoryTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		WatchHistoryTitle.setText("Watch History");
        CalcWatchHistory.add(WatchHistoryTitle);

        CalcWatchHistory.add(Box.createHorizontalStrut(10));

        JButton calculate = new JButton("Calculate");
        calculate.setBounds(100, 130, 100, 30);
        CalcWatchHistory.add(calculate);
        contentView.add(CalcWatchHistory);

        JPanel HistoryOptions1 = new JPanel();
        HistoryOptions1.setAlignmentX(Component.LEFT_ALIGNMENT);
        HistoryOptions1.setLayout(new BoxLayout(HistoryOptions1, BoxLayout.LINE_AXIS));

        JLabel dateRangeLabel = new JLabel();
        dateRangeLabel.setText("From (mm-dd-yyyy): ");
        dateRangeLabel.setBounds(100, 130, 300, 30);
        HistoryOptions1.add(dateRangeLabel);
        
        JTextField fromDate = new JTextField(10);
        fromDate.setBounds(310, 130, 200, 30);
        fromDate.setMaximumSize(fromDate.getPreferredSize());
        HistoryOptions1.add(fromDate);

        JLabel dateRangeLabel2 = new JLabel();
        dateRangeLabel2.setText("To (mm-dd-yyyy): ");
        dateRangeLabel2.setBounds(470, 130, 300, 30);
        HistoryOptions1.add(dateRangeLabel2);

        JTextField toDate = new JTextField(10);
        toDate.setBounds(575, 130, 200, 30);
        toDate.setMaximumSize(toDate.getPreferredSize());
        HistoryOptions1.add(toDate);
        contentView.add(HistoryOptions1);
        ///////////////////////////////////////////////////////////////////

        //Setting up the scroll for the watch history
        JScrollPane scrollpane;
        //Setting up the scrollbar for history
		JPanel scroll = new JPanel();
		int n = 50; // default value that should change
		JButton[] History = new JButton[n];
        scroll.setSize(600, 300);
        //scroll.setLayout(new GridLayout(n, 10, 10, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.ipady=30;
        gbc.ipadx=50;
        scroll.setLayout(new GridBagLayout());
        
        //scroll.setLayout(new BoxLayout(scroll, BoxLayout.X_AXIS));

        for (int i=0; i<n; i++)
        {
            History[i] = new JButton("");
            scroll.add(History[i], gbc);
        } 
        
        scrollpane = new JScrollPane(scroll);
        //scrollpane.setBounds(100,180,1000,250);
        contentView.add(scrollpane);
        //////////////////////////////////////////////////////////////////
		//Buttons for the user to click on the Featured movies
        JPanel recommendedPanel = new JPanel();
        recommendedPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        recommendedPanel.setLayout(new BoxLayout(recommendedPanel, BoxLayout.LINE_AXIS));
		JLabel recomended = new JLabel("Recomended for you");
		recomended.setBounds(100, 380, 1000, 200);
		recomended.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		recommendedPanel.add(recomended);

        recommendedPanel.add(Box.createHorizontalStrut(10));

        JButton calcRec = new JButton("Calculate");
        calcRec.setBounds(100, 130, 100, 30);
        recommendedPanel.add(calcRec);
        contentView.add(recommendedPanel);

        //Creating the Recommendations Scrollbar
        JScrollPane recommendationPane;
        JPanel scrollRec = new JPanel();
        int numRecs = 10;
        JButton[] recommendations = new JButton[numRecs];
        scrollRec.setSize(600, 400);
        //scrollRec.setLayout(new GridLayout(numRecs, 10, 10, 0));
        //scrollRec.setLayout(new BoxLayout(scrollRec, BoxLayout.X_AXIS)); //////////////// changed
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx=1;
        gbc2.weighty=1;
        gbc2.ipady=30;
        gbc2.ipadx=50;
        scrollRec.setLayout(new GridBagLayout());
        
        
        
        for (int i=0; i<numRecs; i++)
        {
            recommendations[i] = new JButton("");
            scrollRec.add(recommendations[i], gbc2);
        }

        recommendationPane = new JScrollPane(scrollRec);
        recommendationPane.setBounds(100,515,1000,250);
        
        contentView.add(recommendationPane);
        //////////////////////////////////////////////////////////////////
        //Buttons for the user to click on the Featured movies
        JPanel notPanel = new JPanel();
        notPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        notPanel.setLayout(new BoxLayout(notPanel, BoxLayout.LINE_AXIS));

		JLabel notRecommended = new JLabel("What NOT To Watch");
		notRecommended.setBounds(100, 715, 1000, 200);
		notRecommended.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		notPanel.add(notRecommended);
        
        notPanel.add(Box.createHorizontalStrut(10));

        JButton calcNotRec = new JButton("Calculate");
        calcNotRec.setBounds(100, 130, 100, 30);
        notPanel.add(calcNotRec);
        contentView.add(notPanel);

        //Creating the what not to watch scrollbar
        JScrollPane notToWatchPane;
        JPanel scrollNotToWatch = new JPanel();
        int numNotSamples = 10;
        JButton[] notRecs = new JButton[numNotSamples];
        scrollNotToWatch.setSize(600, 400);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.BOTH;
        gbc3.weightx=1;
        gbc3.weighty=1;
        gbc3.ipady=30;
        gbc3.ipadx=50;
        scrollNotToWatch.setLayout(new GridBagLayout());
        
        for (int i=0; i<numNotSamples; i++)
        {
            notRecs[i] = new JButton("");
            scrollNotToWatch.add(notRecs[i], gbc3);
        }

        notToWatchPane = new JScrollPane(scrollNotToWatch);
        notToWatchPane.setBounds(100,850,1000,250);
        contentView.add(notToWatchPane);

        //////////////////////////////////////////////////////////////////
        //Adding a strut at the bottom to increase visibility
        contentView.add(Box.createVerticalStrut(50));

        //////////////////////////////////////////////////////////////////
        //listeners section
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer uid = Integer.parseInt(userId);
                String startDate = fromDate.getText();
                String endDate = toDate.getText();
                try{
                    ResultSet watchHistory = sqlQuery.WatchHistory(uid, startDate, endDate); 
                    int i=0;
                    while (watchHistory.next()) {
                        History[i].setText(watchHistory.getString(1));
                        i++;
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                    System.out.println(err);
                }
            }
         });
         //Adds the Listener for recommendation
        calcRec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer uid = Integer.parseInt(userId);
                try{
                    ResultSet recs = sqlQuery.TopRecommendations(uid); 
                    int i=0;
                    while (recs.next()) {
                        recommendations[i].setText(recs.getString(1));
                        i++;
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Unexpected error");
                    System.out.println(err);
                }
            }
        });

        //Adds the listener for not to Watch
        calcNotRec.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Integer uid = Integer.parseInt(userId);
                try{
                    ResultSet recs = sqlQuery.UserBeware(uid); 
                    int i=0;
                    while (recs.next()) {
                        System.out.println(recs.getString(1));
                        notRecs[i].setText(recs.getString(1));
                        i++;
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Unexpected error");
                    System.out.println(err);
                }
            }
        });

        Container contentPane = frame.getContentPane();
        contentPane.add(contentView, BorderLayout.CENTER);
        //contentPane.setVisible(true);
        frame.setSize(500, 500); // initial size of the frame can still be changed by the user
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}