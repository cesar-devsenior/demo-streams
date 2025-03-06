package com.devsenior.cdiaz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ejercicio08();
    }

    private static void ejercicio01() {
        /*
         * Tienes una lista de números enteros.
         * Filtra los números que son múltiplos de 3,
         * ordénalos en orden descendente y
         * multiplica cada número por 5.
         * Finalmente, recoge los resultados en una lista y muéstralos por pantalla.
         */
        var numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        var multiplos3 = new ArrayList<Integer>();
        for (var number : numbers) {
            if (number % 3 == 0) {
                multiplos3.add(number);
            }
        }

        var ordenadosDesc = new ArrayList<Integer>();
        for (var number : multiplos3) {
            if (ordenadosDesc.isEmpty()) {
                ordenadosDesc.add(number);
            } else {
                for (int i = 0; i < ordenadosDesc.size(); i++) {
                    Integer valor = ordenadosDesc.get(i);
                    if (number > valor) {
                        ordenadosDesc.add(i, number);
                        break;
                    }
                    if (i == ordenadosDesc.size() - 1) {
                        ordenadosDesc.add(number);
                    }
                }
            }
        }

        var multiplicados5 = new ArrayList<Integer>();
        for (var number : ordenadosDesc) {
            multiplicados5.add(number * 5);
        }

        for (var number : multiplicados5) {
            System.out.println(number);
        }
    }

    private static void ejercicio01Streams() {
        /*
         * Tienes una lista de números enteros.
         * Filtra los números que son múltiplos de 3,
         * ordénalos en orden descendente y
         * multiplica cada número por 5.
         * Finalmente, recoge los resultados en una lista y muéstralos por pantalla.
         */
        var numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        // var stream = numbers.stream();
        // var stream2 = stream.filter(n -> n % 3 == 0);
        // var stream3 = stream2.sorted(Comparator.reverseOrder());
        // var stream4 = stream3.map(n -> n * 5);
        // stream4.forEach(n -> System.out.println(n)); // -> Consumer

        numbers.stream()
                .filter(n -> n % 3 == 0)
                .sorted(Comparator.reverseOrder())
                .map(n -> n * 5)
                .forEach(System.out::println);
    }

    private static class Empleado {
        private String nombre;
        private Integer salario;

        public Empleado(String nombre, Integer salario) {
            this.nombre = nombre;
            this.salario = salario;
        }

        public String getNombre() {
            return nombre;
        }

        public Integer getSalario() {
            return salario;
        }

    }

    private static void ejercicio02() {
        /*
         * Tienes una lista de objetos Empleado, cada uno con los atributos nombre y
         * salario. Filtra los empleados que tienen un salario mayor a 60,000 y cuenta
         * cuántos empleados cumplen esta condición. Imprime el número de empleados que
         * tienen un salario mayor a 60,000.
         */
        var empleados = Arrays.asList(
                new Empleado("Juan", 60000),
                new Empleado("Ana", 50000),
                new Empleado("Carlos", 70000),
                new Empleado("Luis", 80000),
                new Empleado("Maria", 40000));

        var total = empleados.stream()
                .filter(e -> e.getSalario() > 60000)
                .peek(e -> System.out.println(e.getNombre()))
                .count();
        System.out.printf("El total de empleados que ganan mas de 60.000 son: %,d %n", total);
    }

    private static void ejercicio03() {
        /*
         * Dada una lista de palabras, agrúpalas según la longitud de cada palabra.
         * Imprime las palabras agrupadas por su longitud, mostrando la longitud y la
         * lista de palabras que tienen esa longitud.
         */

        var words = Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape", "kiwi");

        var groups = new TreeMap<Integer, Set<String>>();
        for (var word : words) {
            var length = word.length();
            if (groups.containsKey(length)) {
                groups.get(length).add(word);
            } else {
                var data = new HashSet<String>();
                data.add(word);
                groups.put(length, data);
            }
        }

        for (var key : groups.keySet()) {
            System.out.println("Length: " + key);
            var value = groups.get(key);
            for (var word : value) {
                System.out.println("     " + word);
            }

        }
    }

    private static void ejercicio03Streams() {
        /*
         * Dada una lista de palabras, agrúpalas según la longitud de cada palabra.
         * Imprime las palabras agrupadas por su longitud, mostrando la longitud y la
         * lista de palabras que tienen esa longitud.
         */

        var words = Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape", "kiwi");

        var groups = words.stream()
                .collect(Collectors.groupingBy(String::length));

        groups.forEach((key, wordList) -> System.out.printf("Length: %d%n\t%s%n",
                key,
                wordList.stream()
                        .collect(Collectors.joining(", "))));

    }

    private static void ejercicio04() {
        /*
         * Tienes una lista de números enteros. Calcula el promedio de estos números
         * utilizando Streams y muestra el resultado por pantalla. Asegúrate de manejar
         * el caso donde la lista pueda estar vacía.
         */

        // var numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numbers = Arrays.asList();

        // var promedio = numbers.stream()
        // .collect(Collectors.averagingInt(Integer::intValue));

        double promedio = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseGet(() -> Math.random());

        // if(promedio.isPresent()){
        // System.out.println("El promedio de los numeros es : "+
        // promedio.getAsDouble());
        // } else {
        // System.out.println("La lista está vacía");
        // }
        System.out.println(promedio);

    }

    private static void ejercicio05() {
        /*
         * Dada una lista de cadenas de texto, concatena todas las cadenas en una sola
         * cadena separada por un espacio. Muestra la cadena resultante por pantalla.
         */

        var words = Arrays.asList("Java", "Streams", "are", "powerful");

        var response = words.stream()
                .collect(Collectors.joining(" "));
        System.out.println(response);
    }

    private static class Venta {
        private String producto;
        private Integer cantidad;
        private Double precioPorUnidad;

        public Venta(String producto, Integer cantidad, Double precioPorUnidad) {
            this.producto = producto;
            this.cantidad = cantidad;
            this.precioPorUnidad = precioPorUnidad;
        }

        public String getProducto() {
            return producto;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public Double getPrecioPorUnidad() {
            return precioPorUnidad;
        }

        public Double getTotal() {
            return cantidad * precioPorUnidad;
        }

    }

    private static void ejercicio06() {
        /*
         * Tienes una lista de objetos Venta, cada uno con los atributos producto,
         * cantidad y precioPorUnidad. Filtra las ventas de productos cuyo precio total
         * (cantidad * precioPorUnidad) sea mayor a 100, agrupa las ventas por el nombre
         * del producto y calcula el total de ventas por producto. Ordena los resultados
         * por el total de ventas en orden descendente y muestra el resultado por
         * pantalla.
         */

        var ventas = Arrays.asList(
                new Venta("ProductoA", 10, 12.5),
                new Venta("ProductoB", 5, 25d),
                new Venta("ProductoA", 7, 15d),
                new Venta("ProductoC", 20, 4d),
                new Venta("ProductoC", 200, 5d),
                new Venta("ProductoB", 2, 30d));

        var totalVentasPorProducto = ventas.stream()
                .filter(v -> v.getTotal() > 100)
                .collect(
                        Collectors.groupingBy(
                                Venta::getProducto,
                                Collectors.summingDouble(Venta::getTotal)));
        System.out.println(totalVentasPorProducto);

        totalVentasPorProducto.entrySet().stream()
                // .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(e -> System.out.printf("Producto: %s, cantidad: %,.2f %n", e.getKey(), e.getValue()));

    }

    private static void ejercicio08() {
        /*
         * Dado un párrafo de texto, convierte el texto en una lista de palabras y
         * elimina las palabras repetidas. Luego, agrupa las palabras por su longitud y
         * muestra cada grupo de palabras junto con la cantidad de palabras en ese
         * grupo. Finalmente, encuentra la palabra más larga en el texto y muéstrala por
         * pantalla.
         */

        var texto = """
                Dado un párrafo de texto convierte el texto en una lista de palabras y elimina las palabras repetidas.
                Luego agrupa las palabras por su longitud y muestra cada grupo de palabras junto con la cantidad de palabras en ese grupo.
                Finalmente encuentra la palabra más larga en el texto y muéstrala por pantalla.
                """;
        var palabrasUnicas = Stream.of(texto.replaceAll("\n", " ")
                .replace('.', '\0')
                .split(" "))
                .collect(Collectors.toSet());
        System.out.println(palabrasUnicas);

        var palabrasPorLongitud = palabrasUnicas.stream()
                .collect(Collectors.groupingBy(String::length));

        palabrasPorLongitud.forEach(
                (longitud, palabras) -> System.out.printf("Longitud: %d, Palabras: %d %n", longitud, palabras.size()));

        var palabraMasLargaOpcional = palabrasPorLongitud.entrySet().stream()
            .sorted(Map.Entry.<Integer, List<String>>comparingByKey().reversed())
            .findFirst();
        if(palabraMasLargaOpcional.isPresent()) {
            palabraMasLargaOpcional.get()
                .getValue().stream()
                .findFirst()
                .ifPresent(System.out::println);
        }
    }
}