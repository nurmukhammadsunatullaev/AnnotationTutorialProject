package my.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.app.util.ExportExcel;

@Getter
@Setter
@NoArgsConstructor

public class Person {

   @ExportExcel(headerName = "Person Full Name")
   private String fullName;
   @ExportExcel(headerName = "Person Age")
   private Integer age;

   @ExportExcel(headerName = "Other Information")
   private Double salary;

   private String someText;

   public Person(String fullName, Integer age) {
      this.fullName = fullName;
      this.age = age;
   }
}
