package cop5556sp17;

/**
 * This class contains several static methods useful when developing
 * the code generation part of our compiler.
 */

import cop5556sp17.AST.Type.TypeName;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CodeGenUtils {

    /**
     * Prints the provided classfile, generally created by asm,
     * in a human readable format
     *
     * @param bytecode
     */
    public static void dumpBytecode(byte[] bytecode) {
        int flags = ClassReader.SKIP_DEBUG;
        ClassReader cr;
        cr = new ClassReader(bytecode);
        cr.accept(new TraceClassVisitor(new PrintWriter(System.out)), flags);
    }

    /**
     * Loader for dynamically generated classes.
     * Instantiated by getInstance.
     *
     */
    public static class DynamicClassLoader extends ClassLoader {
        public DynamicClassLoader(ClassLoader parent) {
            super(parent);
        }

        public Class<?> define(String className, byte[] bytecode) {
            return super.defineClass(className, bytecode, 0, bytecode.length);
        }
    }

    ;


    /**
     * Creates an instance of the indicated class from the provided byteCode.
     * args is passed as a parameter to the constructor, and in order to
     * be the correct type for generated code, should be String[]
     *
     *
     * @param name
     * @param byteCode
     * @param args
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Runnable getInstance(String name, byte[] byteCode, Object args)
            throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        DynamicClassLoader loader = new DynamicClassLoader(Thread.currentThread().getContextClassLoader());
        Class<?> testClass = loader.define(name, byteCode);
        Constructor<?> constructor = testClass.getConstructor(args.getClass());
        return (Runnable) constructor.newInstance(args);
    }

    /**
     * Generates code to print the given String.
     * IF !GEN, does not generate code.
     * Used to allow observation of execution of generated program
     * during development and grading.
     *
     * @param mv
     * @param message
     */
    public static void genPrint(boolean GEN, MethodVisitor mv, String message) {
        if (GEN) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn(message);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        }
    }


    /**
     * Generates code to print the value on top of the stack without consuming it.
     * If !GEN, does not generate code.
     *
     * GEN Requires stack is not empty, and type matches the given type.
     *
     * Currently implemented only for integer and boolean.
     *
     * @param GEN
     * @param mv
     * @param type
     */
    public static void genPrintTOS(boolean GEN, MethodVisitor mv, TypeName type) {
        if (GEN) {
            mv.visitInsn(Opcodes.DUP);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitInsn(Opcodes.SWAP);
            switch (type) {
                case INTEGER: {
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(I)V", false);
                }
                break;
                case BOOLEAN: {
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Z)V", false);
                }
                break;
                default: {
                    throw new RuntimeException("genPrintTOS called unimplemented type " + type);
                }
            }
        }
    }
}