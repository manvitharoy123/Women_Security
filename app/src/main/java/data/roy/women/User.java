package data.roy.women;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Inamul on 8/18/2017.
 */

public class User {
    private String user_Id;
    private String userName;
    private String userPhone;
    private String userGroup;

    public static final String USER_INFORMATION="UserInformation";
    public static final String USER_UID_SAVE_KEY = "SaveUid";
    public static final String TOKENS ="Tokens" ;
    public static final String FROM_NAME ="FromName" ;
    public static final String ACCEPT_LIST ="acceptList" ;
    public static final String FROM_UID = "FromUid";
    public static final String TO_UID ="ToUid" ;
    public static final String TO_NAME ="ToName" ;
    public static final String FRIEND_REQUEST = "FriendRequests";
    public static final String PUBLIC_LOCATION = "PublicLocation";
    public static User loggedUser;
    public static User trackingUser;
    public static User userProfile;

    public static Date convertTimeStampToDate(long time){

        return new Date(new Timestamp(time).getTime());


    }

    public static String getDateFormatted(Date date){

        return  new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(date).toString();
    }
    public User(){

    }


    public String getUid() {
        return user_Id;
    }

    public String getEmail() {
        return userName;
    }

    public User(String userName, String userGroup){
        this.userName = userName;

        this.userGroup = userGroup;

    }

    public User(String userName, String userPhone, String userGroup){
        this.userName = userName;
        this.userPhone = userPhone;
        this.userGroup = userGroup;

    }

    public String getUserName(){
        return userName;

    }

    public String getUserGroup(){
        return userGroup;

    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public  void  setUserPhone(String userPhone){
        this.userPhone = userPhone;
    }

    public  void  setUserGroup(String userGroup){
        this.userGroup = userGroup;
    }
}
