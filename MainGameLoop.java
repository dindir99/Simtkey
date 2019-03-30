package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayManager.createDisplay();
		Loader loader=new Loader();
		Renderer renderer=new Renderer();
		StaticShader shader = new StaticShader(null, null);
		float[] vertices = {
				-0.5f, 0.5f, 0, //V0
				-0.5f, -0.5f, 0,	//V1
				0.5f, -0.5f, 0,		//V2
				0.5f, 0.5f, 0	//V3
				};
		int[] indices = {
				0,1,3, //(V0,V1,V3)
				3,1,2	//(V3,V1,V2) bottom triangle
		};
		RawModel model = loader.loadToVAO(vertices,indices);
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			shader.start();
			//game logic
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
