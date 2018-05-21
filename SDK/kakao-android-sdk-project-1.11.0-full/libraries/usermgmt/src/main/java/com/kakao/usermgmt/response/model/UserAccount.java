package com.kakao.usermgmt.response.model;

import android.app.Activity;

import com.kakao.auth.AccessTokenCallback;
import com.kakao.network.response.ResponseBody;
import com.kakao.usermgmt.StringSet;
import com.kakao.util.OptionalBoolean;

import org.json.JSONObject;

import java.util.List;

/**
 * Class for Kakao account user data, meaning these data are account-scoped, not app-scoped.
 *
 * @author kevin.kang. Created on 2018. 4. 4..
 */
public class UserAccount {
    private OptionalBoolean hasEmail;
    private OptionalBoolean isEmailVerified;
    private String email;

    private OptionalBoolean hasPhoneNumber;
    private String phoneNumber;

    private OptionalBoolean isKakaoTalkUser;
    private String displayId;

    private JSONObject response;

    public UserAccount(ResponseBody body) {
        hasEmail = body.has(StringSet.has_email) ? OptionalBoolean.getOptionalBoolean(body.getBoolean(StringSet.has_email)) : OptionalBoolean.NONE;
        isEmailVerified = body.has(StringSet.is_email_verified) ? OptionalBoolean.getOptionalBoolean(body.getBoolean(StringSet.is_email_verified)) : OptionalBoolean.NONE;
        hasPhoneNumber = body.has(StringSet.has_phone_number) ? OptionalBoolean.getOptionalBoolean(body.getBoolean(StringSet.has_phone_number)) : OptionalBoolean.NONE;
        isKakaoTalkUser = body.has(StringSet.is_kakaotalk_user) ? OptionalBoolean.getOptionalBoolean(body.getBoolean(StringSet.is_kakaotalk_user)) : OptionalBoolean.NONE;

        if (body.has(StringSet.email)) email = body.getString(StringSet.email);
        if (body.has(StringSet.phone_number)) phoneNumber = body.getString(StringSet.phone_number);
        if (body.has(StringSet.display_id)) displayId = body.getString(StringSet.display_id);

        response = body.getJson();
    }

    /**
     * This method lets you determine if. In combination with {@link #getEmail()}, you know
     *
     * 1. if (getEmail() != null)
     *  - Use the email
     *
     * 2. if (hasEmail() == false && getEmail() == null)
     *  - User does not have email in Kakao account. There is nothing you can do to retrieve email.
     *
     * 3. if (hasEmail() == true && getEmail() == null)
     *  - User has email in Kakao account but you did not receive it because she/he did not agree
     *  with providing email. Use {@link com.kakao.auth.Session#updateScopes(Activity, List, AccessTokenCallback)}
     *  to update scopes like below:
     *
     *  <code>
     *      List<String> scopes = new ArrayList();
     *      scopes.add("account_email");
     *      Session.getCurrentSession().updateScopes(scopes, new AccessTokenCallback() {
     *        ...
     *      })
     *  </code>
     * @return true if user has email registered in her/his Kakao account.
     */
    public OptionalBoolean hasEmail() {
        return hasEmail;
    }

    /**
     *
     * @return {@link OptionalBoolean#TRUE} if email is verified,
     * {@link OptionalBoolean#FALSE} if not verified,
     * {@link OptionalBoolean#NONE} if this info cannot be provided.
     */
    public OptionalBoolean isEmailVerified() {
        return isEmailVerified;
    }

    /**
     * Returns email of user's Kakao account. This method returns null under following cases:
     *  - when user does not have email (she/he registered with phone number)
     *  - when user did not agree to provide email to this service
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return {@link OptionalBoolean#TRUE} if this user is KakaoTalk user,
     * {@link OptionalBoolean#FALSE} if not,
     * {@link OptionalBoolean#NONE} if user did not agree to provide this info yet.
     */
    public OptionalBoolean isKakaoTalkUser() {
        return isKakaoTalkUser;
    }

    /**
     *
     * @return {@link OptionalBoolean#TRUE} if user hash phone number,
     * {@link OptionalBoolean#FALSE} if
     */
    public OptionalBoolean hasPhoneNumber() {
        return hasPhoneNumber;
    }

    /**
     * Returns phone number of user's Kakao account. This method returns null under the following
     * cases:
     *  - when the account has no phone number
     *  - when user did not agree to provide phone number
     *
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns user's display id. This will be either email address or phone number.
     *
     * @return displayId
     */
    public String getDisplayId() {
        return displayId;
    }

    /**
     * Returns a raw json API response. If there is any data not defined as field in this class,
     * you can query with appropriate keys.
     *
     * @return raw API response
     */
    public JSONObject getResponse() {
        return response;
    }
}
