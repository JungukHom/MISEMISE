package com.kakao.kakaolink.v2.network;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.kakao.kakaolink.BuildConfig;
import com.kakao.kakaolink.internal.KakaoTalkLinkProtocol;
import com.kakao.kakaolink.v2.KakaoLinkTestHelper;
import com.kakao.kakaolink.v2.mocks.TestKakaoUtilService;
import com.kakao.test.common.KakaoTestCase;
import com.kakao.util.IConfiguration;
import com.kakao.util.KakaoUtilService;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.CommonProtocol;

import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author kevin.kang. Created on 2018. 1. 2..
 */
@Config(constants = BuildConfig.class, packageName = "com.kakao.kakaolink")
@RunWith(RobolectricTestRunner.class)
public class DefaultKakaoLinkCoreTest extends KakaoTestCase {
    private DefaultKakaoLinkCore core;
    private IConfiguration configuration;
    private Context context;

    @Override
    public void setup() {
        super.setup();
        KakaoUtilService utilService = new TestKakaoUtilService();
        core = new DefaultKakaoLinkCore(utilService);
        context = spy(RuntimeEnvironment.application.getApplicationContext());
        configuration = utilService.getAppConfiguration(context);
    }

    @Test
    public void kakaoLinkIntent() {
        Intent intent = core.kakaoLinkIntent(context, "app_key", KakaoLinkTestHelper.createKakaoLinkResponse("1234"));
        Uri uri = intent.getData();
        assertNotNull(uri);
        assertEquals(KakaoTalkLinkProtocol.LINK_SCHEME, uri.getScheme());
        assertEquals(KakaoTalkLinkProtocol.LINK_AUTHORITY, uri.getHost());
        assertEquals(KakaoTalkLinkProtocol.LINK_VERSION_40, uri.getQueryParameter(KakaoTalkLinkProtocol.LINKVER));
        assertEquals("app_key", uri.getQueryParameter(KakaoTalkLinkProtocol.APP_KEY));
        assertEquals(KakaoLinkTestHelper.createMockRequestConfiguration().getAppVer(), uri.getQueryParameter(KakaoTalkLinkProtocol.APP_VER));
        assertEquals("1234", uri.getQueryParameter(KakaoTalkLinkProtocol.TEMPLATE_ID));
        assertNotNull(uri.getQueryParameter(KakaoTalkLinkProtocol.TEMPLATE_ARGS));
        assertNotNull(uri.getQueryParameter(KakaoTalkLinkProtocol.TEMPLATE_JSON));
    }

    @Test
    public void kakaoLinkIntentWithLongUri() {
        try {
            core.kakaoLinkIntent(context, "app_key", KakaoLinkTestHelper.createLongKakaoLinkResponse("1234"));
            fail("Long uri should throw KakaoException with URI_LENGTH_EXCEEDED error type.");
        } catch (KakaoException e) {
            assertEquals(KakaoException.ErrorType.URI_LENGTH_EXCEEDED, e.getErrorType());
        }
    }

    @Test
    public void marketIntent() {
        Intent intent = core.kakaoTalkMarketIntent(context);
        Uri uri = intent.getData();
        assertNotNull(uri);
        assertEquals("market", uri.getScheme());
        assertEquals("details", uri.getHost());
        assertEquals("com.kakao.talk", uri.getQueryParameter("id"));
        String referrer = uri.getQueryParameter("referrer");
        assertNotNull(referrer);
        try {
            JSONObject referrerJson = new JSONObject(referrer);
            assertEquals(configuration.getKAHeader(), referrerJson.get(CommonProtocol.KA_HEADER_KEY));
            assertEquals(configuration.getAppKey(), referrerJson.get(KakaoTalkLinkProtocol.APP_KEY));
            assertEquals(configuration.getAppVer(), referrerJson.get(KakaoTalkLinkProtocol.APP_VER));
            assertEquals(configuration.getPackageName(), referrerJson.get(KakaoTalkLinkProtocol.APP_PACKAGE));
        } catch (JSONException e) {
            fail("There was an exception parsing market referrer.");
        }
    }
}
