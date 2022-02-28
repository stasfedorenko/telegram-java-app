package by.stasfedorenko.util;

import by.stasfedorenko.entity.ReportDTO;
import by.stasfedorenko.entity.UserDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class JSONParser {
    private static final Gson gson = new Gson();

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static Map<UserDTO, List<ReportDTO>> readJsonFromUrl(String url) throws IOException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            jsonText = jsonText.substring(jsonText.indexOf("<body>"), jsonText.indexOf("</body>"));
            jsonText = jsonText.substring(jsonText.indexOf("["));
            jsonText = jsonText.replaceAll("&#034;", "\"");
            return gson.fromJson(jsonText, new TypeToken<Map<UserDTO, List<ReportDTO>>>() {
            }.getType());
        }
    }

    public static Map<UserDTO, List<ReportDTO>> getJSON() throws IOException {
        return (readJsonFromUrl("http://35.230.57.225:8080/main-java-app/apply?command=get_json_reports"));
    }
}
