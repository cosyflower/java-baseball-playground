package stringcalculator;


// 검증된 문자열들을 가지고 실질적으로 계산이 진행되는 구간

import stringcalculator.operator.Operator;

public class Calculator {

//    private final InputValidator iv = new InputValidator(); -> 필요하지 않음
    Operator currentOperator;
    private int result = 0;

    public int calculate(String[] inputSet) {

        for (String s : inputSet) {
            System.out.println("s = " + s); // debug
            calculatePartial(s);
        }
        return result;
    }

    private void calculatePartial(String s) {

        InputValidator iv = new InputValidator();

        // opreand (피연산자인 경우 - 수인 경우를 말한다)
        if( iv.isOperand(s) ) {
            int nextOperand = Integer.parseInt(s);
            if( currentOperator == null ){
                result = nextOperand;
                return;
            }
            result = currentOperator.operate(result, nextOperand);
            return;
        }

        currentOperator = Operator.findOperator(s);
//        System.out.println("currentOperator = " + currentOperator); - debug
    }
}
