package com.oem.oem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class OemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OemApplication.class, args);

		try {
			// Establecer el puerto en el que el programa Java escuchará las conexiones
			int serverPort = 7073;

			// Crear un ServerSocket en el puerto especificado
			ServerSocket serverSocket = new ServerSocket(serverPort);

			System.out.println("Esperando conexiones entrantes...");

			while (true) {
				// Esperar y aceptar una conexión entrante
				Socket clientSocket = serverSocket.accept();

				// Crear un nuevo hilo para manejar la conexión entrante
				Thread thread = new Thread(new ConnectionHandler(clientSocket));
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
