import org.python.util.PythonInterpreter;

public class JythonTest {

    public static void main(String[] args) {
        PythonInterpreter interpreter;
        interpreter = new PythonInterpreter();
        interpreter.execfile("src/test/java/test.py");
        interpreter.exec("s = 'Hello World'");
//        interpreter.exec("System.out.println(s)");
        interpreter.exec("print(s)");
        interpreter.exec("testfunc()");
//        interpreter.exec("print(s[1:-1])");
    }
}
