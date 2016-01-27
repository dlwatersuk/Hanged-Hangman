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

import android.support.v4.app.NavUtils;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainMenu extends SherlockActivity
{


	
	
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
		final ActionBar action=getSupportActionBar();
		action.hide();
		
        }
    
    public void singlePlayGO(View view) 
    {
        Intent switchSinglePlay = new Intent(view.getContext(), MainActivity.class);
        
        startActivityForResult(switchSinglePlay, 0);
    }
    
    public void netPlayGO(View view) 
    {
        Intent switchNet = new Intent(view.getContext(), NetPlay.class);
        
        startActivityForResult(switchNet, 0);
    }
    
    public void startMultiPlay(View view) 
    {
        Intent multiPlay = new Intent(view.getContext(), MultiPlayWordChoose.class);
        
        startActivityForResult(multiPlay, 0);
    } 
        
    public void unlockablesGO(View view) 
    {
        Intent switchUnlocks = new Intent(view.getContext(), Unlockables.class);
       
        startActivityForResult(switchUnlocks, 0);
    }
    
    public void checkProfile(View view) 
    {
        Intent switchProfile = new Intent(view.getContext(), UserProfile.class);
        
        startActivityForResult(switchProfile, 0);
    }
        
        
   



    
}
