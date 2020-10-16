
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna) {
        int startIndex = dna.indexOf("ATG");
        String result = "";
        if (startIndex == -1) 
        {
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex+3);
        if (endIndex == -1) 
        {
            return "";
        }
        
        if ((endIndex - startIndex) % 3 == 0) 
        {
            result = dna.substring(startIndex, endIndex+3);
        }
        return result;
    }
    
    public void testSimpleGene() 
        {
        String dna = "CCAATGATCGACTAAAAA";
        System.out.println("DNA is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        
        dna = "CCAATGATCGACAAA";
        System.out.println("DNA is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        
        dna = "CCGATCGACTAAAAA";
        System.out.println("DNA is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        }
}
