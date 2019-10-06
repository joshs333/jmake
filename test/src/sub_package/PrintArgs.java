/**
 * @brief This class will print all arguments passed to it
 **/
package sub_package;

public class PrintArgs {
    public static void main(String[] args) {
        for(int i = 0; i < args.length; ++i) {
            System.out.println(args[i]);
        }
    }
}
