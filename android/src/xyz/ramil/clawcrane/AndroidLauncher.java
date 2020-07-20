package xyz.ramil.clawcrane;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import xyz.ramil.clawcrane.ClawCrane;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		View gameView = initializeForView(new ClawCrane());
		FrameLayout frameLayout = findViewById(R.id.gameView);
		frameLayout.addView(gameView);
		final AdView adView = findViewById(R.id.adVidew);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		final Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
		adView.setAdListener(new AdListener(){
			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				adView.startAnimation(animation);
			}
		});
	}
}
