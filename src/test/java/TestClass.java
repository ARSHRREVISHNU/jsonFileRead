import Pojo.JsonPojoClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

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
        Assert.assertEquals( jsonPojoClass.getData().getCustomers()
                .stream().filter(customer -> customer.getContact().getPhone().isEmpty())
                .count(), 0);
    }

    @Test
    public void emailAddressValidation(){

        boolean gmailpresent = jsonPojoClass.getData().getCustomers().stream()
                .filter(customer -> customer.getContact().getEmail()
                        .contains("@gmail.com")).count() >= 2;
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
