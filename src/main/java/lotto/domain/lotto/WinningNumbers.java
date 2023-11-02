package lotto.domain.lotto;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningNumbers {
    private static final Pattern winningNumbersRegex = Pattern.compile("\\d+(,*\\s*\\d*)*");

    private final List<Integer> numbers;

    public WinningNumbers(String winningNumber) {
        validate(winningNumber);
        this.numbers = winningNumbersToList(winningNumber);
    }

    public Collection<Integer> getNumbers() {
        return Collections.unmodifiableCollection(numbers);
    }

    private void validate(String winningNumber) {
        validateFormat(winningNumber);

        List<Integer> winningNumbers = winningNumbersToList(winningNumber);
        validateSize(winningNumbers);
        validateRange(winningNumbers);
    }

    private void validateFormat(String winningNumber) {
        Matcher matcher = winningNumbersRegex.matcher(winningNumber);
        boolean isNotMatch = !matcher.matches();

        if (isNotMatch) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에는 숫자, 공백, 콤마(,)만 입력할 수 있습니다.");
        }
    }

    private void validateSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개의 숫자가 필요합니다.");
        }
    }

    private void validateRange(List<Integer> winningNumbers) {
        if (hasInvalidRangeNumber(winningNumbers)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1~45 사이의 숫자여야 합니다.");
        }
    }

    private static boolean hasInvalidRangeNumber(List<Integer> winningNumbers) {
        return winningNumbers.stream()
                .allMatch(num -> num >= 1 && num <= 45);
    }

    private List<Integer> winningNumbersToList(String winningNumber) {
        return Arrays.stream(extractNumbers(winningNumber))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    private String[] extractNumbers(String winningNumber) {
        return winningNumber.split("\\D+");
    }
}
