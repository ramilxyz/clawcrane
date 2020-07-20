package xyz.ramil.clawcrane.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

import xyz.ramil.clawcrane.TCSelecttoy.Select;

public class TCAdditionalToy implements Screen {
    public static Preferences prefs;
    public static Sound menu;
    private Stage stage;
    private Game game;
    private BitmapFont bitmapFont;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private FitViewport viewp;
    private Skin buttonBackSkin, rbuttonBackSkin, lbuttonBackSkin, buttonwvSkin;
    private Button.ButtonStyle buttonBackStyle, rbuttonBackStyle, lbuttonBackStyle, buttonwvStyle;
    private Button buttonBack, rbuttonBack, lbuttonBack, buttonwv;
    private Texture backgroundTexture;
    private Texture
            chickenTextuere,
            pumpkinTexture,
            frogTexture,
            foxTexture,
            koalaTexture,
            tigerTexture,
            princessTexture,
            alenTexture;
    private Select chicken,
            pumpkin,
            frog,
            fox,
            koala,
            tiger,
            princess,
            alen;
    private int select_value = 5;
    private boolean videoLoad;
    private String loading, toyhighscore;
    private int i;
    private boolean toyavailable;
    private boolean added;

    private int chicken_sh,
            pumpkin_sh,
            frog_hs,
            fox_hs,
            koala_hs,
            tiger_hs,
            princess_hs,
            alen_hs;

