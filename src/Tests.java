import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;

public class Tests {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        String content = "This is a \"test\" string (which is a bit com-\nplex).";
        System.out.println(Synalogic.results(Synalogic.preprocess(content)));
        Assert.assertEquals("Word count=10\n"+
                "Average word length=3.5\n"+
                "Number of words of length 1 is 2\n"+
                "Number of words of length 2 is 2\n"+
                "Number of words of length 3 is 1\n"+
                "Number of words of length 4 is 2\n"+
                "Number of words of length 5 is 1\n"+
                "Number of words of length 6 is 1\n"+
                "Number of words of length 7 is 1\n"+
                "The most frequently occuring word length is 2, for word lengths of 1 & 2 & 4\n",Synalogic.results(Synalogic.preprocess(content)));
    }
    @Test
    public void test2() {
        String content = "Hello world & good morning. The date is 18/05/2016";
        System.out.println(Synalogic.results(Synalogic.preprocess(content)));
        Assert.assertEquals("Word count=9\n"+
                "Average word length=4.555555555555555\n"+
                "Number of words of length 1 is 1\n"+
                "Number of words of length 2 is 1\n"+
                "Number of words of length 3 is 1\n"+
                "Number of words of length 4 is 2\n"+
                "Number of words of length 5 is 2\n"+
                "Number of words of length 7 is 1\n"+
                "Number of words of length 10 is 1\n"+
                "The most frequently occuring word length is 2, for word lengths of 4 & 5\n",Synalogic.results(Synalogic.preprocess(content)));
    }

}
