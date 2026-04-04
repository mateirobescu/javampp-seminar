package eu.ase.streams_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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

    }

}
