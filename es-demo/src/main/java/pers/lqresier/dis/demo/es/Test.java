package pers.lqresier.dis.demo.es;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/24 10:56
 * Description
 */
public class Test {
    public static int testFinally1() {
        int result = 1;
        try {
            result = 2;
            return result;
        } catch (Exception e) {
            return 0;
        } finally {
            result = 3;
            System.out.println("execute finally1");
        }
    }

    public static StringBuffer testFinally2() {
        StringBuffer s = new StringBuffer("Hello");
        try {
            return s;
        } catch (Exception e) {
            return null;
        } finally {
            s.append(" World");
            System.out.println("execute finally2");
        }
    }

    public static void main(String[] args) {
        int result = testFinally1();
        System.out.println(result);
        StringBuffer resultRef = testFinally2();
        System.out.println(resultRef);
    }
}
