package pk.a.com.molvi.RequestExecuter;

/**
 * Created by WhySoSerious on 8/23/2016.
 */
public class AppURLs {
   // public static String URL = "https://srdrop.azurewebsites.net/Home/MobileCreateProfile";
 //  public static String RegisterUrl = "https://srdrop.azurewebsites.net/Account/Mobileregister";
   // public static final String BaseUrl="https://srdrop.azurewebsites.net/Account/Login";
//  public static final String allUser = "/Home/SearchUser?Area={Cid}&BGId={Bgid}&mobile=1";
    //   final String allUserr = String.format("https://srdrop.azurewebsites.net/Home/SearchUser?Area=%1$s&BGId=%2$s&mobile=%3$s",CityId,BloodId
    //   ,1);

   public static final String BaseUrl= "https://srdrop.azurewebsites.net";
    public static final String bloodCity = "/Home/AllList";
    public static String RegisterUrl = "/Account/Mobileregister";
    public static final String createProfile="/Home/MobileCreateProfile";
    public static final String UserReview = "/Home/SingleUseReviews/{Uid}";
    public static final String LoginUser = "/Account/MobileLogin";
    public static final String allUserr=  "/Home/SearchUser?Area=%1$s&BGId=%2$s&mobile=%3$s";



    public static final String UserId="";
}
