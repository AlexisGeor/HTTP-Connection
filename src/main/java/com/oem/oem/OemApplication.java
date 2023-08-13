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
		// Crear instancia de Server y ejecutar el método start()
		Server server = new Server();
		server.startServer();
	}
}
