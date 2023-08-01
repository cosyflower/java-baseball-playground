package stringcalculator;


// 검증된 문자열들을 가지고 실질적으로 계산이 진행되는 구간 (X)
// 입력받은 문자열을 공백을 기준으로 나누는 구간이다 (O)

public class Formula {
    // .getLine()을 통해서 입력받은 문자열을 저장할 수 있는 formula
    private String input;

    public Formula(String input) {
        this.input = input;
    }

    String[] splitInput() {
        return input.split(" ");
    }
}
