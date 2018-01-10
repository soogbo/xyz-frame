package xyz.java.main.http;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClientDemo {
    public static void main(String[] args) {
        postMethod();
    }
    
    public static void postMethod(){
        HttpMethod post = new PostMethod("http://www.baidu.com");
//        post.addRequestHeader("Authorization", "Basic c3VpeGluZGFpOjFxYXohQCMk");      
//        post.addRequestHeader("userId", String.valueOf(12345));
//        post.addRequestHeader("borrowId", String.valueOf(2345));

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        client.getHttpConnectionManager().getParams().setSoTimeout(5000);

        try {
            int status = client.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String response = post.getResponseBodyAsString();
                JSONObject json = JSON.parseObject(response);    
                System.out.println(json.toString());
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
