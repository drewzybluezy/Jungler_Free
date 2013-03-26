package lol.jungler_free;

import java.util.ArrayList;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

public class MrWizard implements Runnable {
	
	private Thread runningThread;
	private GlobalClock globalClock;
	private Activity parent;
	private int parentIndex;
	
	private final String WOLF_LABEL = "Wolves";
	private final String WRAITH_LABEL = "Wraiths";
	private final String GOLEM_LABEL = "Golems";
	private final String BLUE_LABEL = "Blue Buff";
	private final String RED_LABEL = "Red Buff";
	private final String DRAGON_LABEL = "Dragon";
	private final String BARON_LABEL = "Baron Nashor";
	
	private static ArrayList<TextView> allyArray;
	private static ArrayList<TextView> enemyArray;
	
	private static ArrayList<Button> allyEnabler;
	private static ArrayList<Button> enemyEnabler;
	
	private static MrWizard instance = null;
	
	private static TextView globalTimer;
	
	private MrWizard() {
		globalClock = GlobalClock.getInstance();
		globalClock.start();
	}
	
	public static MrWizard getInstance() {
		return instance;
	}
	
	public static MrWizard getInstance(TextView global, Activity callingAct, int i) {
		if (instance == null) {
		   instance = new MrWizard();
		   allyArray = new ArrayList<TextView>();
		   enemyArray = new ArrayList<TextView>();
		   
		   allyEnabler = new ArrayList<Button>();
		   enemyEnabler = new ArrayList<Button>();
		}
		
		instance.parentIndex = i;
		
		loadState();
		globalTimer = global;
		instance.parent = callingAct;
		return instance;
	}

	public void start() {
		  if(runningThread == null) {
			  runningThread = new Thread(this);
			  runningThread.start();
			  }
		  }
	
	public void run() {
		while (runningThread == Thread.currentThread()) {
			parent.runOnUiThread(new Runnable() {
				
				public void run() {
					globalUpdate();
					updateTimers();
					saveState();
				}
			});
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
	
	private void globalUpdate() {
		globalTimer.setText(globalClock.print());
	}
	
	public static void loadState() {
		if (instance.parentIndex == 0) {
			if (!allyArray.isEmpty()) {
				AllyActivity.updateText(allyArray);
			}
			if (!allyEnabler.isEmpty()) {
				AllyActivity.updateEnabled(allyEnabler);
			}
		}
		else if (instance.parentIndex == 1){
			if (!enemyArray.isEmpty()) {
				EnemyActivity.updateText(enemyArray);
			}
			if (!enemyEnabler.isEmpty()) {
				EnemyActivity.updateEnabled(enemyEnabler);
			}
		}
	}
	
	public void saveState() {
		ArrayList<TextView> temp = new ArrayList<TextView>();
		ArrayList<Button> tempBool = new ArrayList<Button>();
		
		if (instance.parentIndex == 0) {
			temp = AllyActivity.getArrayCurrentState();
			tempBool = AllyActivity.getEnabledState();
			
			for (int i = 0; i < 6; i++) {
				if (allyArray.size() < (i+1))
					allyArray.add(temp.get(i));
				else
					allyArray.set(i, temp.get(i));
			}
			
			for (int i = 0; i < 6; i++) {
				if (allyEnabler.size() < (i+1))
					allyEnabler.add(tempBool.get(i));
				else
					allyEnabler.set(i, tempBool.get(i));
			}
		}
		else if (instance.parentIndex == 1){
			temp = EnemyActivity.getArrayCurrentState();
			tempBool = EnemyActivity.getEnabledState();
			
			for (int i = 0; i < 6; i++) {
				if (enemyArray.size() < (i+1))
					enemyArray.add(temp.get(i));
				else
					enemyArray.set(i, temp.get(i));
			}
			
			for (int i = 0; i < 6; i++) {
				if (enemyEnabler.size() < (i+1))
					enemyEnabler.add(tempBool.get(i));
				else
					enemyEnabler.set(i, tempBool.get(i));
			}
		}
	}
	
	public void reset() {
		allyArray.clear();
		enemyArray.clear();
		
		allyEnabler.clear();
		enemyEnabler.clear();
	}
	
	
	public void updateTimers() {
		for (int i = 0; i < allyArray.size(); i++) {
			if (isFinished(allyArray.get(i).getText().toString())) {
				switch (i) {
				case 0:
					allyArray.get(i).setText(WOLF_LABEL);
					break;
				case 1:
					allyArray.get(i).setText(WRAITH_LABEL);
					break;
				case 2:
					allyArray.get(i).setText(GOLEM_LABEL);
					break;
				case 3:
					allyArray.get(i).setText(BLUE_LABEL);
					break;
				case 4:
					allyArray.get(i).setText(RED_LABEL);
					break;
				case 5:
					allyArray.get(i).setText(DRAGON_LABEL);
					break;
				}
				allyEnabler.get(i).setEnabled(true);
			}
		}
		
		for (int i = 0; i < enemyArray.size(); i++) {
			if (isFinished(enemyArray.get(i).getText().toString())) {
				switch (i) {
				case 0:
					enemyArray.get(i).setText(WOLF_LABEL);
					break;
				case 1:
					enemyArray.get(i).setText(WRAITH_LABEL);
					break;
				case 2:
					enemyArray.get(i).setText(GOLEM_LABEL);
					break;
				case 3:
					enemyArray.get(i).setText(BLUE_LABEL);
					break;
				case 4:
					enemyArray.get(i).setText(RED_LABEL);
					break;
				case 5:
					enemyArray.get(i).setText(BARON_LABEL);
					break;
				}
				enemyEnabler.get(i).setEnabled(true);
			}
		}
		
		loadState();
	}
	
	private boolean isFinished(String clock) {
		if (!clock.contains(":"))
			return false;
		
		int global = (GlobalClock.getGameMinutes()*60) + GlobalClock.getGameSeconds();
		
		String minStr = clock.substring(0, clock.indexOf(':'));
		String secStr = clock.substring(clock.indexOf(':')+1, clock.length());
		
		int min = Integer.parseInt(minStr);
		int sec = Integer.parseInt(secStr);
		
		if(((min*60) + sec) <= global)
			return true;
		else
			return false;
	}

}
