import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Polynomial{
    
    double [] coef;
    int [] expo;
    
    public Polynomial() {
        coef = new double [] {};
        expo = new int [] {};
    }  

    public Polynomial(double co [], int ex[]){
        coef = co.clone();
        expo = ex.clone();
    }

    public Polynomial(File file_to_read) throws FileNotFoundException{
        Scanner input = new Scanner(file_to_read);
        String poly = input.nextLine();
        String prev = "";
        char [] poly_arr = poly.toCharArray();
        ArrayList<Double> co = new ArrayList<Double>();
        ArrayList<Integer> ex = new ArrayList<Integer>();

        for (int i = 0; i < poly.length(); i++){
            if (poly_arr[i] == '+' || poly_arr[i] == '-' || i == poly.length()-1){
                if (i == poly.length()-1){prev = prev + poly_arr[i];}
                int index = prev.indexOf("x");
                if (index == -1){
                    if (i != 0){
                        if (prev.equals("") || prev.equals("-")){prev = prev + poly_arr[i];}
                        co.add(Double.parseDouble(prev));
                        ex.add(0);
                    }
                } else {
                    String num = "";
                    
                    for (int j = 0; j < index; j++){
                        num = num + prev.charAt(j);
                    } 
                    if (num == ""){num = "1";} 
                    co.add(Double.parseDouble(num));
                    
                    num = "";
                    
                    for (int j = index+1; j < prev.length(); j++){num = num + prev.toCharArray()[j];}
                    if (num == ""){num = "1";}
                    ex.add(Integer.valueOf(num));
                }
                prev = "";
                if (poly_arr[i] == '-'){prev = prev + "-";}
            } else {
                prev = prev + poly_arr[i];
            }
        }
        input.close();

        coef = new double [co.size()];
        expo = new int [co.size()];

        for (int i = 0; i < co.size(); i++){
            coef[i] = co.get(i);
            expo[i] = ex.get(i);
        }
    }



    Polynomial add(Polynomial poly){
        ArrayList<Double> new_coef = new ArrayList<Double>();
        ArrayList<Integer> new_expo = new ArrayList<Integer>();
        for (int i = 0; i < this.expo.length; i++){
            new_expo.add(this.expo[i]);
            new_coef.add(this.coef[i]);
        }
        for (int i = 0; i < poly.expo.length; i++){
            int index = new_expo.indexOf(poly.expo[i]);
            if (index == -1){
                new_expo.add(poly.expo[i]);
                new_coef.add(poly.coef[i]);
            } else {
                new_coef.set(index, new_coef.get(index) + poly.coef[i]);
            }
        }
        double [] coef = new double [new_coef.size()];
        int [] expo = new int [new_expo.size()];
        
        for (int i = 0; i < new_coef.size(); i++){
            coef[i] = new_coef.get(i);
            expo[i] = new_expo.get(i);
        }
        Polynomial result = new Polynomial(coef, expo);
        return result;
    }

    Polynomial multiply(Polynomial poly){
        ArrayList<Double> new_coef = new ArrayList<Double>();
        ArrayList<Integer> new_expo = new ArrayList<Integer>();
        Double co;
        int ex;

        for (int i = 0; i < this.coef.length; i++){
            for (int j = 0; j < poly.coef.length; j++){
                ex = this.expo[i] + poly.expo[j];
                co = this.coef[i] * poly.coef[j];
                int index = new_expo.indexOf(ex);
                if (index == -1){
                    new_expo.add(ex);
                    new_coef.add(co);
                } else {
                    new_coef.set(index, new_coef.get(index) + co);
                }
            }
        }
        
        double [] coef = new double [new_coef.size()];
        int [] expo = new int [new_expo.size()];
        
        for (int i = 0; i < new_coef.size(); i++){
            coef[i] = new_coef.get(i);
            expo[i] = new_expo.get(i);
        }
        Polynomial result = new Polynomial(coef, expo);
        return result;
    }

    double evaluate(double x){
        double total = 0;
        for (int i = 0; i < this.coef.length; i++){
            total += this.coef[i] * Math.pow(x, this.expo[i]);
        }
        return total;
    }

    boolean hasRoot(double x){
        if (this.evaluate(x) == 0){
            return true;
        } 
        return false;
    }

    void saveToFile(String file_name) throws IOException{
        File text = new File(file_name);
        text.createNewFile();

        String to_write = "";
        if(this.expo[0] != 0){to_write = to_write + String.valueOf(this.coef[0]) + "x" + String.valueOf(this.expo[0]);}
        else {to_write = to_write + String.valueOf(this.coef[0]);}

        for (int i = 1; i < this.coef.length; i++){
            if (coef[i] > 0){to_write = to_write + "+";} 
            if(this.expo[i] != 0){to_write = to_write + String.valueOf(this.coef[i]) + "x" + String.valueOf(this.expo[i]);}
            else {to_write = to_write + String.valueOf(this.coef[i]);}            
        }
        PrintStream output = new PrintStream(file_name);
        output.println(to_write);
        output.close();
    }

    // This function is for testing purposes
    void print_func(){
        String to_write = "";
        if(this.expo[0] != 0){to_write = to_write + String.valueOf(this.coef[0]) + "x" + String.valueOf(this.expo[0]);}
        else {to_write = to_write + String.valueOf(this.coef[0]);}

        for (int i = 1; i < this.coef.length; i++){
            if (coef[i] > 0){to_write = to_write + "+";} 
            if(this.expo[i] != 0){to_write = to_write + String.valueOf(this.coef[i]) + "x" + String.valueOf(this.expo[i]);}
            else {to_write = to_write + String.valueOf(this.coef[i]);}            
        }
        System.out.println(to_write);
    }            
}