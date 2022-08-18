package your.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;

public class ScreenMother extends ScreenAdapter {
    public GameMain gameMain;
    public SceneLoader mSceneLoader;
    public AsyncResourceManager resourceManager;
    public Viewport mViewport;
    public AssetManager assetManager;
    public OrthographicCamera mCamera;

    public ScreenMother(GameMain gameMain, SceneLoader mSceneLoader, AsyncResourceManager resourceManager, Viewport mViewport, AssetManager assetManager, OrthographicCamera mCamera) {
        this.gameMain = gameMain;
        this.mSceneLoader = mSceneLoader;
        this.resourceManager = resourceManager;
        this.mViewport = mViewport;
        this.assetManager = assetManager;
        this.mCamera = mCamera;
    }

    @Override
    public void resize(int width, int height) {}
}
