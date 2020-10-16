
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import edu.duke.StorageResource;
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
    
    public String findGene(String dna, int where)
    {
        int start = dna.indexOf("ATG",where);
        //int start = dna.indexOf("atg",where);
        if (start == -1)
        {
            return "";
        }
        else
        {
            int taa = findStopCodon(dna,start,"TAA");
            int tag = findStopCodon(dna,start,"TAG");
            int tga = findStopCodon(dna,start,"TGA");
            //int taa = findStopCodon(dna,start,"taa");
            //int tag = findStopCodon(dna,start,"tag");
            //int tga = findStopCodon(dna,start,"tga");
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
    
    public double cgRatio(String dna) {
        int count = 0;
        //int indexC = dna.indexOf("c");
        //int indexG = dna.indexOf("g");
        int indexC = dna.indexOf("C");
        int indexG = dna.indexOf("G");
        while (indexC != -1) {
            count += 1;
            indexC = dna.indexOf("C", indexC+1);
            //indexC = dna.indexOf("c", indexC+1);
        } 
        
        while (indexG != -1) {
            count += 1;
            //indexG = dna.indexOf("g", indexG+1);
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
    
    public void processGenes(StorageResource sr) {
        int num = 0;
        int genes = 0;
        for (String s : sr.data()) {  
            genes += 1;
            if (s.length() > 60) {
                System.out.println(
                "Strings that are longer than 60 characters " + s);
                num += 1;
            }
        }  
        System.out.println("The number of Strings in sr longer than 60 characters " + num);
        System.out.println("Genes " + genes);
        
        int track = 0;
        int longestGene = 0;
        for (String s : sr.data()) {
            if (s.length() > longestGene) {
                longestGene = s.length();
            }
            
            if (cgRatio(s) > 0.35) {
                System.out.println("Strings with C-G-ratio higher than 0.35 " + s);
                track += 1;
            }
        }  
        System.out.println(
        "The number of Strings in sr with C-G-ratio higher than 0.35 " + track);
        System.out.println(
        "The length of the longest gene " + longestGene);
    }    
    
    public void testProcessGenes() {
        //FileResource fr = new FileResource("brca1line.fa");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource storage = new StorageResource();
        storage = getAllGenes(dna);
        processGenes(storage);
        System.out.println("CTG appears " + countCTG(dna));
    }    
}
