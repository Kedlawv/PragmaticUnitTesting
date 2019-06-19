package tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    private Profile profile;

    @Before
    public void createProfile(){
        profile = new Profile();
    }

    @Test
    public void mathesNothingWhenProfileEmpty(){
        Question question = new BooleanQuestion(1, "Relocation Package");
        Criterion criterion = new Criterion(new Answer(question, 1),Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesWhenProfileContainsMatchingAnswer(){
        Question question = new BooleanQuestion(1, "Relocation Package");
        Answer answer = new Answer(question,1);
        Criterion criterion = new Criterion(answer,Weight.Important);
        profile.add(answer);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }
}
