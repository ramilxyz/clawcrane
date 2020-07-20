package xyz.ramil.clawcrane.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import xyz.ramil.clawcrane.Screens.TCGameScreen;
import xyz.ramil.clawcrane.Screens.TCTitleScreen;

public class TCContactListener implements ContactListener {
    public static Preferences prefs;
    private World world;
    private TCGameScreen generate;


    public TCContactListener(World world) {
        super();
        this.world = world;

        prefs = Gdx.app.getPreferences("ToyCrane");

        if (!prefs.contains("soundState")) {
            prefs.putBoolean("soundState", true);
        }


    }

    public static boolean getSoundState() {
        return prefs.getBoolean("soundState");
    }


    @Override
    public void beginContact(Contact contact) {
        String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
        String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();

        //Gdx.app.debug("begin Contact","between: "+classA+" and "+ classB);
        if (classA.equalsIgnoreCase("xyz.ramil.toycrane.Bodies.TCBasket") && classB.equalsIgnoreCase("xyz.ramil.toycrane.Bodies.TCToys")) {
            TCToys a = (TCToys) (contact.getFixtureB().getBody().getUserData());
            a.destroy();
            Gdx.app.log("", "contact");
            TCGameScreen.score++;
            TCGameScreen.clicks += 2;
            TCGameScreen.listenergenerate = true;
            if (getSoundState()) {
                TCTitleScreen.score.play();
            }

        } else if (classB.equalsIgnoreCase("xyz.ramil.toycrane.Bodies.TCBasket") && classA.equalsIgnoreCase("cxyz.ramil.toycrane.Bodies.TCToys")) {
            TCToys a = (TCToys) (contact.getFixtureA().getBody().getUserData());
            a.destroy();
            Gdx.app.log("", "contact");
            TCGameScreen.score++;
            TCGameScreen.clicks += 2;
            TCGameScreen.listenergenerate = true;
            if (getSoundState()) {
                TCTitleScreen.score.play();
            }


        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void postSolve(Contact arg0, ContactImpulse arg1) {
        // TODO Auto-generated method stub
        WorldManifold manifold = arg0.getWorldManifold();


    }

    @Override
    public void preSolve(Contact arg0, Manifold arg1) {
        // TODO Auto-generated method stub
        Body body = null;
        if (arg0.getFixtureA() != null && arg0.getFixtureA().getUserData() != null && arg0.getFixtureA().getUserData().equals("b"))
            body = arg0.getFixtureA().getBody();

        if (arg0.getFixtureB() != null && arg0.getFixtureB().getUserData() != null && arg0.getFixtureB().getUserData().equals("b"))
            body = arg0.getFixtureB().getBody();

        if (body != null) {


            body.setActive(false);
            world.destroyBody(body);
        }

    }
}