    public TCAdditionalToy(Game game) {

        this.game = game;


        toyavailable = false;
        videoLoad = false;
        added = false;
        loading = "";
        toyhighscore = "";
        i = 0;
        chicken_sh = 10; //10
        pumpkin_sh = 20; //20
        frog_hs = 40; //40
        fox_hs = 80; //80
        koala_hs = 150;
        tiger_hs = 300;
        princess_hs = 600;
        alen_hs = 1000;


        bitmapFont = new BitmapFont(Gdx.files.internal("font/highscore/text.fnt"),
                Gdx.files.internal("font/highscore/text.png"), false);
        bitmapFont.setColor(255, 255, 0, 1);


        prefs = Gdx.app.getPreferences("ToyCrane");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        if (!prefs.contains("soundState")) {
            prefs.putBoolean("soundState", true);
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

        menu = Gdx.audio.newSound(Gdx.files.internal("sound/menu.wav"));
        Image image = new Image();


        backgroundTexture = new Texture(Gdx.files.internal("background_title.png"));

        chickenTextuere = new Texture("chicken.png");
        pumpkinTexture = new Texture("pumpkin.png");
        frogTexture = new Texture("frog.png");
        foxTexture = new Texture("fox.png");
        koalaTexture = new Texture("koala.png");
        tigerTexture = new Texture("tiger.png");
        princessTexture = new Texture("princess.png");
        alenTexture = new Texture("alen.png");

        //----------------create camera----------------------------
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 136, 204);
        batch = new SpriteBatch();
        viewp = new FitViewport(136, 204, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewp, batch);
        //-------------------button back--------------------------------------
        buttonBackSkin = new Skin();
        buttonBackSkin.add("buttonBackup", new Texture("button_back_up.png"));
        buttonBackSkin.add("buttonBackdown", new Texture("button_back_down.png"));
        buttonBackStyle = new Button.ButtonStyle();
        buttonBackStyle.up = buttonBackSkin.getDrawable("buttonBackup");
        buttonBackStyle.down = buttonBackSkin.getDrawable("buttonBackdown");
        buttonBack = new Button(buttonBackStyle);
        buttonBack.setBounds(7.5f, 11.5f, 30, 30);
        //-------------------button watch video--------------------------------------
        buttonwvSkin = new Skin();
        buttonwvSkin.add("buttonvideo_up", new Texture("buttonvideo_up.png"));
        buttonwvSkin.add("buttonvideo_down", new Texture("buttonvideo_down.png"));
        buttonwvStyle = new Button.ButtonStyle();
        buttonwvStyle.up = buttonwvSkin.getDrawable("buttonvideo_up");
        buttonwvStyle.down = buttonwvSkin.getDrawable("buttonvideo_down");
        buttonwv = new Button(buttonwvStyle);
        buttonwv.setBounds(53, 70, 29, 22);
        buttonwv.setVisible(false);
        //---------------------lselectbutton-------------------------------------------------------
        lbuttonBackSkin = new Skin();
        lbuttonBackSkin.add("lbuttonBackup", new Texture("select/lbutton_back_up.png"));
        lbuttonBackSkin.add("lbuttonBackdown", new Texture("select/lbutton_back_down.png"));
        lbuttonBackStyle = new Button.ButtonStyle();
        lbuttonBackStyle.up = lbuttonBackSkin.getDrawable("lbuttonBackup");
        lbuttonBackStyle.down = lbuttonBackSkin.getDrawable("lbuttonBackdown");
        lbuttonBack = new Button(lbuttonBackStyle);
        lbuttonBack.setBounds(15, 100, 30, 30);
        //---------------------lselectbutton-------------------------------------------------------
        rbuttonBackSkin = new Skin();
        rbuttonBackSkin.add("rbuttonBackup", new Texture("select/rbutton_back_up.png"));
        rbuttonBackSkin.add("rbuttonBackdown", new Texture("select/rbutton_back_down.png"));
        rbuttonBackStyle = new Button.ButtonStyle();
        rbuttonBackStyle.up = rbuttonBackSkin.getDrawable("rbuttonBackup");
        rbuttonBackStyle.down = rbuttonBackSkin.getDrawable("rbuttonBackdown");
        rbuttonBack = new Button(rbuttonBackStyle);
        rbuttonBack.setBounds(91, 100, 30, 30);

        chicken = new Select(chickenTextuere, 30, 30);
        stage.addActor(chicken);
        chicken.setVisible(false);


        pumpkin = new Select(pumpkinTexture, 30, 30);
        stage.addActor(pumpkin);
        pumpkin.setVisible(false);

        frog = new Select(frogTexture, 30, 30);
        stage.addActor(frog);
        frog.setVisible(false);

        fox = new Select(foxTexture, 30, 30);
        stage.addActor(fox);
        fox.setVisible(false);

        koala = new Select(koalaTexture, 30, 30);
        stage.addActor(koala);
        koala.setVisible(false);

        tiger = new Select(tigerTexture, 30, 30);
        stage.addActor(tiger);
        tiger.setVisible(false);

        princess = new Select(princessTexture, 30, 30);
        stage.addActor(princess);
        princess.setVisible(false);

        alen = new Select(alenTexture, 30, 30);
        stage.addActor(alen);
        alen.setVisible(false);

        stage.addActor(buttonBack);
        stage.addActor(buttonwv);
        stage.addActor(rbuttonBack);
        stage.addActor(lbuttonBack);
        backButton();
        rbackButton();
        lbackButton();

        WVButton();
        toyAvailable();
        addedtotheGame();

        Gdx.input.setInputProcessor(stage);
        sel();
        getSoundState();
        getHighScore();
        getChicken();
        getPumpkin();
        getFrog();
        getFox();
        getKoala();
        getTiger();
        getPrincess();
        getAlen();

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

    public static boolean getSoundState() {
        return prefs.getBoolean("soundState");
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    void sel() {
        if (select_value == 5) {
            chicken.setVisible(true);
        } else chicken.setVisible(false);
        if (select_value == 6) {
            pumpkin.setVisible(true);
        } else pumpkin.setVisible(false);
        if (select_value == 7) {
            frog.setVisible(true);
        } else frog.setVisible(false);
        if (select_value == 8) {
            fox.setVisible(true);
        } else fox.setVisible(false);
        if (select_value == 9) {
            koala.setVisible(true);
        } else koala.setVisible(false);
        if (select_value == 10) {
            tiger.setVisible(true);
        } else tiger.setVisible(false);
        if (select_value == 11) {
            princess.setVisible(true);
        } else princess.setVisible(false);
        if (select_value == 12) {
            alen.setVisible(true);
        } else alen.setVisible(false);
        wvbuttonVisible();


    }

    private void addedtotheGame() {
        if (select_value == 5) {
            if (getChicken()) {
                added = true;
            }
            if (!getChicken()) {
                added = false;
            }
        }
        if (select_value == 6) {
            if (getPumpkin()) {
                added = true;
            }
            if (!getPumpkin()) {
                added = false;
            }
        }
        if (select_value == 7) {
            added = getFrog();
        }
        if (select_value == 8) {
            added = getFox();
        }
        if (select_value == 9) {
            added = getKoala();
        }
        if (select_value == 10) {
            added = getTiger();
        }
        if (select_value == 11) {
            added = getPrincess();
        }
        if (select_value == 12) {
            added = getAlen();
        }
    }

    private void toyAvailable() {
        if (select_value == 5) {
            toyavailable = false;
            if (getChicken() || !videoLoad || (getHighScore() < chicken_sh)) {
                buttonwv.setVisible(false);
                toyhighscore = "10"; //10
                toyavailable = true;
                if (getChicken() || !videoLoad || (getHighScore() >= chicken_sh)) {
                    toyavailable = false;
                    buttonwv.setVisible(true);


                }
            }
        }
        if (select_value == 6) {
            toyavailable = false;
            if (getPumpkin() || !videoLoad || (getHighScore() < pumpkin_sh)) {
                buttonwv.setVisible(false);
                toyhighscore = "20"; //20
                toyavailable = true;
                if (getPumpkin() || !videoLoad || (getHighScore() >= pumpkin_sh)) {
                    toyavailable = false;
                    buttonwv.setVisible(true);


                }
            }
        }
        if (select_value == 7) {
            toyavailable = false;
            if (getFrog() || !videoLoad || (getHighScore() < frog_hs)) {
                buttonwv.setVisible(false);
                toyhighscore = "40"; //40
                toyavailable = true;
                if (getFrog() || !videoLoad || (getHighScore() >= frog_hs)) {
                    toyavailable = false;
                    buttonwv.setVisible(true);
                }
            }
        }
        if (select_value == 8) {
            toyavailable = false;
            if (getFox() || !videoLoad || (getHighScore() < fox_hs)) {
                buttonwv.setVisible(false);
                toyhighscore = "80";
                toyavailable = true;
                if (getFox() || !videoLoad || (getHighScore() >= fox_hs)) {
                    toyavailable = false;
                    buttonwv.setVisible(true);
                }
            }
        }
        if (select_value == 9) {
            toyavailable = false;
            if (getKoala() || !videoLoad || (getHighScore() < koala_hs)) {
                buttonwv.setVisible(false);
                toyhighscore = "150";
                toyavailable = true;
                if (getKoala() || !videoLoad || (getHighScore() >= koala_hs)) {
                    toyavailable = false;
                    buttonwv.setVisible(true);
                }
            }
        }
        if (select_value == 10) {
            toyavailable = false;
            if (getTiger() || !videoLoad || (getHighScore() < tiger_hs)) {
                buttonwv.setVisible(false);
                toyhighscore = "300";
                toyavailable = true;
                if (getTiger() || !videoLoad || (getHighScore() >= tiger_hs)) {
                    toyavailable = false;
                    buttonwv.setVisible(true);
                }
            }
        }
        if (select_value == 11) {
            toyavailable = false;
            if (getPrincess() || !videoLoad || (getHighScore() < princess_hs)) {
                buttonwv.setVisible(false);
                toyhighscore = "600";
                toyavailable = true;
                if (getPrincess() || !videoLoad || (getHighScore() >= princess_hs)) {
                    toyavailable = false;
                    buttonwv.setVisible(true);
                }
            }
        }
        if (select_value == 12) {
            toyavailable = false;
            if (getAlen() || !videoLoad || (getHighScore() < alen_hs)) {
                buttonwv.setVisible(false);
                toyhighscore = "1000";
                toyavailable = true;
                if (getAlen() || !videoLoad || (getHighScore() >= alen_hs)) {
                    toyavailable = false;
                    buttonwv.setVisible(true);
                }
            }
        }

    }

    private void wvbuttonVisible() {
        if (select_value == 5) {
            if (getChicken() || !videoLoad) {
                buttonwv.setVisible(false);

            } else buttonwv.setVisible(true);
            if ((getHighScore() <= chicken_sh) && videoLoad) {
                // buttonwv.setVisible(true);
                addedtotheGame();
                toyAvailable();
            }
        }
        if (select_value == 6) {
            if (getPumpkin() || !videoLoad) {
                buttonwv.setVisible(false);
            } else buttonwv.setVisible(true);
        }
        if (select_value == 7) {
            if (getFrog() || !videoLoad) {
                buttonwv.setVisible(false);
            } else buttonwv.setVisible(true);
        }
        if (select_value == 8) {
            if (getFox() || !videoLoad) {
                buttonwv.setVisible(false);
            } else buttonwv.setVisible(true);
        }
        if (select_value == 9) {
            if (getKoala() || !videoLoad) {
                buttonwv.setVisible(false);
            } else buttonwv.setVisible(true);
        }
        if (select_value == 10) {
            if (getTiger() || !videoLoad) {
                buttonwv.setVisible(false);
            } else buttonwv.setVisible(true);
        }
        if (select_value == 11) {
            if (getPrincess() || !videoLoad) {
                buttonwv.setVisible(false);
            } else buttonwv.setVisible(true);
        }
        if (select_value == 12) {
            if (getAlen() || !videoLoad) {
                buttonwv.setVisible(false);
            } else buttonwv.setVisible(true);
        }

    }

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

    private void rbackButton() {
        rbuttonBack.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (select_value < 12) {
                    select_value++;
                } else {
                    select_value = 5;
                }
                sel();
                toyAvailable();
                addedtotheGame();

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

    private void lbackButton() {
        lbuttonBack.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (select_value > 5) {
                    select_value--;
                } else {
                    select_value = 12;
                }
                sel();
                toyAvailable();
                addedtotheGame();

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


    private void WVButton() {
        buttonwv.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);


                wvbuttonVisible();

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


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        addedtotheGame();


        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, 143, 204);
        stage.getBatch().end();

        stage.getBatch().begin();
        bitmapFont.draw(batch, "watch the video", 13, 203);
        bitmapFont.draw(batch, "and get a new toy", 5, 190);
        stage.getBatch().end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        wvbuttonVisible();
        if (!videoLoad) {
            i++;
            if (i == 10) {
                loading = "";
            }
            if (i == 20) {
                loading = ".";
            }
            if (i == 30) {
                loading = "..";
            }
            if (i == 40) {
                loading = "...";
                i = 0;
            }

            stage.getBatch().begin();
            bitmapFont.draw(batch, "loading" + loading, 40, 90);
            stage.getBatch().end();
        }
        if (added && videoLoad) {
            stage.getBatch().begin();
            bitmapFont.draw(batch, "added to the game", 2, 90);
            stage.getBatch().end();


        }

        if (videoLoad && toyavailable) {
            buttonwv.setVisible(false);
            stage.getBatch().begin();
            bitmapFont.draw(batch, "HIGH", 15, 95);
            bitmapFont.draw(batch, "SCORE " + getHighScore() + "/" + toyhighscore, 20, 80);
            stage.getBatch().end();


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
        stage.dispose();
        menu.dispose();
        game.dispose();
        bitmapFont.dispose();
        chickenTextuere.dispose();
        pumpkinTexture.dispose();
        frogTexture.dispose();
        foxTexture.dispose();
        koalaTexture.dispose();
        tigerTexture.dispose();
        princessTexture.dispose();
        alenTexture.dispose();
        buttonBackSkin.dispose();
        backgroundTexture.dispose();
    }

}
