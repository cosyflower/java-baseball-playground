package stringcalculator.operator;

import java.util.function.BiFunction;

public enum Operator {
    PLUS("+", (first, second) -> first + second),
    MINUS("-", (first, second) -> first - second),
    DIVIDE("/", (first, second) -> first / second),
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
}
