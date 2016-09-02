package pk.a.com.molvi.RequestExecuter;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WhySoSerious on 8/24/2016.
 */
public class ProfileRequest extends StringRequest {
    private Map<String,String> params;

    public ProfileRequest(String Name,String BGId,String Area ,String PhoneNumber, String appUserId, Response.Listener<String> listener) {
        super(Method.POST, AppURLs.BaseUrl+AppURLs.createProfile, listener,null);
        params = new HashMap<>();
        params.put("Name", Name);
        params.put("BGId", BGId);
        params.put("Area", Area);
        params.put("PhoneNumber",PhoneNumber);
        params.put("appUserId",appUserId);


    }

    @Override
    public Map<String,String> getParams()
    {
        return  params;
    }
}
