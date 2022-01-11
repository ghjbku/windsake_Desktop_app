package desktop_app;

import java.util.ArrayList;
import java.util.HashMap;

public class imperial_data {
    private HashMap<Integer,Float> mastery_data= new HashMap<>();
    private HashMap<String,Integer> tier_data= new HashMap<>();
    private int counter = 0;

    private void put_data(float data){
        mastery_data.put(counter,data);
        counter+=50;
    }

    public imperial_data(){
        put_data(0f);
        put_data(1.85f);
        put_data(2.96f);
        put_data(4.33f);
        put_data(6.95f);
        put_data(7.84f);
        put_data(9.99f);
        put_data(12.40f);
        put_data(15.05f);
        put_data(17.98f);
        put_data(21.16f);
        put_data(24.60f);
        put_data(28.30f);
        put_data(32.26f);
        put_data(36.48f);
        put_data(40.96f);
        put_data(45.70f);
        put_data(50.69f);
        put_data(55.95f);
        put_data(61.47f);
        put_data(67.24f);
        put_data(73.27f);
        put_data(79.57f);
        put_data(86.12f);
        put_data(92.93f);
        put_data(95.84f);
        put_data(98.80f);
        put_data(101.81f);
        put_data(104.86f);
        put_data(107.95f);
        put_data(111.09f);
        put_data(114.28f);
        put_data(117.51f);
        put_data(120.78f);
        put_data(124.10f);
        put_data(127.46f);
        put_data(130.87f);
        put_data(134.33f);
        put_data(137.83f);
        put_data(141.37f);
        put_data(144.96f);
        set_tier_data();
    }
    private void set_tier_data(){
        tier_data.put("apprentice",52000);
        tier_data.put("skilled",80000);
        tier_data.put("professional",120000);
        tier_data.put("artisan",160000);
        tier_data.put("master",220000);
        tier_data.put("guru",320000);
    }
    public int get_tier_data(String name) {
        return tier_data.get(name);
    }
    public float get_percentage(int mastery_point){
        return mastery_data.get(mastery_point);
    }
}
