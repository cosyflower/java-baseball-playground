package study;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.Numbers;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    // size() 메서드를 활용해서 크기 구하는 테스트 코드 작성해보기
    @Test
    void checkSize() {
        assertThat(numbers.size()).isEqualTo(3);
    }

    @Test
    void containsCheck() {
        assertThat(numbers.contains(1)).isTrue();
        assertThat(numbers.contains(1)).isTrue();
        assertThat(numbers.contains(2)).isTrue();
        assertThat(numbers.contains(3)).isTrue();
    }

    @ParameterizedTest
    @ValueSource( ints = {1, 2, 9, 10, 13} )
    void isOdd_ShouldReturnTrueForOddNumbers(int number){
        assertTrue( number % 2 == 1 );
    }

    @ParameterizedTest
    @ValueSource( strings = {"", " "} )
    void isBlank(String s){
        assertTrue(Strings.isNullOrEmpty(s));
    }

    @ParameterizedTest
    @CsvSource(value = { "1:true", "2:true", "3:true", "4:false", "5:false" }, delimiter = ':')
    void requirement3( int num, Boolean bool){
        assertThat(numbers.contains(num)).isEqualTo(bool);
    }



}
