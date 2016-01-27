package com.well.hung.hangman;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class FriendsArrayAdapter extends ArrayAdapter<Friend> 
	{
   		private final Activity context;
   		final ArrayList<Friend> friends;
   		private int resourceId;
   		String wordToGuess;
   		public Friend f;
   		public String URL;
   		Boolean ingame = false;
   		Boolean battle = false;
   		public String WORDTEST;
   		public String rivalid;
   		public AlertDialog.Builder alert;
   		public String won = "0";
   		public String WORDGAME;
   		
   		public Button battleBtn;
   		public Button newgameBtn;
   		public View rowView;
   		
   	
   		
   		
   		///////////////////////////////////////
   		public FriendsArrayAdapter
   		(Activity context, int resourceId, ArrayList<Friend> friends) 
   	{
        super(context, resourceId, friends);
        this.context = context;
        this.friends = friends;
        this.resourceId = resourceId;
        
       

    }

   		
   		
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        View rowView = convertView;
        if (rowView == null) 
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = vi.inflate(resourceId, null);
        }
        
        
        
        alert = new AlertDialog.Builder(context);
        alert.setTitle("Hanged! Online Play");
        alert.setMessage("Enter a word for your opponent to guess. Alphabetical characters only and maximum of 25 characters long.");
        final EditText input = new EditText(context);
        alert.setView(input);
        
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
        {
        public void onClick(DialogInterface dialog, int whichButton) 
        {
     	   wordToGuess = input.getText().toString();
     	   SubmitWord submitTask = new SubmitWord();
     	   submitTask.execute(new String[] { "http://www.hanged.comli.com/main.php" });
     	   Log.i("postData", wordToGuess);
         }
        });

       alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
        {
          public void onClick(DialogInterface dialog, int whichButton) 
          {
        	  Intent intent = new Intent(context, NetPlay.class);
        	  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);       
        	  context.startActivity(intent);
          }
        });
        
        final Friend f = friends.get(position);
        TextView rowTxt = (TextView) rowView.findViewById(R.id.rowtext_top);
        rowTxt.setText(f.name+"       ");
        
        
        
        
    	Button battleBtn = (Button) rowView.findViewById(R.id.battleBtn);
        battleBtn.setText(f.id+" Accept Challenge");
        battleBtn.setOnClickListener(new OnClickListener() 
        {
        	   @Override
        	   public void onClick(View v) 
        	   {
        		   rivalid = f.id;
        		   AcceptChallenge task2 = new AcceptChallenge();
             	   task2.execute(new String[] { "http://www.hanged.comli.com/get-currentgame.php" });
        	   }
        });
        
        Button newgameBtn = (Button) rowView.findViewById(R.id.newgameBtn);
        newgameBtn.setText(f.id+" Start new game.");
        
        newgameBtn.setOnClickListener(new OnClickListener() 
        {
        	   @Override
        	   public void onClick(View v) 
        	   {
        		   rivalid = f.id;
                   alert.show();  
        	   }   
        });

       

        	for (int i = 0;i<NetPlay.rivalArray.length-1;i++)
        	{
        		Log.i("VICTIM ARRAY DATA ", NetPlay.rivalArray[i]);
        		
        		if (NetPlay.rivalArray[i].equals(f.id))
        		{
        			ingame = true;
        			break;
        			
        		}
        		else
        		{
        			ingame = false;
        			
        		}
        	}
       
        	for (int i = 0;i<NetPlay.victimArray.length-1;i++)
        	{
        		Log.i("RIVAL ARRAY DATA ", NetPlay.victimArray[i]);
        		
        		if (NetPlay.victimArray[i].equals(NetPlay.myId))
        		{
        			battle = true;
        			break;
        		}
        		else
        		{
        			battle = false;
        			
        		}
        		
        	}
        	
        	if (battle.equals(false))
        	{
        		battleBtn.setVisibility(View.INVISIBLE);
        	}
        	else 
        	{
        		battleBtn.setVisibility(View.VISIBLE);
        	}
        	
        	if (ingame.equals(true))
    		{
        		newgameBtn.setVisibility(View.INVISIBLE);
    		}
        	else 
        	{
        		newgameBtn.setVisibility(View.VISIBLE);
        	}

        
        return rowView;
    }



    	

    
    public class SubmitWord extends AsyncTask<String, Void, String> 
    {
        @Override
        protected String doInBackground(String... urls) 
        {
          String response = "";
          try
          {
    		URL = urls[0];
          	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
          	nameValuePairs.add(new BasicNameValuePair("victim",NetPlay.myId));
          	nameValuePairs.add(new BasicNameValuePair("rival",rivalid));
          	nameValuePairs.add(new BasicNameValuePair("word",wordToGuess));
          	nameValuePairs.add(new BasicNameValuePair("won","0"));
          	
          	  HttpClient httpclient = new DefaultHttpClient();
              HttpPost httppost = new      
              HttpPost("http://www.hanged.comli.com/main.php");
              httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
              
              HttpResponse execute = httpclient.execute(httppost);
              HttpEntity entity = execute.getEntity();
              
              //InputStream is = entity.getContent();
              
              //mText.setText(is.toString());
              
             Log.i("postData", execute.getStatusLine().toString());
             //HttpEntity entity = response.getEntity();
         
          }
          catch(Exception e)
          {
                  Log.e("log_tag", "Error in http connection"+e.toString());
          }
          		return response;
            } 

        @Override
        protected void onPostExecute(String result) 
        {
          //mText.setText("DONE");
          //mSpinner.hide();
        	Intent intent = new Intent(context, NetPlay.class);
        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);       
      	  	context.startActivity(intent);
          /// RELOAD ENTIRE PAGE ////
          
        }

      }
    	 
    
    
    
    private class AcceptChallenge extends AsyncTask<String, Void, String> 
    {
        @Override
        protected String doInBackground(String... urls) 
        {
          String response = "";
          try
          {
    		URL = urls[0];
          	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
          	nameValuePairs.add(new BasicNameValuePair("victim",NetPlay.myId));
          	nameValuePairs.add(new BasicNameValuePair("rival",rivalid));
          	nameValuePairs.add(new BasicNameValuePair("word",wordToGuess));
          	nameValuePairs.add(new BasicNameValuePair("won","0"));
          	
          	  HttpClient httpclient = new DefaultHttpClient();
              HttpPost httppost = new      
              HttpPost("http://www.hanged.comli.com/get-currentgame.php");
              httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
              HttpResponse execute = httpclient.execute(httppost);
              HttpEntity entity = execute.getEntity();
              InputStream is = entity.getContent();
              
              BufferedReader rd = new BufferedReader(new InputStreamReader(is));
              String line = "", res = "";
              res = rd.readLine();
              
              WORDGAME = res;
              
              Log.i("POST CONTENTS - FROM ACCEPT CHALLENGE",(res));
              Log.i("POST CONTENTS - FROM ACCEPT CHALLENGE", NetPlay.myId);
              Log.i("POST CONTENTS - FROM ACCEPT CHALLENGE", rivalid);
     
              //WORDTEST = is.toString();
              
             Log.i("postData", execute.getStatusLine().toString());
             
             
         
          }
          catch(Exception e)
          {
                  Log.e("log_tag", "Error in http connection"+e.toString());
          }
          
          		return response;
            } 

        @Override
        protected void onPostExecute(String result) 
        {
       
          /// RELOAD ENTIRE PAGE ////
           	Intent intent = new Intent(context, NetPlay.class);
        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);       
      	  	context.startActivity(intent);
         
        }

      }
    
    
}   

