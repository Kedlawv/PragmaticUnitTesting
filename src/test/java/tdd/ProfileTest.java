package tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    private Profile profile;
    private BooleanQuestion questionIsThereRelocation;
    private BooleanQuestion questionDoesReimburseTuition;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNotRelocation;
    private Answer answerDoesNotReimburseTuition;
    private Answer answerDoesReimburseTuition;


    @Before
    public void createProfile() {
        profile = new Profile();
    }

    @Before
    public void createQuestionAndAnswer() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation Package");
        questionDoesReimburseTuition = new BooleanQuestion(1, "Reimburse Tuition");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, 1);
        answerThereIsNotRelocation = new Answer(questionIsThereRelocation, 0);
        answerDoesReimburseTuition = new Answer(questionDoesReimburseTuition,1);
        answerDoesNotReimburseTuition = new Answer(questionDoesReimburseTuition,0);

    }


    @Test
    public void matchesNothingWhenProfileEmpty() {
        Criterion criterion = new Criterion(new Answer(questionIsThereRelocation, 1)
                , Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesWhenProfileContainsMatchingAnswer() {
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
        profile.add(answerThereIsRelocation);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

    @Test
    public void doesNotMatchWhenNoMatchingAnswer(){
        profile.add(answerThereIsNotRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertFalse(result);

    }

    @Test
    public void matchesWhenContainsMultipleAnswers(){
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);

        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }
}
