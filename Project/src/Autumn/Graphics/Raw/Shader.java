package Autumn.Graphics.Raw;

import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import Autumn.Mathf.*;

import org.lwjgl.BufferUtils;

public class Shader {

	private final int prog;

	public String vertex = "#version 330 core\n" +
						"\n" +
						"in vec2 pos;\n" +
						"in vec2 coord;\n" +
						"\n" +
						"uniform vec4 color;\n" +
						"uniform mat4 proj;\n" +
						"\n" +
						"uniform vec2 position;\n" +
						"uniform vec2 scale;\n" +
						"\n" +
						"uniform int sh;\n" +
						"\n" +
						"uniform vec4 offset;\n" +
						"\n" +
						"out vec4 c;\n" +
						"out vec2 uc;\n" +
						"\n" +
						"void main() {\n" +
						"\tc = color;\n" +
						"\n" +
						"\tgl_Position = proj * vec4((pos * scale) + position, 0, 1);\n" +
						"\tuc = coord * offset.zw + offset.xy;\n" +
						"}",
				  fragment = "#version 330 core\n" +
						  "\n" +
						  "uniform sampler2D s;\n" +
						  "\n" +
						  "in vec4 c;\n" +
						  "in vec2 uc;\n" +
						  "\n" +
						  "out vec4 cc;\n" +
						  "\n" +
						  "void main() {\n" +
						  "\tcc = c * texture2D(s, uc);\n" +
						  "}";
	
	public Shader() {

		prog = glCreateProgram();
		
		final int vs = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vs, vertex);
		
		glCompileShader(vs);
		if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1) { System.err.println(glGetShaderInfoLog(vs)); System.exit(1); }

		final int fs = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fs, fragment);
		
		glCompileShader(fs);
		if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1) { System.err.println(glGetShaderInfoLog(fs)); System.exit(1); }
		
		glAttachShader(prog, vs);
		glAttachShader(prog, fs);
		
		glBindAttribLocation(prog, 0, "verts");
		glBindAttribLocation(prog, 1, "uvs");
		
		glLinkProgram(prog);
		if(glGetProgrami(prog, GL_LINK_STATUS) != 1) { System.err.println(glGetProgramInfoLog(prog)); System.exit(1); }
		
		glValidateProgram(prog);
		if(glGetProgrami(prog, GL_VALIDATE_STATUS) != 1) { System.err.println(glGetProgramInfoLog(prog)); System.exit(1); }
	}
	public void setUniform(String n, Vector2 v) { int l = glGetUniformLocation(prog, n); if(l != -1) glUniform2f(l, v.x, v.y); }
	//public void setUniform(String n, Vector2f v) { int l = glGetUniformLocation(prog, n); if(l != -1) glUniform2f(l, v.x, v.y); }
	public void setUniform(String n, Vector4f v) { int l = glGetUniformLocation(prog, n); if(l != -1) glUniform4f(l, v.x, v.y, v.z, v.w); }
	//public void setUniform(String n, int i) { int l = glGetUniformLocation(prog, n); if(l != -1) glUniform1i(l, i); }
	public void setUniform(String n, Matrix4 m) { int l = glGetUniformLocation(prog, n); FloatBuffer b = BufferUtils.createFloatBuffer(16); m.get(b); if(l != -1) glUniformMatrix4fv(l, false, b); b.flip(); }
	public void bind() {
		glUseProgram(prog);
	}
	public void unbind() {
		glUseProgram(0);
	}
}