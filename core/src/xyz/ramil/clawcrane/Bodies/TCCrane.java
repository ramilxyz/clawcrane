package xyz.ramil.clawcrane.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TCCrane extends Image {
    public float gx, gy;
    public RevoluteJointDef revoluteJointDef, revoluteJointDef2;
    public RevoluteJoint rrevoluteJoint, lrevoluteJoint;
    public float vertspeed, horizspeed;
    private Body myBodyA, myBodyB, myBodyC, myBodyD, myBodyE, myBodyF;
    private Image myBodyATexture, myBodyBTexture, myBodyCTexture,
            myBodyDTexture, myBodyETexture, myBodyFTexture;
    private World world;

    public TCCrane(World aWorld, float gx, float gy) {

        this.gx = gx;
        this.gy = gy;
        world = aWorld;
//========================================================
        myBodyATexture = new Image(new Texture(Gdx.files.internal("metal_h.png")));
        myBodyATexture.setSize(16, 4);

        myBodyATexture.setPosition(gx, gy);


        BodyDef myBodyDefA = new BodyDef();
        myBodyDefA.type = BodyDef.BodyType.DynamicBody;
        myBodyDefA.position.set(gx, gy);
        myBodyDefA.bullet = true;
        myBodyA = world.createBody(myBodyDefA);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8, 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 2f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        myBodyA.createFixture(shape, 1.0f);
        myBodyA.setAngularVelocity(0);
        myBodyA.setFixedRotation(true);

        myBodyA.setLinearVelocity(0, 70);
        //===========================================
        myBodyBTexture = new Image(new Texture(Gdx.files.internal("metal_h.png")));
        myBodyBTexture.setSize(16, 4);
        myBodyBTexture.setOrigin(8, 2);
        myBodyBTexture.setPosition(gx + 16, gy);

        BodyDef myBodyDefB = new BodyDef();
        myBodyDefB.type = BodyDef.BodyType.DynamicBody;
        myBodyDefB.position.set(gx + 16, gy);
        myBodyDefB.bullet = true;
        myBodyB = world.createBody(myBodyDefB);
        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(8, 2);
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.density = 2f;
        fixtureDef2.friction = 0f;
        fixtureDef2.restitution = 0f;
        myBodyB.createFixture(shape2, 1.0f);
        myBodyB.setAngularVelocity(0);

        //===========================================
        myBodyCTexture = new Image(new Texture(Gdx.files.internal("metal_h.png")));
        myBodyCTexture.setSize(16, 4);
        myBodyCTexture.setOrigin(8, 2);
        myBodyCTexture.setPosition(gx - 16, gy);

        BodyDef myBodyDefC = new BodyDef();
        myBodyDefC.type = BodyDef.BodyType.DynamicBody;
        myBodyDefC.position.set(gx - 16, gy);
        myBodyDefC.bullet = true;
        myBodyC = world.createBody(myBodyDefC);
        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(8, 2);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.density = 2f;
        fixtureDef3.friction = 0f;
        fixtureDef3.restitution = 0f;
        myBodyC.createFixture(shape3, 1.0f);
        myBodyC.setAngularVelocity(0);
        //===========================================
        myBodyDTexture = new Image(new Texture(Gdx.files.internal("claw_l.png")));
        myBodyDTexture.setSize(5, 10);
        myBodyDTexture.setPosition(gx - 24, gy - 12);

        BodyDef myBodyDefD = new BodyDef();
        myBodyDefD.type = BodyDef.BodyType.DynamicBody;
        myBodyDefD.position.set(gx - 24, gy - 12);
        myBodyDefD.bullet = true;
        myBodyD = world.createBody(myBodyDefD);
        PolygonShape shape4 = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(0f, 0);  //низ
        vertices[1] = new Vector2(0, 10);    //лево
        vertices[2] = new Vector2(5, 10);    //право
        shape4.set(vertices);
        FixtureDef fixtureDef4 = new FixtureDef();
        fixtureDef4.density = 2f;
        fixtureDef4.friction = 0f;
        fixtureDef4.restitution = 0f;
        myBodyD.createFixture(shape4, 1.0f);
        myBodyD.setAngularVelocity(0);
        //================================================
        myBodyETexture = new Image(new Texture(Gdx.files.internal("claw_r.png")));
        myBodyETexture.setSize(5, 10);
        myBodyETexture.setOrigin(5, 0);
        myBodyETexture.setPosition(gx + 24, gy - 12);
        BodyDef myBodyDefE = new BodyDef();
        myBodyDefE.type = BodyDef.BodyType.DynamicBody;
        myBodyDefE.position.set(gx + 24, gy - 12);
        myBodyDefE.bullet = true;
        myBodyE = world.createBody(myBodyDefE);
        PolygonShape shape5 = new PolygonShape();
        Vector2[] vertices2 = new Vector2[3];
        vertices2[0] = new Vector2(0, 0);  //низ
        vertices2[1] = new Vector2(-5, 10);    //лево
        vertices2[2] = new Vector2(0, 10);    //право
        shape5.set(vertices2);
        FixtureDef fixtureDef5 = new FixtureDef();
        fixtureDef5.density = 10f;
        fixtureDef5.friction = 0f;
        fixtureDef5.restitution = 0f;
        myBodyE.createFixture(shape5, 1.0f);
        myBodyE.setAngularVelocity(0);
        //=================================================
        myBodyFTexture = new Image(new Texture(Gdx.files.internal("metal_v.png")));
        myBodyFTexture.setSize(4, 208);
        myBodyFTexture.setOrigin(0, 0);
        myBodyFTexture.setPosition(gx + 24, gy - 12);

        BodyDef myBodyDefF = new BodyDef();
        myBodyDefF.type = BodyDef.BodyType.DynamicBody;
        myBodyDefF.position.set(gx, gy);
        myBodyF = world.createBody(myBodyDefF);
        myBodyDefF.bullet = true;
        PolygonShape shape6 = new PolygonShape();
        Vector2[] vertices3 = new Vector2[4];
        vertices3[0] = new Vector2(-2, 0);  //низ
        vertices3[1] = new Vector2(-2, 2);    //лево
        vertices3[2] = new Vector2(2, 2);    //право
        vertices3[3] = new Vector2(2, 0);
        shape6.set(vertices3);
        FixtureDef fixtureDef6 = new FixtureDef();
        fixtureDef6.density = 2f;
        fixtureDef6.friction = 0f;
        fixtureDef6.restitution = 0f;
        myBodyF.createFixture(shape6, 0.0f);
        myBodyF.setAngularVelocity(0);
        myBodyF.setFixedRotation(true);
        myBodyF.setLinearVelocity(0, 0);
        shape.dispose();
        shape2.dispose();
        shape3.dispose();
        shape4.dispose();
        shape5.dispose();
        shape6.dispose();

        rightdefJoint();
        leftdefJoint();
        rWeldJoint();
        lWeldJoint();
        mWeldJoint();
        myBodyA.setUserData("crane1");
        myBodyB.setUserData("crane2");
        myBodyC.setUserData("crane3");
        myBodyD.setUserData("crane4");
        myBodyE.setUserData("crane5");
        myBodyF.setUserData("crane6");

    }

    public void mWeldJoint() {
        WeldJointDef wJointDef3 = new WeldJointDef();
        wJointDef3.bodyA = myBodyA;
        wJointDef3.bodyB = myBodyF;
        wJointDef3.localAnchorA.set(new Vector2(0, 0));
        wJointDef3.localAnchorB.set(new Vector2(0, 2));
        world.createJoint(wJointDef3);
    }

    public void rWeldJoint() {
        WeldJointDef wJointDef = new WeldJointDef();
        wJointDef.bodyA = myBodyC;
        wJointDef.bodyB = myBodyD;
        wJointDef.localAnchorA.set(new Vector2(-6, 0));
        wJointDef.localAnchorB.set(new Vector2(2.5f, 10));
        world.createJoint(wJointDef);
    }

    public void lWeldJoint() {
        WeldJointDef wJointDef2 = new WeldJointDef();
        wJointDef2.bodyA = myBodyB;
        wJointDef2.bodyB = myBodyE;
        wJointDef2.localAnchorA.set(new Vector2(6, 0));
        wJointDef2.localAnchorB.set(new Vector2(-2.5f, 10));
        wJointDef2.referenceAngle = 0;
        wJointDef2.dampingRatio = 0;
        wJointDef2.frequencyHz = 0;
        world.createJoint(wJointDef2);
    }

    public void rightdefJoint() {
        revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.localAnchorA.set(new Vector2(8, 0));
        revoluteJointDef.localAnchorB.set(new Vector2(-8, 0));
        revoluteJointDef.lowerAngle = -0.40f * 3.14f;
        revoluteJointDef.upperAngle = 0;
        revoluteJointDef.enableLimit = true;
        revoluteJointDef.enableMotor = true;
        revoluteJointDef.maxMotorTorque = 1000000.f;
        revoluteJointDef.motorSpeed = -10;
        revoluteJointDef.bodyA = myBodyA;
        revoluteJointDef.bodyB = myBodyB;
        rrevoluteJoint = (RevoluteJoint) world.createJoint(revoluteJointDef);

    }

    public void leftdefJoint() {
        revoluteJointDef2 = new RevoluteJointDef();
        revoluteJointDef2.localAnchorA.set(new Vector2(-8, 0));
        revoluteJointDef2.localAnchorB.set(new Vector2(8, 0));
        revoluteJointDef2.lowerAngle = 0; //закрыт
        revoluteJointDef2.upperAngle = 0.4f * 3.14f;            //открыт
        revoluteJointDef2.enableLimit = true;
        revoluteJointDef2.enableMotor = true;
        revoluteJointDef2.maxMotorTorque = 1000000.f;
        revoluteJointDef2.motorSpeed = 10;
        revoluteJointDef2.bodyA = myBodyA;
        revoluteJointDef2.bodyB = myBodyC;
        lrevoluteJoint = (RevoluteJoint) world.createJoint(revoluteJointDef2);

    }


    public void eventCraneButtonDown() {

        RevoluteJoint rr = rrevoluteJoint;
        RevoluteJoint rl = lrevoluteJoint;
        rr.setMotorSpeed(10);
        rl.setMotorSpeed(-10);
    }

    public void eventCraneButtonUp() {

        RevoluteJoint rr = rrevoluteJoint;
        RevoluteJoint rl = lrevoluteJoint;
        rr.setMotorSpeed(-10);
        rl.setMotorSpeed(10);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        myBodyATexture.draw(batch, parentAlpha);
        myBodyBTexture.draw(batch, parentAlpha);
        myBodyCTexture.draw(batch, parentAlpha);
        myBodyDTexture.draw(batch, parentAlpha);
        myBodyETexture.draw(batch, parentAlpha);
        myBodyFTexture.draw(batch, parentAlpha);


    }

    @Override
    public void act(float delta) {
        myBodyATexture.act(delta);
        myBodyATexture.setRotation(myBodyA.getAngle() * MathUtils.radiansToDegrees);
        myBodyATexture.setPosition(myBodyA.getPosition().x - 8, myBodyA.getPosition().y - 2);

        myBodyBTexture.act(delta);
        myBodyBTexture.setRotation(myBodyB.getAngle() * MathUtils.radiansToDegrees);
        myBodyBTexture.setPosition(myBodyB.getPosition().x - 8, myBodyB.getPosition().y - 2);

        myBodyCTexture.act(delta);
        myBodyCTexture.setRotation(myBodyC.getAngle() * MathUtils.radiansToDegrees);
        myBodyCTexture.setPosition(myBodyC.getPosition().x - 8, myBodyC.getPosition().y - 2);

        myBodyDTexture.act(delta);
        myBodyDTexture.setRotation(myBodyD.getAngle() * MathUtils.radiansToDegrees);
        myBodyDTexture.setPosition(myBodyD.getPosition().x, myBodyD.getPosition().y);

        myBodyETexture.act(delta);
        myBodyETexture.setRotation(myBodyE.getAngle() * MathUtils.radiansToDegrees);
        myBodyETexture.setPosition(myBodyE.getPosition().x - 5, myBodyE.getPosition().y);

        myBodyFTexture.act(delta);
        myBodyFTexture.setRotation(myBodyF.getAngle() * MathUtils.radiansToDegrees);
        myBodyFTexture.setPosition(myBodyF.getPosition().x - 2, myBodyF.getPosition().y + 4);

    }

    public Body getMyBodyA() {
        return myBodyA;
    }

}
