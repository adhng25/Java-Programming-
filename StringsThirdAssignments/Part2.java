
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public double cgRatio(String dna) {
        int count = 0;
        int indexC = dna.indexOf("C");
        int indexG = dna.indexOf("G");
        while (indexC != -1) {
            count += 1;
            indexC = dna.indexOf("C", indexC+1);
        } 
        
        while (indexG != -1) {
            count += 1;
            indexG = dna.indexOf("G", indexG+1);
        } 
        return (double) count/dna.length();
    }  
    
    public int countCTG(String dna) {
        int index = dna.indexOf("CTG");
        int track = 0;
        while (index != -1) {
            track += 1;
            index = dna.indexOf("CTG", index+3);
        }
        return track;
    }    
    
    public void testMethod() {
        System.out.println(cgRatio("ATGCCATAG"));
        System.out.println(countCTG("ATGCTGCACTGACTG"));
    }    
}
