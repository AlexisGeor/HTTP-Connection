package com.oem.oem.controller;

import com.oem.oem.Excepcion.MiExcepcionPersonalizada;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
public class OemController {

    @GetMapping("/ejemplo")
    public String exampleGet() {
        return "GET request received successfully";
    }
    @PostMapping("/ejemplo2")
    public String examplePost() {
        return "Post request received successfully";
    }




    @GetMapping(value = "/1111")
    public String getAutomotor2() throws IOException{
        System.out.println("Solicitud GET recibida correctamente");
        return null;
    }
    @GetMapping(value = "/")
    public String prueba(@RequestHeader MultiValueMap<String, String> headers) {
        System.out.println("Solicitud GET recibida correctamente");
        headers.forEach((key, values) -> {
            System.out.println(key + ": " + values);
        });
        return null;
    }
    @PostMapping("/")
    public String handlePostRequest(@RequestBody String requestPayload) {
        // Procesar la solicitud y devolver la respuesta adecuada
        System.out.println("Solicitud POST recibida correctamente");
        return "Solicitud POST recibida correctamente";
    }

    @PostMapping("/aa")
    public void recibirMensaje(@RequestBody(required = false) String mensajeBody, @RequestHeader(value = "mensaje", required = false) String mensajeHeader, HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        if (!StringUtils.isEmpty(mensajeBody)) {
            System.out.println("Mensaje recibido en el body: " + mensajeBody);
        } else if (!StringUtils.isEmpty(mensajeHeader)) {
            System.out.println("Mensaje recibido en el header: " + mensajeHeader);
        } else {
            System.out.println("No se encontró ningún mensaje");
        }

        System.out.println("IP del dispositivo que envía la solicitud: " + ip);
    }


    @PostMapping("/mensaje")
    public void recibirMensaje(HttpServletRequest request, @RequestBody(required = false) String mensajeBody) throws IOException {
        String metodo = request.getMethod();
        String url = request.getRequestURL().toString();
        String cuerpo = obtenerCuerpoSolicitud(request);
        // Puedes realizar acciones adicionales con los datos obtenidos de la solicitud
        // ...

        System.out.println("Método: " + metodo);
        System.out.println("URL: " + url);
        System.out.println("Cuerpo de la solicitud: " + cuerpo);
    }

    private String obtenerCuerpoSolicitud(HttpServletRequest request) throws IOException {
        String cuerpo = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        return cuerpo;
    }

    @PostMapping("/capturar")
    public void capturarRequest(HttpServletRequest request, @RequestBody(required = false) String requestBody) throws IOException {
        capturarInformacionRequest(request, requestBody);
    }

    private void capturarInformacionRequest(HttpServletRequest request, String requestBody) throws IOException {
        String metodo = request.getMethod();
        String url = request.getRequestURL().toString();
        String protocolo = request.getProtocol();

        Map<String, List<String>> encabezados = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            encabezados.put(headerName, headerValues);
        }

        Map<String, List<String>> parametros = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(parameterName);
            parametros.put(parameterName, Arrays.asList(parameterValues));
        }

        // Imprimir la información capturada
        System.out.println("Método: " + metodo);
        System.out.println("URL: " + url);
        System.out.println("Protocolo: " + protocolo);
        System.out.println("Encabezados: " + encabezados);
        System.out.println("Parámetros: " + parametros);
        System.out.println("Cuerpo de la solicitud: " + requestBody);
    }

    @GetMapping("/capturar")
    public void capturarRequest(HttpServletRequest request, @RequestParam Map<String, String> queryParams) throws IOException {
        String requestBody = ""; // No hay cuerpo en una solicitud GET
        capturarInformacionRequest(request, requestBody, queryParams);
    }

    private void capturarInformacionRequest(HttpServletRequest request, String requestBody, Map<String, String> queryParams) throws IOException {
        String metodo = request.getMethod();
        String url = request.getRequestURL().toString();
        String protocolo = request.getProtocol();

        Map<String, List<String>> encabezados = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            encabezados.put(headerName, headerValues);
        }

        // Imprimir la información capturada
        System.out.println("Método: " + metodo);
        System.out.println("URL: " + url);
        System.out.println("Protocolo: " + protocolo);
        System.out.println("Encabezados: " + encabezados);
        System.out.println("Parámetros de consulta: " + queryParams);
        System.out.println("Cuerpo de la solicitud: " + requestBody);
    }

    @PostMapping("/a")
    public void handleRequest(@RequestBody String requestBody) {
        // Agregar registros para rastrear las solicitudes
        System.out.println("Se recibió una solicitud POST");
        System.out.println("Cuerpo de la solicitud: " + requestBody);
    }
}
