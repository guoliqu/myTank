import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleTest {

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()){
            String str = iter.next();
            if (str.equals("a") || str.equals("b")){
                iter.remove();
            }
        }
    }
}
