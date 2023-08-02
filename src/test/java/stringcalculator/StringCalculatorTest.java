package stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/***
 * 다음 요구사항을 JUnit을 활용해 단위 테스트 코드를 추가해 구현한다.
 * 요구사항
 * 사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 한다.
 * 문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다.
 * 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
 * 예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다
 *
 * 쉬운 테스트 우선으로 진행한다
 * 1. 입력 값에 따라 계산 순서를 진행한다
 * 2. +, - , / , * 사칙연산을 처리해야 한다
 *
 */
/***
 * TO-DO List
 * 1. 공백 기준으로 문자열 나누기
 * 2. 나눠진 문자열 중에서 숫자 문자열을 수로 치환해야 한다
 * 3. operator에 따른 계산 방식을 설정해야 한다
 * 4. 각각의 연산에 따른 결과가 올바른지를 검증해야 한다
 */


public class StringCalculatorTest {

    String plusExp;
    String minusExp;
    String multiExp;
    String dividExp;
    String compliExp;

    String[] strings;
    StringCalculator sc;

    @BeforeEach
    void setUp() {
        plusExp = "2 + 3 + 5";
        minusExp = "7 - 2";
        multiExp = "2 * 7 * 2"; // 28
        dividExp = "5 / 1"; // 5
        compliExp = "2 + 5 * 2 / 7"; // 2

        strings = plusExp.split(" ");
        sc = new StringCalculator();
    }

    @Test
    @DisplayName("Regex_API_테스트하기")
    void checkRegexByPatternMatches() {
        String regex = "^[0-9]*$";
        String input = " 19 22 1";

        assertTrue(Pattern.matches(regex, "20"));
    }

    @ParameterizedTest
    @DisplayName("잘못된_Input_검증하기_null")
    @NullAndEmptySource
    void nullInputValidateV1(String wrongInput) {
        assertThatThrownBy(() -> sc.execute(wrongInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("IllegalArgumentException_확인하기")
    @NullAndEmptySource
    void checkIllegalArgumentException(String wrongInput) {
        assertThatIllegalArgumentException().isThrownBy(() -> sc.execute(wrongInput));
    }

    @ParameterizedTest
    @DisplayName("ValueSource_응용_올바른_문자열")
    @ValueSource( strings = { " ", "1 + 8 / 3", "1 + 4 * 7", "1 - 6 - 9 + 19"})
    void appliedValueSource(String s) {
        int result = sc.execute(s);
        System.out.println("result = " + result);
    }



    @ParameterizedTest
    @DisplayName("사칙연산이_아닌_문자_등장한_경우")
    @ValueSource( strings = { "1 & 8 # 3", "1 + 4 @ 7", "1 - 6 - 9 ) 19"})
    void wrongOperatorThenException(String s) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> sc.execute(s)
        );
    }

    @ParameterizedTest
    @DisplayName("연속된_연산자_등장")
    @ValueSource( strings = { "1 & # 3", "1 + @ 7", "1 - - ) 19"})
    void continueOperatorThenException(String s) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> sc.execute(s)
        );
    }

    @ParameterizedTest
    @DisplayName("연속된_피연산자_등장")
    @ValueSource( strings = { "1 8 # 3", "1 4 @ 7", "1 - 6 - 9 19"})
    void wrongOperandThenException(String s) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> sc.execute(s)
        );
    }

    @ParameterizedTest
    @DisplayName("공백_무시한_입력값")
    @ValueSource( strings = { "1 8# 3", "1 4@ 7", "1 -6 -9 +19"})
    void wrongInputWithWrongSpaces(String s) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> sc.execute(s)
        );
    }

    @ParameterizedTest
    @DisplayName("공백_무시한_입력값")
    @CsvSource( value = {" 1 + 2 - 3 : 0", " 2 + 4 * 2 / 1 : 12", " 2 - 9 * 10 : 2"},
                delimiter = ':')
    void checkResult(String s, int result) {
        assertThat(sc.execute(s)).isEqualTo(result);
    }





}
