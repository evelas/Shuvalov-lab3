import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Shuvalov {
    /**
     * Основной метод. Вызывает параллельный поток
     * @param args - Параметры получаемые из консоли
     */
    public static void main(String[] args) {

        Thread myThread = new Thread(() -> {
            ArrayList<Integer> ints = new ArrayList<>();
            Random rand = new Random();
            for(int i = 0; i < 10; i++) {
                ints.add(rand.nextInt(1000) + 1);
            }
            ArrayList<Integer> newInts = new ArrayList<>();
            for (int x: ints) {
                if(x % 2 == 0) {
                    newInts.add(x);
                }
            }
            Collections.sort(newInts);
            for(int y: newInts) {
                System.out.println("One parameter: " +y);
                NumberCrusher multiple = (int num) -> num*2;
                System.out.println(multiple.multiply(y));
            }
        });
        myThread.start();

        if(myThread.isAlive()) {
            try {
                myThread.join();
            } catch(InterruptedException e) {
                System.out.print(e.getMessage());
            }
        }
        System.out.println("Главный поток завершён...");
    }


    /**
     * Функциональный интерфейс
     */
    @FunctionalInterface
    public interface NumberCrusher {
        int multiply(int num);
    }
}
