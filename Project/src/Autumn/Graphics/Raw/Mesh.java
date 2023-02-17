package Autumn.Graphics.Raw;

import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Mesh {

	private final int vID, uID, vao;
	
	public Mesh(float[] verts, float[] uvs) {
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vID);	
		glBufferData(GL_ARRAY_BUFFER, CreateBuffer(verts), GL_STATIC_DRAW);
		
		glVertexAttribPointer(0, verts.length/3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		uID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, uID);	
		glBufferData(GL_ARRAY_BUFFER, CreateBuffer(uvs), GL_STATIC_DRAW); 
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}
	
	public void render() {
		glBindVertexArray(vao);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ARRAY_BUFFER, vID);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);

		glBindBuffer(GL_ARRAY_BUFFER, uID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		
		glDrawArrays(GL_TRIANGLES, 0, 6);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
		glBindVertexArray(0);
	}
	
	FloatBuffer CreateBuffer(float[] buf) { FloatBuffer b = BufferUtils.createFloatBuffer(buf.length); b.put(buf); b.flip(); return b; }
	
}