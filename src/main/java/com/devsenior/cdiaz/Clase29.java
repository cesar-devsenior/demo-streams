package com.devsenior.cdiaz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Clase29 {
    public static void main(String[] args) {
        ejercicio3();
    }

    /**
     * Dado un archivo CSV con información de ventas, procesa los datos
     * para calcular las ventas totales por región.
     * 
     * Pasos:
     * 
     * 1. Lee el archivo línea por línea usando `Files.lines`.
     * 2. Usa `filter` para eliminar líneas vacías o incorrectas.
     * 3. Divide los datos con `split` para acceder a las columnas necesarias.
     * 4. Usa `map` y `collect` para agrupar las ventas por región y calcular
     * totales.
     */
    private static void ejercicio1() {
        try {
            var lines = Files.lines(Paths.get("ventas.csv"));

            var response = lines.filter(line -> !line.isBlank()) // Strings
                    .skip(1)
                    .map(line -> line.split(",")) // String[]
                    .collect(Collectors.groupingBy(
                            data -> data[0],
                            Collectors.summingInt(data -> Integer.parseInt(data[2]))));

            response.forEach((region, total) -> System.out.println(region + ": " + total));

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e);
        }
    }

    /**
     * Dado un texto, utiliza Streams para contar las palabras únicas y ordenar los
     * resultados alfabéticamente.
     * 
     * Pasos:
     * 
     * 1. Convierte el texto en una lista de palabras usando `split`.
     * 2. Usa un Stream para eliminar caracteres especiales y normalizar las
     * palabras
     * (pasar a minúsculas).
     * 3. Usa `distinct` para encontrar palabras únicas.
     * 4. Colecciona las palabras en una lista ordenada.
     * Ejemplo:
     * 
     * Texto: "Java Streams son poderosos. Streams en Java son eficientes."
     * Resultado: [eficientes, en, java, poderosos, son, streams]
     */
    private static void ejercicio2() {
        var text = "Java Streams son poderosos. Streams en Java son eficientes.";

        var sortedWords = Arrays.stream(text.split(" "))
                .map(word -> word.replace('.', '\0'))
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedWords);

    }

    /**
     * Dada una lista de números enteros (de 1 a 10,000,000), usa Parallel Streams
     * para encontrar todos los números primos.
     * 
     * Pasos:
     * 
     * 1. Genera la lista con `IntStream.range`.
     * 2. Filtra los números primos usando un método auxiliar.
     * 3. Usa parallelStream para paralelizar la operación.
     * 4. Guarda los números primos en una lista.
     * 
     * Desafío adicional: Compara el tiempo de ejecución entre un Stream secuencial
     * y uno paralelo.
     */
    private static void ejercicio3() {
        var begin = System.currentTimeMillis();
        IntStream.rangeClosed(1, 10_000_000)
                .map(Integer::valueOf)
                // .parallel()
                .filter(Clase29::isPrime)
                .sum(); // Validar si el numero es primo
        // .forEach(System.out::println);
        var end = System.currentTimeMillis();
        System.out.println("Tiempo: " + (end - begin) + " ms");
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
