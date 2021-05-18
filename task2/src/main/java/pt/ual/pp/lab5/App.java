package pt.ual.pp.lab5;

import java.util.ArrayList;
import java.util.List;

interface PairAggregator<T> {
    T aggregate(Pair<T> pair);
}

class Pair<T> {
    private final T a;
    private final T b;

    public Pair(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public T getA() { return a; }

    public T getB() { return b; }
}

class PairCollection<T> {
    private final List<Pair<T>> pairs = new ArrayList<>();

    void addPair(Pair<T> pair) { pairs.add(pair); }

    List<T> compute(PairAggregator<T> aggregator) {
        ArrayList<T> result = new ArrayList<>();
        for(final var pair : pairs) {
            result.add(aggregator.aggregate(pair));
        }
        return result;
    }
}

//class SumPairs implements PairAggregator<Integer> {
//    @Override
//    public Integer aggregate(final Pair<Integer> pair) {
//        return pair.getA() + pair.getB();
//    }
//}

public class App {
    public static void main(String[] args) {
        PairCollection<Integer> intPairs = new PairCollection<>();
        intPairs.addPair(new Pair<Integer>(10, 1));
        intPairs.addPair(new Pair<Integer>(20, 2));
        intPairs.addPair(new Pair<Integer>(30, 3));

        // V1
//        List<Integer> results = intPairs.compute(new SumPairs());
//        for(final var result : results) {
//            System.out.println(result);
//        }

        // V2
//        List<Integer> results = intPairs.compute((pair) -> pair.getA() + pair.getB());
//        results.forEach(System.out::println);

        // V3
        intPairs.compute((pair) -> pair.getA() + pair.getB()).forEach(System.out::println);
        intPairs.compute((pair) -> pair.getA() * pair.getB()).forEach(System.out::println);
        intPairs.compute((pair) -> pair.getA() - pair.getB()).forEach(System.out::println);
        intPairs.compute((pair) -> pair.getA() / pair.getB()).forEach(System.out::println);

//        List<Integer> resultsm = intPairs.compute((pair) -> pair.getA() * pair.getB());
//        for(final var result : resultsm) {
//            System.out.println(result);
//        }
    }
}