import java.awt.*;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        MyMatrix<Integer> m = new MyMatrix<>();

        m.put(0, 0, 10);

        Point a = new Point(0, 0);
        Point b = new Point(1, 0);



        if (m.put(0, 0, 11) == null){

            System.out.println("NULL");
        }


    }
}
