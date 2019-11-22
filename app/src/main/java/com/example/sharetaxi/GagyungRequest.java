package com.example.sharetaxi;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GagyungRequest extends JsonArrayRequest{

    final static private String URL = "http://borish3198.cafe24.com/Listview.php";
    private Map<String, String> parameters;

    public GagyungRequest(Response.Listener<JSONArray> listener){
        super(Method.POST, URL, null, listener, null);
        parameters = new HashMap<>();
    }

    @Override
    public Map<String, String>getParams() {
        return parameters;
    }
}
