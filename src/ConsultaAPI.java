import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

// Clase para consultar la API
class ConsultaAPI {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/e357df03c087a026f4cbccd5/latest/USD";

    public static double obtenerTasa(String moneda) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder jsonResponse = new StringBuilder();
                while (scanner.hasNext()) {
                    jsonResponse.append(scanner.nextLine());
                }
                scanner.close();

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonResponse.toString(), JsonObject.class);
                return jsonObject.getAsJsonObject("conversion_rates").get(moneda).getAsDouble();
            }
        } catch (Exception e) {
            System.out.println("Error al consultar la API: " + e.getMessage());
        }
        return -1;
    }
}