package lol.jungler_free;

import android.app.Activity;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;
import java.util.ArrayList;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import lol.jungler_free.R;

public class AllyActivity extends Activity{
	 
	private MrWizard textMonitor;
	
	private Vibrator myVibe;
	
	private static TextView clockDisplay;
	
	private static ArrayList<TextView> textList;
	private static ArrayList<Button> enabler;
	
	private static TextView wolfDisplay;
	private static TextView wraithDisplay;
	private static TextView golemDisplay;
	private static TextView blueDisplay;
	private static TextView redDisplay;
	private static TextView dragonDisplay;
	private static TextView wolfCount;
	private static TextView wraithCount;
	private static TextView golemCount;
	private static TextView blueCount;
	private static TextView redCount;
	private static TextView dragonCount;
	
	private Button toggleButton;
	
	private Button wolves;
	private Button wraiths;
	private Button golems;
	private Button blue;
	private Button red;
	private Button dragon;
	
	private AdView adview;
	private AdRequest re;
	
	private final int WOLF_RESPAWN = 60;
	private final int WRAITH_RESPAWN = 50;
	private final int GOLEM_RESPAWN = 60;
	private final int BLUE_RESPAWN = 300;
	private final int RED_RESPAWN = 300;
	private final int DRAGON_RESPAWN = 360;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
       
        
        setContentView(R.layout.allyscreen);
        
        clockDisplay = (TextView) findViewById(R.id.textView8);
        
        wolfDisplay = (TextView) findViewById(R.id.textView1);
        wraithDisplay = (TextView) findViewById(R.id.textView2);
        golemDisplay = (TextView) findViewById(R.id.textView3);
        blueDisplay = (TextView) findViewById(R.id.textView4);
        redDisplay = (TextView) findViewById(R.id.textView5);
        dragonDisplay = (TextView) findViewById(R.id.textView6);
        wolfCount = (TextView) findViewById(R.id.textView7);
        wraithCount = (TextView) findViewById(R.id.textView9);
        golemCount = (TextView) findViewById(R.id.textView10);
        blueCount = (TextView) findViewById(R.id.textView11);
        redCount = (TextView) findViewById(R.id.textView12);
        dragonCount = (TextView) findViewById(R.id.textView13);
        
        adview = (AdView) findViewById(R.id.ad);
        
        textList = new ArrayList<TextView>();
        enabler = new ArrayList<Button>();
        
        textList.add(wolfDisplay);
        textList.add(wraithDisplay);
        textList.add(golemDisplay);
        textList.add(blueDisplay);
        textList.add(redDisplay);
        textList.add(dragonDisplay);
        
        toggleButton = (Button) findViewById(R.id.Button1);
        wolves = (Button) findViewById(R.id.button1);
        wraiths = (Button) findViewById(R.id.button2);
        golems = (Button) findViewById(R.id.button3);
        blue = (Button) findViewById(R.id.button4);
        red = (Button) findViewById(R.id.button5);
        dragon = (Button) findViewById(R.id.button6);
        
        enabler.add(wolves);
        enabler.add(wraiths);
        enabler.add(golems);
        enabler.add(blue);
        enabler.add(red);
        enabler.add(dragon);
        
        
        
        textMonitor = MrWizard.getInstance(clockDisplay, this, 0);
        textMonitor.start();  
        myVibe = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        

        

        toggleButton.setOnClickListener(new OnClickListener() {
       	 public void onClick(View v) {
       		if (JunglerActivity.allowVibe == true) {
       		 myVibe.vibrate(100);
       		}
       		 Intent switchEnemy = new Intent(AllyActivity.this, EnemyActivity.class);
       		 startActivity(switchEnemy);
       		 finish();
       	 }
        });
        
        wolves.setOnClickListener(new OnClickListener() {
       	 public void onClick(View v) {
       		if (JunglerActivity.allowVibe == true) {
       		myVibe.vibrate(100);
       		}
       		wolfDisplay.setText(calcRespawn(WOLF_RESPAWN));

   			new CountDownTimer(60000, 1000) {

                public void onTick(long millis) {
                    wolfCount.setText("      " + Integer.toString((int) (millis / 1000)));
                }

                

				public void onFinish() {
               		enabler.get(0).setEnabled(true);
               		wolfCount.setText("");
                }
             }.start();
             
       		
       		enabler.get(0).setEnabled(false);
       		lowerAlpha(enabler.get(0));
    	         }
        });
        
