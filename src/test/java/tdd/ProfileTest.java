package tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    private Profile profile;
    private BooleanQuestion questionIsThereRelocation;
    private Answer answer;

    @Before
    public void createProfile() {
        profile = new Profile();
    }

    @Before
    public void createQuestionAndAnswer() {
        Question question = new BooleanQuestion(1, "Relocation Package");
        answer = new Answer(questionIsThereRelocation, 1);

    }


    @Test
    public void mathesNothingWhenProfileEmpty() {
        Criterion criterion = new Criterion(new Answer(questionIsThereRelocation, 1)
                , Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesWhenProfileContainsMatchingAnswer() {
        Criterion criterion = new Criterion(answer, Weight.Important);
        profile.add(answer);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }
}
