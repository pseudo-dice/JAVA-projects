package lab3;
import balloon1.Balloon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BalloonTests {

    // Test to verify that a newly constructed Balloon has radius zero
    @Test
    public void testNewBalloonHasRadiusZero() {
        Balloon balloon = new Balloon(5);
        assertEquals(0, balloon.getRadius());
    }

    // Test to verify that a newly constructed Balloon is not popped
    @Test
    public void testNewBalloonIsNotPopped() {
        Balloon balloon = new Balloon(5);
        assertFalse(balloon.isPopped());
    }
    //Test to verify calling blow(5) on a Balloon with maximum radius 10, the radius should be 5.
    @Test
    public void testBlowOnBalloonWithMaximumRadius() {
        Balloon balloon = new Balloon(10);
        balloon.blow(5);
        assertEquals(5, balloon.getRadius());
    }
    

    // Test to verify that popping the Balloon sets the popped flag to true
    @Test
    public void testPop() {
        Balloon balloon = new Balloon(5);
        balloon.pop();
        assertTrue(balloon.isPopped());
    }

    // Test to verify that blowing a popped Balloon does not change the radius
    @Test
    public void testBlowOnPoppedBalloon() {
        Balloon balloon = new Balloon(5);
        balloon.pop();
        balloon.blow(5);
        assertEquals(0, balloon.getRadius()); // Radius should remain 0
        assertTrue(balloon.isPopped());
    }
 // Test to verify that blowing a popped Balloon does not change the radius
    @Test
    public void testBlowOnPoppedBalloonsec() {
        Balloon balloon = new Balloon(12);
        balloon.blow(5);
        balloon.deflate();
        balloon.blow(7);
        balloon.pop();
        balloon.blow(10);
        assertEquals(0, balloon.getRadius()); // Radius should remain 0
        assertTrue(balloon.isPopped());
    }
    //Test to verify that a balloon with radius 0 can be popped without any effects
    @Test
    public void testBalloonWithRadiusZeroCanBePopped() {
        Balloon balloon = new Balloon(0);
        balloon.blow(10);
        balloon.pop();
        assertTrue(balloon.isPopped());
    }
 // Test to verify that deflating the Balloon sets the radius to zero and unpops it
    @Test
    public void testDeflate() {
        Balloon balloon = new Balloon(10);
        balloon.blow(5);
        balloon.deflate();
        assertEquals(0, balloon.getRadius());
        assertFalse(balloon.isPopped());
    }
    
    // Test to verify that blowing the Balloon at max radius sets the correct radius
    @Test
    public void testBlowAtMaxRadius() {
        Balloon balloon = new Balloon(10);
        balloon.blow(10);
        assertEquals(10, balloon.getRadius());
        assertFalse(balloon.isPopped());
    }
 // Test to verify that blowing the Balloon beyond max radius blows the balloon
    @Test
    public void testBlowBeyondMaxRadius() {
        Balloon balloon = new Balloon(10);
        balloon.blow(12);
        assertEquals(0, balloon.getRadius());
        }
 // Test to verify that blowing the Balloon within max radius sets the correct radius
    @Test
    public void testBlowWithinMaxRadius() {
        Balloon balloon = new Balloon(5);
        balloon.blow(5);
        assertEquals(5, balloon.getRadius());
    }

    // Test to verify that blowing the Balloon at max radius sets the correct radius
    @Test
    public void testBlowAtMaxRadiussec() {
        Balloon balloon = new Balloon(10);
        balloon.blow(10);
        assertEquals(10, balloon.getRadius());
    }
    //Test to verify that deflating the Balloon sets the radius to zero and unpops it
    @Test
    public void testDeflateblow() {
        Balloon balloon = new Balloon(12);
        balloon.blow(5);
        balloon.deflate();
        balloon.blow(10);
        balloon.deflate();
        assertEquals(0, balloon.getRadius());
        assertFalse(balloon.isPopped());
    }
    

}

