package xyz.ramil.clawcrane.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Random;

import xyz.ramil.clawcrane.Bodies.TCBasket;
import xyz.ramil.clawcrane.Bodies.TCBox;
import xyz.ramil.clawcrane.Bodies.TCContactListener;
import xyz.ramil.clawcrane.Bodies.TCCrane;
import xyz.ramil.clawcrane.Bodies.TCEdge;
import xyz.ramil.clawcrane.Bodies.TCToys;


public class TCGameScreen implements Screen {

    static public int score;
    public static boolean listenergenerate;
    public static int clicks;
    public static Preferences prefs;
    public World world;
    private Stage stage;
    private Game game;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private StretchViewport viewp;
    private TCBox box;
    private TCEdge tcEdge;
    private Texture bearTextuere,
            rabbitTexture,
            boxTexture,
            penguinTexture,
            pandaTexture,
            cowTextuere,
            chickenTextuere,
            pumpkinTexture,
            frogTexture,
            foxTexture,
            koalaTexture,
            tigerTexture,
            princessTexture,
            alenTexture;
    private Random random;                      //random 0 or 1, bear or rabbit
    private int a;                          //counter add toys
    private TCCrane tcCrane;
    private TCBasket tcBasket;
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchSkin, button0Skin,
            buttonPlaySkin, buttonBackSkin, buttonRetrySkin;
    private Button.ButtonStyle button0, buttonPlayStyle,
            buttonBackStyle, buttonRetryStyle;
    private Button buttonCrane, buttonPlay,
            buttonBack, buttonRetry;
    private Texture backgroundTexture;
    private Texture backgroundTexture_1;
    private BitmapFont bitmapFont,
            bitmapFonths,
            bitmapFontwhite,
            bitmapFonthswhite,
            bitmapFonthsclick;
    private int toysNumber;
    private boolean generate;
    private boolean backKey;

    private boolean gameOver;


