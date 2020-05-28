package junit5testing.javajunit5testing.mathUtils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // only one instance will be created per class
class MathUtilsTest {

    // we have to initiate an instance of the class to be tested, auto wiring is not   going to work
    MathUtils mathUtils;
    boolean value = true;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    static void initAll() { // static is a mast
        System.out.println(" ====> before all ");
    }

    @BeforeEach
    void init(TestReporter passedTestReporter, TestInfo passedTestInfo) {

           this.testInfo = passedTestInfo;
           this.testReporter = passedTestReporter;


        mathUtils = new MathUtils();
        System.out.println(" ====> before each ");
    }


    @Test   // assumption  test
    void assumptionTest(){
        assumeTrue(value); // if the value is true , the test will continue , if not the test wont continue
        System.out.println(" == assumption test == ");
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    @Test
    @Disabled(" ==> this test is skipped ")
    void skippedTest() {
        // not executed
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    @DisplayName("==  testing test1  fn  ==  ")
    void test1(){
        System.out.println(" === test 1  ====");
    }



  // +++++++++++++++++++++++++++++++++ nested tests +++++++++++++++++++++++++++++++++
    @Nested
    @Tag("ADD")
    @DisplayName("==  testing adding  fns ==  ")
    class AddFn {

        @Test
        @Tag("ADD-POSITIVE")
        @DisplayName("==  testing add positives fn ==  ")
        void  addTowPositives(){

            // ************* just to show how testInfo and testReporter works *****************************************************
            System.out.println(" testInfo ====> " + "tag: "+ testInfo.getTags() + "test name: " + testInfo.getDisplayName());
            testReporter.publishEntry(" testReporter ====> " + " tag: "+ testInfo.getTags() + "test name: " + testInfo.getDisplayName() );
            // ********************************************************************************************************************

            assertEquals(6, mathUtils.add(2,4) , "the add method should add two positives numbers "); // 3rd param is reserved for test failure
            System.out.println(" === add positives test  ====");

        }

        @RepeatedTest(3) // to repeat the test 3 times
        @DisplayName("=== testing add negatives  fn ===")
        void addTwoNegatives(RepetitionInfo repetitionInfo){

            System.out.println(" current repetition =====> " + repetitionInfo.getCurrentRepetition());

            assertEquals(-6, mathUtils.add(-2,-4) , "the add method should add two negatives numbers "); // 3rd param is reserved for test failure
            System.out.println(" === add negatives test  ====");

        }
    }


    @Nested
    @Tag("MULTIPLY")
    @DisplayName(" === test multiplying fn ==== ")
    class Multiplying {

        @Test
        @DisplayName(" === test multiplying 2 positives fn ==== ")
        void multiplyingTowPositives(){
            assertEquals(8, mathUtils.multiply(2,4) , "the add method should add two negatives numbers "); // 3rd param is reserved for test failure
        };

        @Test
        @DisplayName(" === test multiplying via assertAll  fn ==== ")
        void multiplyingViaAssertAll(){
            assertAll(
                    ()->  assertEquals(8, mathUtils.multiply(2,4)),
                    ()->  assertEquals(8, mathUtils.multiply(-2,-4)),
                    ()->  assertEquals(0, mathUtils.multiply(2,0)),
                    ()->  assertEquals(8, mathUtils.multiply(2,4)),
                    ()->  assertEquals(4, mathUtils.multiply(2,2))
                    );
        };
    }







  // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Test
    @DisplayName("==  testing circleArea fn ==  ")
    void circleArea(){ ;
        assertEquals(314.1592653589793, mathUtils.circleArea(10), ()-> "it is supposed to give " + 314.1592653589793  ); // lambda is accepted to show messages
        System.out.println(" ===  circleArea  test  ====");
    }


    @Test // in this example we need to assert throwing an exception
    @DisplayName("==  testing divide  which cause an exception  ==  ")
    void divide(){ ;
        assertThrows(ArithmeticException.class,  () -> mathUtils.divide(2,0), " throw an exception error " );
        System.out.println(" ===  dividing   test which cause an exception  ====");
    }




    ///////////////////////////////////////
    //////////////////////////////////////////

    @AfterEach
    void tearDown() {
        System.out.println(" === after each  ====");
    }

    @AfterAll
    static void tearDownAll() { // static is a must
        System.out.println(" === after all  ====");
    }


}