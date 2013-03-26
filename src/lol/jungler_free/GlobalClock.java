package lol.jungler_free;


public class GlobalClock implements Runnable{
 
 private Thread runningThread;
 private long currentTime, elapsedTime;
 private int gameMinutes, gameSeconds;
 
 private static GlobalClock instance = null;
 private static long START_TIME = System.currentTimeMillis();

 private GlobalClock() {
  gameMinutes = 0;
  gameSeconds = 0;
 }
 
 public static GlobalClock getInstance() {
  if (instance == null) {
   instance = new GlobalClock();
  }
  
  return instance;
 }
 
 public void start() {
  if(runningThread == null) {
	  runningThread = new Thread(this);
	  runningThread.start();
	  }
 }
 
 public void run(){
  while (runningThread == Thread.currentThread()) {
   updateTime();
   try {
    Thread.sleep(1000);
   } catch(InterruptedException e) {}
  }
 }
 
 private void updateTime() {
  currentTime = System.currentTimeMillis();
  elapsedTime = currentTime - START_TIME;
  gameSeconds = ((int)elapsedTime / 1000) % 60;
  gameMinutes = ((int)elapsedTime / 1000) / 60;
 }
 
 public static int getGameMinutes() {
  return instance.gameMinutes;
 }
 
 public static int getGameSeconds() {
  return instance.gameSeconds;
 }
 
 public void reset(){
	 START_TIME = System.currentTimeMillis();
 }
 
 public static String padZeroes(int n) {
   if (n < 10)
     return "0" + n;
   else
     return "" + n;
 }
 
 public String print() {
	   return (padZeroes(getGameMinutes()) + ":" + padZeroes(getGameSeconds()));
	 }
 
 public String toString() {
   return (padZeroes(getGameMinutes()) + ":" + padZeroes(getGameSeconds()));
 }

}
