package edu.unsw.comp9321;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class RestCurationAPI {


		public String ExtractFeaturesRest(String url,String parameter,String tweet) throws ClientProtocolException, IOException
		{
			//////////////Extracting Named Entity/////////////////////
			HttpClient httpClient=new DefaultHttpClient();
			HttpPost postRequest=new HttpPost(url);
			List<NameValuePair> lstParameter=new ArrayList<NameValuePair>();
			lstParameter.add(new BasicNameValuePair(parameter,tweet));
			postRequest.setEntity(new UrlEncodedFormEntity(lstParameter));
			HttpResponse response= httpClient.execute(postRequest);
			HttpEntity entity = response.getEntity();
			if(response.getStatusLine().getStatusCode()!=200)
			{
				throw new RuntimeException("Faild: Http error Code: "+ response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(
	                new InputStreamReader((response.getEntity().getContent())));
			String output;
			String Val="";
			//System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				Val+=output;
			}
			httpClient.getConnectionManager().shutdown();
			return Val;
		}

}

