package ru.anvarzhonov.task3;

/**
 * todo Document type UserFriend
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 20:20
 */
public class UserFriend {
    private final int userId;
    private final int friendId;

    public UserFriend(int userId, int friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserFriend{" +
            "userId=" + userId +
            ", friendId=" + friendId +
            '}';
    }
}
