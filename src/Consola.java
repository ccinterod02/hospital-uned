import java.util.Scanner;

public class Consola {

    private static Consola consola;
    private Scanner scanner;

    private Consola() {
        scanner = new Scanner(System.in);
    }

    public static Consola getInstancia(){
        if (consola == null){
            consola = new Consola();
        }
        return consola;
    }

    public String readString(String mensaje) {
        this.println(mensaje);
        String entrada = this.scanner.nextLine();

        return entrada;
    }

    public int readInt(String mensaje) {
        this.println(mensaje);
        while (!scanner.hasNextInt()) {
            this.println("Por favor, introduce un número entero.");
            scanner.nextLine();
        }
        int entrada = this.scanner.nextInt();
        scanner.nextLine(); 
        return entrada;
    }

    public void println(){
        System.out.println();
    }

    public void println(String string) {
        System.out.println(string);
    }
}