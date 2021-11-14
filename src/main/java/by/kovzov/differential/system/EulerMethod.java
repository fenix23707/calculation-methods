package by.kovzov.differential.system;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class EulerMethod extends DifferentialSystemAbstract {
    @Override
    public double[][] calculate(double[] nz, double a, double b, double h, List< Function<double[], Double>> funcs) {
        int n = getN(a, b, h);
        double [][] answer = new double[n][funcs.size() + 1];
        double x = a;

        //start init
        answer[0][0] = x;
        for (int i = 0; i < nz.length; i++) {
            answer[0][i + 1] = nz[i];
        }
        //main calculate
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < funcs.size(); j++) {
                answer[i][j + 1] = answer[i - 1][j + 1] + h * funcs.get(j).apply(answer[i - 1]);
            }
            x += h;
            answer[i][0] = x;
        }
        return answer;
    }
}
