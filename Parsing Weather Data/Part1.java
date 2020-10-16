
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord min = null;
        for (CSVRecord record : parser) {
            if (Double.parseDouble(record.get("TemperatureF")) != -9999) {
                min = getSmallest(min, record, "TemperatureF");
            }
        }   
        return min;
    }  
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord temp = coldestHourInFile(parser);
        System.out.println("Coldest temperature is " + temp.get("TemperatureF") + " " 
        +  temp.get("DateUTC"));
    }   
    
    public String fileWithColdestTemperature() {
       DirectoryResource dr = new DirectoryResource();
       File fileName = null;
       CSVRecord min = null;
       for (File f : dr.selectedFiles()) {
           FileResource fr = new FileResource(f);
           CSVParser parser = fr.getCSVParser();
           CSVRecord record = coldestHourInFile(parser);
           if (min == null) {
               min = record;
               fileName = f;
           }
            
           else {
               double curTemp = Double.parseDouble(record.get("TemperatureF"));
               double coldestTemp = Double.parseDouble(min.get("TemperatureF"));
               if (curTemp < coldestTemp && curTemp != -9999) {
                   min = record;
                   fileName = f;
               }
           }
       }  
       return fileName.getName(); 
    } 
    
    public CSVRecord getSmallest(CSVRecord min, CSVRecord record, String string) {
        if (min == null) {
            min = record;
            }
            
        else {
            double curWeather = Double.parseDouble(record.get(string));
            double coldestWeather = Double.parseDouble(min.get(string));
            if (curWeather < coldestWeather) {
                min = record;
            }
        } 
        return min;    
    }    
    
    public void testFileWithColdestTemperature() {
        System.out.println("Coldestday was in file " + fileWithColdestTemperature());
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord temp = coldestHourInFile(parser);
        System.out.println("Coldest temperature is " + temp.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        parser = fr.getCSVParser();
        for (CSVRecord r : parser) {
           System.out.println(r.get("DateUTC") + " " + r.get("TemperatureF"));
        }  
    }
    
    //3
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord min = null;
        for (CSVRecord record : parser) {
            System.out.println(record.get("Humidity"));
            if ((record.get("Humidity")).equals("N/A") == false) {
                min = getSmallest(min, record, "Humidity");
            }
        }   
        return min;
    }    
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + 
        " " + csv.get("DateUTC"));
    }  
    
    //4
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord curHumid = null;
        CSVRecord lowestHumid = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            curHumid = lowestHumidityInFile(parser);
            if (lowestHumid == null) {
                lowestHumid = curHumid;
            }    
            else {
                lowestHumid = getSmallest(lowestHumid, curHumid, "Humidity");
            }    
        }   
        return lowestHumid;
    }    
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") +
        " " + record.get("DateUTC"));
    }    
    
    //5
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        double num = 0;
        for (CSVRecord r : parser) {
            sum += Double.parseDouble(r.get("TemperatureF"));
            num += 1;
        }    
        return sum/num;
    }    
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " 
        + averageTemperatureInFile(parser));
    }    
    
    public double testAverageTemperatureWithHighHumidityInFile(
    CSVParser parser, int value) {
        double sum = 0;
        double num = 0;
        for (CSVRecord r : parser) {
            if (Double.parseDouble(r.get("Humidity")) >= value) {
                num += 1;
                sum += Double.parseDouble(r.get("TemperatureF"));                
            }
        }    
        return sum/num;
    }   
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double mean = testAverageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(mean) == true) {
            System.out.println("No temperatures with that humidity");
        }    
        else {
            System.out.println("Average Temp when high Humidity is " + mean);    
        }    
    }   
}
