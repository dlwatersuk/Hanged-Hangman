package com.well.hung.hangman;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;

import android.content.Intent;
import android.graphics.Typeface;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;





public class MultiPlayWordChoose extends SherlockActivity 
{
	
	EditText editText;
	public static String multiWord;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		
		final ActionBar action=getSupportActionBar();
		action.setDisplayShowCustomEnabled(true);
		action.setDisplayShowHomeEnabled(true);
		action.setDisplayShowTitleEnabled(false);
		RelativeLayout relative=new RelativeLayout(getApplicationContext());
		action.setCustomView(relative);
	   // action.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    action.setDisplayHomeAsUpEnabled(true);
	   MenuInflater inflater = getSupportMenuInflater();
	   inflater.inflate(R.menu.multiplay_menu, menu);
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
	           
	            startActivity(intent);
	            
	            break;
	            
	            
	        case R.id.single:
	      	   Intent closeSP = new Intent(this, MainActivity.class);
	      	  
	            startActivityForResult(closeSP, 0);
	            
	         break;
	        
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_play_word_choose);
        editText = (EditText) findViewById(R.id.editText1);
        TextView WordChoseText = (TextView) findViewById(R.id.textView2); 
        TextView description = (TextView) findViewById(R.id.TextView03); 
        EditText editfont = (EditText) findViewById(R.id.editText1); 
        Typeface newfont = Typeface.createFromAsset(getAssets(), "fonts/titillium.otf");
        WordChoseText.setTypeface(newfont); 
        description.setTypeface(newfont); 
        editfont.setTypeface(newfont); 
 
    }


    public void timeToDuel(View view)
    {
    	MultiPlayWordChoose.multiWord = editText.getText().toString();
    	
    	if (!MultiPlayWordChoose.multiWord.equals(" ") && !MultiPlayWordChoose.multiWord.equals("") && !MultiPlayWordChoose.multiWord.equals(null))
    	{
        	if (multiWord.matches("^[a-zA-Z]+$"))
			{
        		if (multiWord.length() <=15)
        		{
        			Intent closeSP = new Intent(this, MultiPlay.class);
        			startActivityForResult(closeSP, 0);
        		}
        		else
        		{
        			Toast.makeText(MultiPlayWordChoose.this,
        			"The 'word' you entered was too long, it must be equal to or less than 15 characters.", Toast.LENGTH_SHORT).show();
        		}
			}
        	else 
        	{
        		Toast.makeText(MultiPlayWordChoose.this,
    			"The 'word' you entered contains invalid characters/symbols, use only A - Z", Toast.LENGTH_SHORT).show();
        	}
    	}
    	else
    	{
    		Toast.makeText(MultiPlayWordChoose.this,
    	    "You did not enter a word!", Toast.LENGTH_SHORT).show();
    	}

    }
    
    
    
    
    
}
