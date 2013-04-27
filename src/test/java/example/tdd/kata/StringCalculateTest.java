package example.tdd.kata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import example.tdd.kata.StringCalculate;

public class StringCalculateTest {

  private StringCalculate calculate;

  @Before
  public void setUp() {
    calculate = new StringCalculate();
  }

  @Test
  public void emptyStringShouldReturnZero() {
    assertEquals(0, calculate.sum(""));
  }

  @Test
  public void oneNumberShouldReturnTheSameInputNumber() {
    assertEquals(1, calculate.sum("1"));
  }

  @Test
  public void twoNumbersShouldReturnSum() {
    assertEquals(3, calculate.sum("1,2"));
  }

  @Test
  public void multipleNumbersShouldReturnSum() {
    assertEquals(6, calculate.sum("1,2,3"));
  }

  @Test
  public void newLineDelimiterShouldReturnSum() {
    assertEquals(6, calculate.sum("1\n2\n3"));
  }

  @Test
  public void customSpecifiedDelimiterShouldSumFigures() {
    assertEquals(3, calculate.sum("//;\n1;2"));
    assertEquals(3, calculate.sum("//-\n1-2"));
  }

  @Test
  public void shouldEscapeCharatersInInput() {
    assertEquals(3, calculate.sum("//?\n1?2"));
  }

  @Test
  public void shouldThrowExceptionWhenInputContainsNegativeValue() {
    try {
      calculate.sum("-1");
      fail("Expected runtime exception!");
    } catch (RuntimeException e) {
      assertEquals("Input contains a negative value '[-1]'", e.getMessage());
    }
  }

  @Test
  public void shouldThrowExceptionWhenInputContainsMultipleNegativeValue() {
    try {
      calculate.sum("-1,-2");
      fail("Expected runtime exception!");
    } catch (RuntimeException e) {
      assertEquals("Input contains a negative value '[-1, -2]'", e.getMessage());
    }
  }

  @Test
  public void shouldIgnoreNumberLargerThan1000() {
    assertEquals(0, calculate.sum("1001"));
    assertEquals(1, calculate.sum("1,1001"));
  }

  @Test
  public void multipleCharacterDelimitersShouldBeHandled() {
    assertEquals(3, calculate.sum("//[;;]\n1;;2"));
  }
}