package your.game.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import your.game.GameMain;
import your.game.ScreenMother;

public class DemoLevel extends ScreenMother {

    public DemoLevel(GameMain gameMain, SceneLoader mSceneLoader, AsyncResourceManager resourceManager, Viewport mViewport, AssetManager assetManager, OrthographicCamera mCamera) {
        super(gameMain, mSceneLoader, resourceManager, mViewport, assetManager, mCamera);
    }

    @Override
    public void show() {

        // deactivate Systems
        // TODO:

        // load Hyperlap 2d Scene
        mSceneLoader.loadScene("MainScene", mViewport);

    }

    @Override
    public void render(float delta) {}
}
