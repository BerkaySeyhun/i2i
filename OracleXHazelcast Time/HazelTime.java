import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelTime {

    /*
     
      Bu kod, Hazelcast'in performansını ölçmek için kullanılır.
      Rastgele 20.000 sayı oluşturur ve bunları map'e koyar.
      Ardından, rastgele 100.000 sayıyı map'ten alır.
      Her iki işlem için de geçen süre milisaniye cinsinden yazdırılır.
      
     */
	
    public static void main(String[] args) {
        HazelcastInstance hazel = Hazelcast.newHazelcastInstance();
        IMap<Integer, Integer> map = hazel.getMap("numbers");
        
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < 20000; i++) {
            map.put(random.nextInt(100000), i);
        }
        long end = System.nanoTime();
        System.out.println("Calculated time calculation for Hazelcast");
        System.out.println("Calculated average time (insert-select) for 20000 random number: " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        
        long start2 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            map.get(random.nextInt(100000));
        }
        long end2 = System.nanoTime();

        System.out.println("Calculated average time (insert-select) for 100000 random number: " + TimeUnit.NANOSECONDS.toMillis(end2 - start2) + " ms");
    }
}