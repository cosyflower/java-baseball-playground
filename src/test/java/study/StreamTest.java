package study;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    void makeStreamIntoHashset() {
        Stream<String> beforeStream = Stream.of("apple", "banana", "peach", "peach");
        HashSet<Object> afterStream = beforeStream.collect(HashSet::new, HashSet::add, HashSet::addAll);
        for (Object o : afterStream) {
            System.out.println("o = " + o + '\n');
        }

    }

    @Test
    void useCollectors() {
        Stream<String> fruits = Stream.of("apple", "banana", "peach", "peach");
        Set<String> baguni = fruits.collect(Collectors.toSet());
        for (String s : baguni) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void useCollectorsJoining() {
        Stream<String> fruits = Stream.of("apple", "banana", "peach", "peach");
        String sentence = fruits.collect(Collectors.joining(", "));
        System.out.println("sentence = " + sentence);
    }

    @Test
    void useCollectorsComparing() {
        Stream<String> fruits = Stream.of("apple", "banana", "peach", "peach");
        Function<String, Integer> getCount = l -> l.length();
        Optional<String> sortedMap = fruits.map(Object::toString).collect(Collectors.maxBy(Comparator.comparing(getCount)));
        System.out.println("sortedMap = " + sortedMap.orElse("Unknown"));
    }

    @Test
    void useCollectorsasList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        Double average = numbers.stream().collect(Collectors.averagingDouble(v -> v * 2));
        System.out.println("average = " + average);
    }

    static class Fruit {
        int number;
        String key;

        public int getNumber() {
            return number;
        }

        public String getKey() {
            return key;
        }

        public Fruit(int number, String key) {
            this.number = number;
            this.key = key;
        }
    }

    @Test
    void sameKeyDuringToList() {
        List<Fruit> fruits = Arrays.asList(new Fruit(1, "banana"),
                new Fruit(2, "apple"),
                new Fruit(3, "pineapple"),
                new Fruit(4, "tree"),
                new Fruit(4, "tree2"));

        Map<Integer, String> fruitMap = fruits.stream().collect(Collectors.toMap(fruit -> fruit.getNumber(),
                fruit -> fruit.getKey()
                , (existing, newValue) -> existing));

        for (Fruit fruit : fruits) {
            System.out.println("fruit.getKey() = " + fruit.getKey()
            + "fruit.getNumber() = " + fruit. getNumber());
        }

        for (Integer key : fruitMap.keySet()) {
            System.out.println("fruitMap.get(key) = " + fruitMap.get(key));
        }
    }

    @Test
    void usingFunctionIdentity() {
        List<Fruit> fruits = Arrays.asList(new Fruit(1, "banana"),
                new Fruit(2, "apple"),
                new Fruit(3, "pineapple"),
                new Fruit(4, "tree"),
                new Fruit(4, "tree2"));

        Map<Integer, Fruit> fruitMap = fruits.stream().collect(Collectors.toMap(fruit -> fruit.getNumber(),
                Function.identity()
                , (existing, newValue) -> existing));

        for (Integer key : fruitMap.keySet()) {
            System.out.println("fruitMap.get(key) = " + fruitMap.get(key));
        }
    }

}
