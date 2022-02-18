package by.stasfedorenko.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParserJSON {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            jsonText = jsonText.substring(jsonText.indexOf("<body>"),jsonText.indexOf("</body>"));
            jsonText = jsonText.substring(jsonText.indexOf("["));
            jsonText = jsonText.replaceAll("&#034;","\"");
            return jsonText;
        }
    }

    public static String getJSON() throws IOException {
        return (readJsonFromUrl("http://34.127.16.38:8080/main-java-app/apply?command=get_json_reports"));
    }
    public static Map<String[], String[]> execute() throws IOException {
        JSONArray jsonArray = new JSONArray(getJSON());
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
