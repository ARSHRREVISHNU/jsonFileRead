package Pojo;

import lombok.Getter;
import java.util.*;

@Getter
public class JsonPojoClass {

 private Data data;



 public boolean checkPhone() {
  return  data.customers
          .stream()
          .filter(customers->customers.getContact().getPhone().isEmpty()).findFirst().isEmpty();
 }

 @Getter
 public static class Data{

  private List<Customers> customers;






  @Getter
  public static class Customers{

   private int id;
   private String name;
   private Contact contact;

   @Getter
   public static class Contact{
    private String phone;
    private String email;

   }
  }
 }
}
