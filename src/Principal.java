import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Sea bienvenido/a al Conversor de Moneda =] ");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.print("Elija una opción válida: ");

            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                realizarConversion(opcion);
            } else if (opcion != 7) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 7);

        System.out.println("Gracias por usar el conversor de moneda. Hasta pronto!");
    }
    private static void realizarConversion(int opcion) {
        String origen = "", destino = "";

        switch (opcion) {
            case 1 -> { // Dólar => Peso argentino
                origen = "USD";
                destino = "ARS";
            }
            case 2 -> { // Peso argentino => Dólar
                origen = "ARS";
                destino = "USD";
            }
            case 3 -> { // Dólar => Real brasileño
                origen = "USD";
                destino = "BRL";
            }
            case 4 -> { // Real brasileño => Dólar
                origen = "BRL";
                destino = "USD";
            }
            case 5 -> { // Dólar => Peso colombiano
                origen = "USD";
                destino = "COP";
            }
            case 6 -> { // Peso colombiano => Dólar
                origen = "COP";
                destino = "USD";
            }
        }

        double tasa;
        if (opcion % 2 == 0) { // Para conversiones de monedas hacia USD
            tasa = 1 / ConsultaAPI.obtenerTasa(origen); // Calcular el inverso
        } else { // Para conversiones de USD hacia otras monedas
            tasa = ConsultaAPI.obtenerTasa(destino);
        }

        if (tasa > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el monto en " + origen + ": ");
            double monto = scanner.nextDouble();
            double resultado = monto * tasa;
            System.out.printf("%.2f %s equivalen a %.2f %s%n", monto, origen, resultado, destino);

            String contenido = String.format("%.2f %s equivalen a %.2f %s%n", monto, origen, resultado, destino);
            GeneradorDeArchivo.generarArchivo(contenido);
        } else {
            System.out.println("No se pudo obtener la tasa de conversión.");
        }
    }
}
