package com.xrc.camera.api;

import com.xrc.camera.model.AECompensationInfo;
import com.xrc.camera.model.AECompensationValue;
import com.xrc.camera.model.AELockInfo;
import com.xrc.camera.model.AELockValue;
import com.xrc.camera.model.AEModeInfo;
import com.xrc.camera.model.AEModeValue;
import com.xrc.camera.model.FocusModeInfo;
import com.xrc.camera.model.FocusModeValue;
import com.xrc.camera.model.ISOInfo;
import com.xrc.camera.model.ISOValue;
import com.xrc.camera.model.Setting;
import com.xrc.camera.model.SettingInfo;
import com.xrc.camera.model.SettingValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("com.xrc.camera.api.DefaultApi")
public class DefaultApi {
    private ApiClient apiClient;

    @Autowired
    public DefaultApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get image.
     * Retrieve the current camera image.
     * <p><b>200</b> - Status 200
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public byte[] imageGet() throws RestClientException {
        Object postBody = null;
        String path = UriComponentsBuilder.fromPath("/image").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "image/jpeg"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<byte[]> returnType = new ParameterizedTypeReference<byte[]>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Status 200
     * <p><b>404</b> - The specified setting is not available.
     * @return AECompensationInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AECompensationInfo settingsAECOMPENSATIONGet() throws RestClientException {
        Object postBody = null;
        String path = UriComponentsBuilder.fromPath("/settings/AE_COMPENSATION").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<AECompensationInfo> returnType = new ParameterizedTypeReference<AECompensationInfo>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>204</b> - The setting has been successfully updated.
     * <p><b>400</b> - The value of the setting cannot be edited or invalid value provided.
     * <p><b>404</b> - The specified setting is not available.
     * @param body The body parameter
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void settingsAECOMPENSATIONPut(AECompensationValue body) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling settingsAECOMPENSATIONPut");
        }
        String path = UriComponentsBuilder.fromPath("/settings/AE_COMPENSATION").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {  };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Status 200
     * <p><b>404</b> - The specified setting is not available.
     * @return AELockInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AELockInfo settingsAELOCKGet() throws RestClientException {
        Object postBody = null;
        String path = UriComponentsBuilder.fromPath("/settings/AE_LOCK").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<AELockInfo> returnType = new ParameterizedTypeReference<AELockInfo>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>204</b> - The setting has been successfully updated.
     * <p><b>400</b> - The value of the setting cannot be edited or invalid value provided.
     * <p><b>404</b> - The specified setting is not available.
     * @param body The body parameter
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void settingsAELOCKPut(AELockValue body) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling settingsAELOCKPut");
        }
        String path = UriComponentsBuilder.fromPath("/settings/AE_LOCK").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {  };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     *
     *
     * <p><b>200</b> - Status 200
     * <p><b>404</b> - The specified setting is not available.
     * @return AEModeInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AEModeInfo settingsAEMODEGet() throws RestClientException {
        Object postBody = null;
        String path = UriComponentsBuilder.fromPath("/settings/AE_MODE").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<AEModeInfo> returnType = new ParameterizedTypeReference<AEModeInfo>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     *
     *
     * <p><b>204</b> - The setting has been successfully updated.
     * <p><b>400</b> - The value of the setting cannot be edited or invalid value provided.
     * <p><b>404</b> - The specified setting is not available.
     * @param body The body parameter
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void settingsAEMODEPut(AEModeValue body) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling settingsAEMODEPut");
        }
        String path = UriComponentsBuilder.fromPath("/settings/AE_MODE").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {  };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Status 200
     * <p><b>404</b> - The specified setting is not available.
     * @return FocusModeInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FocusModeInfo settingsFOCUSMODEGet() throws RestClientException {
        Object postBody = null;
        String path = UriComponentsBuilder.fromPath("/settings/FOCUS_MODE").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<FocusModeInfo> returnType = new ParameterizedTypeReference<FocusModeInfo>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>204</b> - The setting has been successfully updated.
     * <p><b>400</b> - The value of the setting cannot be edited or invalid value provided.
     * <p><b>404</b> - The specified setting is not available.
     * @param body The body parameter
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void settingsFOCUSMODEPut(FocusModeValue body) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling settingsFOCUSMODEPut");
        }
        String path = UriComponentsBuilder.fromPath("/settings/FOCUS_MODE").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {  };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get settings.
     * Retrieve a set of the exposed settings of the camera.
     * <p><b>200</b> - Status 200
     * @return java.util.List&lt;Setting&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public java.util.List<Setting> settingsGet() throws RestClientException {
        Object postBody = null;
        String path = UriComponentsBuilder.fromPath("/settings").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<java.util.List<Setting>> returnType = new ParameterizedTypeReference<java.util.List<Setting>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Status 200
     * <p><b>404</b> - The specified setting is not available.
     * @return ISOInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ISOInfo settingsISOGet() throws RestClientException {
        Object postBody = null;
        String path = UriComponentsBuilder.fromPath("/settings/ISO").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ISOInfo> returnType = new ParameterizedTypeReference<ISOInfo>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>204</b> - The setting has been successfully updated.
     * <p><b>400</b> - The value of the setting cannot be edited or invalid value provided.
     * <p><b>404</b> - The specified setting is not available.
     * @param body The body parameter
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void settingsISOPut(ISOValue body) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling settingsISOPut");
        }
        String path = UriComponentsBuilder.fromPath("/settings/ISO").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {  };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get setting info.
     * Retrieve information about the specified setting.
     * <p><b>200</b> - Status 200
     * <p><b>404</b> - The specified setting is not available.
     * @param setting The setting parameter
     * @return SettingInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SettingInfo settingsSettingGet(Setting setting) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'setting' is set
        if (setting == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'setting' when calling settingsSettingGet");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("setting", setting);
        String path = UriComponentsBuilder.fromPath("/settings/{setting}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<SettingInfo> returnType = new ParameterizedTypeReference<SettingInfo>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Set setting value.
     * Update the value of the specified setting. Refer to /settings/{setting} for the valid values of the specified setting. 
     * <p><b>204</b> - The setting value has been successfully updated.
     * <p><b>400</b> - The value of the setting cannot be edited or invalid value provided.
     * <p><b>404</b> - The specified setting is not available.
     * @param body The body parameter
     * @param setting The setting parameter
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void settingsSettingPut(SettingValue body, Setting setting) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling settingsSettingPut");
        }
        // verify the required parameter 'setting' is set
        if (setting == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'setting' when calling settingsSettingPut");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("setting", setting);
        String path = UriComponentsBuilder.fromPath("/settings/{setting}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {  };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
