
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int end = dna.indexOf(stopCodon,startIndex+3);
        while (end != -1)
        {
            if ((end - startIndex) % 3 == 0)
            {
                return end;
            }
            else
            {
                end = dna.indexOf(stopCodon,end+1);
            }    
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where)
    {
        int start = dna.indexOf("ATG",where);
        if (start == -1)
        {
            return "";
        }
        else
        {
            int taa = findStopCodon(dna,start,"TAA");
            int tag = findStopCodon(dna,start,"TAG");
            int tga = findStopCodon(dna,start,"TGA");
            int temp = Math.min(taa,tag);
            int minIndex = Math.min(temp,tga);
            if (minIndex == dna.length())
            {
                return "";
            } 
            return dna.substring(start,minIndex+3);
        }  
    }  
    
    public StorageResource getAllGenes(String dna)
    {
        int begin = 0;
        StorageResource sr =  new StorageResource();
        while (true)
        {
            String gene = findGene(dna, begin);
            if (gene.isEmpty())
            {
                    break;
            }  
            sr.add(gene);
            begin = dna.indexOf(gene, begin) + gene.length();
        } 
        return sr;
    }
    
    public void testGetAllGenes() {
        StorageResource genes = getAllGenes("ATGTATGCCTAGCTATGTAAT");
        for (String l : genes.data()) {
            System.out.println(l);
        }    
    }
}
