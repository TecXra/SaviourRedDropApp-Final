package pk.a.com.molvi.RequestExecuter;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mian Mohsin on 8/26/2016.
 */
public class LoginRequest extends StringRequest {
    private Map<String,String> params;
    public LoginRequest(String Email,String Password, Response.Listener<String> listener) {
        super(Method.POST, AppURLs.BaseUrl+AppURLs.LoginUser, listener, null);
        params= new HashMap<>();
        params.put("Email",Email);
        params.put("Password",Password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

