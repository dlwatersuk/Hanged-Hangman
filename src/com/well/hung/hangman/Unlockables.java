package com.well.hung.hangman;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Unlockables extends SherlockActivity {
	
TextView currentPtsText;
TextView wordsPts;
String userScore;
ImageButton BtnMoreWords;
String ownWords;
TextView unlockstext;
TextView morewords;

ImageButton recoveryBtn;
TextView recoveryOwnedText;
String recoveryRecorded;



@Override
public boolean onCreateOptionsMenu(Menu menu) 
{
	final ActionBar action=getSupportActionBar();
	action.setDisplayShowCustomEnabled(true);
	action.setDisplayShowHomeEnabled(true);
	action.setDisplayShowTitleEnabled(false);
	RelativeLayout relative=new RelativeLayout(getApplicationContext());
	action.setCustomView(relative);
    action.setDisplayHomeAsUpEnabled(true);
    MenuInflater inflater = getSupportMenuInflater();
    inflater.inflate(R.menu.ab_unlocks, menu);
    return super.onCreateOptionsMenu(menu);
}

@Override
public boolean onOptionsItemSelected(MenuItem item) 
{
    switch (item.getItemId()) 
    {
        case android.R.id.home:
            // app icon in action bar clicked; go home
            Intent intent = new Intent(this, MainMenu.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
            break;
        
        case R.id.backtogame:
     	   Intent closeSP = new Intent(this, MainActivity.class);
     	   finish();
           startActivityForResult(closeSP, 0);
        break;
        
        case R.id.usrprofile:
      	   Intent closeSP1 = new Intent(this, UserProfile.class);
      	    finish();
            startActivityForResult(closeSP1, 0);
         break;
        
    }
    return super.onOptionsItemSelected(item);
}

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlockables);

        currentPtsText = (TextView) findViewById(R.id.TextView05); 
        wordsPts = (TextView) findViewById(R.id.wordsPts); 
        BtnMoreWords = (ImageButton) findViewById(R.id.buybtn);
        morewords = (TextView) findViewById(R.id.TextView03); 
        unlockstext = (TextView) findViewById(R.id.textView2); 
        
        //////////////////// SET FONT ////////////////////////////////////////////////////
        Typeface newfont = Typeface.createFromAsset(getAssets(), "fonts/titillium.otf");
        TextView text1 = (TextView) findViewById(R.id.textView1); 
        TextView text2 = (TextView) findViewById(R.id.textView2); 
        TextView text3 = (TextView) findViewById(R.id.TextView03); 
        TextView text4 = (TextView) findViewById(R.id.TextView04); 
        TextView text5 = (TextView) findViewById(R.id.TextView05); 
        TextView text6 = (TextView) findViewById(R.id.TextView06); 
        TextView text7 = (TextView) findViewById(R.id.TextView07); 
        TextView text8 = (TextView) findViewById(R.id.TextView08); 
        TextView text9 = (TextView) findViewById(R.id.TextView09); 
        TextView text10 = (TextView) findViewById(R.id.TextView10); 
        TextView text01 = (TextView) findViewById(R.id.TextView01); 
        recoveryOwnedText = (TextView) findViewById(R.id.recoveryOwned); 
        
        text01.setTypeface(newfont);
        //text1.setTypeface(newfont);
        text2.setTypeface(newfont);
        text3.setTypeface(newfont);
        text4.setTypeface(newfont);
        text5.setTypeface(newfont);
        text6.setTypeface(newfont);
        text7.setTypeface(newfont);
       // text8.setTypeface(newfont);
       // text9.setTypeface(newfont);
        //text10.setTypeface(newfont);
        /////////////////////////////////////////////////////////////////////////////////
        
        recoveryOwnedText.setTypeface(newfont); 
        currentPtsText.setTypeface(newfont); 
        wordsPts.setTypeface(newfont); 
        morewords.setTypeface(newfont); 
        unlockstext.setTypeface(newfont); 
    
        
        ////////// RECOVERY STUFF //////////////////////////////////////////////////
        
        
        recoveryRecorded = SharedPrefs.getStringPreference(this, SharedPrefs.RECOVERY);
        
        if (recoveryRecorded != null && !recoveryRecorded.equals(""))
        {
        	recoveryOwnedText.setText("Owned: "+recoveryRecorded); 
        	
        }
        else
        {
        	recoveryRecorded = "0";
        	recoveryOwnedText.setText("Owned: "+recoveryRecorded); 
        }
        
        ///////////////// END OF RECOVERY STUFF /////////////////////////////////
        
     
        
        // Get current user points and display //////////////////////////
        userScore = SharedPrefs.getStringPreference(this, SharedPrefs.USERSCORE);
        
        //Check if things are unlocked
        ownWords = SharedPrefs.getStringPreference(this, SharedPrefs.EXTRAWORDS);
        
        if (userScore != null && !userScore.equals(""))
        {
        	currentPtsText.setText("Current Points: "+userScore); 
        	
        }
        else
        {
        	userScore = "0";
        	currentPtsText.setText("Current Points: "+userScore); 
        }
        
        if (ownWords != null && !ownWords.equals(""))
        {
        	if (ownWords.equals("true"))
        	{
        		BtnMoreWords.setEnabled(false);
        		BtnMoreWords.setImageResource(R.drawable.unlocked);
        		wordsPts.setText("Owned."); 
             	wordsPts.setTextColor(Color.GREEN);
             	userScore = SharedPrefs.getStringPreference(Unlockables.this, SharedPrefs.USERSCORE);
             	currentPtsText.setText("Current Points: "+userScore); 
        	}
        }
       
    }
    
    public void buyWords(View view)
    {
    	
    	BtnMoreWords.setEnabled(false);
    	
    	int oldScore;
    	oldScore = Integer.valueOf(userScore);
    	if (oldScore>249)
    	{
    		BtnMoreWords.setImageResource(R.drawable.unlocked);
    		int newScore;
    		newScore = oldScore-250;
    		userScore = String.valueOf(newScore);
      	    String dictSize = "236000";
    		ownWords = "true";
    		SharedPrefs.setStringPreference(Unlockables.this, SharedPrefs.EXTRAWORDS, ownWords);
    		SharedPrefs.setStringPreference(Unlockables.this, SharedPrefs.USERSCORE, userScore);
    		SharedPrefs.setStringPreference(Unlockables.this, SharedPrefs.DICSIZE, dictSize);
    		currentPtsText.setText("Your current points: "+userScore); 
      	  	wordsPts.setText("Owned."); 
      	  	wordsPts.setTextColor(Color.GREEN);
    	}
    	else 
    	{
    		Toast.makeText(Unlockables.this,
    		"You do not have enough points to unlock this.", Toast.LENGTH_SHORT).show();
    	}
    	  

    }
    
    public void buyRecovery(View view)
    {
    	
    	
    	int oldScore;
    	oldScore = Integer.valueOf(userScore);
    	if (oldScore>49)
    	{
    		
    		Toast.makeText(Unlockables.this,
    		"Purchased one Recovery Power-Up", Toast.LENGTH_SHORT).show();
    		int recoveryCalculate = Integer.valueOf(recoveryRecorded);
    		recoveryCalculate = recoveryCalculate + 1;
    		recoveryRecorded = String.valueOf(recoveryCalculate);
    		SharedPrefs.setStringPreference(Unlockables.this, SharedPrefs.RECOVERY, recoveryRecorded);
    		recoveryRecorded = SharedPrefs.getStringPreference(this, SharedPrefs.RECOVERY);		
    		oldScore = oldScore - 50;
    		userScore = String.valueOf(oldScore);
    		SharedPrefs.setStringPreference(Unlockables.this, SharedPrefs.USERSCORE, userScore);
    		currentPtsText.setText("Your current points: "+userScore); 
    		recoveryOwnedText.setText("Owned: "+recoveryRecorded);
    	}
    	else
    	{
    		Toast.makeText(Unlockables.this,
    		"Not enough points", Toast.LENGTH_SHORT).show();
    	}
    }



    
}
