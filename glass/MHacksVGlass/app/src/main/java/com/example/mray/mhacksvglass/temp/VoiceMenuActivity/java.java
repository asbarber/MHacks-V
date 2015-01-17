package com.example.mray.mhacksvglass.temp.VoiceMenuActivity;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.sample.apidemo.R;
import com.google.android.glass.sample.apidemo.card.CardAdapter;
import com.google.android.glass.view.WindowUtils;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity that demonstrates the voice menu API.
 */
public final class VoiceMenuActivity extends Activity {

    private CardScrollView mCardScroller;

    private int mPicture = 0;
    private boolean mVoiceMenuEnabled = true;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // Requests a voice menu on this activity. As for any other window feature,
        // be sure to request this before setContentView() is called
        getWindow().requestFeature(WindowUtils.FEATURE_VOICE_COMMANDS);

        // Ensure screen stays on during demo.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Sets up a singleton card scroller as content of this activity. Clicking
        // on the card toggles the voice menu on and off.
        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardAdapter(createCards(this)));
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Plays sound.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(Sounds.TAP);
                // Toggles voice menu. Invalidates menu to flag change.
                mVoiceMenuEnabled = !mVoiceMenuEnabled;
                getWindow().invalidatePanelMenu(WindowUtils.FEATURE_VOICE_COMMANDS);
            }
        });
        setContentView(mCardScroller);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        super.onPause();
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS) {
            getMenuInflater().inflate(R.menu.voice_menu, menu);
            return true;
        }
        // Good practice to pass through, for options menu.
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS) {
            // Dynamically decides between enabling/disabling voice menu.
            return mVoiceMenuEnabled;
        }
        // Good practice to pass through, for options menu.
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS) {
            switch (item.getItemId()) {
                case R.id.menu_designer: mPicture = 0; break;
                case R.id.menu_coder1:   mPicture = 1; break;
                case R.id.menu_coder2:   mPicture = 2; break;
                case R.id.menu_coder3:   mPicture = 3; break;
                case R.id.menu_coder4:   mPicture = 4; break;
                case R.id.menu_coder5:   mPicture = 5; break;
                case R.id.menu_product:  mPicture = 6; break;
                default: return true;  // No change.
            }
            mCardScroller.setAdapter(new CardAdapter(createCards(this)));
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     * Creates a singleton card list to display as activity content.
     */
    private List<CardBuilder> createCards(Context context) {
        ArrayList<CardBuilder> cards = new ArrayList<CardBuilder>();
        CardBuilder card = new CardBuilder(context, CardBuilder.Layout.TEXT)
                .addImage(getImageResource())
                .setText(R.string.voice_menu_explanation);
        cards.add(card);
        return cards;
    }

   
    }
}
