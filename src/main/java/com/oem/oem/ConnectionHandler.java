package com.oem.oem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static com.oem.oem.Converter.communicarionBoard;

public class ConnectionHandler implements Runnable {
        private Socket clientSocket;

        public ConnectionHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Obtener el flujo de entrada del socket
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Obtener el flujo de salida del socket
                OutputStream outputStream = clientSocket.getOutputStream();

                // Obtener el flujo de salida del socket
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Leer el mensaje del cliente
                String mensaje = reader.readLine();

                // Mostrar el mensaje recibido
                System.out.println("Mensaje recibido: " + mensaje);

                String valor = "01K51D";
                // Enviar la secuencia completa
                outputStream.write(communicarionBoard(valor));

                // Cerrar la conexión
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}





/* La clase ConnectionHandler es responsable de manejar una conexión entrante en un hilo separado. Aquí tienes una explicación breve de la clase ConnectionHandler:

Implementación de Runnable: ConnectionHandler implementa la interfaz Runnable, lo que significa que puede ser ejecutada en un hilo separado. Al implementar Runnable, se debe proporcionar una implementación para el método run(), que define la lógica a ejecutar en el hilo separado.

Método run(): El método run() es donde se define la lógica para manejar la conexión entrante. En este método, se realizan las operaciones necesarias para leer la solicitud del cliente, procesarla y enviar una respuesta. En tu caso, esto incluye la lectura de una cadena en formato hexadecimal y la conversión a una cadena ASCII.

Manejo de la conexión: Dentro del método run(), se utiliza el objeto Socket proporcionado en el constructor para obtener los flujos de entrada y salida de la conexión. Esto permite leer la solicitud del cliente y enviar una respuesta.

Cierre de la conexión: Una vez que se ha procesado la solicitud y se ha enviado la respuesta, se deben cerrar los flujos y el socket para liberar los recursos y finalizar la conexión.

En resumen, la clase ConnectionHandler encapsula la lógica para manejar una conexión entrante en un hilo separado. Al implementar la interfaz Runnable y proporcionar una implementación para el método run(), se define cómo se procesa la conexión y se responde al cliente.
 */
