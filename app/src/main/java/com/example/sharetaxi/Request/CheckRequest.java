package com.example.sharetaxi.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CheckRequest extends StringRequest{

    final static private String URL = "http://borish3198.cafe24.com/Check.php";
    private Map<String, String> parameters;

    public CheckRequest(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String>getParams() {
        return parameters;
    }
}

