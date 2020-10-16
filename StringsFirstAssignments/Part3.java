
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 
{
    public boolean twoOccurrences (String stringa, String stringb)
    {
        int count = 0;
        int index = stringb.indexOf(stringa);
        if (index != -1)
        {
            count = 1;
            int index1 = stringb.indexOf(stringa, index+stringa.length());
            if (index1 != -1)
            {
                count = 2;
                return true;
            }   
        }   
        return false;
    }  
    
    public String lastPart (String stringa, String stringb)
    {
        int index = stringb.indexOf(stringa);
        if (index != -1)
        {
            return stringb.substring(index+stringa.length());
        }
        return stringb;
    }
    
    public void testing()
    {
        System.out.println(twoOccurrences("by","A story by Abby Long"));
        System.out.println(twoOccurrences("a","banana"));
        System.out.println(twoOccurrences("atg","ctgtatgta"));
        
        String stringa = "an";
        String stringb = "banana";
        System.out.println("The part of the string after " + stringa +
        " is " + lastPart(stringa,stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("The part of the string after " + stringa +
        " is " + lastPart(stringa,stringb));
    }
}
