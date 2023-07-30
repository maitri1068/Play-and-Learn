package com.danny_jiang.tracinglibrary.bean;




public class LetterFactory {

    public static final int A = 1;
    public static final int B = A + 1;
    public static final int C = B + 1;
    public static final int D = C + 1;
    public static final int E = D + 1;
    public static final int F = E + 1;
    public static final int G = F + 1;
    public static final int H = G + 1;
    public static final int I = H + 1;
    public static final int J = I + 1;
    public static final int K = J + 1;
    public static final int L = K + 1;
    public static final int M = L + 1;
    public static final int N = M + 1;
    public static final int O = N + 1;
    public static final int P = O + 1;
    public static final int Q = P + 1;
    public static final int R = Q + 1;
    public static final int S = R + 1;
    public static final int T = S + 1;
    public static final int U = T + 1;
    public static final int V = U + 1;
    public static final int W = V + 1;
    public static final int X = W + 1;
    public static final int Y = X + 1;
    public static final int Z = Y + 1;

    public static final int a = Z+1;
    public static final int b = a+1;
    public static final int c = b+1;
    public static final int d = c+1;
    public static final int e = d+1;
    public static final int f = e+1;
    public static final int g = f+1;
    public static final int h = g+1;
    public static final int i = h+1;
    public static final int j = i+1;
    public static final int k = j+1;
    public static final int l = k+1;
    public static final int m = l+1;
    public static final int n = m+1;
    public static final int o = n+1;
    public static final int p = o+1;
    public static final int q = p+1;
    public static final int r = q+1;
    public static final int s = r+1;
    public static final int t = s+1;
    public static final int u = t+1;
    public static final int v = u+1;
    public static final int w = v+1;
    public static final int x = w+1;
    public static final int y = x+1;
    public static final int z = y+1;



    public String getLetterAssets() {
        return "letter/" + letter + "_bg.png";
    }

    public String getTracingAssets() {
        return "trace/" + letter + "_tracing.png";
    }

    public String getStrokeAssets() {
        return "strokes/" + letter + "_PointsInfo.json";
    }


    private int letter = A;

    public void setLetter( int letterChar) {
        this.letter = letterChar;
    }

}