        wraiths.setOnClickListener(new OnClickListener() {
       	 public void onClick(View v) {
       		if (JunglerActivity.allowVibe == true) {
       		myVibe.vibrate(100);
       		}
       		wraithDisplay.setText(calcRespawn(WRAITH_RESPAWN));
       		new CountDownTimer(50000, 1000) {

                public void onTick(long millis) {
                    wraithCount.setText("      " + Integer.toString((int) (millis / 1000)));
                }

                

				public void onFinish() {
               		enabler.get(1).setEnabled(true);
               		wraithCount.setText("");
                }
             }.start();
             
       		
       		enabler.get(1).setEnabled(false);
       		lowerAlpha(enabler.get(1));
    	         }
        });
        golems.setOnClickListener(new OnClickListener() {
       	 public void onClick(View v) {
       		if (JunglerActivity.allowVibe == true) {
       		myVibe.vibrate(100);
       		}
       		golemDisplay.setText(calcRespawn(GOLEM_RESPAWN));
       		new CountDownTimer(60000, 1000) {

                public void onTick(long millis) {
                    golemCount.setText("      " + Integer.toString((int) (millis / 1000)));
                }

                

				public void onFinish() {
               		enabler.get(2).setEnabled(true);
               		golemCount.setText("");
                }
             }.start();
             
       		
       		enabler.get(2).setEnabled(false);
       		lowerAlpha(enabler.get(2));
    	         }
        });
        blue.setOnClickListener(new OnClickListener() {
       	 public void onClick(View v) {
       		if (JunglerActivity.allowVibe == true) {
       		myVibe.vibrate(100);
       		}
       		blueDisplay.setText(calcRespawn(BLUE_RESPAWN));
       		new CountDownTimer(300000, 1000) {

                public void onTick(long millis) {
                    blueCount.setText("      " + Integer.toString((int) (millis / 1000)));
                }

                

				public void onFinish() {
               		enabler.get(3).setEnabled(true);
               		blueCount.setText("");
                }
             }.start();
             
       		
       		enabler.get(3).setEnabled(false);
       		lowerAlpha(enabler.get(3));
    	         }
        });
        red.setOnClickListener(new OnClickListener() {
       	 public void onClick(View v) {
       		if (JunglerActivity.allowVibe == true) {
       		myVibe.vibrate(100);
       		}
       		redDisplay.setText(calcRespawn(RED_RESPAWN));
       		new CountDownTimer(300000, 1000) {

                public void onTick(long millis) {
                    redCount.setText("      " + Integer.toString((int) (millis / 1000)));
                }

                

				public void onFinish() {
               		enabler.get(4).setEnabled(true);
               		redCount.setText("");
                }
             }.start();
             
       		
       		enabler.get(4).setEnabled(false);
       		lowerAlpha(enabler.get(4));
    	         }
        });
        
        dragon.setOnClickListener(new OnClickListener() {
       	 public void onClick(View v) {
       		if (JunglerActivity.allowVibe == true) {
       		myVibe.vibrate(100);
       		}
       		dragonDisplay.setText(calcRespawn(DRAGON_RESPAWN));
       		new CountDownTimer(360000, 1000) {

                public void onTick(long millis) {
                    dragonCount.setText("      " + Integer.toString((int) (millis / 1000)));
                }

                

				public void onFinish() {
               		enabler.get(5).setEnabled(true);
               		dragonCount.setText("");
                }
             }.start();
             
       		
       		enabler.get(5).setEnabled(false);
       		lowerAlpha(enabler.get(5));
    	         }
        });
        
        re = new AdRequest();
        adview.loadAd(re);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    private String calcRespawn(int respawn) {
    	int minutes = GlobalClock.getGameMinutes();
    	int seconds = GlobalClock.getGameSeconds();
    	
    	int currentTime = (60*minutes)+seconds;
    	int timeUp = currentTime+respawn;
    	
    	int reMinutes = timeUp / 60;
    	int reSeconds = timeUp % 60;
    	return "" + GlobalClock.padZeroes(reMinutes) + ":" + GlobalClock.padZeroes(reSeconds);
    }
    
    public static ArrayList<TextView> getArrayCurrentState() {
    	return textList;
    }
    
    public static ArrayList<Button> getEnabledState() {
    	return enabler;
    }
    
    public static void updateText(ArrayList<TextView> timers) {
    	for (int i = 0; i < timers.size(); i++) {
    		textList.get(i).setText(timers.get(i).getText().toString());
    	}
    }
    
    public static void updateEnabled(ArrayList<Button> enable) {
    	for(int i = 0; i < enable.size(); i++) {
    		enabler.get(i).setEnabled(enable.get(i).isEnabled());
    	}
    	makeOpaque(enable);
    }
    
    public static void makeOpaque(ArrayList<Button> enable) {
    	Drawable drawable;
    	for (int i = 0; i < enable.size(); i++) {
    		if (enable.get(i).isEnabled()) {
    			drawable = enabler.get(i).getBackground();
    			drawable.setAlpha(255);
    			enabler.get(i).setBackgroundDrawable(drawable);
    		}
    	}
    }
    
    public static void lowerAlpha(Button button) {
    	Drawable drawable = button.getBackground();
    	drawable.setAlpha(100);
    	button.setBackgroundDrawable(drawable);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) { 
           case R.id.disable_music: {
              if (JunglerActivity.denyMusic == true) {
              Toast.makeText(this, "Music Disabled", Toast.LENGTH_LONG).show();
              JunglerActivity.denyMusic = false;
              break;
              }
       
              if (JunglerActivity.denyMusic == false) {
            	  Toast.makeText(this, "Music Enabled", Toast.LENGTH_LONG).show();
            	  JunglerActivity.denyMusic = true;
            	  break;
              }
              break;  
           }
        
           case R.id.disable_vibe:{
        	   if (JunglerActivity.allowVibe == true) {
        		   Toast.makeText(this, "Vibration Disabled", Toast.LENGTH_LONG).show(); 
        		   JunglerActivity.allowVibe = false;
        		   break;
        	   }
        	   if (JunglerActivity.allowVibe == false) {
        		   Toast.makeText(this, "Vibration Enabled", Toast.LENGTH_LONG).show();
        		   JunglerActivity.allowVibe = true;
        		   break;
        	   }
        	   break;
           }
        }
        	return true;
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	super.onKeyDown(keyCode, event);
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
    		System.exit(0);
    		return true;
    	}
    	return false;
    }
}

