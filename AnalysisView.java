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

public class AnalysisView
{
	private static query sqlQuery;
	private static Graph<String> graph;
    // private Integer customerId;
    // private String endDate;
    // private String startDate;
    // private String genre;

	public AnalysisView() {
		sqlQuery = new query();
		graph = new Graph<String>();

		//construct the graph for fresh tomato numbers
		ArrayList<String> users = new ArrayList<>();
		ArrayList<String> titles = new ArrayList<>();

		//query for the users and store in users array list
		ResultSet resultForUsers = sqlQuery.GetUniqueUsers4andAbove(); 
		try {
			while (resultForUsers.next()) {
				users.add(resultForUsers.getString(1));
			}
		} catch (Exception err) {
			System.out.println(err);
		}

		//get the titles for each user that are rated 4 and above and then add to graph
		for (String user : users) {
			ResultSet titlesForUser =  sqlQuery.UserRatings4andAbove(user);
			try {
				while (titlesForUser.next()) {
					titles.add(titlesForUser.getString(1));
					graph.addEdge(user, titlesForUser.getString(1), true);
				}
			} catch (Exception err) {
				System.out.println(err);
			}
		}

		
	}
	public static void main(String[] args) // maybe pass the strings of the movies as arguments here
	{
		///////////////////////
		//Analysis Queries
		///////////////////////


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
		
		
		// JLabel boxTitle = new JLabel("Filter By Genre:");
		// boxTitle.setBounds(100, 125, 100, 100);
		// analysistView.add(boxTitle);
		
		// Box CheckBoxes = Box.createVerticalBox();
		// CheckBoxes.setBounds(100, 200, 100, 300);
		// CheckBoxes.add(horror);
		// CheckBoxes.add(thriller);
		// CheckBoxes.add(action);
		// CheckBoxes.add(adventure);
		// CheckBoxes.add(romance);
		// CheckBoxes.add(comedy);
		// CheckBoxes.add(sciFi);
		// CheckBoxes.add(documentary);
		// CheckBoxes.add(mystery);
		// analysistView.add(CheckBoxes);
		
		

		// JCheckBox studio1 = new JCheckBox("Warner Bros");
		// JCheckBox studio2 = new JCheckBox("Universal");
		// JCheckBox studio3 = new JCheckBox("Marvel");
		// JCheckBox studio4 = new JCheckBox("DreamWorks");
		// JCheckBox studio5 = new JCheckBox("Sony");
		// JCheckBox studio6 = new JCheckBox("Paramount");
		// JLabel studioTitle = new JLabel("Filter By Studio:");
		// studioTitle.setBounds(500, 125, 100, 100);
		// analysistView.add(studioTitle);
		
		JLabel genre = new JLabel("Enter Genre:");
		genre.setBounds(100, 125, 100, 30);
		analysistView.add(genre);

		JTextField genreValue = new JTextField(30);
		genreValue.setBounds(225, 125, 100, 30);
		analysistView.add(genreValue);


		JButton titlesByGenre = new JButton("submit");
        titlesByGenre.setBounds(100, 150, 100, 30);
        analysistView.add(titlesByGenre);
        titlesByGenre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = genreValue.getText();
				ResultSet result = sqlQuery.MoviesByGenre(input); 
                try {
                    String displayTitlesByGenre = "";
					int counter = 0;
                    while (result.next() && counter < 30) {
                        displayTitlesByGenre += result.getString(1) + "\n";
						counter++;
                    }
                    JOptionPane.showMessageDialog(null, displayTitlesByGenre);
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        });
		
		 JLabel dateRangeLabel = new JLabel();
		 dateRangeLabel.setText("Top Ten From (mm-dd-yyyy): ");
		 dateRangeLabel.setBounds(470, 130, 300, 30);
		 analysistView.add(dateRangeLabel);
		 
		 JTextField fromDate = new JTextField(10);
		 fromDate.setBounds(670, 130, 100, 30);
		 analysistView.add(fromDate);
 
		 JLabel dateRangeLabel2 = new JLabel();
		 dateRangeLabel2.setText("To (mm-dd-yyyy): ");
		 dateRangeLabel2.setBounds(470, 170, 300, 30);
		 analysistView.add(dateRangeLabel2);
 
