import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Plane PLANE_F18 = null;
        boolean salir = false;
        Scanner scan = new Scanner(System.in);

        String fileName = "data/plane.dat";

        FileInputStream inputFile = null;
        BufferedInputStream bufferedInput = null;
        ObjectInputStream objectInput = null;

        try {
            inputFile = new FileInputStream(fileName);
            bufferedInput = new BufferedInputStream(inputFile);
            objectInput = new ObjectInputStream(bufferedInput);

            try {
                Plane p = (Plane) objectInput.readObject();
                PLANE_F18 = p;
                System.out.println(PLANE_F18);

            } catch (EOFException ex) {
                System.out.println("Hemos llegado al final del fichero.");
            } catch (ClassNotFoundException e) {
                System.out.println("Se ha producido un error al leer un objeto");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Se ha producido un error de entrada salida --> no hay ningun avion creado");
        }

        while (!salir) {

            System.out.println("¿Que desea hacer? ");
            System.out.println("1 --> Inicializar F18");
            System.out.println("2 --> Alternar el estado de los flaps");
            System.out.println("3 --> Alternar el estado del tren de aterrizaje");
            System.out.println("4 --> Armar sistema de eyección");
            System.out.println("5 --> Eyectar piloto");
            System.out.println("Q --> Salir del programa");
            String opcion = scan.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Introduzca el número de litros de combustible cargados: ");
                    float fuelLevel = scan.nextFloat();
                    scan.nextLine();
                    System.out.println("Introduzca apodo del piloto: ");
                    String pilotCallSign = scan.nextLine();
                    System.out.println("Introduzca número de escuadrón: ");
                    String squadNumber = scan.nextLine();
                    PLANE_F18 = new Plane(fuelLevel, pilotCallSign, squadNumber);
                    System.out.println(PLANE_F18);
                    break;
                case "2":
                    if (PLANE_F18 != null) {
                        PLANE_F18.toggleFlaps();
                        System.out.println(PLANE_F18);
                    } else {
                        System.out.println("El F-18 no está inicializado \n");
                    }
                    break;
                case "3":
                    if (PLANE_F18 != null) {
                        PLANE_F18.toggleLandingGear();
                        System.out.println(PLANE_F18);
                    } else {
                        System.out.println("El F-18 no está inicializado \n");
                    }
                    break;
                case "4":
                    if (PLANE_F18 != null) {
                        PLANE_F18.ejectionSystem();
                        System.out.println(PLANE_F18);
                    } else {
                        System.out.println("El F-18 no está inicializado \n");
                    }
                    break;
                case "5":
                    if (PLANE_F18.getEjectionSystem()) {
                        PLANE_F18.setSeatOccupation(false);
                        System.out.println(PLANE_F18);
                    } else {
                        System.out.println("Sistema de eyección no inicializado \n");
                    }
                    break;
                case "Q":
                    FileOutputStream outputFile = null;
                    BufferedOutputStream bufferedOutput= null;
                    ObjectOutputStream objectOutput = null;

                    try {
                        outputFile = new FileOutputStream(fileName);
                        bufferedOutput = new BufferedOutputStream(outputFile);
                        objectOutput = new ObjectOutputStream(bufferedOutput);

                        objectOutput.writeObject(PLANE_F18);

                    }catch (FileNotFoundException e){
                        System.out.println("Not found");
                    }catch (IOException e){
                        System.out.println("Error de entrada salida");
                    }finally {
                        try {
                            if (objectOutput != null) objectOutput.close();
                            if (bufferedOutput != null) bufferedOutput.close();
                            if (outputFile != null) outputFile.close();
                        } catch (IOException e) {
                            System.out.println("Error al cerrar streams");
                        }
                    }
                    salir = true;
                    break;

            }
        }
    }
}