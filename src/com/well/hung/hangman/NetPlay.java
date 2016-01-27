

package com.well.hung.hangman;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.R.menu;
import com.actionbarsherlock.app.SherlockActivity;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.R;
import com.facebook.android.Util;
import com.facebook.android.AsyncFacebookRunner.RequestListener;


public class NetPlay extends SherlockActivity {

    private static final String[] PERMISSIONS = new String[] {"publish_stream", "read_stream", "offline_access"};
    private TextView mText;
    private Handler mHandler = new Handler();
    private ProgressDialog mSpinner;
    private final ArrayList<Friend> friends = new ArrayList<Friend>();
    private FriendsArrayAdapter friendsArrayAdapter;
    private ListView listView;
    private TextView CurrentUser;
    public String rivalid;
    public static String myId;
    public static String wordToGuess;
    public String value;
    
    public static String[] rivalArray;
    public static String[] victimArray;
    
	public Boolean isRivalDone = false;
	public Boolean isVictimDone = false;
    
   static EditText input;
   public Context sharedContext;
   

    
    InputStream is = null;
    String URL;
    String newname;
        Facebook facebook = new Facebook("243552379098900");
        private SharedPreferences mPrefs;
        AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook);


        @Override
        public void onCreate(Bundle savedInstanceState) 
        {
        	
        	CheckChallenge task3 = new CheckChallenge();
     	   	task3.execute(new String[] { "http://www.hanged.comli.com/get-currentgame.php" });
     	   	
     	    CheckVictim task4 = new CheckVictim();
    	   	task4.execute(new String[] { "http://www.hanged.comli.com/get-currentgame.php" });
        	
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_net_play);
            
            
            
            
            mText = (TextView)findViewById(R.id.txt); 
            CurrentUser = (TextView)findViewById(R.id.usertxt); 
            // Setup the ListView Adapter that is loaded when selecting "get friends"
            listView = (ListView) findViewById(R.id.friendsview);
            friendsArrayAdapter = new FriendsArrayAdapter(this, R.layout.rowlayout, friends);
            listView.setAdapter(friendsArrayAdapter);

            // Define a spinner used when loading the friends over the network
            mSpinner = new ProgressDialog(listView.getContext());
            mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mSpinner.setMessage("Loading...");
            
            mPrefs = getPreferences(MODE_PRIVATE);
            String access_token = mPrefs.getString("access_token", null);
            long expires = mPrefs.getLong("access_expires", 0);
            if(access_token != null) {
                facebook.setAccessToken(access_token);
            }
            if(expires != 0) {
                facebook.setAccessExpires(expires);
            }
            if(!facebook.isSessionValid()) 
            {

                facebook.authorize(this, new String[] {"email", "publish_stream", "read_stream", "offline_access"}, new DialogListener() 
               {
                    public void onComplete(Bundle values) 
                    {
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("access_token", facebook.getAccessToken());
                        editor.putLong("access_expires", facebook.getAccessExpires());
                        editor.commit();
                        letsDoThis();
                        whoIsYaDaddy(); 
                        
                    }
        
                    
                    public void onFacebookError(FacebookError error) {}
        
                    @Override
                    public void onError(DialogError e) {}
        
                    @Override
                    public void onCancel() {}
                });
            }
            
            listView.setClickable(true);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
            {

              @Override
              public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
              {
            	  Friend f = friends.get(position);
            	  newname = f.id.toString();
            	  mText.setText(newname);
            	  
            	  ///// SET USER ID TO GO TO DATABASE ///// 
            	  rivalid = newname;
            	  
            	  ///// ASK FOR A WORD /// 
            	  //alert.show();
            	  wordToGuess = value;
            	  
            
              }
            
            });
            
            letsDoThis();
            whoIsYaDaddy(); 
            
        	}
                    

        
        
        	 
        	
        	
        	
        
        
        
        
        
        
        
        
        
        public void whoIsYaDaddy()
        {
        	AsyncFacebookRunner myAsyncRunner = new AsyncFacebookRunner(facebook);
        	myAsyncRunner.request("me", new meRequestListener());
        }
        
        public class meRequestListener implements RequestListener
        {

			@Override
			public void onIOException(IOException e, Object state) 
			{
			
				
			}
			@Override
			public void onFileNotFoundException(FileNotFoundException e, Object state) 
			{
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onMalformedURLException(MalformedURLException e, Object state) 
			{
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onFacebookError(FacebookError e, Object state) 
			{
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onComplete(String response, Object state) {
				// TODO Auto-generated method stub
		    	JSONObject me;
				try 
				{
					me = new JSONObject(facebook.request("me"));
					myId = me.getString("id");
		            final String myname = me.getString("name");
		            
		            NetPlay.this.runOnUiThread(new Runnable() 
		            {
		                public void run() 
		                {
		                	CurrentUser.setText("Welcome, "+myname);
		                }
		            });

				} 
				catch (MalformedURLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (JSONException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
             
				
			}


        }
       
        
        
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) 
        {
            super.onActivityResult(requestCode, resultCode, data);

            facebook.authorizeCallback(requestCode, resultCode, data);
        }
 
        public void onResume() 
        {    
            super.onResume();
            facebook.extendAccessTokenIfNeeded(this, null);
        }

    
    public void letsDoThis()
    {
    	     mSpinner.show();
    	     mAsyncRunner.request("me/friends", new FriendsRequestListener());
    }
    



class FriendsRequestListener implements com.facebook.android.AsyncFacebookRunner.RequestListener 
{

	@Override
	public void onComplete(String response, Object state) {
		// TODO Auto-generated method stub
	   mSpinner.dismiss();
	        try {

	            final JSONObject json = new JSONObject(response);
	            JSONArray d = json.getJSONArray("data");
	            int l = (d != null ? d.length() : 0);
	            Log.d("Facebook-Example-Friends Request", "d.length(): " + l);

	            for (int i=0; i<l; i++) {
	                JSONObject o = d.getJSONObject(i);
	                String n = o.getString("name");
	                String id = o.getString("id");
	                Friend f = new Friend();
	                f.id = id;
	                f.name = n;
	                friends.add(f);
	            }

	            // Only the original owner thread can touch its views
	            NetPlay.this.runOnUiThread(new Runnable() 
	            {
	                public void run() 
	                {
	                    friendsArrayAdapter = new FriendsArrayAdapter(NetPlay.this, R.layout.rowlayout, friends);
	                    listView.setAdapter(friendsArrayAdapter);
	                    friendsArrayAdapter.notifyDataSetChanged();
	                }
	            });
	        } 
	        catch (JSONException e) 
	        {
	            Log.w("Facebook-Example", "JSON Error in response");
	        }
	        
	      
	}


	@Override
	public void onIOException(IOException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFileNotFoundException(FileNotFoundException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMalformedURLException(MalformedURLException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFacebookError(FacebookError e, Object state) 
	{
		// TODO Auto-generated method stub
		
	}
}

public class CheckChallenge extends AsyncTask<String, Void, String> 
{
    @Override
    protected String doInBackground(String... urls) 
    {
      String response = "";
      try
      {
		URL = urls[0];
      	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
      	nameValuePairs.add(new BasicNameValuePair("victim",NetPlay.myId)); // need to return these to an array
      	nameValuePairs.add(new BasicNameValuePair("rival",rivalid));

      	nameValuePairs.add(new BasicNameValuePair("word","null"));
      	nameValuePairs.add(new BasicNameValuePair("won","0"));
      	
      	  HttpClient httpclient = new DefaultHttpClient();
          HttpPost httppost = new      
          HttpPost("http://www.hanged.comli.com/check-rival.php");
          httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
          
          HttpResponse execute = httpclient.execute(httppost);
          HttpEntity entity = execute.getEntity();
          
          InputStream is = entity.getContent();
          
          BufferedReader rd = new BufferedReader(new InputStreamReader(is));
          String line = "", res = "";
          res = rd.readLine();
        
          rivalArray = res.split(",");
          
          //mText.setText(is.toString());
          Log.i("POST CONTENTS - FROM CHECK CHALLENGE",(res));
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
    	
       isRivalDone = true;

    }

  }

public class CheckVictim extends AsyncTask<String, Void, String> 
{
    @Override
    protected String doInBackground(String... urls) 
    {
      String response = "";
      try
      {
		URL = urls[0];
      	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
      	nameValuePairs.add(new BasicNameValuePair("victim",NetPlay.myId)); // need to return these to an array
      	nameValuePairs.add(new BasicNameValuePair("rival",rivalid));
      	
      	
      	nameValuePairs.add(new BasicNameValuePair("word","null"));
      	nameValuePairs.add(new BasicNameValuePair("won","0"));
      	
      	  HttpClient httpclient = new DefaultHttpClient();
          HttpPost httppost = new      
          HttpPost("http://www.hanged.comli.com/check-rival.php");
          httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
          
          HttpResponse execute = httpclient.execute(httppost);
          HttpEntity entity = execute.getEntity();
          
          InputStream is = entity.getContent();
          
          BufferedReader rd = new BufferedReader(new InputStreamReader(is));
          String line = "", res = "";
          res = rd.readLine();
          
          victimArray = res.split(",");
          
          Log.i("POST CONTENTS - FROM CHECK VICTIM",(res));
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
    	
        
        isVictimDone = true;

      
    }

  }



}