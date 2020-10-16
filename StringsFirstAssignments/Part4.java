
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part4 
{
    public void printURL () {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String word : ur.words())
        {
            
            if (word.toLowerCase().indexOf("youtube.com") != -1)
            {
                int startIndex = word.indexOf("\"");
                int endIndex = word.lastIndexOf("\"");
                String s = word.substring(startIndex+1, endIndex);
                System.out.println(s);
            }    
        }
    }
    
    public static void main(String[] args) {
        Part4 url = new Part4();
        url.printURL();
    }
}
