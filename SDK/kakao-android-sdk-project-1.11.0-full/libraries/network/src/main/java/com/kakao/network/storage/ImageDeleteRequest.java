package com.kakao.network.storage;

import android.net.Uri;

import com.kakao.network.ApiRequest;
import com.kakao.network.ServerProtocol;
import com.kakao.util.IConfiguration;

/**
 * @author kevin.kang. Created on 2017. 3. 20..
 */

public class ImageDeleteRequest extends ApiRequest {
    private String imageUrl;
    private String imageToken;
    protected final String IMAGE_URL = "image_url";
    protected final String IMAGE_TOKEN = "image_token";

    public ImageDeleteRequest(IConfiguration configuration, final String imageUrl, final String imageToken) {
        super(configuration);
        this.imageUrl = imageUrl;
        this.imageToken = imageToken;
    }

    @Override
    public String getMethod() {
        return DELETE;
    }

    @Override
    public Uri.Builder getUriBuilder() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(ServerProtocol.SCHEME);
        builder.authority(ServerProtocol.API_AUTHORITY);
        if (imageUrl != null) {
            builder.appendQueryParameter(IMAGE_URL, imageUrl);
        }
        if (imageToken != null) {
            builder.appendQueryParameter(IMAGE_TOKEN, imageToken);
        }
        return builder;
    }
}
