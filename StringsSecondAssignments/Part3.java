
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    
    
    public String findGene(String dna, int startIndex)
    {
        int start = dna.indexOf("ATG",startIndex);
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
    
    public void printAllGenes(String dna)
    {
        int begin = 0;
        while (true)
        {
            String gene = findGene(dna,begin);
            if (gene.isEmpty())
            {
                    break;
            }  
            System.out.println(gene);
            begin = dna.indexOf(gene,begin) + gene.length();
        }   
    }
    
    public int countGenes(String dna) {
        int begin = 0;
        int num = 0;
        while (true)
        {
            String gene = findGene(dna,begin);
            if (gene.isEmpty())
            {
                    break;
            }  
            num += 1;
            begin = dna.indexOf(gene,begin) + gene.length();
        }  
        return num;
    }    
    
    public void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
        System.out.println(countGenes("ATGTATGCCCTAGT"));
        System.out.println(countGenes("TATGCCCTAGT"));
        System.out.println(countGenes("ATGTATGCCTAGCTATGTAAT"));
    }    
}
