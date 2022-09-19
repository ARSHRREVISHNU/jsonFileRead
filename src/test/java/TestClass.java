import Pojo.JsonPojoClass;
import com.beust.ah.A;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass {

File file;
    ObjectMapper objectMapper;
    JsonPojoClass jsonPojoClass;

    boolean idValue;
    int count;
    @BeforeClass
public void fileRead() throws IOException {
    file = new File("/Users/testvagrant/IdeaProjects/assignmentOne/src/test/java/jsonFiles/JSONFile.json");


    objectMapper = new ObjectMapper();

    jsonPojoClass = objectMapper.readValue(file, JsonPojoClass.class);

     idValue = true;
     count = jsonPojoClass.getData().getCustomers().size();
}
    @Test
    public void nonEmptyPhoneNumberCheck() throws IOException {


        //System.out.println( jsonPojoClass.checkPhone());

       // System.out.println(jsonPojoClass.getData().getCustomers().get(1).getContact().getEmail());
       // List<JsonPojoClass.Data.Customers> customers1 =jsonPojoClass.getData().getCustomers().stream().filter(customers -> customers.getContact().getPhone().isBlank()).collect(Collectors.toList());
       //customers1.stream(customers2 -> customers1.)
           Assert.assertEquals( jsonPojoClass.getData().getCustomers().stream().filter(customer -> customer.getContact().getPhone().isEmpty()).count(), 0);
        //List<Customers> = jsonPojoClass.getData().getCustomers()
        //JsonPath js = new JsonPath(JSONfile);
      //  js.get("customers.size()");
}

@Test
public void emailAddressValidation(){

    boolean gmailpresent = jsonPojoClass.getData().getCustomers().stream().filter(customer -> customer.getContact().getEmail().contains("@gmail.com")).count() >= 2;
    Assert.assertEquals(gmailpresent, true);
}

@Test
public void customerPresentOrNot(){
   boolean countMoreThanOne = jsonPojoClass.getData().getCustomers().size() > 0;
   Assert.assertEquals(countMoreThanOne, true);

}

@Test
    public void customerIdSingleDigitCheck(){

    for (int i = 0; i<count; i++) {
        if(jsonPojoClass.getData().getCustomers().get(i).getId() > 9) {
            System.out.println(jsonPojoClass.getData().getCustomers().get(i).getId());
            idValue = false;
        }
    }
    Assert.assertEquals(idValue, true);
}
}
