/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacecadets1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Thanuj Vasuthevan
 */
public class SpaceCadets1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        final String websiteURL = "https://www.ecs.soton.ac.uk/people/";
        String emailID = "";
        boolean inputLoop = true;
        
        
        while(inputLoop)
        {
            //Welcome message to user.
            System.out.println("Please type the ID of the page from which you would like a name, or press enter with an empty input to end the program:");
            
            //Takes user input using BufferedReader 
            try
            {
                emailID = inputReader.readLine();
            }
            catch(IOException exception)
            {
                System.out.println("The following error has occured:" + exception + "\n Please try again");
            }
            
            //Checks whether input is empty to terminate program
            if(emailID.isEmpty())
            {
                inputLoop = false;
            }
            //Forms URL object and BufferedReader object to go through every line of HTML of the web page to find the name associated with the page
            else
            {
                try 
                {
                    URL pageURL = new URL(websiteURL + emailID);
                    BufferedReader websiteReader = new BufferedReader(new InputStreamReader(pageURL.openStream()));
                    String htmlLine;
                    String associatedName = "";
                    
                    
                    while((htmlLine=websiteReader.readLine()) != null)
                    {
                        if(htmlLine.contains("property=\"name\""))
                        {
                            htmlLine = htmlLine.split("property=\"name\"")[1];
                            for(int i=0; i<htmlLine.length(); i++)
                            {
                                if(htmlLine.charAt(i)=='>')
                                {
                                    i++;
                                    while(i<htmlLine.length())
                                    {
                                        if(htmlLine.charAt(i)!='<')
                                        {
                                            associatedName+=htmlLine.charAt(i);
                                        }
                                        else
                                        {
                                            i=htmlLine.length();
                                        }
                                        i++;
                                    }
                                }
                            }
                            System.out.println("The name associated with this page is " + associatedName);
                            break;
                        }
                    }
                } 
                catch (Exception exception) {
                    inputLoop = false;
                    System.out.println("The following error has occured:" + exception + "\n Please try again");
                }
            }
        }
        
        System.out.println("Program Terminated.");
        
        
    }
    
}
