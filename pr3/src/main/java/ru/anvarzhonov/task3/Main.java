package ru.anvarzhonov.task3;

import org.apache.commons.lang3.RandomUtils;
import rx.Observable;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * todo Document type Main
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 20:20
 */
public class Main {
    static final int FRIEND_ARRAY_SIZE = 10;

    public static void main(String[] args) {
        List<UserFriend> result = new ArrayList<>();

        UserFriend[] userFriends = fillArray();
        ObservableService service = new ObservableService(userFriends);
        List<Integer> userIds = fillIntegerArray();

        PublishSubject<UserFriend> publishSubject = PublishSubject.create();
        publishSubject.subscribe(result::add);

        Observable.from(userIds)
            .flatMap(service::getFriend)
            .subscribe(publishSubject::onNext);

        System.out.println("=========================\nИтоговый результат");

        for (Integer userId : userIds) {
            System.out.printf("UserId: %s \n", userId);
            result.stream().filter(o -> o.getUserId() == userId)
                .forEach(o -> System.out.println("   " + o));
        }
    }

    private static List<Integer> fillIntegerArray() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < FRIEND_ARRAY_SIZE; i++) {
            result.add(RandomUtils.nextInt(1, 10));
        }
        System.out.println("Массив userIds:" + result);
        return result;
    }

    private static UserFriend[] fillArray() {
        UserFriend[] arr = new UserFriend[FRIEND_ARRAY_SIZE];

        for (int i = 0; i < FRIEND_ARRAY_SIZE; i++) {
            arr[i] = new UserFriend(RandomUtils.nextInt(1, 10), RandomUtils.nextInt(1, 10));
            System.out.println(arr[i]);
        }

        return arr;
    }
}
