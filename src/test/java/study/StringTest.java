package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

//    "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
    @Test
    void split() {
        String s = "1,2";
        assertThat(s.split(",")).contains("1", "2");
    }

    @Test
    void split2() {
        String s = "1,";
        assertThat(s.split(",")).containsExactly("1");
    }

    @Test
    void substring() {
        String s = "(1,2)";
        String substring = s.substring(1, 4);
        assertThat(substring).isEqualTo("1,2");
    }

    @Test
    @DisplayName("charAt()을 활용해서 IndexOutOfBoundsException 확인하기")
    void charAtAndIndexOutOfBoundsException() {
        String s = "abc";
        assertThatThrownBy(() -> s.charAt(6))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range");

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> s.charAt(6))
                .withMessageContaining("St");
    }

    @Test
    @DisplayName("Regex_API_테스트하기")
    void checkRegexByPatternMatches() {
        String regex = "^[0-9]*$";
        String input = " 19 22 1";

        assertTrue(Pattern.matches(regex, "20"));
    }

}
