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

public class TCToys extends Image {

    private Body body;
    private World world;
    private boolean delete;


    public TCToys(Texture texture,  //Toy texture file
                  int txW, int txH, // Toy texture Width, Height
                  float pos_x, float pos_y,
                  World aWorld, String BELfile, String name) {
        super(texture);
        this.setSize(txW, txH); // 23 26
        this.setPosition(pos_x, pos_y);
        world = aWorld;
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal(BELfile));


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.getX(), this.getY());
        bodyDef.bullet = true;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.09f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0.5f;

        float scale = this.getWidth();
        loader.attachFixture(body, name, fixtureDef, scale);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        body.setAngularVelocity(3);
        body.setUserData(this);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (delete) {
            world.destroyBody(body);
            this.remove();
        }
        this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        this.setPosition(body.getPosition().x - this.getWidth() / 2, body.getPosition().y - this.getHeight() / 2);

    }

    public void destroy() {
        delete = true;

    }


    public Body getBody() {
        return body;
    }
}
