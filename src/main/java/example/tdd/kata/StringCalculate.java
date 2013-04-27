package example.tdd.kata;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculate {

  private static final int IGNORE_NUMBER_LIMIT = 1000;
  private static final String DEFAULT_REGEX_PATTERN = "^//((.)|(\\[(.+)\\]))\n(.*)";
  private static final String DEFAULT_SINGLE_DELIMTERS = ",|\n";
  private static final int REGEX_GROUP_SINGLE_DELIMETER = 2;
  private static final int REGEX_GROUP_MULTIPLE_DELIMETER = 4;
  private static final int REGEX_GROUP_NUMBER_SEQUENCE = 5;

  private Pattern numberPattern = Pattern.compile(DEFAULT_REGEX_PATTERN);

  public int sum(String numbers) {
    String delimeters = getDelimeters(numbers);
    String numbersString = getNumbers(numbers);

    String[] numberValues = getNumberArray(numbersString, delimeters);
    return sumValues(numberValues);
  }

  private String getDelimeters(String numbers) {
    String delimeters = DEFAULT_SINGLE_DELIMTERS;
    Matcher match = numberPattern.matcher(numbers);
    if (match.find()) {
      delimeters = getDefinedDelimiter(match);
    }
    return delimeters;
  }

  private String getDefinedDelimiter(Matcher match) {
    String delimeters;
    if (hasSingleCharacterDelimeter(match)) {
      delimeters = Pattern.quote(match.group(REGEX_GROUP_SINGLE_DELIMETER));
    } else {
      delimeters = Pattern.quote(match.group(REGEX_GROUP_MULTIPLE_DELIMETER));
    }
    return delimeters;
  }

  private boolean hasSingleCharacterDelimeter(Matcher match) {
    return match.group(REGEX_GROUP_SINGLE_DELIMETER) != null;
  }

  private String getNumbers(String numbers) {
    Matcher match = numberPattern.matcher(numbers);
    if (match.find()) {
      numbers = match.group(REGEX_GROUP_NUMBER_SEQUENCE);
    }
    return numbers;
  }

  private String[] getNumberArray(String numbers, String delimeters) {
    if (numbers.isEmpty()) {
      return new String[0];
    }
    return numbers.split(delimeters);
  }

  private int sumValues(String[] numberValues) {
    int sum = 0;

    for (String number : numberValues) {
      int intValue = toInteger(number);
      validateInputValue(intValue, numberValues);
      sum += intValue;
    }
    return sum;
  }

  private void validateInputValue(int intValue, String[] values) {
    if (intValue < 0) {
      throw new RuntimeException("Input contains a negative value '" + Arrays.toString(values) + "'");
    }
  }

  private int toInteger(String value) {
    return ignoreNumberGreaterThanAThousand(Integer.parseInt(value));
  }

  private int ignoreNumberGreaterThanAThousand(int number) {
    if (number > IGNORE_NUMBER_LIMIT) {
      number = 0;
    }
    return number;
  }

}
