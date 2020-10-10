import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//class that holds helper functions, for loading functions and what not
public class Utils {

    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();

        try{
            //bufferedreader is for reading input from a character based input stream txt file, reads the file that gets marked as "path"
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null)
                builder.append(line + "\n");

            br.close(); //closes the file stream when finished
        }catch (IOException e){  //catches input/output exceptions which happens when code has a problem reading, writing, or searching for a file or directory.
            e.printStackTrace();  //when an IOexception happens, throws this method which will print the class and line that is causing it
        }
        return builder.toString();  //converts everything that was appended in the StringBuilder to a regular string
    }

    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);  //takes the numbers that were converted to strings, and parses them back into an integer
        }catch(NumberFormatException e){ //this error happens when a number isn't succesfully parsed into an int, but the code tries to use it as one.
            e.printStackTrace();
            return 0;
        }
    }

}
