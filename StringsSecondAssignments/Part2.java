
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int index = stringb.indexOf(stringa);
        while (index != -1) {
            count += 1;
            index = stringb.indexOf(stringa,index+stringa.length());
        }
        return count;
        }
        
    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }    
}
