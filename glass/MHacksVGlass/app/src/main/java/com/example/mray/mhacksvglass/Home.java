package com.example.mray.mhacksvglass;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.firebase.client.Firebase;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;

/**
 * An {@link Activity} showing a tuggable "Hello World!" card.
 * <p/>
 * The main content view is composed of a one-card {@link CardScrollView} that provides tugging
 * feedback to the user when swipe gestures are detected.
 * If your Glassware intends to intercept swipe gestures, you should set the content view directly
 * and use a {@link com.google.android.glass.touchpad.GestureDetector}.
 *
 * @see <a href="https://developers.google.com/glass/develop/gdk/touch">GDK Developer Guide</a>
 */
public class Home extends Activity {

    /**
     * {@link CardScrollView} to use as the main content view.
     */
    private CardScrollView mCardScroller;

    /**
     * "Hello World!" {@link View} generated by {@link #buildView()}.
     */
    private View mView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        mView = buildView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardScrollAdapter() {
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Object getItem(int position) {
                return mView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return mView;
            }

            @Override
            public int getPosition(Object item) {
                if (mView.equals(item)) {
                    return 0;
                }
                return AdapterView.INVALID_POSITION;
            }
        });
        // Handle the TAP event.
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Plays disallowed sound to indicate that TAP actions are not supported.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(Sounds.DISALLOWED);
            }
        });
        setContentView(mCardScroller);
        Firebase.setAndroidContext(this);

    }




    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();

        DatabaseManager db = new DatabaseManager(this);

        if (getIntent() != null && getIntent().getExtras() != null) {
            ArrayList<String> voiceResults = getIntent().getExtras()
                    .getStringArrayList(RecognizerIntent.EXTRA_RESULTS);

        }
    }


    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        super.onPause();
    }

    /**
     * Builds a Glass styled "Hello World!" view using the {@link CardBuilder} class.
     */
    private View buildView() {
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.AUTHOR);
        card.setFootnote(R.string.footnote);

        card.setText(R.string.send);
//        card.setTimestamp(voiceResults.get(0));
        card.setHeading(R.string.firstname_lastname);
        card.setSubheading(R.string.transaction_type);
        card.setIcon(R.drawable.ic_glass_logo);
        return card.getView();
    }

}
