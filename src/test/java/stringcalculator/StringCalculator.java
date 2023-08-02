package stringcalculator;


// 계산하는 기능을 담당하는 클래스
public class StringCalculator {

    private final InputValidator iv = new InputValidator();
    private final Calculator calculator = new Calculator();

    public int execute(String s) {
        // 먼저 검증해야 한다
        try {
            iv.validateInput(s); // 문제가 없다면 true, 있다면 false를 반환하는 상황
        } catch (Exception e) {
            // Exception 별로 어떤 문제를 가지고 있는지를 알려줘야 한다
            // View 에서는 어떤 값을 return 해줘야 다시 입력을 해야 하는 상황이라는 걸 알 수 있을까?
            System.out.println("Wrong Input");
            throw e;
        }

        // 검증 완료된 문자열이라면
        return calculator.calculate(new Formula(s).getFormula());
    }

}
