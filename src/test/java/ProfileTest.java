import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ProfileTest {

    private enum TestBoolean {
        TRUE(1),FALSE(0);
        private int value;

        TestBoolean(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private Profile profile;
    private Criteria criteria;

    private Question questionReimbursesTuition;
    private Answer answerReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;

    private Question questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNoRelocation;

    private Question questionOnsiteDaycare;
    private Answer answerNoOnsiteDaycare;
    private Answer answerHasOnsiteDaycare;

    @Before
    public void createProfile (){profile = new Profile("Bull Hockey, Inc.");}

    @Before
    public void createCriteria(){criteria = new Criteria();}



    @Before
    public void createQuestionsAndAnswers(){

        questionIsThereRelocation =
                new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation =
                new Answer(questionIsThereRelocation, TestBoolean.TRUE.getValue());
        answerThereIsNoRelocation =
                new Answer(questionIsThereRelocation, TestBoolean.FALSE.getValue());

        questionReimbursesTuition =
                new BooleanQuestion(1, "Reimburses tuition?");
        answerReimbursesTuition =
                new Answer(questionReimbursesTuition, TestBoolean.TRUE.getValue());
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, TestBoolean.FALSE.getValue());

        questionOnsiteDaycare =
                new BooleanQuestion(1, "Onsite daycare?");
        answerHasOnsiteDaycare =
                new Answer(questionOnsiteDaycare, TestBoolean.TRUE.getValue());
        answerNoOnsiteDaycare =
                new Answer(questionOnsiteDaycare, TestBoolean.FALSE.getValue());
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {

        profile.add(new Answer(questionIsThereRelocation, 0));
        criteria.add(new Criterion(new Answer(questionIsThereRelocation, 1), Weight.MustMatch));

        boolean match = profile.matches(criteria);

        assertFalse(match);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        //Arrange
        profile.add(new Answer(questionIsThereRelocation, 0));
        criteria.add(new Criterion(new Answer(questionIsThereRelocation, 1), Weight.DontCare));

        //Act
        boolean matches = profile.matches(criteria);

        //Assert
        assertTrue(matches);

    }


    @Test
    public void scoreAccumulatesCriterionValuesForMatches() {
        profile.add(answerThereIsRelocation);
        profile.add(answerReimbursesTuition);
        profile.add(answerNoOnsiteDaycare);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.WouldPrefer));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));
        criteria.add(new Criterion(answerHasOnsiteDaycare, Weight.VeryImportant));

        profile.matches(criteria);

        int expectedScore = Weight.WouldPrefer.getValue() + Weight.Important.getValue();
        assertThat(profile.score(), equalTo(expectedScore));
    }

    @Test
    public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition);
        profile.add(answerNoOnsiteDaycare);
        criteria.add(new Criterion(answerReimbursesTuition,Weight.VeryImportant));
        criteria.add(new Criterion(answerHasOnsiteDaycare, Weight.Important));

        boolean result = profile.matches(criteria);

        assertFalse(result);
    }
}
