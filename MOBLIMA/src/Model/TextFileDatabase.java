import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileDatabase {

    /**
     * Write given data to the file and replace the old one.
     *
     * @param fileName Name of the file to be rewritten.
     * @param data Data to be written.
     * @throws IOException When not able to open a file.
     */
    public static void write(String fileName, List data) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i =0; i < data.size() ; i++) {
                out.println((String)data.get(i));
            }
        }
        finally {
            out.close();
        }
    }

    /**
     * Deletes records which match given parameters and writes results to given file.
     *
     * @param columnId Column id of parameter to be checked.
     * @param value Value that record has to match to be deleted.
     * @param allData List of all records to be checked.
     * @param fileName Name of file where results will be written.
     * @return List of all records after the delete.
     */
    public static ArrayList<String> delete(int columnId, String value, ArrayList<String> allData, String fileName){
        ArrayList<String> toDelete = new ArrayList<>();
        String[] testLength = allData.get(0).split(",");
        try{
            if(testLength.length-1<columnId){
                throw new ColumnIndexException("Column index out of bounds!");
            }
            else{
                for(int i = 0;i<allData.size();i++){
                    String[] tokens = allData.get(i).split(",");
                    if(tokens[columnId].equals(value)){
                        toDelete.add(allData.get(i));
                    }
                }
                for(String stringToDelete : toDelete){
                    allData.remove(stringToDelete);
                }
            }
            write(fileName, allData);
        }
        catch (ColumnIndexException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return allData;
    }

    /**
     * Appends a single line to the given file
     *
     * @param fileName File to which line will be appended
     * @param data String to be appended
     * @throws IOException When not able to open a file.
     */
    public static void writeSingle(String fileName, String data) throws IOException{
        PrintWriter out = new PrintWriter(new FileWriter(fileName, true));
        try{
            out.println(data);
        }
        finally {
            out.close();
        }
    }

    /**
     * Reads all data from given file.
     *
     * @param fileName File from which data should be read.
     * @return List of all records in the given file.
     * @throws IOException When not able to open a file.
     */
    public static ArrayList<String> read(String fileName) throws IOException {
        ArrayList data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(!line.equals("")) {
                    data.add(line);
                }
            }
        }
        finally{
            scanner.close();
        }
        return data;
    }

    /**
     * Returns all records which match given conditions.
     *
     * @param columnId Id of column which should be checked.
     * @param value Value that records has to match.
     * @param allData List of all records.
     * @return List of records that match given parameters.
     */
    public static ArrayList<String> retrieve(int columnId, String value, ArrayList<String> allData){
        ArrayList<String> results = new ArrayList<>();

        String[] testLength = allData.get(0).split(",");
        try{
            if(testLength.length-1<columnId){
                throw new ColumnIndexException("Column index out of bounds!");
            }
            else{
                for(String record : allData){
                    String[] tokens = record.split(",");
                    if(tokens[columnId].equals(value)){
                        results.add(record);
                    }
                }
            }
        }
        catch (ColumnIndexException e){
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Replaces the values in the records that match given conditions and saves them to the file.
     *
     * @param columnId Column id that should be checked.
     * @param oldValue Value to be replaced.
     * @param newValue New value of the field.
     * @param allData List of all records.
     * @param fileName Name of the file where records should be written.
     * @return List of all records after the update.
     */
    public static ArrayList<String> update(int columnId, String oldValue, String newValue, ArrayList<String> allData, String fileName){
        String[] testLength = allData.get(0).split(",");
        try{
            if(testLength.length-1<columnId){
                throw new ColumnIndexException("Column index out of bounds!");
            }
            else{
                for(int i = 0;i<allData.size();i++){
                    String[] tokens = allData.get(i).split(",");
                    if(tokens[columnId].equals(oldValue)){
                        tokens[columnId] = newValue;
                    }
                    allData.set(i,String.join(",", tokens));
                }
            }
            write(fileName, allData);
        }
        catch (ColumnIndexException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return allData;
    }

}

class ColumnIndexException extends Exception{
    public ColumnIndexException(String message){
        super(message);
    }
}