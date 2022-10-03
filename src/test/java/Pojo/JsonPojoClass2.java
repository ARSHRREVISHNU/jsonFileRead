package Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class JsonPojoClass2 {

    //@JsonProperty("name")
    private String name;
    //@JsonProperty("location")
    private String location;
    //@JsonProperty("Players")
    private List<Player> player;

    @Getter
    public static class Player{
        private String name;
        private String country;
        private String role;
        @JsonProperty("price-in-crores")
        private String price_in_crores;
    }

}
