package desktop_app.database;

import java.util.HashMap;
import java.util.Map;

public class levelup_values {
    private final Map<Integer, Integer> level_map;
    private final Map<Integer,String> level_strings;
    public levelup_values(){
        level_map= new HashMap<>();
        level_map.put(10, 1);
        level_map.put(20, 2);
        level_map.put(30, 3);
        level_map.put(40, 4);
        level_map.put(50, 5);
        level_map.put(60, 6);
        level_map.put(65, 7);
        level_map.put(75, 8);
        level_map.put(90, 9);
        level_map.put(100, 10);

        level_strings= new HashMap<>();
        level_strings.put(1, "Nobody");
        level_strings.put(2, "Ant");
        level_strings.put(3, "Can lift up grass");
        level_strings.put(4, "Meh");
        level_strings.put(5, "Strong");
        level_strings.put(6, "Can lift you up");
        level_strings.put(7, "Powerful");
        level_strings.put(8, "Master");
        level_strings.put(9, "Grandmaster");
        level_strings.put(10,"Godly");
    }

    public int get_level(int pushups,int situps){
        int level_num =(int) (pushups+(situps/1.8));

                if(level_num<=10){
                    return level_map.get(10);
                }else if(level_num<=20){
                    return level_map.get(20);
                }else if(level_num<=30){
                    return level_map.get(30);
                }else if(level_num<=40){
                    return level_map.get(40);
                }else if(level_num<=50){
                    return level_map.get(50);
                }else if(level_num<=60){
                    return level_map.get(60);
                }else if(level_num<=65){
                    return level_map.get(65);
                }else if(level_num<=75){
                    return level_map.get(75);
                }else if(level_num<=90){
                    return level_map.get(90);
                }else if(level_num<=100){
                    return level_map.get(100);
                }
        return 0;
    }

    public String get_level_string(int level_value){
        switch (level_value){
            case 1:return level_strings.get(1);
            case 2:return level_strings.get(2);
            case 3:return level_strings.get(3);
            case 4:return level_strings.get(4);
            case 5:return level_strings.get(5);
            case 6:return level_strings.get(6);
            case 7:return level_strings.get(7);
            case 8:return level_strings.get(8);
            case 9:return level_strings.get(9);
            case 10:return level_strings.get(10);
            default:return "noob";
        }
    }
}
