package edu.gvsu.cis.eldridjo.smitedataretrieval.tests;


import org.junit.Test;
import java.util.List;
import edu.gvsu.cis.eldridjo.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.eldridjo.smitedataretrieval.playerinfo.PlayerInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.playerinfo.PlayerStatus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PlayerStatusTest {

    @Test
    public void test() throws Exception{
        SmiteMaster master = new SmiteMaster();
        List<PlayerStatus> list = master.getPlayerStatus("Scatmancon2");

        for(PlayerStatus x : list){
            assertTrue("", x.getStatus() >= 0 && x.getStatus() <= 5);
            assertFalse("", x.getMatch() < 0);
            assertTrue("", x.getPersonal_status_message() != null);
            assertTrue("", x.getStatus_string() != null);
            assertTrue("", x.getRet_msg() != null);

        }
    }



}
