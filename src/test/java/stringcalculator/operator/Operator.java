package stringcalculator.operator;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operator {
    PLUS("+", (first, second) -> first + second),
    MINUS("-", (first, second) -> first - second),

    DIVIDE("/", (first, second) -> { if(second == 0) {
        throw new IllegalArgumentException(); }
        return (first / second); } ),
    MULTIPLY("*", (first, second) -> first * second);

    private String op;
    private BiFunction<Integer, Integer, Integer> biFunction;

    Operator(String op, BiFunction<Integer, Integer, Integer> biFunction) {
        this.op = op;
        this.biFunction = biFunction;
    }

    public String getOp() {
        return op;
    }

    public Integer operate(int result, int number) {
        return biFunction.apply(result, number);
    }

    private static final Map<String, Operator> descriptions = Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(Operator::getOp, Function.identity())));

    public static Operator findOperator(String s) {
        // Operator 찾는 과정에서 존재하지 않는 연산자 오버로딩 처리
        return Optional.ofNullable(descriptions.get(s)).orElseThrow(() -> new IllegalArgumentException());
    }


}
