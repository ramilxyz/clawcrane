package xyz.ramil.clawcrane.TCSelecttoy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Select extends Image {

    public Select(Texture texture, int width, int height) {
        super(texture);
        this.setSize(width, height);
        this.setPosition(53, 100);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);


    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }


}
