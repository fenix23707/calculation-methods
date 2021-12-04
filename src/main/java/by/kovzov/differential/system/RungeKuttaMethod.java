package by.kovzov.differential.system;

import java.util.List;
import java.util.function.Function;

public class RungeKuttaMethod extends DifferentialSystemAbstract {
    @Override
    public double[][] calculate(double[] nz, double a, double b, double h, List<Function<double[], Double>> funcs) {
        int n = getN(a, b, h);
        double[][] answer = new double[n][funcs.size() + 1];
        double x = a;

        answer[0][0] = x;
        for (int i = 0; i < nz.length; i++) {
            answer[0][i + 1] = nz[i];
        }

        for (int i = 1; i < n; i++) {
            double k1 = getKorL1(answer[i - 1], funcs.get(0));
            double l1 = getKorL1(answer[i - 1], funcs.get(1));

            double k2 = getKorL2(answer[i - 1], h, k1, l1, funcs.get(0));
            double l2 = getKorL2(answer[i - 1], h, k1, l1, funcs.get(1));

            double k3 = getKorL3(answer[i - 1], h, k2, l2, funcs.get(0));
            double l3 = getKorL3(answer[i - 1], h, k2, l2, funcs.get(1));

            double k4 = getKorL4(answer[i - 1], h, k3, l3, funcs.get(0));
            double l4 = getKorL4(answer[i - 1], h, k3, l3, funcs.get(1));

            answer[i][0] = answer[i - 1][0] + h;
            answer[i][1] = answer[i - 1][1] + h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            answer[i][2] = answer[i - 1][2] + h / 6 * (l1 + 2 * l2 + 2 * l3 + l4);
        }
        return answer;
    }

    private double getKorL1(double[] args, Function<double[], Double> func) {
        return func.apply(args);
    }

    private double getKorL2(double[] args, double h, double k1, double l1, Function<double[], Double> func) {
        double x = args[0] + h / 2;
        double y = args[1] + k1 * h / 2;
        double z = args[2] + l1 * h / 2;
        return func.apply(new double[]{x, y, z});
    }

    private double getKorL3(double[] args, double h, double k2, double l2, Function<double[], Double> func) {
        double x = args[0] + h / 2;
        double y = args[1] + k2 * h / 2;
        double z = args[2] + l2 * h / 2;
        return func.apply(new double[]{x, y, z});
    }

    private double getKorL4(double[] args, double h, double k3, double l3, Function<double[], Double> func) {
        double x = args[0] + h;
        double y = args[1] + k3 * h;
        double z = args[2] + l3 * h;
        return func.apply(new double[]{x, y, z});
    }


}