		 JTextField toDate = new JTextField(10);
		 toDate.setBounds(670, 170, 100, 30);
		 analysistView.add(toDate);
 
		JButton calculate = new JButton("Submit");
        calculate.setBounds(470, 210, 100, 30);
        analysistView.add(calculate);
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startDate = fromDate.getText();
                String endDate = toDate.getText();
                ResultSet result2 = sqlQuery.TopRatedMovies(startDate, endDate); 
                try {
                    String displayTopRatedMovies = "";
                    while (result2.next()) {
                        displayTopRatedMovies += result2.getString(1) + "\n";
                    }
                    JOptionPane.showMessageDialog(null, displayTopRatedMovies);
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
		});

		// Box studioBoxes = Box.createVerticalBox();
		// studioBoxes.setBounds(500, 200, 100, 300);
		// studioBoxes.add(studio1);
		// studioBoxes.add(studio2);
		// studioBoxes.add(studio3);
		// studioBoxes.add(studio4);
		// studioBoxes.add(studio5);
		// studioBoxes.add(studio6);
		
		// analysistView.add(studioBoxes);
		
		
		
		
		
		String UserName = "Carlos"; // get the users name and put it here later
		
		
		userTitle.setBounds(100,-100, 1000, 300);
		userTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		userTitle.setText("Welcome Back " + UserName + "!");
		analysistView.add(userTitle); // Add the name of the user to the 
		
		TITLE.setBounds(100, -200, 1000, 300);
		TITLE.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		TITLE.setText("Aggie Movies For Researchers");
		
		
		
		String movie1 = "avengers"; // name of the 3 most popular or relevant movies
		String movie2 = "inception";
		String movie3 = "Fast & Furious 6";
		  
		// Buttons for the user to click on the Featured movies
        JButton button1 = new JButton(movie1); //"place holder should be changed to movie name
        JButton button2 = new JButton(movie2);
        JButton button3 = new JButton(movie3);
  
        // x axis, y axis, width, height
        button1.setBounds(100, 500, 300, 200);
        button2.setBounds(500, 500, 300, 200);
        button3.setBounds(900, 500, 300, 200);
  
        analysistView.add(button1); // buttons added to the frame
        analysistView.add(button2);
        analysistView.add(button3);
		
		AnalysisView thisObj = new AnalysisView();

		System.out.println(freshTomatoNumber(thisObj, "Justice League","Rambo: First Blood Part II"));
        
          
        analysistView.setSize(10000, 10000); // initial size of the frame can still be changed by the user
        analysistView.setLayout(null);
        analysistView.setVisible(true);
        ////////////////////////////////////////////////////////////////////////////
		/////////ABOVE IS ANALYST VIEWER //////////////////////////////////////////
        
	}

	public static ArrayList<String> freshTomatoNumber(AnalysisView thisObj, String src, String dest) {
		ArrayList<String> path = new ArrayList<String>();
		LinkedList<String> queue = new LinkedList<String>(); 
		Map<String, List<String> > map = thisObj.graph.map;
		HashSet<String> visited = new HashSet<>();
		Map<String,String> pred = new HashMap<>();
		boolean connected = false;

		// pred.put("null", src);
		queue.add(src);
		visited.add(src);
		while (!queue.isEmpty()) {
            String u = queue.remove();
			for (String w : map.get(u)) {
				// System.out.println(map.get(u));
				// System.out.println(visited.contains(w));
				if (visited.contains(w) == false) {
					// System.out.println("w: " + w);
					visited.add(w);
                    pred.put(u, w);
                    queue.add(w);
 
                    // stopping condition (when we find
                    // our destination)
                    if (w.equals(dest)) {
						connected = true;
						// System.out.println(dest + " " + w);
                    	break;
					}
						
                }
            }
			// System.out.println("queue: " + queue);
            
        }
		if(connected){
			String v = src;
			while(!v.equals(dest)) {
				path.add(v);
				v = pred.get(v);
			}
			path.add(dest);
		}
		else {
			System.out.println(visited);
			System.out.println(pred);
			System.out.println("Given source and destination are not connected");
		}
		

		// System.out.println("Graph:\n"
        //                    + thisObj.graph.toString());
		return path;

	}

}
