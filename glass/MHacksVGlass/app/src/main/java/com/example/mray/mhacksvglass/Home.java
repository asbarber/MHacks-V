package com.example.mray.mhacksvglass;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.firebase.client.Firebase;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;


public class Home extends Activity {

    /**
     * {@link CardScrollView} to use as the main content view.
     */
    private CardScrollView mCardScroller;

    private View mView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        mView = buildView();
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
        DatabaseManager db = new DatabaseManager(this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        if (getIntent() != null && getIntent().getExtras() != null) {
//            ArrayList<String> voiceResults = getIntent().getExtras()
//                    .getStringArrayList(RecognizerIntent.EXTRA_RESULTS);
//
//            Log.d("poop", voiceResults.get(0));


//
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(url));
//            startActivity(i);
        }
//    }


    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        super.onPause();
    }

    /**
     * Builds a Glass styled "Hello World!" view using the {@link CardBuilder} class.
     */

    // Card for Venmo
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


//    // Card for Banking
//    private View buildView() {
//        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.AUTHOR);
//        card.setFootnote(R.string.footnote);
//
//        card.setText(R.string.send);
////        card.setTimestamp(voiceResults.get(0));
//        card.setHeading(R.string.firstname_lastname);
//        card.setSubheading(R.string.transaction_type);
//        card.setIcon(R.drawable.ic_glass_logo);
//        return card.getView();
//    }
//
//
//
//    // Card for Networking
//    private View buildView() {
//        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.AUTHOR);
//        card.setFootnote(R.string.footnote);
//
//        card.setText(R.string.send);
////        card.setTimestamp(voiceResults.get(0));
//        card.setHeading(R.string.firstname_lastname);
//        card.setSubheading(R.string.transaction_type);
//        card.setIcon(R.drawable.ic_glass_logo);
//        return card.getView();
//    }

}