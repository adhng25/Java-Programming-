
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 
{
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
    
    public void testFindStopCodon()
    {
        System.out.println(findStopCodon("TAGATGCGATGGACTTAAATAT", 0, "TAA"));
        System.out.println(findStopCodon("TAGATGCGATGGACTTAGATAT", 0, "TAG"));
        System.out.println(findStopCodon("TAGATGCGATGGACTTGAATAT", 0, "TGA"));
        System.out.println(findStopCodon("TAGATGCGATGGACTTATAT", 0, "TAA"));
    }   
    
    public void testFindGene()
    {
        String dna = "TAGCTAGCTAGC";
        System.out.println("The dna string is: " + dna);
        System.out.println("The gene is " + findGene(dna,0));
        
        dna = "TATGCTAGCTTAGAGC";
        System.out.println("The dna string is: " + dna);
        System.out.println("The gene is " + findGene(dna,0));
        
        dna = "TATGCTAGCTTAAGCGTGA";
        System.out.println("The dna string is: " + dna);
        System.out.println("The gene is " + findGene(dna,0));
        
        dna = "TATGCTAGCTAGC";
        System.out.println("The dna string is: " + dna);
        System.out.println("The gene is " + findGene(dna,0));
        
        dna = "TATGCTAGCTTAACATGTGAC";
        System.out.println("The dna string is: " + dna);
        System.out.println("The gene is " + findGene(dna,0));
    }    
    
    public void printAllGenes(String dna)
    {
        int begin = 0;
        while (true)
        {
            String gene = findGene(dna, begin);
            if (gene.isEmpty())
            {
                    break;
            }  
            System.out.println(gene);
            begin = dna.indexOf(gene, begin) + gene.length();
        }   
    }
    
    public static void main (String[] args)
    {
        Part1 p1 = new Part1();
        //p1.testFindStopCodon();
        //p1.testFindGene();
        p1.printAllGenes("TATGCTAGCTTAACATGTAGC");
    }   
     
}

