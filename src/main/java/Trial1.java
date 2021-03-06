import java.util.concurrent.atomic.AtomicInteger;

public class Trial1 extends AbstractTrial {
    private static final AtomicInteger val = new AtomicInteger();

    public static void main(String... args) throws InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            assert size() == 0;

            Thread th1 = th();
            Thread th2 = th();
            Thread th3 = th();

            th1.start();
            th2.start();
            th3.start();

            th1.join();
            th2.join();
            th3.join();

            // Каждый поток, по завершении работы, должен добавить уникальный идентификатор от 1 до числа потоков.

            assert size() == 3;
            assert containsKey(1);
            assert containsKey(2);
            assert containsKey(3);
            assert contains(th1);
            assert contains(th2);
            assert contains(th3);

            clear();

            System.out.println("Checked " + i);
        }
    }

    private static Thread th() {
        return new Thread(() -> {
            // Используйте поле val для синхронизации потоков
            // и метод put(int) для добавления уникального идентификатора
            // Использование reflection запрещено

            // Правки можно вносить от этой линии
            put(val.addAndGet(1));
            val.compareAndSet(3,0);
            // До этой
        });
    }

}
