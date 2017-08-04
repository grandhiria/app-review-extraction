
package sg.edu.ntu.medicine.lkc.androidapp_reviewextraction;
import java.io.IOException;

/*
 *
 * @author: Venugopal Giridharan
 * Purpose: Extracts user review for a single app, and put those data into an excel sheet
 *
 */
public class AndroidApp_ReviewExtraction {


    public static void main(String[] args) throws IOException {
        ExecuteScript obj=new ExecuteScript();
        obj.createExcelFile();
        obj.jsonOutput();
    }
    
}
