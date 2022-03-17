package com.minirocket.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture targetTexture;
	Rectangle targetRect;
	Random rng;



	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		targetTexture = new Texture("target.png");

		targetRect = new Rectangle((Gdx.graphics.getWidth() / 2) - targetTexture.getWidth(), (Gdx.graphics.getHeight() / 2) - targetTexture.getHeight(), targetTexture.getWidth(), targetTexture.getHeight());

		font = new BitmapFont();
		font.setColor(Color.WHITE);

		rng = new Random();
	}

	@Override
	public void render ()
	{
		//Clearing the background
		Gdx.gl.glClearColor(0.5f, 0.5f, 1, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(targetTexture, targetRect.x, targetRect.y, targetRect.width, targetRect.height);

		if(Gdx.input.justTouched())
		{
			Vector2 touchPosition = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			Rectangle touchedRect = new Rectangle(touchPosition.x, touchPosition.y, 1, 1);
			if(Intersector.overlaps(touchedRect, targetRect))
			{
				changeTargetPosition();
			}
		}
		batch.end();
	}

	private void changeTargetPosition()
	{
		targetRect.setPosition(rng.nextInt(Gdx.graphics.getWidth() - (int) targetRect.width / 2) +
				targetRect.height, rng.nextInt(Gdx.graphics.getHeight() - (int) targetRect.height / 2) +
				targetRect.height);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		targetTexture.dispose();
	}
}
