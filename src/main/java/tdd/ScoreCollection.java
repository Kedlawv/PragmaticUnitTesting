package tdd;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {
    private List<Scoreable> score = new ArrayList<>();

    public void add(Scoreable scoreable){
        score.add(scoreable);
    }

    public int arithmeticMean(){
        int total = score.stream().mapToInt(Scoreable::getScore).sum();
        return total/score.size();
    }

    public int getSize(){
        return score.size();
    }
}
