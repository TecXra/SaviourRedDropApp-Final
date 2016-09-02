package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;


import com.sun.jna.platform.win32.Netapi32Util;

import Models.BloodCity;
import Models.BloodGroup;
import Models.Cities;
import Models.SRUser;


public class RequestExecutor extends AsyncTask<Object, Object, Object> {
	public AsyncResponse delegate = null;
	public Context con;


	ArrayList<String> reviews = new ArrayList<>();

	ArrayList<BloodCity> bgcity = new ArrayList<BloodCity>();
	ArrayList<BloodGroup> blood = new ArrayList<BloodGroup>();
	ArrayList<Cities> city = new ArrayList<Cities>();



	ArrayList<SRUser> UserList = new ArrayList<SRUser>();
	public RequestExecutor(Context con) {
		super();
		this.con = con;
	}

	@Override
	protected void onPostExecute(Object result) {
		delegate.onProcessCompelete(result);
	};
	@Override
	protected Object doInBackground(Object... params) {
		//write logic here


		if (Utils.isNetworkAvailable(con)) {
			switch(params[0].toString()) {
				case "1": {
					try {
						return getAllUsers((String)params[1],(String)params[2]);


					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}

				case "2": {
					return getBgCities();


				}
				case "3": {
					try {
						return getUserReviews((String)params[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				case "4": {
					return Login((String)params[1],(String)params[2]);

				}



				default: {
					return null;
				}
			}

		}

		else {
			return null ;//"Network error";
		}
		return null ;//"Network error";
	}





	public Object Login(String Username,String Password)
	{

		String jsonString="";
		String User_Id = "";

		try {
			HttpClient httpclient = Utils.getClient();
			HttpPost httppost = new HttpPost(RgPreference.BaseUrl+RgPreference.LoginUser);

			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("Email", Username));
			formparams.add(new BasicNameValuePair("Password", Password));

			httppost.setEntity(new UrlEncodedFormEntity(formparams));

		//	httpclient.execute(httppost);
			HttpResponse response = httpclient.execute(httppost);

			jsonString = EntityUtils.toString(response.getEntity());


		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		return jsonString;




	}












	public Object getUserReviews(String Uid) throws IOException
	{

		HttpClient httpclient = Utils.getClient();

		HttpGet httpget = new HttpGet(RgPreference.BaseUrl + RgPreference.UserReview.replace("{Uid}",Uid));
		String jsonString = "";
		try {

			HttpResponse response = httpclient.execute(httpget);
			jsonString = EntityUtils.toString(response.getEntity());

			JSONArray jsonArray= new JSONArray(jsonString);

			for (int i = 0; i < jsonArray.length(); i++)
			{

				String FeedBack = jsonArray.getJSONObject(i).getString("FeedBack");



				reviews.add(FeedBack);


			}

		} catch (JSONException e) {
			e.printStackTrace();
		}


		return reviews;
	}




















	// get user list

	public Object getAllUsers(String Bid,String Cid) throws IOException
	{

		HttpClient httpclient = Utils.getClient();

		HttpGet httpget = new HttpGet(RgPreference.BaseUrl + RgPreference.allUser.replace("{Cid}",Cid).replace("{Bgid}",Bid));

		String jsonString = "";
		try {

			HttpResponse response = httpclient.execute(httpget);
			jsonString = EntityUtils.toString(response.getEntity());

				JSONArray jsonArray= new JSONArray(jsonString);

			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject bObject= jsonArray.getJSONObject(i).getJSONObject("BloodGroup");

				String Blood = bObject.getString("BloodGroupName");

				JSONObject cObject= jsonArray.getJSONObject(i).getJSONObject("City");
				String city = cObject.getString("CityName");


					String Id="" + jsonArray.getJSONObject(i).getInt("Id");
					String Name = jsonArray.getJSONObject(i).getString("Name");
					String Number =jsonArray.getJSONObject(i).getString("PhoneNumber");

				UserList.add(new SRUser(Id,Name,Blood,city,Number));


			}

		} catch (JSONException e) {
			e.printStackTrace();
		}


		return UserList;
	}















	public Object getBgCities()
	{
		HttpClient httpclient = Utils.getClient();

		HttpGet httpget = new HttpGet(RgPreference.BaseUrl + RgPreference.bloodCity);    //"http://192.168.1.100/bus"

		String jsonString = "";
		try {

			HttpResponse response = httpclient.execute(httpget);
			jsonString = EntityUtils.toString(response.getEntity());

			JSONObject jsonObject = new JSONObject(jsonString);

			JSONArray bArray = jsonObject.getJSONArray("bg");



			for (int i = 0; i < bArray.length(); i++)
			{
				String bid,bname = null;

						bid=	"" + bArray.getJSONObject(i).getInt("Id");
						bname=bArray.getJSONObject(i).getString("BloodGroupName");

				blood.add(new BloodGroup(bid,bname));
			}

			JSONArray cArray = jsonObject.getJSONArray("city");



			for (int j = 0; j < cArray.length(); j++)
			{
				String cid,cname=null;

				cid=	"" + cArray.getJSONObject(j).getInt("Id");
				cname=cArray.getJSONObject(j).getString("CityName");


				city.add(new Cities(cid,cname));
			}


			bgcity.add(new BloodCity(blood,city));




		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return bgcity;
	}







}
