package me.modmuss50.optifabric.patcher;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;


import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ASMUtils {

	public static ClassNode readClassFromBytes(byte[] bytes) {
		Validate.notNull(bytes, "Cannot read null bytes");
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, ClassReader.SKIP_FRAMES);
		return classNode;
	}

	public static byte[] writeClassToBytes(ClassNode classNode) {
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	public static ClassNode asClassNode(JarEntry entry, JarFile jarFile) throws IOException {
		InputStream is = jarFile.getInputStream(entry);
		byte[] bytes = IOUtils.toByteArray(is);
		return ASMUtils.readClassFromBytes(bytes);
	}

	public static boolean isSynthetic(int flags) {
		return (flags & Opcodes.ACC_SYNTHETIC) != 0;
	}

}
