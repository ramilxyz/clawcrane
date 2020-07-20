package xyz.ramil.clawcrane.Bodies;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TCBasket extends Image {
    private World aWorld;
    private Body myBodyA;

    public TCBasket(World world) {

        aWorld = world;
        BodyDef myBodyDefA = new BodyDef();
        myBodyDefA.type = BodyDef.BodyType.StaticBody;
        myBodyDefA.position.set(150, 55);
        myBodyA = world.createBody(myBodyDefA);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(15, 15);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        myBodyA.createFixture(shape, 1.0f);


        shape.dispose();
        myBodyA.setUserData(this);
    }

    public Body getMyBodyA() {
        return myBodyA;
    }
}
