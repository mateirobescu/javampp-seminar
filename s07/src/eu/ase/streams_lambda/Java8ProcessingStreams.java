package eu.ase.streams_lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8ProcessingStreams {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "");

        int co1 = 0;
        for(int i = 0; i < strings.size(); i++) {
            if(strings.get(i) != null && strings.get(i).length() == 0)
                co1 += 1;
        }
        System.out.println(co1);

        int co2 = 0;
        for(String s : strings) {
            if(s.isEmpty())
                co2++;
        }
        System.out.println(co2);

        Predicate<String> predEmptyStr = (String s) -> {
            boolean res = s.isEmpty();
            return res;
        };

        long countEmptyStrings = strings.stream().filter(predEmptyStr).count();
        System.out.println(countEmptyStrings);

        long count = strings.stream().filter(s -> s.isEmpty()).count();
        System.out.println(count);

        count = strings.parallelStream().filter(s -> s.isEmpty()).count();
        System.out.println(count);

        count = strings.stream().filter(s -> s.length() == 3).count();
        System.out.println(count);

        List<String> filtered = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("Filtered list: " + filtered);

        String mergedString = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged string: " + mergedString);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squares = numbers.stream().map(i -> i * i).distinct().toList();
        System.out.println("Squares: " + squares);

        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 6 , 17, 8, 19);
        IntSummaryStatistics stats = integers.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Avg: " + stats.getAverage());

        Random random = new Random();
//        for(int i = 0; i < 10; i++) {
//            System.out.println(random.nextInt());
//        }
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

}
