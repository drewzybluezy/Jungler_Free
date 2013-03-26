package lol.jungler_free;


import lol.jungler_free.R;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Vibrator;

public class JunglerActivity extends Activity{
    
	private GlobalClock globalClock;
	
	private Button mainButton;
	private Vibrator myVibe;
	private MediaPlayer mp;
	private AdView adview;
	private AdRequest re;
	
	
	 public static boolean denyMusic;
	 public static boolean allowVibe;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myVibe = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        adview = (AdView) findViewById(R.id.ad);
        allowVibe = true;
        globalClock = GlobalClock.getInstance();
        
       
        
        }
       

    @Override
    public void onPause() {
     super.onPause();
     mp.release();
    
    }
    
    @Override
    public void onStop() {
     super.onStop();
     mp.release();
     
    }
    
    @Override
    public void onResume() {
      super.onResume();
      
      mp = MediaPlayer.create(JunglerActivity.this, R.raw.mainmusic);   
      mainButton = (Button) findViewById(R.id.button2);
      
      re = new AdRequest();
      adview.loadAd(re);
      
      if (mp.isPlaying() == false) {
    	  mp.start();
    	  mp.setLooping(true);
    	  }
      
      mainButton.setOnClickListener(new OnClickListener() {
    	  public void onClick(View v) {
    		  if (mp.isPlaying() == true) {
              mp.release();
              }
    		  
    		  if (JunglerActivity.allowVibe == true) {
    		        myVibe.vibrate(100);
    		  }
    		  
    		  Intent playgame = new Intent(JunglerActivity.this, AllyActivity.class);
    		  
    		  globalClock.start();
    		  globalClock.reset();
    		  
    		  MrWizard start = MrWizard.getInstance();
    	      if (start != null) {
    	    	  start.reset();
    	      }
    		  
    		  startActivity(playgame);
    		  }
         });
     
    }
    
    @Override
    public void onDestroy() {
     super.onDestroy();
     System.exit(0);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) { 
           case R.id.disable_music: {
              if (denyMusic == true) {
              Toast.makeText(this, "Music Disabled", Toast.LENGTH_LONG).show();
              denyMusic = false;
              break;
              }
       
              if (denyMusic == false) {
            	  Toast.makeText(this, "Music Enabled", Toast.LENGTH_LONG).show();
            	  denyMusic = true;
            	  break;
              }
              break;  
           }
        
           case R.id.disable_vibe:{
        	   if (allowVibe == true) {
        		   Toast.makeText(this, "Vibration Disabled", Toast.LENGTH_LONG).show(); 
        		   allowVibe = false;
        		   break;
        	   }
        	   if (allowVibe == false) {
        		   Toast.makeText(this, "Vibration Enabled", Toast.LENGTH_LONG).show();
        		   allowVibe = true;
        		   break;
        	   }
        	   break;
           }
        }
        	return true;
    }

}