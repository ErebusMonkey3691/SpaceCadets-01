import java.io.*;
import java.net.*;

public class SCChallengeEmail {
    public static void main(String[] args) 
        throws IOException {
        // InputStreamReader conveerting bytes to a stream of characters for us :)
        BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
        
        // Take user input for email ID.
        System.out.print("Enter an email id: ");
        String emailID = myReader.readLine();

        // Construct web page address via string concatenation.
        String createdURL = "https://www.ecs.soton.ac.uk/people/" + emailID;

        // Create a URL object from this URL.
        URL userWebsite = new URL(createdURL);
        
        // Create a URLConnection object to connect to the URL.
        URLConnection urlConn = userWebsite.openConnection();

        // Wrap in buffered reader to read from the URL.
        BufferedReader urlReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

        String line;
        String name = "";
        
        while ((line = urlReader.readLine()) != null) {
            // Loop through each line of the HTML until we find the og:title property.
            if (line.contains("property=\"og:title\"")) {
                
                int nameStartIndex = line.indexOf("content=\"") + 9;
                // Name should be between nameStartIndex and length of the line - 4.
                name = line.substring(nameStartIndex, line.length()-4);
                
                break;
            }
        }

        if (name != "") {
            System.out.println("The provided email id is associated with the name: " + name + ".");
        } else {
            System.out.println("There is no publicly available name associated with that email id.");
        }

    }
}

// Possible improvements:
//    - Input sanitisation to ensure the user's input is able to be understood (e.g. no leading whitespace)
//    - Use regex to search for needed line maybe instead of the crude contains method?