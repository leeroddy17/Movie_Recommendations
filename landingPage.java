import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class landingPage {
    
    public static void main(String[] args) {
        JFrame landingPage = new JFrame(); // Make the frame
        landingPage.setSize(10000, 10000 );
        landingPage.setVisible(true);
        landingPage.setLayout(null);
        landingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton contentViewButton = new JButton("Content View");
	    JButton analysisViewButton = new JButton("Analysis View");
        
        contentViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = (String)JOptionPane.showInputDialog(
                    landingPage,
                    "Enter your user ID", 
                    "Log In to content View",            
                    JOptionPane.PLAIN_MESSAGE
                );

                if(result != null) {
                    landingPage.dispose();
                    contentView contentViewPage = new contentView(result); 
                    contentViewPage.main(null);
                }
              
            }
         });

         analysisViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                landingPage.dispose();
                AnalysisView analysisViewPage = new AnalysisView(); 
                analysisViewPage.main(null);
            }
         });




        contentViewButton.setBounds(500,400,300,200);
        analysisViewButton.setBounds(900, 400, 300, 200);        
        landingPage.add(contentViewButton);
        landingPage.add(analysisViewButton);

       
    }
}
