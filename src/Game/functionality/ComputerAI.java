package Game.functionality;

import java.util.Random;

public class ComputerAI {
    float winPercent;
    float timeToMakeDecision;
    float maxTime;
    public ComputerAI (float winPercent,float timeToMakeDecision,float maxTime)

    {
        this.winPercent = winPercent;
        this.timeToMakeDecision = timeToMakeDecision;
        this.maxTime = maxTime;
    }
    public float computerMakeDecision()
    {
        return this.timeToMakeDecision;
    }
    public boolean isDecisionCorrect()
    {
        Random generator = new Random();
        int percent = generator.nextInt(100);
        System.out.println("_______________________");
        System.out.println("SZANSE");
        System.out.println(percent);
        System.out.println("_______________________");

        return percent <= winPercent;
    }
}
