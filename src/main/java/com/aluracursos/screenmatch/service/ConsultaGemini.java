package com.aluracursos.screenmatch.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGemini {
    public static String obtenerTraduccion(String texto){
        String modelo = "gemini-2.0-flash-lite";
        String prompt = "Traduce el siguiente texto al españo: " + texto;
        Client cliente = new Client.Builder().apiKey("AIzaSyA5ATIbY8k8wY0z5pZhWFBBapR4PUBbCI0").build();
        try {
            GenerateContentResponse respuesta = cliente.models.generateContent(
                    modelo,
                    prompt,
                    null // Parámetro para configuraciones adicionales
            );
            if(!respuesta.text().isEmpty()) {
                return respuesta.text();
            }
        }catch (Exception e) {
            System.out.println("Error al llamar a la API de Gemini para traducción: " + e.getMessage());
        }
        return null;
    }
}
