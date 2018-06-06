package Effort_Log;


import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.examples.CreateTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.BorderStyle;

/**
@author Sanchit & Pratik
*
* @version 1.2  10:38 pm          2018-04-18
*  The mainline of a JavaFX-based GUI implementation of Effort Log Project
* 
*/
/*
* Here we will learn how to create Excel file and header for the same.
*/

public class Merge_Sheet {

              public static final String SAMPLE_XLSX_FILE_PATH[] = 
            	  {""+"Amandeep_SD Faculty Effort Log v2.xlsx", 
            		  "Anil_SD Faculty Effort Log v2.xlsx",
            		  "Anuj_SD Faculty Effort Log v2.xlsx",
            		  "Gunjan_SD Faculty Effort Log v2.xlsx",
            		  "Hira_SD Faculty Effort Log v2.xlsx",
            		  "Kamal_SD Faculty Effort Log v2.xlsx",
            		  "Manju_SD Faculty Effort Log v2.xlsx",
            		  "Manoj_SD Faculty Effort Log v2.xlsx",
            		  "Meghna_SD Faculty Effort Log v2.xlsx",
            		  "Neera_SD Faculty Effort Log v2.xlsx",
            		  "Nikita_SD Faculty Effort Log v2.xlsx",
            		  "Rajeev_SD Faculty Effort Log v2.xlsx",
            		  "Ritu_SD Faculty Effort Log v2.xlsx",
            		  "Rohini_SD Faculty Effort Log v2.xlsx"};

              static XSSFSheet Sheets[] = new XSSFSheet[14] ;
              Collection<File> files;
              static XSSFWorkbook workbook;

              File exactFile;
              {
                  // Create a new workbook..............
            	  
            	  workbook = new XSSFWorkbook();
                  XSSFFont font = workbook.createFont();
                  font.setFontHeightInPoints((short) 50);
                  font.setFontName("Arial");
                  font.setItalic(true);
                  
                  // iterating the sheets with respect to ndx.............
                  for(int ndx = 0; ndx < SAMPLE_XLSX_FILE_PATH.length ; ++ndx ) {
                           Sheets[ndx] = workbook.createSheet(SAMPLE_XLSX_FILE_PATH[ndx]);
                 }   
              }
              
              public Merge_Sheet() {
              }
              
              void createExcelFile(){
                  FileOutputStream fos = null;
                  try {
                       fos = new FileOutputStream(new File("D:\\Semester - 2\\App Development\\Zip files\\Effort Log Analysis\\sanchit.xlsx"));
                          System.out.println("Processing Start.......");
                       for(int ndx= 0; ndx < SAMPLE_XLSX_FILE_PATH.length ; ++ndx)
                           {                                       
                      Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH[ndx]));
             
                // Retrieving the number of sheets in the Workbook...............
                      
                      Sheet sheet = workbook.getSheetAt(0);                        
                         DataFormatter dataFormatter = new DataFormatter();
                       int snumber=0;
                             Iterator<Row> rowIterator = sheet.rowIterator();
                                while (rowIterator.hasNext()) {
                                  Row row = rowIterator.next();
                                     Row row1 = Sheets[ndx].createRow(snumber);
                                    
               // here is the iteration of the columns for the current row starts................   
                                    
                        Iterator<Cell> cellIterator = row.cellIterator();
                           int sp=0;
                           while (cellIterator.hasNext()) {
                             Cell cell = cellIterator.next();
                             String cellValue = dataFormatter.formatCellValue(cell);
                                Cell cell1 = row1.createCell(sp);
                                cell1.setCellValue(cellValue);
                                sp++;
                                     }
                                snumber++;
                              }
                            } 
                       
                      System.out.println("please wait for a while........");
              
                      workbook.write(fos);
                      workbook.close();
                      fos.flush();
                      fos.close();
                      } 
                  
                  catch (Exception e) {
                      e.printStackTrace();
                  }
          }
          
              public static void main(String args[])  {
                      Merge_Sheet S = new Merge_Sheet();
                                 S.createExcelFile(); 
                     System.out.println("File has been created ");                                
              }
              
}
