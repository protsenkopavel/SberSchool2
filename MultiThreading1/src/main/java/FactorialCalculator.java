public class FactorialCalculator implements Runnable{
    private final long number;

    public FactorialCalculator(long number) {
        this.number = number;
    }

    @Override
    public void run() {
        long factorial = calc(number);
        System.out.println("Факториал числа \"" + number + "\" равен: " + factorial);
    }

    public long calc(long number) {
//        System.out.println("Вызван calc:" + number);
        if (number < 0)
            throw new IllegalArgumentException("Не сегодня");
        else if (number <= 1)
            return 1;
        else
            return number * calc(number - 1);
    }
}
