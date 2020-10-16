
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 
{
    public String findSimpleGene (String dna, String startCodon, 
    String stopCodon) 
    {
        int startIndex = dna.indexOf(startCodon);
        String result = "";
        if (startIndex == -1) 
        {
            return "";
        }
        int endIndex = dna.indexOf(stopCodon, startIndex+3);
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
        System.out.println("Gene is " + findSimpleGene(dna,"ATG","TAA"));
        
        dna = "gatgctataat";
        System.out.println("DNA is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna,"atg","taa"));
        
        dna = "CCAATGATCGACAAA";
        System.out.println("DNA is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna,"ATG","TAA"));
        
        dna = "CCGATCGACTAAAAA";
        System.out.println("DNA is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna,"ATG","TAA"));
        
        dna = "CCAATGATCCGACTAAAAA";
        System.out.println("DNA is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna,"ATG","TAA"));
    }
}
