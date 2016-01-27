package com.well.hung.hangman;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs 
{
	public static String PREF_FILE = "HangedRes";
	
	//////////////////////////////////////////////////////////////////
	
	public static String USERSCORE = "0";
	public static String EXTRAWORDS = "false";
	public static String GAMESWON = "won";
	public static String GAMESLOST = "lost";
	public static String DICSIZE = "huge";
	public static String RECOVERY = "dirtyearsbill";

	/////////////////////////////////////////////////////////////////
	
	static SharedPreferences settings;
	static SharedPreferences.Editor editor;

	///////////////////////////////////////////////////////////////////
	
	public static String getStringPreference(Context context, String key) 
	{
		settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
		editor = settings.edit();
		String result = settings.getString(key, null);
		editor.clear();
		return result;
		
	}
	
	public static void setStringPreference(Context context, String key, String value) 
	{
		settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
		editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
		editor.clear();
	}
}