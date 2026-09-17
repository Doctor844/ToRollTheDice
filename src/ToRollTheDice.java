

/**
 * Игра запускает приложение и принимает ввод числе с указанным диапозоном от 1 до MAX_NUMBER.
 * Полее ввода принимает так же RESULT : выводит текущую статистику попыток угадать загаданное число и лучший счёт bestResult.
 * Если число меньше загаданного, то выводит "Не ожидал от тебя такого. Загаданное число меньше, брат", если больше, то "Я сам в шоке, но, загаданное число больше, брат"
 * Число 505 - SOS выводит на экран загаданное программой числою
 * EXIT : закрывает приложение, т.к. цикл работы приложения бесконечный по-другому его закрыть невозможно. Выставляет флаг keepAlive = false.
 * Некорректный ввод выводит "Вы ввели не число. Повторите попытку"
 * Если число выходит из диатозона от 1 до MAX_NUMBER , то выводит "Число должно быть от 1 до 100. Повторите попытку"
 */

import java.util.Random;
import java.util.Scanner;


public class ToRollTheDice {
    private static final String RESULT = "RESULT";
    private static final String EXIT = "EXIT";
    private static final int SOS_MAGIC_NUMBER = 505;
    private static int bestResult = 0;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    public static void setBestResult(int bestResult) {
        ToRollTheDice.bestResult = bestResult;
    }

    public static int getBestResult() {
        return bestResult;
    }

    static boolean find(Scanner scanner) {
        int count = 0;
        int randomNumber = new Random().nextInt(MAX_NUMBER) + 1;
        int temp = 0;

        while (temp != randomNumber) {
            System.out.printf("Введите число от %d до %d: ", MIN_NUMBER, MAX_NUMBER);

            if (scanner.hasNextLine()) {
                String s = scanner.nextLine().trim();

                if(s.equalsIgnoreCase(EXIT)){
                    return false;
                }

                if (s.equalsIgnoreCase(RESULT)) {
                    System.out.println(String.format("Текущее количество попыток: %s. Лучший результат: %s", count, getBestResult()));
                    continue;
                }

                try {
                    temp = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели не число. Повторите попытку");
                    continue;
                }

                if (temp == SOS_MAGIC_NUMBER) {
                    System.out.println("Подсказка " + randomNumber);
                    continue;
                }

                if (temp < 1 || temp > 100) {
                    System.out.printf("Число должно быть от 1 до %s. Повторите попытку", MAX_NUMBER);
                    continue;
                }

                count++;

                if (temp == randomNumber) {
                    if (getBestResult() == 0 || count < getBestResult()) {
                        setBestResult(count);
                    }
                } else {
                    System.out.println(randomNumber < temp
                            ? "Не ожидал от тебя такого. Загаданное число меньше, брат"
                            : "Я сам в шоке, но, загаданное число больше, брат");
                }

            }
        }
        System.out.println(String.format("Число: %d, попыток: %d. Лучший результат %d%n", randomNumber, count, getBestResult()));

        return true;
    }

    public static void main(String[] args) {
        boolean keepAlive = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepAlive) {
                keepAlive = find(scanner);
            }
        }
    }
}