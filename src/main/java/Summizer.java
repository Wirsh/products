import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Summizer {
    public static void main(String[] args) {
        try {
            InputStream in =  new FileInputStream("/home/franc/menu.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            Map<String, Double> products = new HashMap<>();
            for (int i =0; i<61; i++) {
                Row row = iterator.next();
                if(row.getRowNum()!=0){
                    double sum;
                    String prodKey =row.getCell(2).getStringCellValue().toLowerCase().trim();
                    if(products.get(prodKey)== null){
                        sum = row.getCell(5).getNumericCellValue();
                    }
                    else {
                        sum = products.get(prodKey)+ row.getCell(5).getNumericCellValue();
                    }
                    products.put(prodKey,sum);
                }

            }
            for (String key: products.keySet()) {
                System.out.println(key +"  "+products.get(key));
            }
        } catch (IOException e) {
            System.out.println("Smth went wrong");
        }
    }
}