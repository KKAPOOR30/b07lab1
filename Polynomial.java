public class Polynomial{
    double [] coef;
    
    public Polynomial() {
        coef = new double []{0};
    }

    public Polynomial(double p []){
        coef = p;
    }

    Polynomial add(Polynomial poly){
        int max_len = Math.max(this.coef.length, poly.coef.length);
        double [] result = new double [max_len];
        for (int i = 0; i < max_len; i++){
            double a = 0;
            double b = 0;
            if (i < this.coef.length){
                b = this.coef[i];
            }
            if (i < poly.coef.length){
                a = poly.coef[i];
            }
            result[i] = a + b;
        } 
        Polynomial p = new Polynomial(result);
        return p;
    }

    double evaluate(double x){
        double total = 0;
        for (int i = 0; i < this.coef.length; i++){
            total += this.coef[i] * Math.pow(x, i);
        }
        return total;
    }

    boolean hasRoot(double x){
        if (this.evaluate(x) == 0){
            return true;
        } 
        return false;
    }
}