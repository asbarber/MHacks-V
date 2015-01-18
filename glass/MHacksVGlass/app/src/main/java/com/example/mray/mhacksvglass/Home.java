package com.example.mray.mhacksvglass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;

import com.example.mray.mhacksvglass.datasources.BankInfo;
import com.example.mray.mhacksvglass.datasources.CareerInfo;
import com.example.mray.mhacksvglass.datasources.Concur;
import com.example.mray.mhacksvglass.datasources.VenmoConnection;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.glass.view.WindowUtils;
import com.google.android.glass.widget.CardBuilder;

import java.util.List;


public class Home extends Activity {

    private Firebase firebaseRef;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(WindowUtils.FEATURE_VOICE_COMMANDS);
        setContentView(R.layout.activity_venmo);
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase("https://mhacksv.firebaseio.com/");
        firebaseRef.child("GlassInit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                startGlass();
                firebaseRef.child("GlassInit").setValue(false);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });

    }

//    @Override
//    public boolean onCreatePanelMenu(int featureId, Menu menu) {
//        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS) {
//            getMenuInflater().inflate(R.menu.voice_menu, menu);
//            return true;
//        }
//        return super.onCreatePanelMenu(featureId, menu);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.voice_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS) {
//            switch (item.getItemId()) {
//                case R.id.menu_venmo:
//                   // displaySpeechRecognizer();
//                    break;
//                case R.id.menu_network:
//                    // handle top-level cats menu item
//                    break;
//                default:
//                    return true;
//            }
//            return true;
//        }
//        return super.onMenuItemSelected(featureId, item);
//    }

    private static final int SPEECH_REQUEST = 0;

    private void startGlass() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra( RecognizerIntent.EXTRA_PROMPT, "What kind of transaction?\nSend Money (Venmo)\nNetwork (LinkedIn)\nFinancial (Cap. One)\nResume" );
        startActivityForResult(intent, SPEECH_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            if (spokenText.toLowerCase().trim().contains("send money")) {
                Log.d("MYOwnConnection","Venmo connection");
                VenmoConnection vc = new VenmoConnection();
                vc.execute(spokenText.replace("send money","").trim());
            } else if (spokenText.toLowerCase().trim().contains("network")) {
                execLinkedIn();
            } else if (spokenText.toLowerCase().trim().contains("financial")) {
                execCOne();
            } else if (spokenText.toLowerCase().trim().contains("concur")) {
                execConcur();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void execLinkedIn() {
        Log.d("MYOwnConnection","Linked in");

        //LinkedInConnection lic = new LinkedInConnection();
        //CareerInfo ci = lic.execute();

        CareerInfo ci = new CareerInfo("Katelyn","Dunaski","Michigan State University, Applied Engineering Sciences","url");

        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.AUTHOR);
        card.setFootnote(R.string.footnote);

        card.setText(ci.getHeadline());
//          card.setTimestamp(voiceResults.get(0));
        card.setHeading(ci.getFirstName() + " " + ci.getLastName());
//            card.setSubheading(ci.getHeadline());
        card.setIcon(R.drawable.ic_glass_logo);
        setContentView(card.getView());
        onResume();
    }

    private void execCOne() {
        Log.d("MYOwnConnection","COne");

        BankInfo info = BankInfo.Sample;

        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.AUTHOR);
        card.setFootnote(R.string.footnote);

        card.setText("Upcoming Payment: " + info.getUpcomingPayment());
        //card.setTimestamp(voiceResults.get(0));
        card.setHeading("Balance: " + info.getBalance());
        card.setSubheading("Recent Transition: " + info.getRecentTransition());
        card.setIcon(R.drawable.ic_glass_logo);

        setContentView(card.getView());
        onResume();
    }

    private void execConcur() {
        Log.d("MYOwnConnection","Concur");

        Concur concur = new Concur();
        concur.sendToConcur();

        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.AUTHOR);
        card.setFootnote(R.string.footnote);
        card.setText(R.string.send);
        card.setTimestamp("Need to implement");
        card.setHeading(R.string.firstname_lastname);
        card.setSubheading(R.string.transaction_type);
        card.setIcon(R.drawable.ic_glass_logo);
        setContentView(card.getView());
        onResume();
    }

}

//    // Card for Venmo
//    private View buildView() {
//        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.AUTHOR);
//        card.setFootnote(R.string.footnote);
//
//        card.setText(R.string.send);
////        card.setTimestamp(voiceResults.get(0));
//        card.setHeading(R.string.firstname_lastname);
//        card.setSubheading(R.string.transaction_type);
//        card.setIcon(R.drawable.myo_logo);
//        return card.getView();
//    }


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