    public TCGameScreen(Game aGame) {


        Sprite sprite = new Sprite(new Texture("background_1.png"));
        sprite.setPosition(30, 30);


        prefs = Gdx.app.getPreferences("ToyCrane");
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        if (!prefs.contains("soundState")) {
            prefs.putBoolean("soundState", false);
        }

        if (!prefs.contains("chicken")) {
            prefs.putBoolean("chicken", false);
        }
        if (!prefs.contains("pumpkin")) {
            prefs.putBoolean("pumpkin", false);
        }
        if (!prefs.contains("frog")) {
            prefs.putBoolean("frog", false);
        }
        if (!prefs.contains("fox")) {
            prefs.putBoolean("fox", false);
        }
        if (!prefs.contains("koala")) {
            prefs.putBoolean("koala", false);
        }
        if (!prefs.contains("frog")) {
            prefs.putBoolean("frog", false);
        }
        if (!prefs.contains("tiger")) {
            prefs.putBoolean("tiger", false);
        }
        if (!prefs.contains("princess")) {
            prefs.putBoolean("princess", false);
        }
        if (!prefs.contains("alen")) {
            prefs.putBoolean("alen", false);
        }

        listenergenerate = false;
        generate = true;
        gameOver = false;
        backKey = false;

        toysNumber = 0;

        game = aGame;

        random = new Random();
        clicks = 15;

        score = 0;

        a = 1;

        world = new World(new Vector2(0, -100), true);
        world.setContactListener(new TCContactListener(world));

        backgroundTexture = new Texture(Gdx.files.internal("background_1.png"));
        backgroundTexture_1 = new Texture(Gdx.files.internal("background_1.png"));


        //----------------create camera----------------------------
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        viewp = new StretchViewport(136, 204, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewp, batch);

        //------------------Bodies texture load-------------
        bearTextuere = new Texture("bear.png");
        rabbitTexture = new Texture("rabbit.png");
        boxTexture = new Texture("box.png");
        penguinTexture = new Texture("penguin.png");
        pandaTexture = new Texture("panda.png");
        cowTextuere = new Texture("cow.png");
        chickenTextuere = new Texture("chicken.png");
        pumpkinTexture = new Texture("pumpkin.png");
        frogTexture = new Texture("frog.png");
        foxTexture = new Texture("fox.png");
        koalaTexture = new Texture("koala.png");
        tigerTexture = new Texture("tiger.png");
        princessTexture = new Texture("princess.png");
        alenTexture = new Texture("alen.png");

        //------------------Font---------------------------
        bitmapFont = new BitmapFont(Gdx.files.internal("font/bigfont/text.fnt"),
                Gdx.files.internal("font/bigfont/text.png"), false);
        bitmapFont.getData().setScale(0.5f);
        bitmapFont.setColor(0, 111/255f, 255/255f, 1);

        bitmapFonths = new BitmapFont(Gdx.files.internal("font/bigfont/text.fnt"),
                Gdx.files.internal("font/bigfont/text.png"), false);
        bitmapFonths.getData().setScale(0.5f);
        bitmapFonths.setColor(0, 111/255f, 255/255f, 1);

        bitmapFontwhite =  new BitmapFont(Gdx.files.internal("font/bigfont/text.fnt"),
                Gdx.files.internal("font/bigfont/text.png"), false);
        bitmapFontwhite.getData().setScale(0.5f);
        bitmapFontwhite.setColor(0, 111/255f, 255/255f, 1);

        bitmapFonthswhite =  new BitmapFont(Gdx.files.internal("font/bigfont/text.fnt"),
                Gdx.files.internal("font/bigfont/text.png"), false);
        bitmapFonthswhite.setColor(0, 111/255f, 255/255f, 1);
        bitmapFonthswhite.getData().setScale(0.5f);

        bitmapFonthsclick =  new BitmapFont(Gdx.files.internal("font/bigfont/text.fnt"),
                Gdx.files.internal("font/bigfont/text.png"), false);
        bitmapFonthsclick.setColor(0, 111/255f, 255/255f, 1);
        bitmapFonthsclick.getData().setScale(0.5f);
        //-----------------Touchpad------------------------
        touchSkin = new Skin();
        touchSkin.add("touchBackground", new Texture("touchpad_b.png"));
        touchSkin.add("touchKnob", new Texture("touchpad_k.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();
        Drawable dtouchBackground = touchSkin.getDrawable("touchBackground");
        Drawable dtouchKnob = touchSkin.getDrawable("touchKnob");
        dtouchBackground.setMinHeight(40);
        dtouchBackground.setMinWidth(40);
        dtouchKnob.setMinHeight(15);
        dtouchKnob.setMinWidth(15);
        touchpadStyle.background = dtouchBackground;
        touchpadStyle.knob = dtouchKnob;
        touchpad = new Touchpad(5, touchpadStyle);
        touchpad.setBounds(10, 4, 45, 45);
        //--------------------Button------------------------------
        button0Skin = new Skin();
        button0Skin.add("button0up", new Texture("button0_up.png"));
        button0Skin.add("button0down", new Texture("button0_down.png"));
        button0 = new Button.ButtonStyle();
        button0.up = button0Skin.getDrawable("button0up");
        button0.down = button0Skin.getDrawable("button0down");
        buttonCrane = new Button(button0);
        buttonCrane.setBounds(81, 4, 45, 45);
        //---------------------------------------------------------
        buttonPlaySkin = new Skin();
        buttonPlaySkin.add("buttonPlayup", new Texture("button_play_up.png"));
        buttonPlaySkin.add("buttonPlaydown", new Texture("button_play_down.png"));
        buttonPlayStyle = new Button.ButtonStyle();
        buttonPlayStyle.up = buttonPlaySkin.getDrawable("buttonPlayup");
        buttonPlayStyle.down = buttonPlaySkin.getDrawable("buttonPlaydown");
        buttonPlay = new Button(buttonPlayStyle);
        buttonPlay.setBounds(97.5f, 11.5f, 30, 30);
        buttonPlay.setVisible(false);
        //---------------------------------------------------------
        buttonBackSkin = new Skin();
        buttonBackSkin.add("buttonBackup", new Texture("button_back_up.png"));
        buttonBackSkin.add("buttonBackdown", new Texture("button_back_down.png"));
        buttonBackStyle = new Button.ButtonStyle();
        buttonBackStyle.up = buttonBackSkin.getDrawable("buttonBackup");
        buttonBackStyle.down = buttonBackSkin.getDrawable("buttonBackdown");
        buttonBack = new Button(buttonBackStyle);
        buttonBack.setBounds(7.5f, 11.5f, 30, 30);
        buttonBack.setVisible(false);
        //---------------------------------------------------------
        buttonRetrySkin = new Skin();
        buttonRetrySkin.add("buttonRetryup", new Texture("button_retry_up.png"));
        buttonRetrySkin.add("buttonRetrydown", new Texture("button_retry_down.png"));
        buttonRetryStyle = new Button.ButtonStyle();
        buttonRetryStyle.up = buttonRetrySkin.getDrawable("buttonRetryup");
        buttonRetryStyle.down = buttonRetrySkin.getDrawable("buttonRetrydown");
        buttonRetry = new Button(buttonRetryStyle);
        buttonRetry.setBounds(45, 4, 45, 45);
        buttonRetry.setVisible(false);

        box = new TCBox(world, 136, 118, boxTexture);
        tcBasket = new TCBasket((world));
        tcEdge = new TCEdge(world);

        tcCrane = new TCCrane(world, 60, 100);
        stage.addActor(buttonBack);
        stage.addActor(buttonPlay);
        stage.addActor(buttonRetry);


        stage.addActor(box);
        stage.addActor(tcBasket);
        stage.addActor(tcEdge);

        stage.addActor(tcCrane);

        stage.addActor(touchpad);
        stage.addActor(buttonCrane);
        craneButton();
        playButton();
        retryButton();
        backButton();

        generateToy();
        getChicken();
        getPumpkin();
        getFrog();
        getFox();
        getKoala();
        getTiger();
        getPrincess();
        getAlen();

        debugRenderer = new Box2DDebugRenderer();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
        Gdx.input.setInputProcessor(stage);
        //    unlockach();


    }


    public static boolean getSoundState() {
        return prefs.getBoolean("soundState");
    }

    public static boolean getChicken() {
        return prefs.getBoolean("chicken");
    }

    public static boolean getPumpkin() {
        return prefs.getBoolean("pumpkin");
    }

    public static boolean getFrog() {
        return prefs.getBoolean("frog");
    }

    public static boolean getFox() {
        return prefs.getBoolean("fox");
    }

    public static boolean getKoala() {
        return prefs.getBoolean("koala");
    }

    public static boolean getTiger() {
        return prefs.getBoolean("tiger");
    }

    public static boolean getPrincess() {
        return prefs.getBoolean("princess");
    }

    public static boolean getAlen() {
        return prefs.getBoolean("alen");
    }

    //--------------------Button Crane-----------------------------------------------------------------
    private void craneButton() {
        buttonCrane.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                tcCrane.eventCraneButtonUp();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                tcCrane.eventCraneButtonDown();
                clicks -= 1;
                if (clicks < 1) {
                    clicks = 0;
                }
                return true;
            }
        });
    }

    //--------------------Button Back------------------------------------------------------------
    private void backButton() {
        buttonBack.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new TCTitleScreen(game));

                dispose();

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (getSoundState()) {
                    TCTitleScreen.menu.play();
                }

                return true;
            }

        });
    }

    private void playButton() {
        buttonPlay.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                buttonPlay.setVisible(false);
                buttonBack.setVisible(false);
                buttonRetry.setVisible(false);
                buttonCrane.setVisible(true);
                touchpad.setVisible(true);
                backKey = false;

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (getSoundState()) {
                    TCTitleScreen.menu.play();
                }


                return true;
            }

        });
    }

    private void retryButton() {
        buttonRetry.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                score = 0;
                clicks = 15;
                Gdx.app.log("", "" + stage.getActors());
                if ((stage.getActors().size > 9) && (stage.getActors().get(9) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(9));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 10) && (stage.getActors().get(10) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(10));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 11) && (stage.getActors().get(11) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(11));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 12) && (stage.getActors().get(12) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(12));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 13) && (stage.getActors().get(13) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(13));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 14) && (stage.getActors().get(14) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(14));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 15) && (stage.getActors().get(15) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(15));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 16) && (stage.getActors().get(16) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(16));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 17) && (stage.getActors().get(17) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(17));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 18) && (stage.getActors().get(18) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(18));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 19) && (stage.getActors().get(19) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(19));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 20) && (stage.getActors().get(20) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(20));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 21) && (stage.getActors().get(21) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(21));
                    a.destroy();
                    toysNumber--;
                }
                if ((stage.getActors().size > 22) && (stage.getActors().get(22) != null)) {
                    TCToys a = (TCToys) (stage.getActors().get(22));
                    a.destroy();
                    toysNumber--;
                }
                toysNumber = 0;
                buttonPlay.setVisible(false);
                buttonRetry.setVisible(false);
                buttonBack.setVisible(false);
                touchpad.setVisible(true);
                buttonCrane.setVisible(true);
                generate = true;
                a = 1;
                gameOver = false;


            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (getSoundState()) {
                    TCTitleScreen.menu.play();
                }


                return true;
            }

        });
    }


    public void generateToy() {

        int x = random.nextInt(13);


        if (x == 0) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys bears = new TCToys(bearTextuere, 13, 15,
                        30, 170,
                        world, "bear.json", "bear");
                stage.addActor(bears);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys bears = new TCToys(bearTextuere, 13, 15,
                            80, 170,
                            world, "bear.json", "bear");
                    stage.addActor(bears);
                    toysNumber++;


                }
            }
        }

        if (x == 1) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys rabbit = new TCToys(rabbitTexture, 12, 18,
                        30, 170,
                        world, "rabbit.json", "rabbit");
                stage.addActor(rabbit);
                toysNumber++;


            } else {
                TCToys rabbits = new TCToys(rabbitTexture, 12, 18,
                        80, 170,
                        world, "rabbit.json", "rabbit");
                stage.addActor(rabbits);
                toysNumber++;
            }
        }
        //-----------------------------------------------------------------------
        if (x == 2) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys cow = new TCToys(cowTextuere, 16, 13,
                        30, 170,
                        world, "cow.json", "cow");
                stage.addActor(cow);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys cow = new TCToys(cowTextuere, 16, 13,
                            80, 170,
                            world, "cow.json", "cow");
                    stage.addActor(cow);
                    toysNumber++;


                }
            }
        }
        //-----------------------------------------------------------------------
        if (x == 3) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys penguin = new TCToys(penguinTexture, 16, 16,
                        30, 170,
                        world, "penguin.json", "penguin");
                stage.addActor(penguin);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys penguin = new TCToys(penguinTexture, 16, 16,
                            80, 170,
                            world, "penguin.json", "penguin");
                    stage.addActor(penguin);
                    toysNumber++;


                }
            }
        }
        //-----------------------------------------------------------------------
        if (x == 4) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys panda = new TCToys(pandaTexture, 13, 15,
                        30, 170,
                        world, "panda.json", "panda");
                stage.addActor(panda);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys panda = new TCToys(pandaTexture, 13, 15,
                            80, 170,
                            world, "panda.json", "panda");
                    stage.addActor(panda);
                    toysNumber++;


                }
            }
        }
        //-----------------------------------------------------------------------
        if (x == 5) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys chicken = new TCToys(chickenTextuere, 14, 16,
                        30, 170,
                        world, "chicken.json", "chicken");
                stage.addActor(chicken);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys chicken = new TCToys(chickenTextuere, 14, 16,
                            80, 170,
                            world, "chicken.json", "chicken");
                    stage.addActor(chicken);
                    toysNumber++;


                }
            }
        }

        //-----------------------------------------------------------------------
        if (x == 6) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys pumpkin = new TCToys(pumpkinTexture, 15, 14,
                        30, 170,
                        world, "pumpkin.json", "pumpkin");
                stage.addActor(pumpkin);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys pumpkin = new TCToys(pumpkinTexture, 15, 14,
                            80, 170,
                            world, "pumpkin.json", "pumpkin");
                    stage.addActor(pumpkin);
                    toysNumber++;


                }
            }

        }
        if (x == 7) {

            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys frog = new TCToys(frogTexture, 15, 15,
                        30, 170,
                        world, "frog.json", "frog");
                stage.addActor(frog);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys frog = new TCToys(frogTexture, 15, 15,
                            80, 170,
                            world, "frog.json", "frog");
                    stage.addActor(frog);
                    toysNumber++;


                }

            }
        }
        if (x == 8) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys fox = new TCToys(foxTexture, 14, 16,
                        30, 170,
                        world, "fox.json", "fox");
                stage.addActor(fox);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys fox = new TCToys(foxTexture, 14, 16,
                            80, 170,
                            world, "fox.json", "fox");
                    stage.addActor(fox);
                    toysNumber++;


                }
            }
        }
        if (x == 9) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys koala = new TCToys(koalaTexture, 15, 16,
                        30, 170,
                        world, "koala.json", "koala");
                stage.addActor(koala);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys koala = new TCToys(koalaTexture, 15, 16,
                            80, 170,
                            world, "koala.json", "koala");
                    stage.addActor(koala);
                    toysNumber++;


                }
            }

        }
        if (x == 10) {
            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys tiger = new TCToys(tigerTexture, 15, 15,
                        30, 170,
                        world, "tiger.json", "tiger");
                stage.addActor(tiger);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys tiger = new TCToys(tigerTexture, 15, 15,
                            80, 170,
                            world, "tiger.json", "tiger");
                    stage.addActor(tiger);
                    toysNumber++;


                }
            }
        }
        if (x == 11) {

            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys princess = new TCToys(princessTexture, 12, 23,
                        30, 170,
                        world, "princess.json", "princess");
                stage.addActor(princess);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys princess = new TCToys(princessTexture, 12, 23,
                            80, 170,
                            world, "princess.json", "princess");
                    stage.addActor(princess);
                    toysNumber++;


                }
            }

        }
        if (x == 12) {

            if (tcCrane.getMyBodyA().getPosition().x > 50) {
                TCToys alen = new TCToys(alenTexture, 15, 14,
                        30, 170,
                        world, "alen.json", "alen");
                stage.addActor(alen);
                toysNumber++;


            } else {
                if (tcCrane.getMyBodyA().getPosition().x < 50) {
                    TCToys alen = new TCToys(alenTexture, 15, 14,
                            80, 170,
                            world, "alen.json", "alen");
                    stage.addActor(alen);
                    toysNumber++;


                }
            }

        }


    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen", "show");

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //  renderer

        // debugRenderer.render(world, camera.combined);
        if (clicks < 4) {
            bitmapFonthsclick.setColor(255, 0, 0, 1);
        } else {
            bitmapFonthsclick.setColor(0, 111/255f, 255/255f, 1);

        }

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, 136, 204);
        // stage.getBatch().draw(backgroundTexture_1, 0, 53, 136, 118);


        stage.getBatch().end();


        stage.getBatch().begin();

        bitmapFont.draw(batch, "SCORE ", 5, 203);
        bitmapFonths.draw(batch, "" + score, 5, 185);

        bitmapFontwhite.draw(batch, "CLICKS " +
                "" + clicks, 70, 203);
        bitmapFonthsclick.draw(batch, "" + clicks, 70, 185);


        stage.getBatch().end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if (buttonCrane.isPressed()) {
            tcCrane.getMyBodyA().setLinearVelocity(tcCrane.getMyBodyA().getLinearVelocity().x + touchpad.getKnobPercentX() * 15,
                    tcCrane.getMyBodyA().getLinearVelocity().y - 30);
        }
        tcCrane.getMyBodyA().setLinearVelocity(tcCrane.getMyBodyA().getLinearVelocity().x + touchpad.getKnobPercentX() * 15,
                tcCrane.getMyBodyA().getLinearVelocity().y + 15);

        if (touchpad.getKnobPercentY() < -0.1f) {
            tcCrane.getMyBodyA().setLinearVelocity(tcCrane.getMyBodyA().getLinearVelocity().x + touchpad.getKnobPercentX() * 15,
                    tcCrane.getMyBodyA().getLinearVelocity().y + touchpad.getKnobPercentY() * 15);

        }

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);


        if (listenergenerate) {
            generateToy();

            listenergenerate = false;
            toysNumber--;
            Gdx.app.log("toyNumber", "" + toysNumber);
        }


        if (gameOver) {
            tcGameOver();

        }


        a++;

        if ((generate) && (a == 50)) {
            a = 1;

            generateToy();
            Gdx.app.log("toyNumber", "" + toysNumber);

        }
        if (clicks == 0) {
            Timer testingTimer = new Timer();
            testingTimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    if (clicks == 0) {
                        gameOver = true;


                    }
                }
            }, 2);


        }

        if (toysNumber == 11) {
            generate = false;
        }


        setHighScore(score);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            backKey = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.MENU)) {
            backKey = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            backKey = true;
        }

        if (backKey && clicks != 0) {


            touchpad.setVisible(false);
            buttonCrane.setVisible(false);
            buttonPlay.setVisible(true);
            buttonBack.setVisible(true);
            buttonRetry.setVisible(true);

        }


    }

    private void tcGameOver() {

        stage.getBatch().begin();

        bitmapFont.draw(batch, "GAME", 30, 150);
        bitmapFont.draw(batch, "OVER", 33, 130);

        stage.getBatch().end();

        Gdx.input.setCatchBackKey(false);
        touchpad.setVisible(false);
        buttonCrane.setVisible(false);

        buttonBack.setVisible(true);
        buttonRetry.setVisible(true);


    }

    public void setHighScore(int score) {

        if (prefs.getInteger("highScore") < score) {
            prefs.putInteger("highScore", score);
            prefs.flush();

        }


    }

    @Override
    public void resize(int width, int height) {

        viewp.update(width, height);
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {


        batch.dispose();

        world.dispose();
        stage.dispose();

        bitmapFont.dispose();
        bitmapFonths.dispose();
        bitmapFonthsclick.dispose();
        bitmapFonthswhite.dispose();

        bearTextuere.dispose();
        rabbitTexture.dispose();
        boxTexture.dispose();
        penguinTexture.dispose();
        pandaTexture.dispose();
        cowTextuere.dispose();
        chickenTextuere.dispose();
        pumpkinTexture.dispose();
        frogTexture.dispose();
        foxTexture.dispose();
        koalaTexture.dispose();
        tigerTexture.dispose();
        princessTexture.dispose();
        alenTexture.dispose();

        touchSkin.dispose();
        button0Skin.dispose();
        buttonPlaySkin.dispose();
        buttonBackSkin.dispose();
        buttonRetrySkin.dispose();

        debugRenderer.dispose();
        game.dispose();

        backgroundTexture.dispose();
    }


}
