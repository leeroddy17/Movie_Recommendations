// Java Program to illustrate the GroupLayout class
// import java.awt.BorderLayout;
// import java.awt.Component;
// import java.awt.Container;
// import java.awt.Dimension;
// import java.awt.GridLayout;

// import java.awt.event.*;
// import java.awt.*;
// import java.sql.*;
// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import java.util.Map;
// import java.util.ArrayList;
// //import javafx.geometry.Insets;
// import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;    
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
// creating a class GroupLayoutExample
public class AnalysisView {
	
	// Main Method
	public static void main(String[] args)
	{

       //Toplevel
        JFrame frame = new JFrame("Analysis View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        query sqlQuery = new query();

        JPanel analysisView = new JPanel();
        analysisView.setLayout(new BoxLayout(analysisView, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // main panel that will hold all panels
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        String user = "User";
        JLabel pageTitle = new JLabel("Welcome " + user);
        
        panel2.add(Box.createRigidArea(new Dimension(20,0))); // adds padding
        panel2.add(pageTitle);
        ////////////////////////////////////////////////////////////////
       //Top Ten movies section
        JPanel panel3 = new JPanel();
        panel3.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        JLabel date1 = new JLabel("Top ten movies from:");
        JTextField fromDate = new JTextField(10);
        fromDate.setBounds(310, 130, 200, 30);
        fromDate.setMaximumSize(fromDate.getPreferredSize());
        JLabel date2 = new JLabel("To:");
        JTextField toDate = new JTextField(10);
        toDate.setBounds(310, 130, 200, 30);
        toDate.setMaximumSize(fromDate.getPreferredSize());
        JButton calculate = new JButton("calculate");
        //Spacing
        panel3.add(Box.createRigidArea(new Dimension(20,0)));
        panel3.add(date1);
        panel3.add(fromDate);
        panel3.add(Box.createRigidArea(new Dimension(5,0)));
        panel3.add(date2);
        panel3.add(toDate);
        panel3.add(Box.createRigidArea(new Dimension(5,0)));
        panel3.add(calculate);

        JPanel scrollTop10 = new JPanel(); // cult classics
        scrollTop10.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollTop10.setLayout(new BoxLayout(scrollTop10, BoxLayout.X_AXIS));
        
        JScrollPane topTen = new JScrollPane();        
		JPanel scroll = new JPanel();
		int n = 10; // default value that should change
		JButton[] History = new JButton[n];
		
		
       // scroll.setLayout(new GridLayout(n, 10, 0, 0));
        scroll.setLayout(new BoxLayout(scroll, BoxLayout.X_AXIS));

        for (int i=0; i<n; i++)
        {
            History[i] = new JButton("MOVIE");
            scroll.add(History[i]);
        } 
        
        topTen = new JScrollPane(scroll);
        topTen.setMaximumSize(new Dimension(1400,200));
        
        scrollTop10.add(Box.createRigidArea(new Dimension(20,0)));
        scrollTop10.add(topTen);
        
        ////////////////////////////////////////////////////////////////
        //Fresh Tomato Number
        JPanel panel4 = new JPanel();
        panel4.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        JLabel tomatoTitle = new JLabel("Fresh tomato number (pop up)");
        JButton tomato = new JButton("calculate");
        panel4.add(Box.createRigidArea(new Dimension(20,0)));
        panel4.add(tomatoTitle);
        panel4.add(Box.createRigidArea(new Dimension(5,0)));
        panel4.add(tomato);
        
        
        JPanel tomatoTitles = new JPanel();
        tomatoTitles.setAlignmentX(Component.LEFT_ALIGNMENT);
        tomatoTitles.setLayout(new BoxLayout(tomatoTitles, BoxLayout.X_AXIS));
        JLabel title1 = new JLabel("Title 1:");
        JLabel title2 = new JLabel("Title 2:");
        JTextField t1 = new JTextField(20);
        t1.setBounds(310, 130, 200, 30);
        t1.setMaximumSize(t1.getPreferredSize());
        JTextField t2 = new JTextField(20);
        t2.setBounds(310, 130, 200, 30);
        t2.setMaximumSize(t2.getPreferredSize());
        
        tomatoTitles.add(Box.createRigidArea(new Dimension(20,0)));
        tomatoTitles.add(title1);
        tomatoTitles.add(Box.createRigidArea(new Dimension(5,0)));
        tomatoTitles.add(t1);
        tomatoTitles.add(Box.createRigidArea(new Dimension(20,0)));
        tomatoTitles.add(title2);
        tomatoTitles.add(Box.createRigidArea(new Dimension(5,0)));
        tomatoTitles.add(t2);
        
        ////////////////////////////////////////////////////////////////
       //Cult Classics section
        JPanel panel5 = new JPanel(); // cult classics
        panel5.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        JLabel cultClassics = new JLabel("Top 20 Cult Classics");
        JButton cult = new JButton("calculate");
        panel5.add(Box.createRigidArea(new Dimension(20,0)));
        panel5.add(cultClassics);
        panel5.add(Box.createRigidArea(new Dimension(5,0)));
        panel5.add(cult);
        
        JPanel scrollTop20 = new JPanel(); // cult classics
        scrollTop20.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollTop20.setLayout(new BoxLayout(scrollTop20, BoxLayout.X_AXIS));
        
        JScrollPane top20 = new JScrollPane();        
		JPanel scroll2 = new JPanel();
		int n2 = 50; // default value that should change
		JButton[] list = new JButton[n];
		
		
       // scroll.setLayout(new GridLayout(n, 10, 0, 0));
        scroll2.setLayout(new BoxLayout(scroll2, BoxLayout.X_AXIS));

        for (int i=0; i<n2; i++)
        {
            list[i] = new JButton("MOVIE");
            scroll2.add(list[i]);
        } 
        
        top20 = new JScrollPane(scroll2);
        top20.setMaximumSize(new Dimension(1400,200));
        
        scrollTop20.add(Box.createRigidArea(new Dimension(20,0)));
        scrollTop20.add(top20);
        
                
        ////////////////////////////////////////////////////////////////
       //Indirect Director
        JPanel panel6 = new JPanel();
        panel6.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
        JLabel indirectDir = new JLabel("Indirect Director: ");
        JButton dir = new JButton("calculate");
        
        panel6.add(Box.createRigidArea(new Dimension(20,0)));
        panel6.add(indirectDir);
        panel4.add(Box.createRigidArea(new Dimension(5,0)));
        panel6.add(dir);
        
        JPanel panel7 = new JPanel();

        panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
        panel7.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField act = new JTextField(10);
        act.setBounds(310, 130, 200, 30);
        act.setMaximumSize(act.getPreferredSize());
        JLabel actor = new JLabel("actor:");
        String DIRECTOR = "";
        JLabel indirect = new JLabel("Indirect Director: " + DIRECTOR);
        
        panel7.add(Box.createRigidArea(new Dimension(20,0)));
        panel7.add(actor);
        panel7.add(act);
        panel7.add(Box.createRigidArea(new Dimension(20,0)));
        panel7.add(indirect);

        ////////////////////////////////////////////////////////////////
        //Hollywood pairs
        JPanel panel8 = new JPanel();
        panel8.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
        JLabel HPLabel = new JLabel("Hollywood Pairs");
        JButton HPCalc = new JButton("Calculate");
        panel8.add(Box.createRigidArea(new Dimension(20,0)));
        panel8.add(HPLabel);
        panel8.add(Box.createRigidArea(new Dimension(5,0)));
        panel8.add(HPCalc);

        // JPanel panel9 = new JPanel();
        // panel9.setAlignmentX(Component.LEFT_ALIGNMENT);
        // panel9.setLayout(new BoxLayout(panel9, BoxLayout.X_AXIS));
        // JLabel HPLabel = new JLabel("Hollywood Pairs");
        // JButton HPCalc = new JButton("Calculate");

        //Spacing and display 
        panel.add(panel2);
        panel.add(panel3);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(scrollTop10); // first scollable
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(panel4);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(tomatoTitles);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(panel5);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(scrollTop20);  // second scrollable
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(panel6);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(panel7);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(panel8);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        
        analysisView.add(Box.createVerticalStrut(50)); // strut for visibility
        Container contentPane = frame.getContentPane();
        contentPane.add(analysisView, BorderLayout.CENTER);
        

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
		
        //////////////////////////////////////////////////////////////////
        //listeners section
        //Adds the listener for the top rated movies
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startDate = fromDate.getText();
                String endDate = toDate.getText();
                ResultSet toprates = sqlQuery.TopRatedMovies(startDate, endDate); 
                try {
                    int i=0;
                    while (toprates.next()) {
                        History[i].setText(toprates.getString(1));
                        i++;
                    }
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
         });

        //Adds the Listener for recommendation
        // tomato.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         String titleA = t1.getText();
        //         String titleB = t2.getText();
        //         freshTomatoNumber(this, titleA, titleB);
        //         // ResultSet recs = sqlQuery.TopRecommendations(uid); 
        //         // try {
        //         //     int i=0;
        //         //     while (recs.next()) {
        //         //         recommendations[i].setText(recs.getString(1));
        //         //         i++;
        //         //     }
        //         // } catch (Exception err) {
        //         //     System.out.println(err);
        //         // }
        //     }
        // });

        //Adds the listener for not to Watch
        cult.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ResultSet recs = sqlQuery.CultClassics(); 
                try {
                    int i=0;
                    while (recs.next()) {
                        list[i].setText(recs.getString(1));
                        i++;
                    }
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        });

        dir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actor =act.getText();
                Map<String,ArrayList<String>> recs = sqlQuery.dataForIndirectDirector(actor); 
            }
        });

        HPCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Graph recs = sqlQuery.graphForHollyWoodPairs(); 
            }
        });
		//Did not know where to put this, this is the hollywood pairs query.
        // // System.out.println(tomatoObj.freshTomatoNumber("Justice League","Rambo: First Blood Part II"));
        // sqlQuery = new query();
                
        // Graph<String> hollywoodPairsGraph = sqlQuery.graphForHollyWoodPairs();
        // hollywoodPairsGraph.printDecimalWeightedMap();		
	}

	

	// public static ArrayList<String> freshTomatoNumber(AnalysisView thisObj, String src, String dest) {
	// 	ArrayList<String> path = new ArrayList<String>();
	// 	LinkedList<String> queue = new LinkedList<String>(); 
	// 	Map<String, List<String> > map = thisObj.graph.map;
	// 	HashSet<String> visited = new HashSet<>();
	// 	Map<String,String> pred = new HashMap<>();
	// 	boolean connected = false;

	// 	// pred.put("null", src);
	// 	queue.add(src);
	// 	visited.add(src);
	// 	while (!queue.isEmpty()) {
    //         String u = queue.remove();
	// 		for (String w : map.get(u)) {
	// 			// System.out.println(map.get(u));
	// 			// System.out.println(visited.contains(w));
	// 			if (visited.contains(w) == false) {
	// 				// System.out.println("w: " + w);
	// 				visited.add(w);
    //                 pred.put(u, w);
    //                 queue.add(w);
 
    //                 // stopping condition (when we find
    //                 // our destination)
    //                 if (w.equals(dest)) {
	// 					connected = true;
	// 					// System.out.println(dest + " " + w);
    //                 	break;
	// 				}
						
    //             }
    //         }
	// 		// System.out.println("queue: " + queue);
            
    //     }
	// 	if(connected){
	// 		String v = src;
	// 		while(!v.equals(dest)) {
	// 			path.add(v);
	// 			v = pred.get(v);
	// 		}
	// 		path.add(dest);
	// 	}
	// 	else {
	// 		System.out.println(visited);
	// 		System.out.println(pred);
	// 		System.out.println("Given source and destination are not connected");
	// 	}
		

	// 	// System.out.println("Graph:\n"
    //     //                    + thisObj.graph.toString());
	// 	return path;

	// }
}
