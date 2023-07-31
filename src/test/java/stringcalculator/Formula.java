package stringcalculator;


// 검증된 문자열들을 가지고 실질적으로 계산이 진행되는 구간

import stringcalculator.operator.Operator;

public class Formula {

    private final InputValidator iv = new InputValidator();
    private Operator currentOperator;
    private int result = 0;

    public int calculate(String input) {
        String[] inputs = input.split(" ");
        for (String s : inputs) {
            System.out.println("s = " + s);
            calculatePartial(s);
        }
        return result;
    }

    private void calculatePartial(String s) {
        if( iv.isOperand(s) ) {
            if( currentOperator == null ){
                result = Integer.parseInt(s);
                return;
            }
            result = currentOperator.operate(result, Integer.parseInt(s));
        }

        for(Operator operator : Operator.values()) {
            if(s.equals(operator.getOp())) {
                currentOperator = operator;
                return ;
            }
        }
    }


}
