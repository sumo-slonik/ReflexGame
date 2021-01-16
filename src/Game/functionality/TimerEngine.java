package Game.functionality;

import Game.gui.GameController;
import javafx.scene.control.TextField;

import java.util.Timer;
import java.util.TimerTask;

public class TimerEngine {
    Timer timer;
    public Float leftMilliSeconds;
    TextField timerField;
    TextField avgTime;
    float startTime;
    float timeSum = 0;
    int attempts = 0;
    GameController actualGame = null;
    Integer maxRounds = null;
    public TimerEngine(float seconds, TextField timerField, GameController actualGame, TextField avgTime,int maxRounds) {
        timer = new Timer();
        this.avgTime = avgTime;
        this.leftMilliSeconds = seconds * 1000;
        this.timerField = timerField;
        startTime = seconds;
        this.actualGame = actualGame;
        this.maxRounds = maxRounds;
    }
    public TimerEngine(float seconds, TextField timerField, GameController actualGame, TextField avgTime) {
        timer = new Timer();
        this.avgTime = avgTime;
        this.leftMilliSeconds = seconds * 1000;
        this.timerField = timerField;
        startTime = seconds;
        this.actualGame = actualGame;
    }
    public TimerEngine(float seconds, TextField timerField) {
        timer = new Timer();
        timer.schedule(new RemindTask(), (long) (500));
        this.leftMilliSeconds = seconds * 1000;
        this.timerField = timerField;
        startTime = seconds;
    }
    public TimerEngine(float seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), (long) (500));
        this.leftMilliSeconds = seconds * 1000;
        this.timerField = null;
        startTime = seconds;

    }

    class RemindTask extends TimerTask {
        public void run() {
            if (maxRounds == null || maxRounds > actualGame.getRounds()) {
                System.out.println(leftMilliSeconds);

                if (attempts > 0) {
                    avgTime.setText("" + (timeSum / attempts));
                }
                if (leftMilliSeconds <= 0) {
                    if (timerField != null) {
                        String seconds = ((Integer) (((Double) (Math.floor(leftMilliSeconds / 1000))).intValue())).toString();
                        String mSeconds = ((Integer) (((Double) (Math.floor(leftMilliSeconds % 1000))).intValue())).toString();
                        timerField.setText(seconds + "s" + " " + mSeconds + "ms");
                        if (actualGame != null) {
                            //System.out.println(actualGame.test());
                            actualGame.timePass();
                            int a = 2 + 2;
                            //makeNewTimer(startTime);
                        }
                    }
                    timer.cancel();
                } else {
                    leftMilliSeconds -= 500;
                    timer.schedule(new RemindTask(), (long) (500));
                    if (timerField != null) {
                        String seconds = ((Integer) (((Double) (Math.floor(leftMilliSeconds / 1000))).intValue())).toString();
                        String mSeconds = ((Integer) (((Double) (Math.floor(leftMilliSeconds % 1000))).intValue())).toString();
                        timerField.setText(seconds + "s" + " " + mSeconds + "ms");
                    }
                }
            }else
            {
                actualGame.makeWinnerGreen();
            }
        }
    }

    public void makeNewTimer(float seconds) {
        timer.cancel();
        timer = new Timer();
        timeSum += (startTime*1000-leftMilliSeconds)/1000;
        attempts+=1;
        timer.schedule(new RemindTask(), (long) (500));
        this.leftMilliSeconds = startTime * 1000;
    }
    public void makeNewTimerFail(float seconds) {
        timer.cancel();
        timer = new Timer();
        timer.schedule(new RemindTask(), (long) (500));
        this.leftMilliSeconds = startTime * 1000;
    }
    public void makeNewTimerReset(float seconds) {
        timer.cancel();
        timer = new Timer();
        timeSum = 0;
        attempts=0;
        timer.schedule(new RemindTask(), (long) (500));
        this.leftMilliSeconds = startTime * 1000;
    }
    public void resetTimer()
    {
        this.leftMilliSeconds = startTime * 1000;
        timer.schedule(new RemindTask(), (long) (500));
    }
    public void delTimer()
    {
        this.timer.cancel();
    }
    public void startTimer()
    {
        timer.schedule(new RemindTask(), (long) (500));
    }
    public Float getLeftMilliSeconds() {return this.leftMilliSeconds;}
    public static void main(String args[]) {
        new TimerEngine(5);
    }
    public float getTimePass() {return this.startTime*1000-this.leftMilliSeconds;}
}
