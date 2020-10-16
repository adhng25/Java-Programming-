
/**
 * Write a description of Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Assignment {
    public void tester() {
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser(); 
       System.out.println(countryInfo(parser, "Nauru"));
       
       parser = fr.getCSVParser();
       listExportersTwoProducts(parser, "cotton", "flowers");
       
       parser = fr.getCSVParser();
       System.out.println(numberOfExporters(parser, "cocoa"));
       
       parser = fr.getCSVParser();
       bigExporters(parser, "$999,999,999,999");
    } 
    
    public String countryInfo(CSVParser parser, String country) {
        String string = "";
        for (CSVRecord record : parser) {
            if (record.get("Country").contains(country)) {
                String nation = record.get("Country");
                String export = record.get("Exports");
                String val = record.get("Value (dollars)");
                string = (nation + ": " + export + ": " + val);
                return string;
            } 
        }
        return "NOT FOUND";
    }  
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, 
    String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            } 
        }
    }  
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count += 1;
            } 
        }
        return count;
    }    
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String val = record.get("Value (dollars)");
            if (val.length() > amount.length()) {
                String nation = record.get("Country");
                System.out.println(nation + " " + val);
            } 
        }
    }   
}
