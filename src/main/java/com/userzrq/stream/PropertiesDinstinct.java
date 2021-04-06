package com.userzrq.stream;

import com.userzrq.stream.vo.User;
import net.bytebuddy.matcher.CollectionErasureMatcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class PropertiesDinstinct {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        users.add(new User(1, "1234567"));
        users.add(new User(2, "51253643"));
        users.add(new User(3, "536124"));
        users.add(new User(4, "1234567"));
        users.add(new User(1, "5125551"));
        users.add(new User(3, "536124"));
        users.add(new User(5, "1234567"));

        TreeSet<User> userMobileSet1 = new TreeSet<>(comparing(User::getMobile));
        TreeSet<User> userMobileSet2 = new TreeSet<>(Comparator.comparing(User::getMobile));


        ArrayList<User> unique1 = users.stream().collect(
                collectingAndThen(
                        toCollection(
                                () -> new TreeSet<>(comparing(User::getId))), ArrayList::new)
        );

        ArrayList<User> unique2 = users.stream().collect(
                collectingAndThen(
                        toCollection(
                                () -> new TreeSet<>(comparing(user -> user.getId() + ";" + user.getMobile()))), ArrayList::new)
        );

        List<User> users1 = removeByStreamFilter(users);

        List<User> users2 = removeDuplicateMobileBySet(users);

        System.out.println(unique1);
        System.out.println(unique2);
        System.out.println(users1);
        System.out.println(users2);
    }


    private static List<User> removeByStreamFilter(List<User> list) {
        List<String> mobiles = new ArrayList<>();

        List<User> collect = list.stream().filter(
                user -> {
                    boolean contains = mobiles.contains(user.getMobile());
                    mobiles.add(user.getMobile());
                    return contains;
                }
        ).collect(Collectors.toList());
        return collect;
    }

    private static List<User> removeDuplicateMobileBySet(List<User> list) {
        TreeSet<User> users = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return (user1.getMobile() + user1.getId()).compareTo((user2.getMobile()) + user2.getId());
            }
        });

        users.addAll(list);
        ArrayList<User> result = new ArrayList<>(users);
        return result;
    }
}
