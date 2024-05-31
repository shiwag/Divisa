use Hash Map para crear un bucket con las divisas a convertir
la ventaja de usar hash map es la de poder relacionar un numero con un string
el numero sirve para seleccionar la divisa , ya que el numero se relaciona con la clave de la divisa
en el formato string esperado por la api
se declaran las variables in , string y una variable adicional para la cantidad a convertir

Uso System.out.println para realizar 2 menus de opciones, uno para selccionar la divisa que se cambiara, uso el mismo menu pero agregando un mensaje de moneda a convertir
en cada caso solicito via Scanner el valor 

procedo con la solicitud a la api con URL url = new URL(GET_URL);
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

    httpURLConnection.setRequestMethod("GET");
    int responseCode = httpURLConnection.getResponseCode();

    hasta aqui corria el programa, pero tuve problemas con el Json al solicitar el valor "conversion_rate"
    me mandaba un error que no encontraba el valor, lo corregi , mi error era como lo coloque 
    pero despues aparecio un error de numero.... que no entendia ,  Exception in thread "main" org.json.JSONException: JSONObject["conversion_rate"] is not a JSONObject (class java.math.BigDecimal : 0.1936).
   at org.json.JSONObject.wrongValueFormatException

    trate de cambiar el tipo de variable para que manejara el valor , por que parecia esceder  float y double
    pero no funcionaba , despues de batallar un buen tiempo de no entender por que marcaba JSONException; cambie Json por
    la libreria Jackson
    La cual con ObjectMapper me permitio maneja el valor con get Double

    como ya habia hecho la salida , pense que la exception de Json tenia que ver con la salida , cheque un poco la opcion Math... lo cual no sirivio , pero me permitio tener una salida a 2 decimales
    , pero anterirormente probe con printf por si era el formato de salida ... pero no pude concatenar %.2f con el valor Int y los strings de los coddigos de las monedas
    por lo que use Math.round . Con lo cual tenia ya la salida de datos para mostrar

    en este momento encerre parte del programa en un bucle con Do , agregue condicionantes para los numeros admitidos de las opciones del menu y las opciones para continuar o salir del programa


    
    Ya que tuve respuesta del api y pude imprimir , puse un bocle con do y
