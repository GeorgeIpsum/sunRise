package com.sunrise.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.sunrise.util.Constants;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

/**
 * Created by Ibrahim Saberi on 6/19/2017.
 */
public class Assets implements Disposable, AssetErrorListener {

    public class AssetLuigi {
        public final AtlasRegion body;

        public AssetLuigi(TextureAtlas atlas) {
            body = atlas.findRegion("0");
        }
    }

    public static final String TAG = Assets.class.getName();

    public AssetLuigi luigi;

    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    private Assets() {}

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();

        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for(String a : assetManager.getAssetNames()) Gdx.app.debug(TAG, "assets: " + a);
    }

    TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

    for(Texture t : atlas.getTextures()) {
        t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    luigi = new AssetLuigi(atlas);

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    @Override
    public void error(String filename, Class type, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", (Exception)throwable);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
    }

}


