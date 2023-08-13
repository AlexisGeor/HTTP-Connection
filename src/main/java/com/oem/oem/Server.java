package com.oem.oem;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void startServer() {
        try {
            // Establecer el puerto en el que el programa Java escuchará las conexiones
            int serverPort = 9100;

            // Crear un ServerSocket en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(serverPort);

            System.out.println("Esperando conexiones entrantes...");

            while (true) {
                // Esperar y aceptar una conexión entrante
                Socket clientSocket = serverSocket.accept();

                // Crear un nuevo hilo para manejar la conexión entrante utilizando la clase ConnectionHandler
                Thread thread = new Thread(new ConnectionHandler(clientSocket));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}







/*
La clase Server es responsable de iniciar un servidor y manejar las conexiones entrantes.

Método startServer(): Este método es el punto de entrada para iniciar el servidor. Aquí se realiza la configuración inicial del servidor, como establecer el puerto en el que el servidor escuchará las conexiones entrantes. Luego, se crea un ServerSocket en el puerto especificado y se entra en un bucle infinito para esperar y aceptar conexiones entrantes.

Bucle de aceptación de conexiones: El bucle while (true) se encarga de esperar y aceptar conexiones entrantes utilizando el método accept() del ServerSocket. Cuando se acepta una conexión, se crea un nuevo objeto Socket para representar la conexión entrante.

Creación de un nuevo hilo para manejar la conexión: Una vez que se acepta una conexión entrante, se crea un nuevo hilo utilizando la clase ConnectionHandler. Se pasa el objeto Socket recién creado al constructor de ConnectionHandler para que pueda manejar esa conexión específica en el hilo separado.

Clase ConnectionHandler: Esta clase es una clase interna (nested class) dentro de la clase Server. Implementa la interfaz Runnable, lo que significa que puede ser ejecutada en un hilo separado. La clase ConnectionHandler se encarga de manejar una conexión entrante específica.

Método run(): Este método es donde se define la lógica para manejar la conexión. En este caso, se lee la solicitud del cliente, se procesa (en tu caso, se convierte de hexadecimal a ASCII) y se envía una respuesta al cliente. Luego, se cierran los flujos y el socket para finalizar la conexión.
Al implementar la clase Server, se puede utilizar el método startServer() para iniciar el servidor en el punto de entrada de la aplicación (por ejemplo, en el método main() de la clase OemApplication). Al llamar al método startServer(), el servidor se iniciará y comenzará a aceptar conexiones entrantes. Cada conexión entrante será manejada en un hilo separado por la clase ConnectionHandler.
*/