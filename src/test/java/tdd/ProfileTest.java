package tdd;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ProfileTest {
    @Test
    public void mathesNothingWhenProfileEmpty(){
        Profile profile = new Profile();
        Question question = new BooleanQuestion(1, "Relocation Package");
        Criterion criterion = new Criterion(new Answer(question, 1),Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }
}
