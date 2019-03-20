/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileAndUserIO;

import Utilities.StrUtilities;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Dorian
 */
public class FileIO
{
    
    /**
     * Reads a .txt file and returns it as a String
     * @param filename the text file to read
     * @return a string containing the content of the filename
     */
    public static String readTextFile(String filename)
    {
        String res = "";
        String line = null;
        try
        {
            //creating vars
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
          
            //reading
            while((line = bufferedReader.readLine()) != null)
            {
                res += line;
                res += "\n";
            }
            // removes the last line of the string
            res = StrUtilities.chomp(res);
            
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file : ");
            System.err.println(filename);
            System.out.println("--");
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            System.out.println("Reading error with file : ");
            System.err.println(filename);
            System.out.println("--");
            ex.printStackTrace();
        }
        return res;
    }
}
