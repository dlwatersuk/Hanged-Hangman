package com.well.hung.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.well.hung.hangman.R;
import android.content.Context;
import android.content.res.Resources;

/// Public class to load dictionary, so it's not overdone.
public class DictionaryLoader extends MainActivity
{
		public static void loader (Context context)
		{
			Resources dictionaryFile = context.getResources();
        	InputStream inStream = dictionaryFile.openRawResource(R.raw.fulldictionary);
        	BufferedReader dictRead = new BufferedReader(new InputStreamReader(inStream));
        
        	try 
        	{
        		MainActivity.loadedDictionary = dictRead.readLine();
        	}
        	
        	catch (IOException e) 
        	{
        		//TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        	
        	MainActivity.splitDict = MainActivity.loadedDictionary.split(",");
		}	
}