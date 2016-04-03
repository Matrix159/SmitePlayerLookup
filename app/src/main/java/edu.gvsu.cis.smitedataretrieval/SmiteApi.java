package edu.gvsu.cis.smitedataretrieval;

import edu.gvsu.cis.smitedataretrieval.connectioninfo.DataUsedInfo;
import edu.gvsu.cis.smitedataretrieval.connectioninfo.SessionInfo;
import edu.gvsu.cis.smitedataretrieval.godinfo.GodInfo;
import edu.gvsu.cis.smitedataretrieval.iteminfo.ItemInfo;
import edu.gvsu.cis.smitedataretrieval.matchinfo.MatchDetails;
import edu.gvsu.cis.smitedataretrieval.matchinfo.RecentMatch;
import edu.gvsu.cis.smitedataretrieval.playerinfo.FriendsInfo;
import edu.gvsu.cis.smitedataretrieval.playerinfo.PlayerGodInfo;
import edu.gvsu.cis.smitedataretrieval.playerinfo.PlayerInfo;
import edu.gvsu.cis.smitedataretrieval.playerinfo.PlayerStatus;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

/**
 * Created by Josh on 2/17/2016.
 */
public interface SmiteApi {

    /**
     * Creates a session for the smite API
     * @param devId
     * @param signature
     * @param timestamp
     * @return A SessionInfo object
     */
    @GET("/createsessionJson/{devId}/{signature}/{timestamp}")
    SessionInfo createSession(@Path("devId") String devId, @Path("signature") String signature, @Path("timestamp") String timestamp);

    /**
     * Tests the current session
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @return A string for the test results
     */
    @GET("/testsessionJson/{devId}/{signature}/{sessionId}/{timestamp}")
    String testSession(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId, @Path("timestamp") String timestamp);

    /**
     * Get data usage
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @return An object describing data usage
     */
    @GET("/getdatausedJson/{devId}/{signature}/{sessionId}/{timestamp}")
    List<DataUsedInfo> getDataUsed(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId, @Path("timestamp") String timestamp);

    /**
     * Retrieves friend info for a given player
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @param playerName
     * @return A list for friend info
     */
    @GET("/getfriendsJson/{devId}/{signature}/{sessionId}/{timestamp}/{playerName}")
    List<FriendsInfo> getFriends(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId,
                                 @Path("timestamp") String timestamp,
                                 @Path("playerName") String playerName);

    /**
     * Retrieves god information about a given player
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @param playerName
     * @return A list of player god info
     */
    @GET("/getgodranksJson/{devId}/{signature}/{sessionId}/{timestamp}/{playerName}")
    List<PlayerGodInfo> getGodRanks(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId,
                                    @Path("timestamp") String timestamp,
                                    @Path("playerName") String playerName);

    /**
     * Retrievs information about Gods
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @param languageCode
     * @return A list of god info
     */
    @GET("/getgodsJson/{devId}/{signature}/{sessionId}/{timestamp}/{languageCode}")
    List<GodInfo> getGods(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId,
                          @Path("timestamp") String timestamp,
                          @Path("languageCode") int languageCode);

    /**
     * Retrieves information about items
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @param languageCode
     * @return A list of item info
     */
    @GET("/getitemsJson/{devId}/{signature}/{sessionId}/{timestamp}/{languageCode}")
    List<ItemInfo> getItems(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId,
                            @Path("timestamp") String timestamp,
                            @Path("languageCode") int languageCode);

    /**
     * Retrieves information about a given match
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @param match_id
     * @return A list of match details
     */
    @GET("/getmatchdetailsJson/{devId}/{signature}/{sessionId}/{timestamp}/{match_id}")
    List<MatchDetails> getMatchDetails(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId,
                                       @Path("timestamp") String timestamp,
                                       @Path("match_id") int match_id);

    /**
     * Retrieves recent match history of a player
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @param playerName
     * @return A list of Recent Matches
     */
    @GET("/getmatchhistoryJson/{devId}/{signature}/{sessionId}/{timestamp}/{playerName}")
    List<RecentMatch> getMatchHistory(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId,
                                      @Path("timestamp") String timestamp,
                                      @Path("playerName") String playerName);

    /**
     * Retrieves player info
     * @param devId
     * @param signature
     * @param sessionId
     * @param timestamp
     * @param playerName
     * @return A list for each player
     */
    @GET("/getplayerJson/{devId}/{signature}/{sessionId}/{timestamp}/{playerName}")
    List<PlayerInfo> getPlayer(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId,
                               @Path("timestamp") String timestamp,
                               @Path("playerName") String playerName);

    @GET("/getplayerstatusJson/{devId}/{signature}/{sessionId}/{timestamp}/{playerName}")
    List<PlayerStatus> getPlayerStatus(@Path("devId") String devId, @Path("signature") String signature, @Path("sessionId") String sessionId,
                                       @Path("timestamp") String timestamp,
                                       @Path("playerName") String playerName);




}
