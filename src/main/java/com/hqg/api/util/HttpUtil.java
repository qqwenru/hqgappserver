package com.hqg.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	private static HttpURLConnection getPostHttpConn(String url) throws Exception{
		URL httpUrl = new URL(url);
		HttpURLConnection con = (HttpURLConnection)httpUrl.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        return con;
	}
	
	/**
	 * @Description http post 请求
	 * @param url
	 * @param data
	 * @date 2017年6月22日

	 * @since 1.0
	 * @version 1.0
	 * @throws Exception 
	 */
	public static String post(String url,String data){
		try {
			BufferedReader br = null;
			HttpURLConnection con = getPostHttpConn(url);
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "utf-8");
			osw.write(data);
			osw.flush();
			System.out.println("request data: "+ data);
			StringBuffer result = new StringBuffer();
			String s = null;
			if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
				while ((s=br.readLine()) != null){
					result.append(s);
				}
			}
			s = result.toString();
			br.close();
			osw.close();
			System.out.println("request data: "+ s);
			return s;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args){
		post("https://yun.tim.qq.com/v5/tlssmssvr/sendsms", "");
	}

	
}
