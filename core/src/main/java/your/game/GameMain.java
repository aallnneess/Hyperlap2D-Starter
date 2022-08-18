package your.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.utils.TextureArrayCpuPolygonSpriteBatch;
import your.game.screens.DemoLevel;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameMain extends Game {

	// TODO: TimeStep value ?
	public static int timeStep = 300;

	protected SceneConfiguration config;
	protected SceneLoader mSceneLoader;
	protected Viewport mViewport;
	protected OrthographicCamera mCamera;
	protected Box2DDebugRenderer box2DDebugRenderer;
	public AssetManager assetManager;
	protected AsyncResourceManager resourceManager;


	@Override
	public void create() {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		// Constructs a CpuSpriteBatch with the default shader. (size default: 2000)
		config = new SceneConfiguration(new TextureArrayCpuPolygonSpriteBatch());

		//Create a basic camera and a viewport
		mCamera = new OrthographicCamera();
		// TODO: Set your worldWidth and worldHeight !
		mViewport = new FitViewport(24,13,mCamera);

		// Creates a new AssetManager with all default loaders.
		assetManager = new AssetManager();
		// set asset manager logger
		assetManager.getLogger().setLevel(Logger.DEBUG);

		// Sets a new AssetLoader for the given type
		assetManager.setLoader(AsyncResourceManager.class,new ResourceManagerLoader(assetManager.getFileHandleResolver()));

		// Adds the given asset to the loading queue of the AssetManager
		assetManager.load("project.dt",AsyncResourceManager.class);
		// TODO: Add Skin to assetManager ? -> assetManager.load("ExampleSkin.json", Skin.class);

		// Blocks until all assets are loaded.
		assetManager.finishLoading();

		// Initialize resourceManager from assetManager
		resourceManager = assetManager.get("project.dt",AsyncResourceManager.class);

		config.setResourceRetriever(resourceManager);

		// add systems
		// TODO: add systems -> config.addSystem(new ExampleSystem);

		// Initialize HyperLap2D's SceneLoader, all assets will be loaded here
		mSceneLoader = new SceneLoader(config);

		// add components to mapper
		// TODO: add components to mapper -> ComponentRetriever.addMapper("ExampleComponent.class);

		box2DDebugRenderer = new Box2DDebugRenderer();

		setScreen();

	}

	public void setScreen() {
		setScreen(new DemoLevel(this,mSceneLoader,resourceManager,mViewport,assetManager,mCamera));
	}

	@Override
	public void render() {

		//Clear screen
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Apply ViewPort and update SceneLoader's ECS engine
		mViewport.apply();
		mSceneLoader.getEngine().process();

		getScreen().render(Gdx.graphics.getDeltaTime());

		box2DDebugRenderer.render(mSceneLoader.getWorld(),mViewport.getCamera().combined);
	}


	@Override
	public void resize(int width, int height) {
		mViewport.update(width, height);
		getScreen().resize(width,height);

		if (width != 0 && height !=0) mSceneLoader.resize(width, height);
	}

	@Override
	public void dispose() {
		getScreen().dispose();
		mSceneLoader.dispose();
	}
}
