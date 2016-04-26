package edu.gvsu.cis.eldridjo.smitedataretrieval;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import edu.gvsu.cis.eldridjo.smitedataretrieval.connectioninfo.DataUsedInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.connectioninfo.SessionInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo.GodInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.iteminfo.ItemInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.matchinfo.MatchDetails;
import edu.gvsu.cis.eldridjo.smitedataretrieval.matchinfo.RecentMatch;
import edu.gvsu.cis.eldridjo.smitedataretrieval.playerinfo.FriendsInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.playerinfo.PlayerGodInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.playerinfo.PlayerInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.playerinfo.PlayerStatus;
import retrofit.RestAdapter;

/**
 * Created by Eldridge on 3/23/2016.
 */
public class SmiteMaster  {

    private static final String DEV_ID = "1621";
    private static final String AUTH_KEY = "C7674733395A4668B6A6E983865A9EDB";
    public static final int PC = 1, XBOX = 2, PS4 = 3;
    private String timestamp;
    Firebase myRef;
    String sessionId = null;
    SmiteApi service;
    public SmiteMaster(android.content.Context context, int type)
    {
        Firebase.setAndroidContext(context);

        if(type == PC)
        {
            myRef = new Firebase("https://matrixprogramming.firebaseio.com/sessioninfoPC/");
            myRef.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    sessionId = dataSnapshot.child("session_id").getValue().toString();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError)
                {
                    System.out.println("Firebase Error.");
                }
            });
            timestamp = newTimeStamp();
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.smitegame.com/smiteapi.svc")
                    .build();
            service = restAdapter.create(SmiteApi.class);
        }
        if(type == XBOX)
        {
            myRef = new Firebase("https://matrixprogramming.firebaseio.com/sessioninfoXBOX/");
            myRef.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    sessionId = dataSnapshot.child("session_id").getValue().toString();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError)
                {
                    System.out.println("Firebase Error.");
                }
            });
            timestamp = newTimeStamp();
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.xbox.smitegame.com/smiteapi.svc")
                    .build();
            service = restAdapter.create(SmiteApi.class);
        }
        if(type == PS4)
        {
            myRef = new Firebase("https://matrixprogramming.firebaseio.com/sessioninfoPS4/");
            myRef.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    sessionId = dataSnapshot.child("session_id").getValue().toString();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError)
                {
                    System.out.println("Firebase Error.");
                }
            });
            timestamp = newTimeStamp();
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.ps4.smitegame.com/smiteapi.svc")
                    .build();
            service = restAdapter.create(SmiteApi.class);
        }

    }
    public SmiteMaster()
    {
        myRef = new Firebase("https://matrixprogramming.firebaseio.com/sessioninfo/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sessionId = dataSnapshot.child("session_id").getValue().toString();
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Firebase Error.");
            }
        });
        timestamp =  newTimeStamp();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.smitegame.com/smiteapi.svc")
                .build();
        service = restAdapter.create(SmiteApi.class);

    }

    public String getSessionId()
    {
        return sessionId;
    }

    public SessionInfo createSession()
    {
        return service.createSession(DEV_ID, createSignature("createsession"), timestamp);
    }

    public String testSession()
    {
        return service.testSession(DEV_ID, createSignature("testsession"), sessionId, timestamp);
    }

    public List<DataUsedInfo> getDataUsed()
    {
        return service.getDataUsed(DEV_ID, createSignature("getdataused"), sessionId, timestamp);
    }

    public List<FriendsInfo> getFriends(String player)
    {
        return service.getFriends(DEV_ID, createSignature("getfriends"), sessionId, timestamp, player);
    }

    public List<PlayerGodInfo> getPlayerGodInfo(String player)
    {
        return service.getGodRanks(DEV_ID, createSignature("getgodranks"), sessionId, timestamp, player);
    }

    public List<GodInfo> getGods(int languageCode)
    {
        System.out.println("http://api.smitegame.com/smiteapi.svc/getgodsJson/" + DEV_ID+"/" + createSignature("getgods")+"/" + sessionId+"/" + timestamp+"/" + 1);
        return service.getGods(DEV_ID, createSignature("getgods"), sessionId, timestamp, languageCode);

    }

    public List<ItemInfo> getItems(int languageCode)
    {
        System.out.println("http://api.smitegame.com/smiteapi.svc/getitemsJson/" + DEV_ID+"/" + createSignature("getitems")+"/" + sessionId+"/" + timestamp+"/" + 1);
        return service.getItems(DEV_ID, createSignature("getitems"), sessionId, timestamp, languageCode);
    }

    public List<MatchDetails> getMatchDetails(int matchId)
    {
        return service.getMatchDetails(DEV_ID, createSignature("getmatchdetails"), sessionId, timestamp, matchId);
    }

    public List<RecentMatch> getMatchHistory(String player)
    {
        return service.getMatchHistory(DEV_ID, createSignature("getmatchhistory"), sessionId, timestamp, player);
    }

    public List<PlayerInfo> getPlayer(String player)
    {
        System.out.println("http://api.smitegame.com/smiteapi.svc/getplayerJson/" + DEV_ID+"/" + createSignature("getplayer")+"/" + sessionId+"/" + timestamp+"/" + player);
        System.out.println("http://api.xbox.smitegame.com/smiteapi.svc/getplayerJson/" + DEV_ID+"/" + createSignature("getplayer")+"/" + sessionId+"/" + timestamp+"/" + player);
        return service.getPlayer(DEV_ID, createSignature("getplayer"), sessionId, timestamp, player);
    }

    public List<PlayerStatus> getPlayerStatus(String player)
    {
        System.out.println("http://api.smitegame.com/smiteapi.svc/getplayerstatusJson/" + DEV_ID+"/" + createSignature("getplayerstatus")+"/" + sessionId+"/" + timestamp+"/" + player);
        return service.getPlayerStatus(DEV_ID, createSignature("getplayerstatus"), sessionId, timestamp, player);
    }


    private String newTimeStamp()
    {
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        timestamp =  dateFormat.format(new Date());
        return timestamp;
    }

    private String createSignature(String type)
    {

        String signature = getMD5Hash(DEV_ID + type + AUTH_KEY + timestamp);
        return signature;
    }

    private static String getMD5Hash(String input)
    {
        StringBuilder sb =  new StringBuilder();
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(input.getBytes("UTF-8"));
            for(byte b : thedigest) {
                sb.append(String.format("%02X", b).toLowerCase());
            }

        }
        catch(UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
        }
        catch(NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
