/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.ntu.medicine.lkc.androidapp_reviewextraction;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import sg.edu.ntu.medicine.lkc.json.domain.AppPackage;
import sg.edu.ntu.medicine.lkc.json.domain.Review;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/*
 *
 * @author: Venugopal Giridharan
 * Purpose: Extracts user review for a single app, and put those data into an excel sheet
 *
 */
public class ExecuteScript {

    private HSSFWorkbook wb;
    boolean excelFileCreated;

    private HSSFSheet sheet[] = new HSSFSheet[1];
    int rowNumber = 1;
    int row = 1;

    public void jsonOutput() throws MalformedURLException, IOException {
        //String jsonResponse = null;
        Properties p = new Properties();
        InputStream input = null;
        LocalDate start_date = null;
        String token = new String();
        String package_name = new String();
        String api_endpoint = new String();
        String output_file = new String();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        try {
            //get the properties file
            input = new FileInputStream("/opt/review.properties");
            // load a properties file
            p.load(input);
            // get the property value
            start_date = LocalDate.parse(p.getProperty("start_date"), f);
            token = p.getProperty("access_token");
            package_name = p.getProperty("package_name");
            api_endpoint = p.getProperty("api_endpoint");
            output_file = p.getProperty("file_output");
            System.out.println("The output file is: " +output_file);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        int i = 0;
        String jsonResponse = new String();
        LocalDate end_date = LocalDate.now();

        while (start_date.isBefore(LocalDate.now())) {
            //increment the days
            //The reviews and comments will be extracted for the dates between start_date and end_date
            end_date = start_date.plusDays(10);

            String url = api_endpoint + package_name + "&access_token=" + token + "&start_date=" + start_date + "&end_date=" + end_date + "&limit=100" + "";
            //https://data.42matters.com/api/v2.0/android/apps/reviews.json?p=com.mysugr.android.companion&access_token=e196c13938cf6c497594cad2ceb8b30385954dfa&start_date=2017-05-07&end_date=2017-05-17&limit=100
            start_date = end_date;

            URL myURL = new URL(url);
            HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();

            myURLConnection.setRequestMethod("GET");
            myURLConnection.setRequestProperty("Content-Type", "application/json");
            myURLConnection.setRequestProperty("Content-Language", "en-US");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);
            try {
                InputStream inputStr = myURLConnection.getInputStream();
                String encoding = myURLConnection.getContentEncoding() == null ? "UTF-8"
                        : myURLConnection.getContentEncoding();
                jsonResponse = IOUtils.toString(inputStr, encoding);

                Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create(); //this is done so that json key names with underscores are not ignored
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(jsonResponse);
                String prettyJsonString = gson.toJson(je);
                System.out.println(prettyJsonString);

                AppPackage results = gson.fromJson(prettyJsonString, AppPackage.class);
                List<Review> tempData = results.getReviews();
                updateExcelFile(results, tempData, end_date);
                try (FileOutputStream fileOutput = new FileOutputStream(output_file)) {
                    wb.write(fileOutput);
                    fileOutput.flush();
                }

            }  catch (IOException e) {
                //go to the next token when an IOException is thrown
                //go to 42matters.com, create an account to get an access token
                String[] tokenString = {"68c28a43656b43fc837cd6731c7cea6c53262768", "68c28a43656b43fc837cd6731c7cea6c53262768", "b2eab160749d943c691f33f4555161cf05c0b147", "08efdbf01fe4b54669aeb09e4745ddcc3d96da3d"};
                try {
                    if (token == tokenString[i]) {
                        token = tokenString[i + 1];
                    }
                    i = i++;

                    e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    void createExcelFile() {
        wb = new HSSFWorkbook();

        sheet[0] = wb.createSheet("Reviews");
        // Create row at index zero ( Top Row)
        HSSFRow row = sheet[0].createRow((short) 0);
        String[] headers = {"Package Name", "Title", "Developer", "Market URL", "Average Rating", "price", "App Version", "User Id", "Language", "User Review", "Review Date", "User Rating", "Total Reviews", "Review Start Date", "Review End Date"};
        for (int i = 0; i < headers.length; i++) {

            HSSFCell cell = row.createCell((short) i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(headers[i]);
            excelFileCreated = false;
        }

    }

    void updateExcelFile(AppPackage results, List<Review> tempData, LocalDate end_date) {
        rowNumber = row;
        LocalDate end_date1 = end_date;
        for (Review data : tempData) {

            HSSFRow row1 = sheet[0].createRow((short) rowNumber++);
            int k = 0;

            //cell 1
            if (tempData.indexOf(data) == 0) {
                HSSFCell cell1 = row1.createCell((short) (k));
                cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell1.setCellValue(results.getPackageName());

                //cell 2
                HSSFCell cell2 = row1.createCell((short) (k + 1));
                cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2.setCellValue(results.getTitle());

                //cell 3
                HSSFCell cell3 = row1.createCell((short) (k + 2));
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell3.setCellValue(results.getDeveloper());

                //cell 4
                HSSFCell cell4 = row1.createCell((short) (k + 3));
                cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell4.setCellValue(results.getMarketUrl());

                //cell 5
                HSSFCell cell5 = row1.createCell((short) (k + 4));
                cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell5.setCellValue(results.getRating());

                //cell 6
                HSSFCell cell6 = row1.createCell((short) (k + 5));
                cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell6.setCellValue(results.getPrice());

                HSSFCell cell13 = row1.createCell((short) (k + 12));
                cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell13.setCellValue(results.getTotalReviews());

                HSSFCell cell14 = row1.createCell((short) (k + 13));
                cell14.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell14.setCellValue(results.getStartDate());

                HSSFCell cell15 = row1.createCell((short) (k + 14));
                cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell15.setCellValue(end_date1.toString());
            }
            //_______________________________
            //cell 7
            HSSFCell cell7 = row1.createCell((short) (k + 6));
            cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell7.setCellValue(data.getAppVersion());

            //cell 8
            HSSFCell cell8 = row1.createCell((short) (k + 7));
            cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell8.setCellValue(data.getAuthorId());

            //cell 9
            if (data.getLang() != null) {
                HSSFCell cell9 = row1.createCell((short) (k + 8));
                cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell9.setCellValue(data.getLang());
            } else {
                HSSFCell cell9 = row1.createCell((short) (k + 8));
                cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell9.setCellValue("NULL");
            };

            //cell 10
            if (data.getContent() != null) {
                HSSFCell cell10 = row1.createCell((short) (k + 9));
                cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell10.setCellValue(data.getContent());
            } else {
                HSSFCell cell10 = row1.createCell((short) (k + 9));
                cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell10.setCellValue("NULL");
            };

            //cell 11
            if (data.getDate() != null) {
                HSSFCell cell11 = row1.createCell((short) (k + 10));
                cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell11.setCellValue(data.getDate());
            } else {
                HSSFCell cell11 = row1.createCell((short) (k + 10));
                cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell11.setCellValue("NULL");
            }
            //cell 12
            HSSFCell cell12 = row1.createCell((short) (k + 11));
            cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell12.setCellValue(data.getRating());

            System.out.println("**************************************************************\n");
        }
        row = rowNumber;
    }
}
