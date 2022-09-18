import Pojo.JsonPojoClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestClass {

File file;

@BeforeClass
public void fileRead(){
    file = new File("/Users/testvagrant/IdeaProjects/assignmentOne/src/test/java/jsonFiles/JSONFile.json");
}
    @Test
    public void readJSONFile() throws IOException {


    ObjectMapper objectMapper = new ObjectMapper();

        JsonPojoClass jsonPojoClass = objectMapper.readValue(file, JsonPojoClass.class);
        System.out.println(jsonPojoClass.getData().getCustomers());
    }
}
