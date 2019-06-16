import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;


class ScoreCollectionTest {

    @Test
    void add() {

        ScoreCollection collection = new ScoreCollection();
        collection.add(()->5);

        assertThat(collection.getSize(),equalTo(1));

    }

    @Test
    void answersArithmeticMeanOfTwoNumbers() {
        //Arrange
        ScoreCollection collection = new ScoreCollection();
        collection.add(()->5);
        collection.add(()->7);
        //Act
        int actualResult = collection.arithmeticMean();
        //Assert
        assertThat(actualResult,equalTo(6));
    }
}