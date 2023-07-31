package stringcalculator;

import stringcalculator.operator.Operator;

import java.util.regex.Pattern;

public class InputValidator {
    // input 관련 검증을 진행하는 유틸리티 클래스의 역할
    // Input 관련 발생 가능한 예외 관련 처리를 해주는 유틸리티 클래스

    private Boolean isOperatorV1(String input) { // 연산자인지 확인합니다
       String[] operationType = new String[]{"+", "-", "/", "*"};
        for (String s : operationType) {
            if (input.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isOperator(String input) { // 연산자인지 확인합니다
        for (Operator value : Operator.values()) {
            if(input.equals(value.getOp())) {
                return true;
            }
        }
        return false;
    }

    private Boolean isOperandV1(String input) { // 피연산자인지 확인합니다
        try {
            int number = Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Operand 구별하는 또 다른 방법(정규표현식 활용하는 방식)
    public boolean defineOperand(String input) {
        String regex = "^[0-9]*$";
        if(Pattern.matches(regex, input)) {
            return true;
        }
        return false;
    }

    public Boolean isOperand(String input) { // 피연산자인지 확인합니다
        String regex = "^[0-9]*$";
        if(Pattern.matches(regex, input)) {
            return true;
        }
        return false;
    }

    // 잘못된 문자열의 경우를 생각해보자
    public Boolean validateInput(String s) {
        // null 아니면 빈 공백인 경우에 대해서 (처리 완료)
        if (s == null || s.equals(" ")) {
            return false;
        }

        // 먼저 공백을 기준으로 문자열들을 구분을 해둔 상황에서
        String[] inputs = s.split("");

        // 공백 규칙을 지키지 아니한 경우
        // 공백 기준으로 나누되 순서로 접근하는 방향이 제일 좋을 듯 하다 ( 1, 3, 5,, 홀수 index에 operator가 위치해야 하므로)
        // 마찬가지로 짝수 번째 인덱스에는 수로 구성된 문자열이 들어왔는지를 확인해야 한다
        for (int i = 0; i < inputs.length; i++) {
            if (i % 2 == 0 && !isOperand(inputs[i])) {
                return false;
            }

            if (i % 2 == 1 && !isOperator(inputs[i])) {
                return false;
            }
        }

        return true;
    }


}