package com.oem.oem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


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

                // Enviar una respuesta al cliente

				/*String respuesta = "Java resp";
				writer.println(respuesta);*/

				/*
				char[] caracteres = {'1','3','0','3','1','4','B','3','5','1','4','4',3};  // 3 0 3 1 4 B 3 5 3 1 4 4

				// Convertir los caracteres a un arreglo de bytes
				byte[] respuestaBytes = new byte[caracteres.length];
				for (int i = 0; i < caracteres.length; i++) {
					respuestaBytes[i] = (byte) caracteres[i];
				}

				// Enviar la respuesta byte a byte
				for (byte b : respuestaBytes) {
					outputStream.write(b);
				}
				 */
                String valor = "01K51D";

                // Construir la secuencia completa
                char startChar = 2;
                String middleString = AsciiToHexConverter.asciiToHex(valor); // '01K51D' "30314B353144"  "30314F393135"'010915' Conversion de String a cadena Ascci
                char endChar = 3;

                // Convertir la secuencia completa en un arreglo de bytes
                byte[] sequenceBytes = new byte[middleString.length() / 2 + 2];
                sequenceBytes[0] = (byte) startChar;

                for (int i = 0; i < middleString.length(); i += 2) {
                    String byteString = middleString.substring(i, i + 2);
                    byte sequenceByte = (byte) Integer.parseInt(byteString, 16);
                    sequenceBytes[i / 2 + 1] = sequenceByte;
                }

                sequenceBytes[sequenceBytes.length - 1] = (byte) endChar;

                // Enviar la secuencia completa
                outputStream.write(sequenceBytes);


                // Cerrar la conexión
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


