package com.rdw.library.network;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.rdw.library.interfacemethods.RwSuccessCallbackListener;
import com.rdw.library.logs.RwLog;
import com.rdw.library.utils.RwUtils;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RwVolleyRequestCall {
    Context context;

    public RwVolleyRequestCall(Context context) {
        this.context = context;
    }

    public void postRequestCall(final String TAG, String strUrl, final Map<String, String> headers, final String mRequestBody, final int resultCode, final RwSuccessCallbackListener callbackListener) {

        RwLog.LogE(TAG, "post strUrl:" + strUrl);
        callbackListener.onStartRequest();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, strUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RwLog.LogE(TAG, "response:" + response);
                try {
                    callbackListener.onSuccess(response, resultCode);
                } catch (Exception e) {
                    e.printStackTrace();
                    RwUtils.messageLong(context, e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                error.printStackTrace();
                RwLog.LogE(TAG, "error" + error.getMessage());
                callbackListener.onFailure(error.getMessage(), resultCode);
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(TAG);
        RwVolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

    public void getRequestCall(final String TAG, String strUrl, final Map<String, String> headers, final int resultCode, final RwSuccessCallbackListener callbackListener) {

        RwLog.LogE(TAG, "get strUrl:" + strUrl);
        callbackListener.onStartRequest();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, strUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RwLog.LogE(TAG, "get response:" + response);
                try {
                    callbackListener.onSuccess(response, resultCode);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                RwLog.LogE(TAG, "error" + error.getMessage());
                callbackListener.onFailure(error.getMessage(), resultCode);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(TAG);
        // Add the request to the RequestQueue.
        RwVolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }


    /*public void putRequestCall(final String TAG, String strUrl, final RwSuccessCallbackListener callbackListener) {

        Log.v(TAG, "put strUrl:" + strUrl);
        callbackListener.onStartRequest();

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, strUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Do something with the response
                RwLog.LogE(TAG, "Put response:" + response);
                callbackListener.onSuccess(response,resultCode);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                RwLog.LogE(TAG, "error" + error.getMessage());
                callbackListener.onFailure(error.getMessage(),resultCode);
            }
        });
        stringRequest.setTag(TAG);
        // Add the request to the RequestQueue.
        RwVolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

    public void deleteRequestCall(final String TAG, String strUrl, final RwSuccessCallbackListener callbackListener) {

        Log.v(TAG, "delete strUrl:" + strUrl);
        callbackListener.onStartRequest();
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, strUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Do something with the response
                RwLog.LogE(TAG, "delete response:" + response);
                callbackListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                RwLog.LogE(TAG, "error" + error.getMessage());
                callbackListener.onFailure(error.getMessage());
            }
        });
        stringRequest.setTag(TAG);
        // Add the request to the RequestQueue.
        RwVolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

    public void jsonPostRequestCall(final String TAG, String strUrl, final RwSuccessCallbackListener callbackListener) {

        Log.v(TAG, "delete strUrl:" + strUrl);
        callbackListener.onStartRequest();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                strUrl, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with the response
                        RwLog.LogE(TAG, "delete response:" + response);
                        callbackListener.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                RwLog.LogE(TAG, "error" + error.getMessage());
                callbackListener.onFailure(error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "rahul");
                params.put("email", "rahul@gmail.com");
                params.put("password", "tete");

                return params;
            }

        };
        jsonObjReq.addMarker(TAG);

        RwVolleySingleton.getInstance(context).getRequestQueue().add(jsonObjReq);
    }*/
}