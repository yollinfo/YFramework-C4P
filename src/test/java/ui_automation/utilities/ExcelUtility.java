package ui_automation.utilities;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
public class ExcelUtility {
    private static XSSFSheet workSheet;
    private static XSSFWorkbook workBook;
    private static XSSFCell cell;
    private static XSSFRow row;
    private static MissingCellPolicy xRow;


    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            workBook = new XSSFWorkbook(ExcelFile);
            workSheet = workBook.getSheet(SheetName);
        } catch (Exception e) {

            throw (e);

        }
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception{
        try{
            cell = workSheet.getRow(RowNum).getCell(ColNum);
            return cell.getStringCellValue();
        }catch (Exception e){
            return"";
        }
    }


    public static void setCellData(String path, String value,  int RowNum, int ColNum) throws Exception {
        try{
            row  = workSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            workBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }catch(Exception e){

            throw (e);

        }

    }


    public static void createExcelAndWrite(String fileName, String value){
        workBook = new XSSFWorkbook();
        workSheet = workBook.createSheet("FIRST SHEET");
        row = workSheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue(value);
        try (FileOutputStream fos = new FileOutputStream(new File(fileName)))
        {
            workBook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}