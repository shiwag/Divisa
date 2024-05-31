import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DivisasB {

    public static void main(String[] args) throws  IOException {

            Boolean running = true;

        do{
        HashMap<Integer, String> monedaCodigos = new HashMap<Integer, String>();

        monedaCodigos.put(1, "USD");
        monedaCodigos.put(2, "EUR");
        monedaCodigos.put(3, "BRL");
        monedaCodigos.put(4, "MXN");
        monedaCodigos.put(5, "CNY");
        monedaCodigos.put(6, "JPY");

        Integer deMon, aMon;
        String deCodigo, aCodigo;
        double monto;

        Scanner entra = new Scanner(System.in);

        System.out.println("Bienvenido al convertidor de Divisas");
        System.out.println("Moneda a cambiar: \n");
        System.out.println("1:USD (Us Dolar)\t 2:EUR (EU Euro)\t 3:BRL (Brasil Real) ");
        System.out.println("4:MXN (Mexico Peso)\t 5:CNY (China Renminbi)\t 6:JPY (Japon Yen) ");

        deMon = entra.nextInt();

        while (deMon < 1 || deMon > 6) {
            System.out.println("Seleccione una opcion valida (1-6)");
            System.out.println("1:USD (Us Dolar)\t 2:EUR (EU Euro)\t 3:BRL (Brasil Real) ");
            System.out.println("4:MXN (Mexico Peso)\t 5:CNY (China Renminbi)\t 6:JPY (Japon Yen) ");

            deMon = entra.nextInt();
        }
        deCodigo = monedaCodigos.get(deMon);

        System.out.println("A que moneda se cambiara ");
        System.out.println("1:USD (Us Dolar)\t 2:EUR (EU Euro)\t 3:BRL (Brasil Real) ");
        System.out.println("4:MXN (Mexico Peso)\t 5:CNY (China Renminbi)\t 6:JPY (Japon Yem) ");

        aMon = entra.nextInt();

        while (aMon < 1 || aMon > 6) {
            System.out.println("Seleccione una moneda valida (1-6)");
            System.out.println("1:USD (Us Dolar)\t 2:EUR (EU Euro)\t 3:BRL (Brasil Real) ");
            System.out.println("4:MXN (Mexico Peso)\t 5:CNY (China Renminbi)\t 6:JPY (Brasil real) ");

            aMon = entra.nextInt();
        }

        aCodigo = monedaCodigos.get(aMon);

        System.out.println("Cantidad a cambiar? : ");
        monto = entra.nextFloat();

        sendHttpGETRequest(deCodigo, aCodigo, monto);

        System.out.println("Gusta hacer otra conversion?");
        System.out.println("1: Si \t cualquier numero: No ");

        if (entra.nextInt() != 1) {
            running = false;
        }
    } while(running);

System.out.println("Gracias por usar el conevertidor de divisas");
}

private static void sendHttpGETRequest(String deCodigo, String aCodigo, double monto) throws IOException {

    String GET_URL = "https://v6.exchangerate-api.com/v6/8664480ff6db0085a866c773/pair/" + aCodigo + "/" + deCodigo;
    URL url = new URL(GET_URL);
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

    httpURLConnection.setRequestMethod("GET");
    int responseCode = httpURLConnection.getResponseCode();

    if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> data = mapper.readValue(response.toString(), Map.class);

        Double exchangeRate = (Double) data.get("conversion_rate");

       //System.out.println("tipo de cambio = " + exchangeRate);
        System.out.println();

        double totalI = monto / exchangeRate;
        double totalR = Math.round(totalI * 100.0)/100.0;

        System.out.println(monto + " " + deCodigo + " = " + totalR + " " + aCodigo );




    } else {
        System.out.println("Get fallo");
    }
}
}
