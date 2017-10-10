import java.io.*;
import java.util.HashMap;

/**
 * Created by evol on 10/10/17.
 */
public class Test {
    public static void main(String[] args){
        try {
            System.out.println(new File("test").getAbsolutePath());
            BufferedReader reader = new BufferedReader(new FileReader(new File("test")));
            BufferedWriter writer = new BufferedWriter(new FileWriter("out"));
            HashMap<String, Integer> states = new HashMap<>();
            HashMap<String, Integer> codes = new HashMap<>();
            Integer state = 1;
            Integer postcode = 1;

            String s;
            while ((s = reader.readLine()) != null){
                String[] split = s.trim().split(" ");
                if(split.length == 3){
                    Integer st = states.get(split[1]);
                    if(st == null){
                        st = ++state;
                        states.put(split[1], st);
                    }
                    Integer code = states.get(split[1]);
                    if(code == null){
                        code = ++postcode;
                        states.put(split[1], st);
                    }

                }
            }

            System.out.println(states);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
