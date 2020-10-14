public class Test {
    public static void main(String[] args) {
        String[] s = "I think /I hear +them. Stand,- ho*! Who's there?".split("[+\\-*/]");
        for (String str :
                s) {
            System.out.println(str);
        }
    }
}
