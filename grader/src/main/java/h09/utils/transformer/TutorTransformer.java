package h09.utils.transformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

/**
 * An abstract class transformer which will transform a class into another class for testing purposes.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public class TutorTransformer implements ClassTransformer {

    /**
     * The source class that should be transformed.
     */
    private final String source;
    /**
     * The original class name that should be replaced.
     */
    private final String original;
    /**
     * THe replacement class name.
     */
    private final String replacement;
    /**
     * The location to replace the class name in bytecode.
     */
    private final String location;


    /**
     * Retrieves the class name if there exists alternative class names.
     *
     * @param classNames the alternative class names
     *
     * @return the found class name
     */
    private static String getClassName(final String... classNames) {
        for (final var className : classNames) {
            try {
                Class.forName(className);
                return className.replaceAll("\\.", "/");
            } catch (ClassNotFoundException e) {
                continue;
            }
        }
        return null;
    }

    /**
     * Constructs and initializes a transformer on the source class.
     *
     * @param sources     the source classes name to be transformed
     * @param originals   the original classes name that that should be replaced
     * @param replacement the replacement class
     * @param location    the replacement location in bytecode
     */
    public TutorTransformer(final String[] sources, final String[] originals, final String replacement, final String location) {
        this.source = getClassName(sources);
        this.original = getClassName(originals);
        this.replacement = replacement;
        this.location = location;
    }

    @Override
    public String getName() {
        return source;
    }

    @Override
    public void transform(final ClassReader reader, final ClassWriter writer) {
        if (reader.getClassName().equals(source)) {
            reader.accept(new TutorClassVisitor(writer), 0);
        } else {
            reader.accept(writer, 0);
        }
    }

    /**
     * The class visitor used for the transformation.
     */
    private class TutorClassVisitor extends ClassVisitor {

        /**
         * Constructs and initialized the class visitor.
         *
         * @param classVisitor the class visitor instance
         */
        public TutorClassVisitor(final ClassVisitor classVisitor) {
            super(Opcodes.ASM9, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(final int access, final String name, final String descriptor, final String signature,
                                         final String[] exceptions) {
            if (location == null || name.equals(location)) {
                return new TutorMethodVisitor(super.visitMethod(access, name, descriptor, signature, exceptions));
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
    }

    /**
     * The method visitor used for the transformation.
     */
    private class TutorMethodVisitor extends MethodVisitor {

        /**
         * Constructs and initialized the method visitor.
         *
         * @param methodVisitor the method visitor instance
         */
        public TutorMethodVisitor(final MethodVisitor methodVisitor) {
            super(Opcodes.ASM9, methodVisitor);
        }

        @Override
        public void visitTypeInsn(final int opcode, final String type) {
            if (opcode == Opcodes.NEW && type.equals(original)) {
                super.visitTypeInsn(opcode, replacement);
            } else {
                super.visitTypeInsn(opcode, type);
            }
        }

        @Override
        public void visitMethodInsn(final int opcode, final String owner, final String name, final String descriptor,
                                    final boolean isInterface) {
            if (owner.equals(original)) {
                super.visitMethodInsn(opcode, replacement, name, descriptor, isInterface);
            } else {
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
            }
        }
    }
}
