package tutorial_syntax;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 6/8/13
 */
public class Conditions {
    public static void main(String[] args){
        // Test switch with char
        String monthString;

        switch (args[0].charAt(0)){
            case 'm': monthString = "March"; break;
            case 'f': monthString = "February"; break;
            default: monthString = "whatever"; break;
        }
        System.out.println(monthString);
        System.out.println();

        // Test if
        System.out.println("The winner is: " + max(3, 6));
        System.out.println("The winner is: " + max(3, 3));
        System.out.println();
    }

    public static int max(int n1, int n2){
        if (n1 >= n2){
            return n1;
        } else {
            return n2;
        }
    }
}
