package my.app.util;

import lombok.AllArgsConstructor;
import my.app.models.Person;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import sun.rmi.transport.ObjectTable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExportExcelLibrary {

    private Class objectClass;
    private List objects;

    public ExportExcelLibrary(Class objectClass, List objects) {
        this.objectClass = objectClass;
        this.objects = objects;
    }

    List<String> headers=new ArrayList<String>();

    public String excute() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Field [] fields=objectClass.getDeclaredFields();

        for (Field f : fields){
            if(f.isAnnotationPresent(ExportExcel.class)){
                headers.add(f.getAnnotation(ExportExcel.class).headerName());
            }
            else{
                headers.add(f.getName());
            }
        }

        int rowNumber=0;
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet=workbook.createSheet();
        Row headerRow=sheet.createRow(rowNumber++);

        for (int i=0;i<headers.size();i++) {
            Cell cell=headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
        }

        for(Object o : objects){
            Row row=sheet.createRow(rowNumber++);
            int cellNumber=0;
            for(Field f : fields){

                String methodName="get"+Character.toUpperCase(f.getName().charAt(0))+f.getName().substring(1);

                Method method=objectClass.getMethod(methodName);
                Object result=method.invoke(o);
                Cell cellName=row.createCell(cellNumber++);
                cellName.setCellValue(String.valueOf(result!=null?result:""));
            }
        }

        workbook.write(new FileOutputStream("person.xls"));

        workbook.close();


        return "";

    }



}
