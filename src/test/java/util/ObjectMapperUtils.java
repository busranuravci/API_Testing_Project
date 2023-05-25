package util;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;



public class ObjectMapperUtils {



    public static <T> T convertJsonToJava(String json, Class<T> cls) {      //Generic Method

        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
