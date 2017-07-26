package com.rdw.library.interfacemethods;

/**
 * Created by Trust on 20-Jun-17.
 */

public interface RwSuccessCallbackListener {
    void onSuccess(String response, int resultCode);
    void onFailure(String failure, int resultCode);
    void onStartRequest();
}
