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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class TCTitleScreen implements Screen {

    public static Preferences prefs;
    public static Sound menu, toy, score, gameover;
    float tctextx;
    private Stage stage;
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private StretchViewport viewp;
    private Skin buttonPlaySkin;
    private Skin buttonSoundSkin;
    private Skin buttonmtSkin;
    private Skin buttonhsSkin;
    private Skin buttonachSkin;
    private Skin buttonqSkin;
    private Skin buttonnewSkin;
    private Button.ButtonStyle buttonPlayStyle;
    private Button.ButtonStyle buttonSoundStyle;
    private Button.ButtonStyle buttonmtStyle;
    private Button.ButtonStyle buttonhsStyle;
    private Button.ButtonStyle buttonachStyle;
    private Button.ButtonStyle buttonqStyle;
    private Button.ButtonStyle buttonnewStyle;
    private Button buttonPlay;
    private Button buttonSound;
    private Button buttonmt;
    private Button buttonhs;
    private Button buttonach;
    private Button buttonq;
    private Button buttonnew;
    private Texture backgroundTexture;
    private BitmapFont bitmapFont, bitmapFontHS, bitmapFontHSwhite;


    public TCTitleScreen(Game aGame) {

        tctextx = 0;


        menu = Gdx.audio.newSound(Gdx.files.internal("sound/menu.wav"));
        toy = Gdx.audio.newSound(Gdx.files.internal("sound/toy.wav"));
        score = Gdx.audio.newSound(Gdx.files.internal("sound/score.wav"));
        gameover = Gdx.audio.newSound(Gdx.files.internal("sound/gameover.wav"));


        prefs = Gdx.app.getPreferences("ToyCrane");

        if (!prefs.contains("soundState")) {
            prefs.putBoolean("soundState", false);
        }

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }


        game = aGame;

        backgroundTexture = new Texture(Gdx.files.internal("background_1.png"));

        //----------------create camera----------------------------
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        viewp = new StretchViewport(136, 204, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewp, batch);
        //------------------Font---------------------------
        bitmapFont = new BitmapFont(Gdx.files.internal("font/bigfont/text.fnt"),
                Gdx.files.internal("font/bigfont/text.png"), false);
        bitmapFont.setColor(0, 111/255f, 255/255f, 1);

        bitmapFontHS = new BitmapFont(Gdx.files.internal("font/highscore/text.fnt"),
                Gdx.files.internal("font/highscore/text.png"), false);
        bitmapFontHS.setColor(0, 111/255f, 255/255f, 1);

        bitmapFontHSwhite = new BitmapFont(Gdx.files.internal("font/highscore/text.fnt"),
                Gdx.files.internal("font/highscore/text.png"), false);
        bitmapFontHSwhite.setColor(0, 111/255f, 255/255f, 1);

        //--------------------Button Play------------------------------
        buttonPlaySkin = new Skin();
        buttonPlaySkin.add("buttonPlayup", new Texture("button_play_up.png"));
        buttonPlaySkin.add("buttonPlaydown", new Texture("button_play_down.png"));
        buttonPlayStyle = new Button.ButtonStyle();
        buttonPlayStyle.up = buttonPlaySkin.getDrawable("buttonPlayup");
        buttonPlayStyle.down = buttonPlaySkin.getDrawable("buttonPlaydown");
        buttonPlay = new Button(buttonPlayStyle);
        buttonPlay.setBounds(53, 120, 30, 30);
        //--------------------------------------------------------------



        //--------------------Button quit------------------------------
        buttonqSkin = new Skin();
        buttonqSkin.add("button_q_up", new Texture("button_q_up.png"));
        buttonqSkin.add("button_q_down", new Texture("button_q_down.png"));
        buttonqStyle = new Button.ButtonStyle();
        buttonqStyle.up = buttonqSkin.getDrawable("button_q_up");
        buttonqStyle.down = buttonqSkin.getDrawable("button_q_down");
        buttonq = new Button(buttonqStyle);
        buttonq.setBounds(50, 11, 39, 20);
        //--------------------------------------------------------------




        //--------------------Add actor-------------------------
        stage.addActor(buttonPlay);
        stage.addActor(buttonq);

        ButtonPlay();
        ButtonSound();
        ButtonQ();

        Gdx.input.setInputProcessor(stage);


    }


    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static boolean getSoundState() {
        return prefs.getBoolean("soundState");
    }

    private void ButtonPlay() {

        buttonPlay.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new TCGameScreen(game));

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (getSoundState()) {
                    menu.play();
                }

                return true;
            }

        });
    }

    private void ButtonQ() {

        buttonq.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                Gdx.app.exit();


            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

        });
    }

    private void ButtonAch() {

        buttonach.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);


            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (getSoundState()) {
                    menu.play();
                }

                return true;
            }

        });
    }


    private void ButtonHS() {

        buttonhs.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);


            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (getSoundState()) {
                    menu.play();
                }

                return true;
            }

        });
    }

    private void ButtonMoreToys() {

        buttonmt.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                //  playServices.showScore();

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (getSoundState()) {
                    menu.play();
                }

                return true;
            }

        });
    }


    private void ButtonSound() {
        Gdx.app.log("", "" + getSoundState());
        if (getSoundState()) {
            buttonSoundSkin = new Skin();
            buttonSoundSkin.add("buttonSoundup", new Texture("button_sound_up.png"));
            buttonSoundSkin.add("buttonSounddown", new Texture("button_sound_up.png"));
            buttonSoundStyle = new Button.ButtonStyle();
            buttonSoundStyle.up = buttonSoundSkin.getDrawable("buttonSoundup");
            buttonSoundStyle.down = buttonSoundSkin.getDrawable("buttonSounddown");
            buttonSound = new Button(buttonSoundStyle);
            buttonSound.setBounds(10, 140, 10, 10);
            stage.addActor(buttonSound);

        } else {
            buttonSoundSkin = new Skin();
            buttonSoundSkin.add("buttonSoundup", new Texture("button_sound_down.png"));
            buttonSoundSkin.add("buttonSounddown", new Texture("button_sound_down.png"));
            buttonSoundStyle = new Button.ButtonStyle();
            buttonSoundStyle.up = buttonSoundSkin.getDrawable("buttonSoundup");
            buttonSoundStyle.down = buttonSoundSkin.getDrawable("buttonSounddown");
            buttonSound = new Button(buttonSoundStyle);
            buttonSound.setBounds(10, 140, 10, 10);
            stage.addActor(buttonSound);
        }

        buttonSound.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (getSoundState()) {
                    prefs.putBoolean("soundState", false);
                    prefs.flush();
                    buttonSound.remove();
                    ButtonSound();

                } else {
                    prefs.putBoolean("soundState", true);

                    prefs.flush();
                    buttonSound.remove();
                    ButtonSound();
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


                return true;
            }

        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, 143, 204);


        stage.getBatch().end();


        stage.getBatch().begin();
        tctextx = tctextx + 1;
        if (tctextx == 80) {
            tctextx = -160;
        }


        bitmapFont.draw(batch, "CLAW CRANE", 16, 180);
        bitmapFontHSwhite.draw(batch, "HIGH", 24, 110);
        bitmapFontHS.draw(batch, "SCORE: " + getHighScore(), -tctextx, 95);
        //   bitmapFontHSwhite.draw(batch, "LEVEL", 87, 77);
        //  bitmapFontHS.draw(batch, "1", 103, 62);


        stage.getBatch().end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


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
        toy.dispose();
        score.dispose();
        gameover.dispose();
        bitmapFont.dispose();
        bitmapFontHS.dispose();
        bitmapFontHSwhite.dispose();
        backgroundTexture.dispose();
        game.dispose();

        buttonPlaySkin.dispose();
        buttonSoundSkin.dispose();
        buttonmtSkin.dispose();

        buttonhsSkin.dispose();
        buttonachSkin.dispose();
        buttonqSkin.dispose();


    }


}
