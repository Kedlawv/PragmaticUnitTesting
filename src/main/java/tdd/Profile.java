package tdd;

public class Profile {
    private Answer answer;

    public boolean matches(Criterion criterion) {
        if(answer != null){
            return true;

        }

        return false;

    }

    public void add(Answer answer) {
        this.answer = answer;
    }
}
