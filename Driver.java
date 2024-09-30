import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) throws IOException {
        Polynomial empty = new Polynomial();
        
        double [] c1 = {1,-4,-2};
        int [] e1 = {2,0,1};
        Polynomial p1 = new Polynomial(c1, e1);  // x^2-4-2x

        double [] c2 = {1,-1};
        int [] e2 = {2,0};
        Polynomial p2 = new Polynomial(c2, e2); // x^2-1

        // CHECKING CONTRRUCTORS AND EVALUATE()
        System.out.println(p1.evaluate(3));  // Ans: -1
        System.out.println(empty.evaluate(3));  // Ans: 0

        // CHACKING HASROOT()
        System.out.println(p2.hasRoot(0)); // false
        System.out.println(p2.hasRoot(1)); // true
        System.out.println(p2.hasRoot(-1));  // true
        System.out.println(empty.hasRoot(1)); // true

        // CHECKING ADD()
        Polynomial new_p1 = p1.add(empty);
        Polynomial new_p2 = empty.add(p2);
        Polynomial sum1 = p1.add(p2);   // 2x^2-5-2x
        Polynomial sum2 = p2.add(p1);

        System.out.println(new_p1.evaluate(3)); // -1 
        System.out.println(new_p2.evaluate(4)); // 15
        System.out.println(sum1.evaluate(3)); // 7
        System.out.println(sum2.evaluate(3)); // 7

        // CHECKING MULTIPLY() BY 0
        Polynomial new_empty = empty.multiply(p1);
        Polynomial new_empty2 = p2.multiply(empty);
        
        System.out.println(new_empty.evaluate(0)); // All 0
        System.out.println(new_empty.evaluate(1));
        System.out.println(new_empty.evaluate(2));
        System.out.println(new_empty2.evaluate(0));
        System.out.println(new_empty2.evaluate(1));
        System.out.println(new_empty2.evaluate(2));

        // CHECKING MULTIPLY() 
        Polynomial prod = p1.multiply(p2); // x^4 - 5x^2 - 2x^3 + 4 + 2x

        System.out.println(prod.evaluate(0)); // 4
        System.out.println(prod.evaluate(1)); // 0
        System.out.println(prod.hasRoot(1)); // true
        System.out.println(prod.evaluate(-2)); // 12
        prod.print_func();

        // CHECKING FILE CONSTRUCTOR
        File input1 = new File("input1.txt");
        File input2 = new File("input2.txt");
        File input3 = new File("input3.txt");

        Polynomial pf1 = new Polynomial(input1);
        Polynomial pf2 = new Polynomial(input2);
        Polynomial pf3 = new Polynomial(input3);

        pf1.print_func(); // Whatever is in a file called input1.txt
        pf2.print_func(); // Whatever is in a file called input2.txt
        pf3.print_func(); // Whatever is in a file called input3.txt

        // CHECKING WRINTING TO FILE
        String file_name = "output.txt";
        String file_name_2 = "output2.txt";

        pf1.saveToFile(file_name); // Create
        p1.saveToFile(file_name); // Overwrite
        pf1.saveToFile(file_name_2); // Create
    }
}