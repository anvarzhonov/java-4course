package ru.anvarzhonov.task3;

//import io.reactivex.rxjava3.core.Observable;

import rx.Observable;

/**
 * todo Document type ObservableService
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 20:22
 */
public class ObservableService {
    UserFriend[] userFriends;

    public ObservableService(UserFriend[] userFriends) {
        this.userFriends = userFriends;
    }

    public Observable<UserFriend> getFriend(int userId) {
        Observable<UserFriend> userFriendsForUserId = Observable.from(userFriends)
            .filter(x -> x.getUserId() == userId);

//        userFriendsForUserId.ta;
//        System.out.printf("UserFriends for %s : %s \n", userFriendsForUserId, userId);

        return userFriendsForUserId;
    }
}
