package my.app;

import my.app.models.Person;
import my.app.util.ExportExcelLibrary;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main  {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {

        List<Person> person=new ArrayList<Person>();
        person.add(new Person("June" ,12));
        person.add(new Person("June" ,12));
        person.add(new Person("June" ,12));
        person.add(new Person("June" ,12));
        person.add(new Person("June" ,12));
        person.add(new Person("June" ,12));
        person.add(new Person("June" ,12));
        person.add(new Person("June" ,12));



        ExportExcelLibrary library=
                new ExportExcelLibrary(Person.class,person);

        library.excute();
    }
}
