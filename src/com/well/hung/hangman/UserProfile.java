package com.well.hung.hangman;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Typeface;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class UserProfile extends SherlockActivity {
TextView WinText;
TextView LoseText;
TextView dictText;
TextView currPoints;
	String winCount;
	String lossCount;
	String dictSize;
	TextView UnlockablesText;
	TextView UserProfile;
	RelativeLayout unlock1;
	RelativeLayout unlock2;
	RelativeLayout unlock3;
	
	
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
	    inflater.inflate(R.menu.profile_menu, menu);
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
	        
	        case R.id.unlocks:
		     	   Intent closeSP1 = new Intent(this, Unlockables.class);
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
        setContentView(R.layout.activity_user_profile);
        
        String points = SharedPrefs.getStringPreference(UserProfile.this, SharedPrefs.USERSCORE);
        
        currPoints = (TextView)findViewById(R.id.tv2); 
        dictText = (TextView)findViewById(R.id.TextView02); 
        LoseText = (TextView)findViewById(R.id.TextView04); 
        WinText = (TextView)findViewById(R.id.TextView01); 
        UnlockablesText = (TextView)findViewById(R.id.TextView03); 
        UserProfile = (TextView)findViewById(R.id.textView1); 
        
    	RelativeLayout unlock1 = (RelativeLayout)findViewById(R.id.relativeLayout1); 
    	RelativeLayout unlock2 = (RelativeLayout)findViewById(R.id.RelativeLayout02); 
    	RelativeLayout unlock3 = (RelativeLayout)findViewById(R.id.RelativeLayout04); 
    	
    	unlock1.setVisibility(View.INVISIBLE);
    	unlock2.setVisibility(View.INVISIBLE);
    	unlock3.setVisibility(View.INVISIBLE);
        
        Typeface newfont = Typeface.createFromAsset(getAssets(), "fonts/titillium.otf");
        
        currPoints.setTypeface(newfont); 
        dictText.setTypeface(newfont); 
       	LoseText.setTypeface(newfont); 
       	WinText.setTypeface(newfont); 
       	UserProfile.setTypeface(newfont); 
       	UnlockablesText.setTypeface(newfont); 
        
        if (points != null)
        {
        	currPoints.setText("Current Points: "+points);
        }
        else
        {
        	currPoints.setText("Current Points: 0");
        }
        
        dictSize = SharedPrefs.getStringPreference(UserProfile.this, SharedPrefs.DICSIZE);
        
        if (dictSize != null)
        {
        	dictText.setText("Dictionary Size: "+dictSize+" words.");
        }
        else
        {
        	dictText.setText("Dictionary Size: 118,000 words.");
        }
        
        winCount = SharedPrefs.getStringPreference(UserProfile.this, SharedPrefs.GAMESWON);
        
        if (winCount != null)
        {
        	WinText.setText("Games Won: "+winCount);
        }
        else
        {
        	WinText.setText("Games Won: 0");
        }
        
        lossCount = SharedPrefs.getStringPreference(UserProfile.this, SharedPrefs.GAMESLOST);
        
        if (lossCount != null)
        {
        	LoseText.setText("Games Lost: "+lossCount);
        }
        else
        {
        	LoseText.setText("Games Lost: 0");
        }
    }
    
    public void clearPrefs(View view)
    {
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(UserProfile.this);

        alertDialog.setPositiveButton("Yes", new OnClickListener() 
        {
            @Override
            public void onClick(DialogInterface dialog, int which) 
            {
            	//// CLEAR ALL SETTINGS //// 
                SharedPrefs.editor = SharedPrefs.settings.edit();
                SharedPrefs.editor.clear();
                SharedPrefs.editor.commit();
         	   Intent intent = getIntent();
         	   finish();
         	   startActivity(intent);
            }
        });
        
        	alertDialog.setNegativeButton("No", null);
        	alertDialog.setMessage("Are you sure you want to delete all user settings? this will remove any scores and points you have gained. This action CANNOT be reversed.");
        	alertDialog.setTitle("Delete Hanged! User Settings");
        	alertDialog.show();
    }

    
}
