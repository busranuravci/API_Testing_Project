package testData;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {
    public Map<String,String> reqresUsersSetUp(String name, String job) {
        Map<String,String> reqresUsersSetUp= new HashMap<>();
        reqresUsersSetUp.put("name", name);
        reqresUsersSetUp.put("job", job);


        return reqresUsersSetUp;
    }



}
