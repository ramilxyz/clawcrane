package xyz.ramil.clawcrane.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import xyz.ramil.clawcrane.BodyEditorLoader;


/**
 * Created by julienvillegas on 01/02/2017.
 */

public class TCBox extends Image {

    private Body body;
    private World world;

    public TCBox(World aWorld, int pos_x, float pos_y, Texture texture) {
        super(texture);

        this.setSize(pos_x, pos_y);
        this.setPosition(0, 53);

        world = aWorld;

        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("box.json"));
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(this.getX(), this.getY());
        bodyDef.bullet = true;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        float scale = this.getWidth();
        loader.attachFixture(body, "box", fixtureDef, scale);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);

        body.setUserData(this);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        this.setPosition(body.getPosition().x, body.getPosition().y);

    }


}

