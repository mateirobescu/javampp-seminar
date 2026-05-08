package ex1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<AccessLog> logs = AccessLog.readFromFile("access_logs.txt");
        if(logs.isEmpty())
            return;

        System.out.println("===Fara Streams===");

        System.out.println("Numarul de requesturi: " + logs.size());

        double avgRepsonseTime = 0;
        for(AccessLog al : logs)
            avgRepsonseTime += al.getResponseTimeMs();
        avgRepsonseTime /= logs.size();
        System.out.println("Timpul mediu de raspuns: " + avgRepsonseTime);

        int noOfRequestsOver400 = 0;
        for(AccessLog al : logs)
            if (al.getStatusCode() >= 400)
                noOfRequestsOver400++;
        System.out.println("Numarul de requesturi >= 400: " + noOfRequestsOver400);

        AccessLog maxResponseTimeLog = logs.getFirst();
        for(AccessLog al : logs)
            if(maxResponseTimeLog.getResponseTimeMs() < al.getResponseTimeMs())
                maxResponseTimeLog = al;
        System.out.println("Enpointul cu cu cel mai mare timp de raspuns: " + maxResponseTimeLog.getEndpoint());

        System.out.println("\n=== Cu Streams===");
        System.out.println("Numarul de requesturi: " + logs.stream().count());
        System.out.println("Timpul mediu de raspuns: " +
                logs.stream()
                        .mapToInt(AccessLog::getResponseTimeMs)
                        .average()
                        .orElse(0)
        );
        System.out.println("Numarul de requesturi >= 400: " +
                logs.stream()
                        .filter(log -> log.getStatusCode() >= 400)
                        .count()
        );
        System.out.println("Enpointul cu cu cel mai mare timp de raspuns: " +
                logs.stream()
                        .max(Comparator.comparingInt(AccessLog::getResponseTimeMs))
                        .map(AccessLog::getEndpoint)
                        .orElse("N/A")
        );

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log_report.txt"))) {
            bw.write(String.format("%-15s %-15s %-15s %-15s\n", "Request count", "Average resp", "Over 400", "Slowest endpoint"));
            bw.write(String.format("%-15d %-15.4f %-15d %-15s\n", logs.size(), avgRepsonseTime, noOfRequestsOver400, maxResponseTimeLog.getEndpoint()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("\nDictionary endpointuri dupa requesturi: ");
        Map<String, Integer> result = logs.stream()
                .collect(Collectors.groupingBy(AccessLog::getEndpoint, Collectors.summingInt(log -> 1)));
        System.out.println(result);

        System.out.print("Enpointuri sortate descrescator dupa requesturi: ");
        List<String> enpointsByReq = result.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed())
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(enpointsByReq);

    }
}
