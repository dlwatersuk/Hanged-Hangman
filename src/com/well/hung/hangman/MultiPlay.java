package com.well.hung.hangman;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.well.hung.hangman.R;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import android.os.Bundle;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;


public class MultiPlay extends SherlockActivity
{
ImageButton buttonA;
ImageButton buttonB;
ImageButton buttonC;
ImageButton buttonD;
ImageButton buttonE;
ImageButton buttonF;
ImageButton buttonG;
ImageButton buttonH;
ImageButton buttonI;
ImageButton buttonJ;
ImageButton buttonK;
ImageButton buttonL;
ImageButton buttonM;
ImageButton buttonN;
ImageButton buttonO;
ImageButton buttonP;
ImageButton buttonQ;
ImageButton buttonR;
ImageButton buttonS;
ImageButton buttonT;
ImageButton buttonU;
ImageButton buttonV;
ImageButton buttonW;
ImageButton buttonX;
ImageButton buttonY;
ImageButton buttonZ;
ImageButton hintButton;
ImageButton newword;
RelativeLayout winorLose;
RelativeLayout ButtonLayout;
RelativeLayout FunctionLayout;
RelativeLayout TextLayout;
RelativeLayout relativeLayout1;
RelativeLayout relativeLayout2;
RelativeLayout relativeLayout3;
ImageView hangImage;
LinearLayout row1;
LinearLayout row2;
LinearLayout row3;
LinearLayout row4;
String CHECKEM;
String SHOWEM;
static String loadedDictionary;
String loadedWord;
String hintWord;
int Location;
TextView textView2; 
TextView showWord; 
String secretWord;
String[] secretWordArray;
String[] secretWordArrayHint;
String secretWordHint;
static String[] splitDict;
int score = 0;
int lives = 10;
int penalties = 0;
boolean answer = false;

AlphaAnimation alpha = new AlphaAnimation(0.4F, 0.4F);

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
   inflater.inflate(R.menu.ab_multi, menu);
   
 
   
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
            
        case R.id.newword:
        	Intent newWord = new Intent(this, MultiPlayWordChoose.class);
         	 finish();
            startActivityForResult(newWord, 0);
        	//getAWord();
        break;
        
        case R.id.profile:
     	   Intent closeSP = new Intent(this, UserProfile.class);
     	  
           startActivityForResult(closeSP, 0);
           
        break;
        
        case R.id.unlocks:
      	   Intent closeSP1 = new Intent(this, Unlockables.class);
      	 
            startActivityForResult(closeSP1, 0);
           
         break;
        
    }
    return super.onOptionsItemSelected(item);
}


