import Pojo.JsonPojoClass;
import Pojo.JsonPojoClass2;
import com.beust.ah.A;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass {

    File file;
    File file2;
    ObjectMapper objectMapper;
    JsonPojoClass jsonPojoClass;
    JsonPojoClass2 jsonPojoClass2;


    boolean idValue;
    int count;
    @BeforeClass
    public void fileRead() throws IOException {
        file = new File("./src/test/java/jsonFiles/JSONFile.json");
        file2 = new File("./src/test/java/jsonFiles/JSONFile2.json");

        objectMapper = new ObjectMapper();

        //Response response = (Response) file;
        //jsonPojoClass = response.as(JsonPojoClass.class);
        jsonPojoClass =   objectMapper.readValue(file, JsonPojoClass.class);
        jsonPojoClass2 = objectMapper.readValue(file2, JsonPojoClass2.class);
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

    //JsonFile 2 operations



    @Test
    public void toCheckforeignPlayers(){
        int totalPlayers = jsonPojoClass2.getPlayer().size();
        int indianPlayers = jsonPojoClass2.getPlayer().stream().filter(t -> t.getCountry().contentEquals("India")).collect(Collectors.toList()).size();
        int foreignPlayer = totalPlayers - indianPlayers;
            Assert.assertEquals(foreignPlayer, 4);
    }


    @Test
    public void oneWicketKeeperValidation(){

        boolean atleastOneWicketKeeper = jsonPojoClass2.getPlayer().stream().filter(t -> t.getRole().contentEquals("Wicket-keeper")).count()>0;

        Assert.assertEquals(atleastOneWicketKeeper, true);


    }
}
