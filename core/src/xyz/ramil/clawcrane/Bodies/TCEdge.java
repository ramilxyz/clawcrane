package xyz.ramil.clawcrane.Bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class TCEdge extends Image {
    private Body myBodyA;
    private World world;

    public TCEdge(World aWorld) {
        world = aWorld;
        BodyDef myBodyDefA = new BodyDef();
        myBodyDefA.type = BodyDef.BodyType.StaticBody;
        myBodyDefA.position.set(0, 0);
        myBodyA = world.createBody(myBodyDefA);

        rightEdge();
        leftEdge();
        topEdge();
        myBodyA.setUserData("edge");
    }

    private void rightEdge() {
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(135, 90), new Vector2(135, 203));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        myBodyA.createFixture(shape, 10.0f);
        myBodyA.setAngularVelocity(0);
        myBodyA.setFixedRotation(true);
        myBodyA.setLinearVelocity(0, 0);
        myBodyA.setUserData("redge");
        shape.dispose();
    }

    private void leftEdge() {
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(1, 30), new Vector2(1, 203));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        myBodyA.createFixture(shape, 10.0f);
        myBodyA.setAngularVelocity(0);
        myBodyA.setFixedRotation(true);
        myBodyA.setLinearVelocity(0, 0);
        myBodyA.setUserData("ledge");
        shape.dispose();
    }

    private void topEdge() {
        BodyDef myBodyDefA = new BodyDef();
        myBodyDefA.type = BodyDef.BodyType.StaticBody;
        myBodyDefA.position.set(0, 0);
        myBodyA = world.createBody(myBodyDefA);
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(1, 203), new Vector2(135, 203));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        myBodyA.createFixture(shape, 10.0f);
        myBodyA.setAngularVelocity(0);
        myBodyA.setFixedRotation(true);
        myBodyA.setLinearVelocity(0, 0);
        myBodyA.setUserData("ledge");
        shape.dispose();
    }
}
