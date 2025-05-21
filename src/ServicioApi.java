import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ServicioApi {
    private final String API_URL = "https://api.exchangerate.host/latest";

    public double obtenerTasa(String base, String destino) {
        try {
            String endpoint = API_URL + "?base=" + base + "&symbols=" + destino;
            URL url = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder contenido = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contenido.append(inputLine);
            }
            in.close();
            con.disconnect();

            JSONObject json = new JSONObject(contenido.toString());
            return json.getJSONObject("rates").getDouble(destino);
        } catch (Exception e) {
            System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
            return -1;
        }
    }
}
