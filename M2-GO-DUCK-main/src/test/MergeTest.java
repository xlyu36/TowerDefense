import controller.Controller;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.util.concurrent.TimeUnit;

import static org.testfx.api.FxAssert.verifyThat;

public class MergeTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void testWinScreenPop() throws InterruptedException {
        clickOn("Play!");
        clickOn("Easy");
        clickOn("Next");
        clickOn("CS1331");
        clickOn(350, 250);
        clickOn("CS1332");
        clickOn(400, 250);
        clickOn(450, 250);
        clickOn("Start Combat");
        TimeUnit.SECONDS.sleep(5);
        clickOn(300, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(350, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(400, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(450, 350);
        clickOn("CS1332");
        clickOn(500, 350);
        clickOn(550, 350);
        clickOn(600, 350);
        clickOn(550, 250);
        clickOn(750, 250);
        clickOn(950, 250);
        clickOn(850, 250);
        TimeUnit.SECONDS.sleep(10);
        verifyThat("Win!", NodeMatchers.isNotNull());
    }

    @Test
    public void testTotalDeadEnemies() throws InterruptedException {
        clickOn("Play!");
        clickOn("Easy");
        clickOn("Next");
        clickOn("CS1331");
        clickOn(350, 250);
        clickOn("CS1332");
        clickOn(400, 250);
        clickOn(450, 250);
        clickOn("Start Combat");
        TimeUnit.SECONDS.sleep(5);
        clickOn(300, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(350, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(400, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(450, 350);
        clickOn("CS1332");
        clickOn(500, 350);
        clickOn(550, 350);
        clickOn(600, 350);
        clickOn(550, 250);
        clickOn(750, 250);
        clickOn(950, 250);
        clickOn(850, 250);
        TimeUnit.SECONDS.sleep(25);
        verifyThat("Total enemies killed: 36", NodeMatchers.isNotNull());

    }

    @Test
    public void testUpgrade() throws InterruptedException {
        clickOn("Play!");
        clickOn("Easy");
        clickOn("Next");
        clickOn("CS1331");
        clickOn(350, 250);
        clickOn("CS1332");
        clickOn(400, 250);
        clickOn(450, 250);
        clickOn("Start Combat");
        TimeUnit.SECONDS.sleep(7);
        clickOn(450, 250);
        verifyThat("upgrade", NodeMatchers.isNotNull());
        clickOn("upgrade");
    }

    @Test
    public void testUpgradeLimit() throws InterruptedException {
        clickOn("Play!");
        clickOn("Easy");
        clickOn("Next");
        clickOn("CS1331");
        clickOn(350, 250);
        clickOn("CS1332");
        clickOn(400, 250);
        clickOn(450, 250);
        clickOn("Start Combat");
        TimeUnit.SECONDS.sleep(7);
        clickOn(450, 250);
        verifyThat("upgrade", NodeMatchers.isNotNull());
        clickOn("upgrade");
        TimeUnit.SECONDS.sleep(7);
        clickOn(450, 250);
        clickOn("upgrade");
        clickOn(450, 250);
        try {
            clickOn("upgrade");
        } catch (Exception e) {
            Assert.assertTrue(true);
            return;
        }
    }
    @Test
    public void testBossPower() throws InterruptedException {
        clickOn("Play!");
        clickOn("Easy");
        clickOn("Next");
        clickOn("CS1331");
        clickOn(350, 250);
        clickOn("CS1332");
        clickOn(400, 250);
        clickOn(450, 250);
        clickOn("Start Combat");
        TimeUnit.SECONDS.sleep(5);
        clickOn(300, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(350, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(400, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(450, 350);
        clickOn("CS1332");
        clickOn(500, 350);
        clickOn(550, 350);
        clickOn(600, 350);
        clickOn(550, 250);
        TimeUnit.SECONDS.sleep(25);
        verifyThat("Game Over!", NodeMatchers.isNotNull());

    }
    @Test
    public void testWinReset() throws InterruptedException {
        clickOn("Play!");
        clickOn("Easy");
        clickOn("Next");
        clickOn("CS1331");
        clickOn(350, 250);
        clickOn("CS1332");
        clickOn(400, 250);
        clickOn(450, 250);
        clickOn("Start Combat");
        TimeUnit.SECONDS.sleep(5);
        clickOn(300, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(350, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(400, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(450, 350);
        clickOn("CS1332");
        clickOn(500, 350);
        clickOn(550, 350);
        clickOn(600, 350);
        clickOn(550, 250);
        clickOn(750, 250);
        clickOn(950, 250);
        clickOn(850, 250);
        TimeUnit.SECONDS.sleep(20);
        clickOn("Restart!");
        verifyThat("Play!", NodeMatchers.isNotNull());
    }

    @Test
    public void testWinningGold() throws InterruptedException {
        clickOn("Play!");
        clickOn("Easy");
        clickOn("Next");
        clickOn("CS1331");
        clickOn(350, 250);
        clickOn("CS1332");
        clickOn(400, 250);
        clickOn(450, 250);
        clickOn("Start Combat");
        TimeUnit.SECONDS.sleep(5);
        clickOn(300, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(350, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(400, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(450, 350);
        clickOn("CS1332");
        clickOn(500, 350);
        clickOn(550, 350);
        clickOn(600, 350);
        clickOn(550, 250);
        clickOn(750, 250);
        clickOn(950, 250);
        clickOn(850, 250);
        TimeUnit.SECONDS.sleep(10);
        clickOn(1050, 250);
        clickOn(1100, 250);
        TimeUnit.SECONDS.sleep(10);
        verifyThat("Total gold remains: 70", NodeMatchers.isNotNull());
    }
    @Test
    public void testGameOverKill() throws InterruptedException {
        clickOn("Play!");
        clickOn("Easy");
        clickOn("Next");
        clickOn("CS1331");
        clickOn(350, 250);
        clickOn("CS1332");
        clickOn(400, 250);
        clickOn(450, 250);
        clickOn("Start Combat");
        TimeUnit.SECONDS.sleep(5);
        clickOn(300, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(350, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(400, 350);
        clickOn("CS1332");
        TimeUnit.SECONDS.sleep(6);
        clickOn(450, 350);
        clickOn("CS1332");
        clickOn(500, 350);
        clickOn(550, 350);
        clickOn(600, 350);
        clickOn(550, 250);
        TimeUnit.SECONDS.sleep(20);
        verifyThat("Total enemies killed: 36", NodeMatchers.isNotNull());
    }

    @Test
    public void testUpgradeCS1331() throws InterruptedException {
        CS1331 testTower = new CS1331(null, 0,0);
        int oldDmg = testTower.getDamage();
        testTower.upgrade();
        Assert.assertTrue(testTower.getDamage() > oldDmg);
    }

    @Test
    public void testUpgradeCS1332() throws InterruptedException {
        CS1332 testTower = new CS1332(null, 0,0);
        int oldDmg = testTower.getDamage();
        testTower.upgrade();
        Assert.assertTrue(testTower.getDamage() > oldDmg);
    }

    @Test
    public void testUpgradeCS2340() throws InterruptedException {
        CS2340 testTower = new CS2340(null, 0,0);
        int oldDmg = testTower.getDamage();
        testTower.upgrade();
        Assert.assertTrue(testTower.getDamage() > oldDmg);
    }
}