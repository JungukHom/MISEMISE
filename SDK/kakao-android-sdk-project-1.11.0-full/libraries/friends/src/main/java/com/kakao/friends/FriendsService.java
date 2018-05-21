/*
  Copyright 2014-2017 Kakao Corp.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.kakao.friends;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.friends.api.FriendsApi;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.friends.response.FriendsResponse;
import com.kakao.network.tasks.ITaskQueue;
import com.kakao.network.tasks.KakaoResultTask;
import com.kakao.network.tasks.KakaoTaskQueue;

/**
 * 유저의 친구 리스트와 각 친구의 정보를 얻어오는 API
 * (제휴를 통해 권한이 부여된 특정 앱에서만 호출이 가능합니다.)
 *
 * @author leo.shin
 */
public class FriendsService {
    /**
     * 친구의 리스트를 얻어온다.
     * (제휴를 통해 권한이 부여된 특정 앱에서만 호출이 가능합니다.)
     * @param context {@link FriendContext} 친구리스트 요청정보를 담고있는 context
     */
    public void requestFriends(final ApiResponseCallback<FriendsResponse> callback, final FriendContext context) {
        taskQueue.addTask(new KakaoResultTask<FriendsResponse>(callback) {
            @Override
            public FriendsResponse call() throws Exception {
                return api.requestFriends(context);
            }
        });
    }

    /**
     * 친구 정보요청을 통해 얻은 데이터를 토대로 Operation을 수행할 수 있다.
     * (제휴를 통해 권한이 부여된 특정 앱에서만 호출이 가능합니다.)
     * @param callback 응답결과를 받을 callback.
     * @param context Operation에 필요한 데이터를 담은 Context.
     */
    public void requestFriendsOperation(final ResponseCallback<FriendsResponse> callback, final FriendOperationContext context) {
        taskQueue.addTask(new KakaoResultTask<FriendsResponse>(callback) {
            @Override
            public FriendsResponse call() throws Exception {
                return api.requestFriendsOperation(context);
            }
        });
    }

    private static FriendsService instance = new FriendsService(FriendsApi.getInstance(),
            KakaoTaskQueue.getInstance());

    public static FriendsService getInstance() {
        return instance;
    }

    private FriendsApi api;
    private ITaskQueue taskQueue;

    FriendsService(final FriendsApi api, final ITaskQueue taskQueue) {
        this.api = api;
        this.taskQueue = taskQueue;
    }
}
