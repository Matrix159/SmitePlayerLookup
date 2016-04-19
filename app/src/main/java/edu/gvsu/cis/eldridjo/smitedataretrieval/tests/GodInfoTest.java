package edu.gvsu.cis.eldridjo.smitedataretrieval.tests;
import org.junit.Test;

import java.util.List;

import edu.gvsu.cis.eldridjo.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo.GodInfo;

import static org.junit.Assert.assertTrue;

public class GodInfoTest {

    @Test
    public void testEverythingAboutGodInfo() throws Exception{
        SmiteMaster smiteMaster = new SmiteMaster();
        List<GodInfo> list = smiteMaster.getGods(1);

        for(GodInfo x : list){
            //abilities for gods
            assertTrue("Ability 1 description isn't null", x.getAbility1() != null);
            assertTrue("Ability 2 description isn't null", x.getAbility2() != null);
            assertTrue("Ability 3 description isn't null", x.getAbility3() != null);
            assertTrue("Ability 4 description isn't null", x.getAbility4() != null);
            assertTrue("Ability 5 description isn't null", x.getAbility5() != null);

            assertTrue("Ability 1 returns not null (an actual ability)", x.getAbility1() != null);
            assertTrue("Ability 2 returns not null (an actual ability)", x.getAbility2() != null);
            assertTrue("Ability 3 returns not null (an actual ability)", x.getAbility3() != null);
            assertTrue("Ability 4 returns not null (an actual ability)", x.getAbility4() != null);
            assertTrue("Ability 5 returns not null (an actual ability)", x.getAbility5() != null);

            assertTrue("Ability 1 returns not null (an actual description)", x.getAbilityDescription1() != null);
            assertTrue("Ability 2 returns not null (an actual description)", x.getAbilityDescription2() != null);
            assertTrue("Ability 3 returns not null (an actual description)", x.getAbilityDescription3() != null);
            assertTrue("Ability 4 returns not null (an actual description)", x.getAbilityDescription4() != null);
            assertTrue("Ability 5 returns not null (an actual description)", x.getAbilityDescription5() != null);

            assertTrue("Ability 1's ID is greater than zero (which would be invalid)", x.getAbilityId1() >= 0);
            assertTrue("Ability 2's ID is greater than zero (which would be invalid)", x.getAbilityId2() >= 0);
            assertTrue("Ability 3's ID is greater than zero (which would be invalid)", x.getAbilityId3() >= 0);
            assertTrue("Ability 4's ID is greater than zero (which would be invalid)", x.getAbilityId4() >= 0);
            assertTrue("Ability 5's ID is greater than zero (which would be invalid)", x.getAbilityId5() >= 0);

            assertTrue("Ability 1's URL for image isn't null", x.getGodAbility1_URL() != null);
            assertTrue("Ability 2's URL for image isn't null", x.getGodAbility2_URL() != null);
            assertTrue("Ability 3's URL for image isn't null", x.getGodAbility3_URL() != null);
            assertTrue("Ability 4's URL for image isn't null", x.getGodAbility4_URL() != null);
            assertTrue("Ability 5's URL for image isn't null", x.getGodAbility5_URL() != null);

            assertTrue("God's attack speed is a valid int between 0 and 2.5",x.getAttackSpeed() > 0.0 && x.getAttackSpeed() < 2.5);
            assertTrue("God gains positive attack speed per level up",x.getAttackSpeedPerLevel() >= 0);
            assertTrue("The Object for the god's attack isn't null",x.getBasicAttack() != null);
            assertTrue("The cons of the god doesn't return null",x.getCons() != null);
            assertTrue("The URL for the god picture isn't null",x.getGodCard_URL() != null);

            assertTrue("The URL for the gof icon image isn't null",x.getGodIcon_URL() != null);
            assertTrue("The health of the god is a valid int between 0 and 10_000",x.getHealth() >= 0 && x.getHealth() < 10_000);
            assertTrue("The god's health per five seconds is always staying the same of increasing",x.getHealthPerFive() >= 0);
            assertTrue("The god's hp5 gain per level is greater than, or equal to, 0",x.getHP5PerLevel() >= 0);
            assertTrue("The ID of the god is a valid int greater than 0",x.getId() >= 0);

            assertTrue("Object for the most recent god isn't null",x.getLatestGod() != null);
            assertTrue("String for the lore of the god isn't null",x.getLore() != null);
            assertTrue("Magical power is valid by being greater than, or equal to zero",x.getMagicalPower() >= 0);
            assertTrue("Magical power per level is valid by being greater than, or equal to zero",x.getMagicalPowerPerLevel() >= 0);
            assertTrue("Magical protections is valid by being greater than, or equal to zero",x.getMagicProtection() >= 0);

            assertTrue("Magical protections per level is valid by being greater than, or equal to zero",x.getMagicalPowerPerLevel() >= 0);
            assertTrue("God's mana is valid int by being greater than, or equal to zero",x.getMana() >= 0);
            assertTrue("Mana per five seconds is valid int by being greater than, or equal to, zero",x.getManaPerFive() >= 0);
            assertTrue("Mana per level is valid int by being greater than, or equal to, zero",x.getManaPerLevel() >= 0);
            assertTrue("MP5 per level is valid int by being greater than, or equal to, zero",x.getMP5PerLevel() >= 0);
            assertTrue("String for the god's name isn't null",x.getName() != null);

            assertTrue("String for god's on free rotation isn't null",x.getOnFreeRotation() != null );
            assertTrue("String containing the god's pantheon isn't null",x.getPantheon() != null);
            assertTrue("Physical power is valid int by being greater than, or equal to, zero",x.getPhysicalPower() >= 0);
            assertTrue("Physical power per level is valid int by being greater than, or equal to, zero",x.getPhysicalPowerPerLevel() >= 0);
            assertTrue("Physical protections is valid int by being greater than, or equal to, zero",x.getPhysicalProtection() >= 0);
            assertTrue("Physical protections per level is valid int by being greater than, or equal to, zero",x.getPhysicalProtectionPerLevel() >= 0);

            assertTrue("String containing pros of god isn't null",x.getPros() != null);
            assertTrue("String containing ret message isn't null",x.getRet_msg() != null);
            assertTrue("String containing roles list isn't null",x.getRoles() != null);
            assertTrue("Speed is valid int by being greater than, or equal to, zero",x.getSpeed() >= 0);
            assertTrue("String containing the god title isn't null",x.getTitle() != null);
            assertTrue("String containing god type isn't null",x.getType() != null);



        }
    }
}
