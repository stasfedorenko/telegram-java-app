package by.stasfedorenko.parser;

import org.json.JSONArray;
import java.util.*;


public class    JSONtoMap {

    public static Map<String[], String[]> execute(String someJsonString) {
        JSONArray jsonArray = new JSONArray(someJsonString);
        List<JSONArray> jsonArrayList = new ArrayList<>();
        Map<String[], String[]> map = new LinkedHashMap<>();

        String[] listKeys;
        String[] listValues;

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonArrayList.add(jsonArray.getJSONArray(i));
        }

        for (JSONArray objects : jsonArrayList) {
            listKeys = new String[2];
            listValues = new String[3];
            if (objects.getJSONObject(0).length()>0) {
                listKeys[0] = objects.getJSONObject(0).getString("firstName");
                listKeys[1] = objects.getJSONObject(0).getString("lastName");
            }
            if (objects.getJSONArray(1).length()>0) {
                listValues[0] = objects.getJSONArray(1)
                        .getJSONObject(0).getString("reportTitle");
                listValues[1] = objects.getJSONArray(1)
                        .getJSONObject(0).getString("reportBody");
                listValues[2] = String.valueOf(objects.getJSONArray(1)
                        .getJSONObject(0).getInt("laborCost"));
            }
            map.put(listKeys, listValues);
        }
        return map;
    }
}
