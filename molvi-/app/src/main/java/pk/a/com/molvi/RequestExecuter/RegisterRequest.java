package pk.a.com.molvi.RequestExecuter;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WhySoSerious on 8/19/2016.
 */
public class RegisterRequest extends StringRequest {

  //  private static String RegisterUrl ="https://srdrop.azurewebsites.net/Account/Mobileregister";
    private Map<String,String> params;

    public RegisterRequest(String Email,String Password , String ConfirmPassword, Response.Listener<String> listener) {
        super(Method.POST, AppURLs.BaseUrl+AppURLs.RegisterUrl, listener, null);
        params = new HashMap<>();
        params.put("Email", Email);
        params.put("Password", Password);
        params.put("ConfirmPassword", ConfirmPassword);


    }
    @Override
    public Map<String,String> getParams()
    {
        return  params;
    }
}