@Override
public void onCreate(Bundle savedInstanceState) 
{
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        
        alpha.setDuration(1000);
        alpha.setFillAfter(true);
        
        TextLayout = (RelativeLayout) findViewById(R.id.relativeLayout3);
        
        row1 = (LinearLayout) findViewById(R.id.row1);
        row2 = (LinearLayout) findViewById(R.id.row2);
        row3 = (LinearLayout) findViewById(R.id.row3);
        row4 = (LinearLayout) findViewById(R.id.row4);
        
        BitmapDrawable TileMe = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.wordbar));
        TileMe.setTileModeX(TileMode.REPEAT);
        TileMe.setTileModeY(TileMode.MIRROR);
        /////////////////////////////////////////////
        TextLayout.setBackgroundDrawable(TileMe);

        buttonA = (ImageButton) findViewById(R.id.buttonA);
        buttonB = (ImageButton) findViewById(R.id.buttonB);
        buttonC = (ImageButton) findViewById(R.id.buttonC);
        buttonD = (ImageButton) findViewById(R.id.buttonD);
        buttonE = (ImageButton) findViewById(R.id.buttonE);
        buttonF = (ImageButton) findViewById(R.id.buttonF);
        buttonG = (ImageButton) findViewById(R.id.buttonG);
        buttonH = (ImageButton) findViewById(R.id.buttonH);
        buttonI = (ImageButton) findViewById(R.id.buttonI);
        buttonJ = (ImageButton) findViewById(R.id.buttonJ);
        buttonK = (ImageButton) findViewById(R.id.buttonK);
        buttonL = (ImageButton) findViewById(R.id.buttonL);
        buttonM = (ImageButton) findViewById(R.id.buttonM);
        buttonN = (ImageButton) findViewById(R.id.buttonN);
        buttonO = (ImageButton) findViewById(R.id.buttonO);
        buttonP = (ImageButton) findViewById(R.id.buttonP);
        buttonQ = (ImageButton) findViewById(R.id.buttonQ);
        buttonR = (ImageButton) findViewById(R.id.buttonR);
        buttonS = (ImageButton) findViewById(R.id.buttonS);
        buttonT = (ImageButton) findViewById(R.id.buttonT);
        buttonU = (ImageButton) findViewById(R.id.buttonU);
        buttonV = (ImageButton) findViewById(R.id.buttonV);
        buttonW = (ImageButton) findViewById(R.id.buttonW);
        buttonX = (ImageButton) findViewById(R.id.buttonX);
        buttonY = (ImageButton) findViewById(R.id.buttonY);
        buttonZ = (ImageButton) findViewById(R.id.buttonZ);
        ButtonLayout = (RelativeLayout) findViewById(R.id.relativeLayout2);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        relativeLayout3 = (RelativeLayout) findViewById(R.id.relativeLayout3);
        winorLose = (RelativeLayout) findViewById(R.id.winlosebar);
        hangImage = (ImageView) findViewById(R.id.imageView1); 
        textView2 = (TextView)findViewById(R.id.textView2); 
        
        winorLose.setVisibility(View.INVISIBLE);
        
        //Winners banner

        textView2.setText("");
        Typeface newfont = Typeface.createFromAsset(getAssets(), "fonts/titillium.otf");
        textView2.setTypeface(newfont); 
        
        getAWord();
   
}


    public void getAWord()
    {
    	 secretWord = MultiPlayWordChoose.multiWord;
    	 secretWord = secretWord.toUpperCase();
         secretWordArray = secretWord.split("");
         textView2.setText("");
         
         // Split the word //
         for (int i=1;i<secretWordArray.length;i++)
   	    {
   		   String keepThis = (String) textView2.getText();
   		   SHOWEM = keepThis+"_";
   		   textView2.setText(SHOWEM);
   		   secretWord = secretWord.trim(); 
   	    }
         
   	    //reveal funny characters, because I'm too nice.
         for (int i=1;i<secretWordArray.length;i++)
         {
        	 CHECKEM = secretWordArray[i];
        	 if (CHECKEM.equals("-"))
        	 	{
        		 answer = true;
        		 Location = i-1;
        		 StringBuilder BuildMaString = new StringBuilder(SHOWEM);
        		 BuildMaString.setCharAt(Location, '-');
        		 textView2.setText(BuildMaString);
        		 SHOWEM = (String) textView2.getText();
        	 	}
        	 answer = false;
         }
        	 
        	 // REVEAL SPACES :@
          for (int i=1;i<secretWordArray.length;i++)
          {
         	 CHECKEM = secretWordArray[i];
         	 if (CHECKEM.equals(" "))
         	 {
         		 answer = true;
         		 Location = i-1;
         		 
         			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
     				BuildMaString.setCharAt(Location, ' ');
     				textView2.setText(BuildMaString);
     				SHOWEM = (String) textView2.getText();	
         	 }
  
   	
          }
          answer = false;
    }
    
    public void helpMe(View view)
    {
    	//give a warning about losing lots of points//
     	secretWordArrayHint = secretWord.split("");
    	List<String> list = Arrays.asList(secretWordArrayHint);
    	Collections.shuffle(list);
    	secretWordHint = "";
    		for (int i=0;i<secretWord.length();i++)
    		{
    		secretWordHint = secretWordHint+secretWordArrayHint[i];
    		}
    	penalties = 4;
    	
    	Toast.makeText(MultiPlay.this,
    	"Here is the word, albeit jumbled: "+secretWordHint, Toast.LENGTH_SHORT).show();	
    	 hintButton = (ImageButton) findViewById(R.id.buttonHint);
    	hintButton.setEnabled(false);
    }
    
    public void didWeWin()
    {
    	//Give a toast
    	String CheckWin = (String) textView2.getText();
    	if (CheckWin.equals(secretWord))
    	{
    		// On screen toast //
			Toast.makeText(MultiPlay.this,
			"Congratulations, you've won.", Toast.LENGTH_SHORT).show();	
			
			row1.setVisibility(View.GONE);
    		row2.setVisibility(View.GONE);
    		row3.setVisibility(View.GONE);
    		row4.setVisibility(View.GONE);
    		//ButtonLayout.setClickable(false);
    		score = 0;
    		////////////////////// WIN BANNER ///////////////////////////////////////////
			Resources res = getResources(); 
			Drawable winbannerdraw = res.getDrawable(R.drawable.winbanner); 
			winorLose.setBackgroundDrawable(winbannerdraw);
			winorLose.setVisibility(View.VISIBLE);
			///////////////////// END OF WIN BANNER /////////////////////////////////////
			
			hangImage.startAnimation(alpha);
			relativeLayout3.startAnimation(alpha);
			

			/////////// SOUND PLAYER //////////////////
			MediaPlayer winplayer = MediaPlayer.create(MultiPlay.this, R.raw.winsound);
			winplayer.start();
			 
			/////// WIN COUNTER ///////
			String winCount = SharedPrefs.getStringPreference(MultiPlay.this, SharedPrefs.GAMESWON);
			
			if (winCount != null)
			{
				int winInt = Integer.valueOf(winCount);
				winInt = winInt + 1;
				winCount = String.valueOf(winInt);
				SharedPrefs.setStringPreference(MultiPlay.this, SharedPrefs.GAMESWON, winCount);
			}
			else
			{
				winCount = "0";
				int winInt = Integer.valueOf(winCount);
				winInt = winInt + 1;
				winCount = String.valueOf(winInt);
				SharedPrefs.setStringPreference(MultiPlay.this, SharedPrefs.GAMESWON, winCount);
			}

			
			
    	}
    	
    	
    	
    }
    public void didWeDie()

    {
    	
    	if (lives<1)
    	{
    		Toast.makeText(MultiPlay.this,
    		"Game Over!", Toast.LENGTH_SHORT).show();
    		
    		row1.setVisibility(View.GONE);
    		row2.setVisibility(View.GONE);
    		row3.setVisibility(View.GONE);
    		row4.setVisibility(View.GONE);
    		//ButtonLayout.setClickable(false);
    		
    		////////////////////// WIN BANNER 
			Resources res = getResources(); 
			Drawable losebannerdraw = res.getDrawable(R.drawable.losebanner); 
			winorLose.setBackgroundDrawable(losebannerdraw);
			winorLose.setVisibility(View.VISIBLE);
			///////////////////// END OF WIN BANNER
			
			hangImage.startAnimation(alpha);
			//ButtonLayout.startAnimation(alpha);
			relativeLayout3.startAnimation(alpha);
			
			/////// SOUND PLAYER ////////////////////
			MediaPlayer loseplayer = MediaPlayer.create(this, R.raw.losesound);
			loseplayer.start();
			/////////////////////////////////////////////////////////////////
    		
    		textView2.setText("The word was: "+secretWord);
    		String lossCount = SharedPrefs.getStringPreference(MultiPlay.this, SharedPrefs.GAMESLOST);
    		
    		if (lossCount != null)
    		{
    			int lossInt = Integer.valueOf(lossCount);
    			lossInt = lossInt + 1;
    			lossCount = String.valueOf(lossInt);
    			SharedPrefs.setStringPreference(MultiPlay.this, SharedPrefs.GAMESLOST, lossCount);
    		}
    		else 
    		{
    			lossCount = "0";
    			int lossInt = Integer.valueOf(lossCount);
    			lossInt = lossInt + 1;
    			lossCount = String.valueOf(lossInt);
    			SharedPrefs.setStringPreference(MultiPlay.this, SharedPrefs.GAMESLOST, lossCount);
    		}
			
    	}

    	if (lives == 10)
    	{
    		hangImage.setImageResource(R.drawable.hang10);
    	}
    	else if (lives == 9)
    	{
    		hangImage.setImageResource(R.drawable.hang9);
    	}
    	else if (lives == 8)
    	{
    		hangImage.setImageResource(R.drawable.hang8);
    	}
    	else if (lives == 7)
    	{
    		hangImage.setImageResource(R.drawable.hang7);
    	}
    	else if (lives == 6)
    	{
    		hangImage.setImageResource(R.drawable.hang6);
    	}
    	else if (lives == 5)
    	{
    		hangImage.setImageResource(R.drawable.hang5);
    	}
    	else if (lives == 4)
    	{
    		hangImage.setImageResource(R.drawable.hang4);
    	}
    	else if (lives == 3)
    	{
    		hangImage.setImageResource(R.drawable.hang3);
    	}
    	else if (lives == 2)
    	{
    		hangImage.setImageResource(R.drawable.hang2);
    	}
    	else if (lives == 1)
    	{
    		hangImage.setImageResource(R.drawable.hang1);
    	}
    	else if (lives == 0)
    	{
    		hangImage.setImageResource(R.drawable.hang0);
    	}
    }

    
   public void getNewWord(View view) 
   {
	   // Regenerate the form back to default status //
	   //get a new word //
	   Intent newWord = new Intent(this, MultiPlayWordChoose.class);
	   finish();
       startActivityForResult(newWord, 0);
	  // getAWord();
   }
    /// Close game
   public void closeGame(View view)

   {
	   Intent closeSP = new Intent(view.getContext(), MainMenu.class);
       startActivityForResult(closeSP, 0);
   }

   
   ///////////////////// Guess Buttons Codes /////////////////////
   public void guessA(View view)
   {
	   buttonA.setEnabled(false);
	   
	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("A"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
				BuildMaString.setCharAt(Location, 'A');
				
				textView2.setText(BuildMaString);
				
				SHOWEM = (String) textView2.getText();	
				
				answer = true;
				
				
    	 }

     }
	   
	   // if statement for letter guessing result
	   if (!answer)
	   {
		   		//false
		   		buttonA.setImageResource(R.drawable.wrong);
		   		// On screen toast //
		   		lives = lives-1;
		   		didWeDie();
		   		
	    }
	   
		if (answer)
		{
				//true
				buttonA.setImageResource(R.drawable.tick);
				// On screen toast //
					
				answer = false;
				didWeWin();
		}
			 
			   
   }
   public void guessB(View view)
   {
	   buttonB.setEnabled(false);
	   
	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("B"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
				BuildMaString.setCharAt(Location, 'B');
				
				textView2.setText(BuildMaString);
				
				SHOWEM = (String) textView2.getText();	
				
				answer = true;
    	 }

     }
	   
	   // if statement for letter guessing result
	   if (!answer)
	   {
		   		//false
		   		buttonB.setImageResource(R.drawable.wrong);
		   		// On screen toast //
		   	
		   		lives = lives-1;
		   		didWeDie();
		   		
	    }
	   
		if (answer)
		{
				//true
				buttonB.setImageResource(R.drawable.tick);
				// On screen toast //
				
				answer = false;
				didWeWin();
		}
			 
			   
   }
   public void guessC(View view)

   {
	   buttonC.setEnabled(false);
	   
	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("C"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
				BuildMaString.setCharAt(Location, 'C');
				
				textView2.setText(BuildMaString);
				
				SHOWEM = (String) textView2.getText();	
				
				answer = true;
    	 }

     }
	   
	   // if statement for letter guessing result
	   if (!answer)
	   {
		   		//false
		   		buttonC.setImageResource(R.drawable.wrong);
		   		// On screen toast //
		   	
		   		lives = lives-1;
		   		didWeDie();
		   		
	    }
	   
		if (answer)
		{
				//true
				buttonC.setImageResource(R.drawable.tick);
				// On screen toast //
				
				answer = false;
				didWeWin();
		}
			 
			   
   }
   public void guessD(View view)

