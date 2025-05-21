import java.util.Scanner;

public class Menu {
    public void mostrar() {
        Scanner sc = new Scanner(System.in);
        ServicioApi api = new ServicioApi();
        ConversorMoneda conversor = new ConversorMoneda();

        while (true) {
            System.out.println("\n=== CONVERSOR DE MONEDAS ===");
            System.out.println("1. USD → EUR");
            System.out.println("2. CLP → USD");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();

            if (opcion == 3) break;

            System.out.print("Ingrese cantidad: ");
            double cantidad = sc.nextDouble();
            String base = "", destino = "";

            switch (opcion) {
                case 1: base = "USD"; destino = "EUR"; break;
                case 2: base = "CLP"; destino = "USD"; break;
                default: System.out.println("Opción no válida"); continue;
            }

            double tasa = api.obtenerTasa(base, destino);
            if (tasa > 0) {
                double resultado = conversor.convertir(cantidad, tasa);
                System.out.printf("Resultado: %.2f %s\n", resultado, destino);
            } else {
                System.out.println("No se pudo obtener la tasa.");
            }
        }
        sc.close();
    }
}
