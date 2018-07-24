package com.easytnt.commons.util;

/**
 * 字符处理工具
 * @author Liguiqing
 * @since V3.0
 */

public class StringUtilWrapper {
    static char[] words = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public  enum Word{
        A(0),B(1),C(2),D(3),E(4),F(5),G(6),H(7),I(8),J(9),K(10),L(11),
        M(12),N(13),O(14),P(15),Q(16),R(17),S(18),T(19),U(20),V(21),
        W(22),X(23),Y(24),Z(25);
        private char w;

        private String upper;

        private String lower;

        private int i = 0;

        Word(int i){
            this.i = i;
            this.w = words[i];
            this.upper = String.valueOf(this.w).toUpperCase();
            this.lower = String.valueOf(this.w).toLowerCase();
        }

        public String upperCase(){
            return this.upper;
        }

        public String lowerCase(){
            return this.lower;
        }

        public static Word get(int i){
            switch(i){
                case 1:return A;
                case 2:return B;
                case 3:return C;
                case 4:return D;
                case 5:return E;
                case 6:return F;
                case 7:return G;
                case 8:return H;
                case 9:return I;
                case 10:return J;
                case 11:return K;
                case 12:return L;
                case 13:return M;
                case 14:return N;
                case 15:return O;
                case 16:return P;
                case 17:return Q;
                case 18:return R;
                case 19:return S;
                case 20:return T;
                case 21:return U;
                case 22:return V;
                case 23:return W;
                case 24:return X;
                case 25:return Y;
                case 26:return Z;
                default:return A;
            }
        }
    }

    public static String randomOf(Word... src){
        int i = NumberUtilWrapper.randomBetween(0,src.length);
        return Word.get(i).lower;
    }

    public static String randomOf(char... src){
        int i = NumberUtilWrapper.randomBetween(0,src.length);
        return Word.get(i).lower;
    }
}