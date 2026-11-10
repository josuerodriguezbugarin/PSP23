import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BuscadorIP {
    public static void main(String[] args) {// Enseña la propia IP
        Scanner sc = new Scanner(System.in);
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("Equipo local: ");
            System.out.println("Nombre del equipo: " + local.getHostName());
            System.out.println("IP del equipo:     " + local.getHostAddress());
        } catch (UnknownHostException e) {
            System.err.println("No se ha podido obtener la ip");
        }
        while (true) { //Bucle, se repite hasta "salir"
            System.out.print("Introduce un dominio: ");
            String dominio = sc.nextLine().trim();
            if (dominio.equalsIgnoreCase("salir")) {
                System.out.println("fin");
                break;
            }
            if (dominio.isEmpty()) {
                continue;
            }
            //Buscar las isp
            try {
                InetAddress[] direcciones = InetAddress.getAllByName(dominio);
                System.out.println("\nDirecciones IP para " + dominio + ":");
                for (InetAddress dir : direcciones) {
                    if (dir instanceof Inet4Address) {
                        System.out.println("  [IPv4] " + dir.getHostAddress());
                    } else if (dir instanceof Inet6Address) {
                        System.out.println("  [IPv6] " + dir.getHostAddress());
                    }
                }
                System.out.println();

            } catch (UnknownHostException e) {
                // Captura del error si el dominio no existe para permitir continuar el programa
                System.out.println("Error: El dominio '" + dominio + "' no existe o no se puede resolver.\n");
            }
        }

        sc.close();
    }
}