{
	   buttonD.setEnabled(false);
	   
	   // calculate if the letter exists // 
  for (int i=1;i<secretWordArray.length;i++)
  {
 	 CHECKEM = secretWordArray[i];
 	 if (CHECKEM.equals("D"))
 	 {
 		    Location = i-1;
 		 
 			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
 			
				BuildMaString.setCharAt(Location, 'D');
				
				textView2.setText(BuildMaString);
				
				SHOWEM = (String) textView2.getText();	
				
				answer = true;
 	 }

  }
	   
	   // if statement for letter guessing result
	   if (!answer)
	   {
		   		//false
		   		buttonD.setImageResource(R.drawable.wrong);
		   		// On screen toast //
		   		
		   		lives = lives-1;
		   		didWeDie();
		   		
	    }
	   
		if (answer)
		{
				//true
				buttonD.setImageResource(R.drawable.tick);
				// On screen toast //
				
				answer = false;
				didWeWin();
		}
			 
			   
}
   public void guessE(View view)

   {
   	   buttonE.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("E"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'E');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonE.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   		
   		   	lives = lives-1;
   		   	didWeDie();
   		 
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonE.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }   
   public void guessF(View view)

   {
   	   buttonF.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("F"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'F');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonF.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonF.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessG(View view)

   {
   	   buttonG.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("G"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'G');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonG.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonG.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessH(View view)

   {
   	   buttonH.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("H"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'H');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonH.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonH.setImageResource(R.drawable.tick);
   				// On screen toast //
   			
   				answer = false;
   				
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessI(View view)

   {
   	   buttonI.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("I"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'I');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonI.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonI.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessJ(View view)

   {
   	   buttonJ.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("J"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'J');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonJ.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   		
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonJ.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessK(View view)

   {
   	   buttonK.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("K"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'K');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonK.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonK.setImageResource(R.drawable.tick);
   				// On screen toast //
   		
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessL(View view)

   {
   	   buttonL.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("L"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'L');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonL.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonL.setImageResource(R.drawable.tick);
   				// On screen toast //
   			
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessM(View view)

   {
   	   buttonM.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("M"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'M');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonM.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		  
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonM.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessN(View view)

   {
   	   buttonN.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("N"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'N');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonN.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonN.setImageResource(R.drawable.tick);
   				// On screen toast //
   	
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessO(View view)

   {
   	   buttonO.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("O"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'O');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonO.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		   	didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonO.setImageResource(R.drawable.tick);
   				// On screen toast //
   		
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessP(View view)

   {
   	   buttonP.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("P"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'P');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonP.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   		
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonP.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessQ(View view)

   {
   	   buttonQ.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("Q"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'Q');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonQ.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonQ.setImageResource(R.drawable.tick);
   				// On screen toast //
   	
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessR(View view)

   {
   	   buttonR.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("R"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'R');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonR.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonR.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessS(View view)

   {
   	   buttonS.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("S"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'S');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonS.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   		
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonS.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessT(View view)

   {
   	   buttonT.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("T"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'T');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonT.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   		
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonT.setImageResource(R.drawable.tick);
   				// On screen toast //
   			
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessU(View view)

   {
   	   buttonU.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("U"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'U');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonU.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   		
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonU.setImageResource(R.drawable.tick);
   				// On screen toast //
   			
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessV(View view)

   {
   	   buttonV.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("V"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'V');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonV.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonV.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessW(View view)

   {
   	   buttonW.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("W"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'W');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonW.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonW.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessX(View view)

   {
   	   buttonX.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("X"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'X');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonX.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonX.setImageResource(R.drawable.tick);
   				// On screen toast //
   			
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessY(View view)

   {
   	   buttonY.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("Y"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'Y');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonY.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   		
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonY.setImageResource(R.drawable.tick);
   				// On screen toast //
   			
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
   public void guessZ(View view)

   {
   	   buttonZ.setEnabled(false);
   	   
   	   // calculate if the letter exists // 
     for (int i=1;i<secretWordArray.length;i++)
     {
    	 CHECKEM = secretWordArray[i];
    	 if (CHECKEM.equals("Z"))
    	 {
    		    Location = i-1;
    		 
    			StringBuilder BuildMaString = new StringBuilder(SHOWEM);
    			
   				BuildMaString.setCharAt(Location, 'Z');
   				
   				textView2.setText(BuildMaString);
   				
   				SHOWEM = (String) textView2.getText();	
   				
   				answer = true;
    	 }

     }
   	   
   	   // if statement for letter guessing result
   	   if (!answer)
   	   {
   		   		//false
   		   		buttonZ.setImageResource(R.drawable.wrong);
   		   		// On screen toast //
   		   	
   		   	lives = lives-1;
   		 didWeDie();
   	    }
   	   
   		if (answer)
   		{
   				//true
   				buttonZ.setImageResource(R.drawable.tick);
   				// On screen toast //
   				
   				answer = false;
   				didWeWin();
   		}
   			 
   			   
   }
}



    
  
