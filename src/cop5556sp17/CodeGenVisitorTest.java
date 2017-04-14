
package cop5556sp17;

import cop5556sp17.AST.ASTNode;
import cop5556sp17.AST.Program;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class CodeGenVisitorTest {

    static final boolean doPrint = true;

    static void show(Object s) {
        if (doPrint) {
            System.out.println(s);
        }
    }

    boolean devel = false;
    boolean grade = true;


    @Test
    public void emptyProg() throws Exception {
        //scan, parse, and type check the program
        String progname = "emptyProg";
        String input = progname + " { }";
        Scanner scanner = new Scanner(input);
        scanner.scan();
        Parser parser = new Parser(scanner);
        ASTNode program = parser.parse();
        TypeCheckVisitor v = new TypeCheckVisitor();
        program.visit(v, null);
        show(program);

        //generate code
        CodeGenVisitor cv = new CodeGenVisitor(devel, grade, null);
        byte[] bytecode = (byte[]) program.visit(cv, null);

        //output the generated bytecode
        CodeGenUtils.dumpBytecode(bytecode);

        //write byte code to file
        String name = ((Program) program).getName();
        String classFileName = "" + name + ".class";
        OutputStream output = new FileOutputStream(classFileName);
        output.write(bytecode);
        output.close();
        System.out.println("wrote classfile to " + classFileName);

        // directly execute bytecode
        String[] args = new String[0]; //create command line argument array to initialize params, none in this case
        Runnable instance = CodeGenUtils.getInstance(name, bytecode, args);
        instance.run();
    }

    @Test
    public void Prog1() throws Exception {
        //scan, parse, and type check the program
        String progname = "Prog1";
        String input = progname + " {integer a a<-3;integer b b<-a;boolean c c<-false;}";
        Scanner scanner = new Scanner(input);
        scanner.scan();
        Parser parser = new Parser(scanner);
        ASTNode program = parser.parse();
        TypeCheckVisitor v = new TypeCheckVisitor();
        program.visit(v, null);
        show(program);

        //generate code
        CodeGenVisitor cv = new CodeGenVisitor(devel, grade, null);
        byte[] bytecode = (byte[]) program.visit(cv, null);

        //output the generated bytecode
        CodeGenUtils.dumpBytecode(bytecode);

        //write byte code to file
        String name = ((Program) program).getName();
        String classFileName = "" + name + ".class";
        OutputStream output = new FileOutputStream(classFileName);
        output.write(bytecode);
        output.close();
        System.out.println("wrote classfile to " + classFileName);

        // directly execute bytecode
        String[] args = new String[0]; //create command line argument array to initialize params, none in this case
        Runnable instance = CodeGenUtils.getInstance(name, bytecode, args);
        instance.run();
    }

    @Test
    public void ifProg() throws Exception {
        //scan, parse, and type check the program
        String progname = "ifProg";
        String input = progname + " { integer a a <- 1; if(a != 0) { a <- 0;}}";
        Scanner scanner = new Scanner(input);
        scanner.scan();
        Parser parser = new Parser(scanner);
        ASTNode program = parser.parse();
        TypeCheckVisitor v = new TypeCheckVisitor();
        program.visit(v, null);
        show(program);

        //generate code
        CodeGenVisitor cv = new CodeGenVisitor(devel, grade, null);
        byte[] bytecode = (byte[]) program.visit(cv, null);

        //output the generated bytecode
        CodeGenUtils.dumpBytecode(bytecode);

        //write byte code to file
        String name = ((Program) program).getName();
        String classFileName = "" + name + ".class";
        OutputStream output = new FileOutputStream(classFileName);
        output.write(bytecode);
        output.close();
        System.out.println("wrote classfile to " + classFileName);

        // directly execute bytecode
        String[] args = new String[0]; //create command line argument array to initialize params, none in this case
        Runnable instance = CodeGenUtils.getInstance(name, bytecode, args);
        instance.run();
    }

    @Test
    public void whileProg() throws Exception {
        //scan, parse, and type check the program
        String progname = "whileProg";
        String input = progname + " { integer a a <- 1; while(a != 0) { integer x x<-2; a <- 0; } a<-2;}";
        Scanner scanner = new Scanner(input);
        scanner.scan();
        Parser parser = new Parser(scanner);
        ASTNode program = parser.parse();
        TypeCheckVisitor v = new TypeCheckVisitor();
        program.visit(v, null);
        show(program);

        //generate code
        CodeGenVisitor cv = new CodeGenVisitor(devel, grade, null);
        byte[] bytecode = (byte[]) program.visit(cv, null);

        //output the generated bytecode
        CodeGenUtils.dumpBytecode(bytecode);

        //write byte code to file
        String name = ((Program) program).getName();
        String classFileName = "" + name + ".class";
        OutputStream output = new FileOutputStream(classFileName);
        output.write(bytecode);
        output.close();
        System.out.println("wrote classfile to " + classFileName);

        // directly execute bytecode
        String[] args = new String[0]; //create command line argument array to initialize params, none in this case
        Runnable instance = CodeGenUtils.getInstance(name, bytecode, args);
        instance.run();
    }

    @Test
    public void Prog3() throws Exception {
        //scan, parse, and type check the program
        String progname = "Prog3";
        String input = progname + " integer i{ integer x integer y boolean z  if(true){x<-1;y<-1000;y<-y+1;z<-x==y;} while(i<4){i<-i+1;integer xx xx<-1;xx<-xx-1;}}";
        Scanner scanner = new Scanner(input);
        scanner.scan();
        Parser parser = new Parser(scanner);
        ASTNode program = parser.parse();
        TypeCheckVisitor v = new TypeCheckVisitor();
        program.visit(v, null);
        show(program);

        //generate code
        CodeGenVisitor cv = new CodeGenVisitor(devel, grade, null);
        byte[] bytecode = (byte[]) program.visit(cv, null);

        //output the generated bytecode
        CodeGenUtils.dumpBytecode(bytecode);

        //write byte code to file
        String name = ((Program) program).getName();
        String classFileName = "" + name + ".class";
        OutputStream output = new FileOutputStream(classFileName);
        output.write(bytecode);
        output.close();
        System.out.println("wrote classfile to " + classFileName);

        // directly execute bytecode
        String[] args = new String[1];
        args[0] = "1";//create command line argument array to initialize params, none in this case
        Runnable instance = CodeGenUtils.getInstance(name, bytecode, args);
        instance.run();
    }


    @Test
    public void Prog4() throws Exception {
        //scan, parse, and type check the program
        String progname = "Prog4";
        String input = progname + " {integer x}";
        Scanner scanner = new Scanner(input);
        scanner.scan();
        Parser parser = new Parser(scanner);
        ASTNode program = parser.parse();
        TypeCheckVisitor v = new TypeCheckVisitor();
        program.visit(v, null);
        show(program);

        //generate code
        CodeGenVisitor cv = new CodeGenVisitor(devel, grade, null);
        byte[] bytecode = (byte[]) program.visit(cv, null);

        //output the generated bytecode
        CodeGenUtils.dumpBytecode(bytecode);

        //write byte code to file
        String name = ((Program) program).getName();
        String classFileName = "" + name + ".class";
        OutputStream output = new FileOutputStream(classFileName);
        output.write(bytecode);
        output.close();
        System.out.println("wrote classfile to " + classFileName);

        // directly execute bytecode
        String[] args = new String[0];
        Runnable instance = CodeGenUtils.getInstance(name, bytecode, args);
        instance.run();
    }

    @Test
    public void Prog5() throws Exception {
        //scan, parse, and type check the program
        String progname = "Prog5";
        String input = progname + " integer y { integer x\tx <- 6;\ty <- x + 1; \twhile(x >= 2) { \t\tx <- x - 1;\t}}";
        Scanner scanner = new Scanner(input);
        scanner.scan();
        Parser parser = new Parser(scanner);
        ASTNode program = parser.parse();
        TypeCheckVisitor v = new TypeCheckVisitor();
        program.visit(v, null);
        show(program);

        //generate code
        CodeGenVisitor cv = new CodeGenVisitor(devel, grade, null);
        byte[] bytecode = (byte[]) program.visit(cv, null);

        //output the generated bytecode
        CodeGenUtils.dumpBytecode(bytecode);

        //write byte code to file
        String name = ((Program) program).getName();
        String classFileName = name + ".class";
        OutputStream output = new FileOutputStream(classFileName);
        output.write(bytecode);
        output.close();
        System.out.println("wrote classfile to " + classFileName);

        // directly execute bytecode
        String[] args = new String[1];
        args[0] = "0";
        Runnable instance = CodeGenUtils.getInstance(name, bytecode, args);
        instance.run();
    }
}

