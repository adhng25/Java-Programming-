
/**
 * Write a description of Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Assignment {
    public void totalBirths(FileResource fr) {
        int totalNames = 0;
        int boyNames = 0;
        int girlNames = 0;
        for (CSVRecord r : fr.getCSVParser(false)) {
            //totalNames += Integer.parseInt(r.get(2));
            totalNames += 1;
            if (r.get(1).equals("M")) {
                //boyNames += Integer.parseInt(r.get(2));
                boyNames += 1;
            }    
            else {
                //girlNames += Integer.parseInt(r.get(2));
                girlNames += 1;
            }    
        } 
        System.out.println("Total names " + totalNames);
        System.out.println("Boy names " + boyNames);
        System.out.println("Girl names " + girlNames);
    }    
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }  
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource();
        int track = 0;
        int rank = 0;
        for (CSVRecord r : fr.getCSVParser(false)) {
            if (r.get(1).equals(gender)) {
                track += 1;
                if (r.get(0).equals(name)) {
                    rank = track;
                    return rank;
                } 
            }
        } 
        return -1;
    }    
    
    public void testGetRank() {
        System.out.println("Rank is " + getRank(1971, "Frank", "M"));
        //System.out.println("Rank is " + getRank(1960, "Emily", "F"));
    } 
    
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource();
        int track = 0;
        for (CSVRecord r : fr.getCSVParser(false)) {
            if (r.get(1).equals(gender)) {
                track += 1;
                if (track == rank) {
                    return r.get(0);
                }    
            }
        }
        return "NO NAME";
    }
    
    public void testGetName() {
        System.out.println("Name is " + getName(1982, 450, "M"));
        //System.out.println("Name is " + getName(1980, 350, "F"));
    } 
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + 
        " if born in " + newYear);
    }
        
    public void testNameInYear() {
        //whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }    
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int year = 0; 
        int curYear = 1879;
        int rank = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int track = 0;
            int curRank = 0;
            curYear += 1;
            for (CSVRecord r : fr.getCSVParser(false)) {
                if (r.get(1).equals(gender)) {
                    track += 1;
                    if (r.get(0).equals(name)) {
                        curRank = track;
                    }    
                }
            }
            if (rank == 0 && curRank != 0) {
                rank = curRank;
                year = curYear;
            }    
            
            else if (curRank < rank && curRank != 0){
                rank = curRank;
                year = curYear;
            }  
        }
        if (year != 0) {
            return year;
        }    
        return -1;
    }
    
    public void testHighestRank() {
        //System.out.println("Year of Highest Rank " + yearOfHighestRank("Genevieve", "F"));
        System.out.println("Year of Highest Rank " + yearOfHighestRank("Mich", "M"));
    }    
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int num = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int track = 0;
            int curRank = 0;
            for (CSVRecord r : fr.getCSVParser(false)) {
                if (r.get(1).equals(gender)) {
                    track += 1;
                    if (r.get(0).equals(name)) {
                        curRank = track;
                    }    
                }
            }
            
            if (curRank != 0) {
                totalRank += curRank;
                num += 1;
            }   
        } 
        if (totalRank != 0) {
            return (double)totalRank/num;
        }    
        return -1;
    }  
    
    public void testAverageRank() {
        System.out.println("Average Rank " + getAverageRank("Robert", "M"));
        //System.out.println("Average Rank " + getAverageRank("Susan", "F"));
    } 
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource();
        int totalBirth = 0;
        for (CSVRecord r : fr.getCSVParser(false)) {
            if (r.get(1).equals(gender)) {
                totalBirth += Integer.parseInt(r.get(2));
                if (r.get(0).equals(name)) {
                    totalBirth -= Integer.parseInt(r.get(2));
                    break;
                } 
            }
        } 
        return totalBirth;
    }   
    
    public void testTotalBirthsRankedHigher() {
        System.out.println("Total births " + getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }    
}
