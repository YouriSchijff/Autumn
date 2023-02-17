package Autumn.Graphics.Raw;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import Autumn.Graphics.Window;
import Autumn.Mathf.*;

import org.lwjgl.BufferUtils;

public class Texture {
	static float[] a = new float[] {
			0,1,1,
			1,1,0,
			1,0,0,
			0,0,1, 
	};
	private int id, w, h;
	//private final static List<Texture> ti = new ArrayList<>();
	//private final static Texture tl = null;
	private final Mesh m; private final Shader s;
	private Vector2 v;
	private final Vector4f c;
	//private boolean st = false;
	
	public Texture(String name) {
		this.v = new Vector2(0,0);
		this.c = new Vector4f(1,1,1,1);

		m = new Mesh(a,a);
		s = new Shader();

		createTexture(name);

		//ti.add(this);
	}
	
	/*public Texture(String name, Vector2 position, Vector4f color) {
		this.v = position;
		this.c = color;

		m = new Mesh(a,a);
		s = new Shader();

		createTexture(name);

		ti.add(this);
	}*/
	
	private void createTexture(String name) {
		id = glGenTextures();

		glBindTexture(GL_TEXTURE_2D, id);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,GL_CLAMP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,GL_CLAMP);

		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer c = BufferUtils.createIntBuffer(1);
		
		ByteBuffer d = stbi_load("Project/res/"+name, w, h, c, 4);

		this.w = w.get();
		this.h = h.get();

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.w, this.h, 0, GL_RGBA, GL_UNSIGNED_BYTE, d);

		try { if(d != null) stbi_image_free(d); }
		catch(Exception e) { System.exit(-1); }
	}

	public void render() {
		s.bind();
		
		s.setUniform("color", new Vector4f(c.x,c.y,c.z,c.w));
		s.setUniform("proj", Matrix4.Ortho(0, Window.size.x, Window.size.y, 0, -1, 1));
		s.setUniform("position", new Vector2(v.x,v.y)); s.setUniform("scale", new Vector2(w, h));
		s.setUniform("offset", new Vector4f(0,0,1,1));
		
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, id);
		
		m.render();
		
		s.unbind();
		unbind();
	}

	public void setPosition(int x, int y) { v = new Vector2(x,y); }
	public void addPosition(int x, int y) { v = new Vector2(v.x+x,v.y+y); }

	private void unbind() { glBindTexture(GL_TEXTURE_2D, 0); }
	//public int id() { return id; }
	//public static void cleanUp() { for(Texture t : ti) { glDeleteTextures(t.id); } }

	//public void setStatic(boolean bool) { st=bool; }
	//public static Texture find(String n) { for(int i = 0; i < ti.size(); i++) { tl = ti.get(i); if(tl.n.startsWith(n)) return tl; } return null; }
	//else   { s.setUniform("position", new Vector2((int)Math.round(v.x*Camera.zoom)+Camera.x,(int)Math.round(v.y*Camera.zoom)+Camera.y)); s.setUniform("scale", new Vector2f(((float)w)*Camera.zoom,((float)h)*Camera.zoom));}
}