package by.stasfedorenko.parser;

import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JSON {
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
        return (JSON.readJsonFromUrl("http://34.127.16.38:8080/main-java-app/apply?command=get_json_reports"));
    }
}
