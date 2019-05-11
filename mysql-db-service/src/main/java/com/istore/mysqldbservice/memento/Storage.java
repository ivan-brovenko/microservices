package com.istore.mysqldbservice.memento;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@ToString
public class Storage {
    private Map<String, LinkedList<UserSnapshot>> userMap = new HashMap<>();
    private Integer index;

    public void addSnapshot(UserSnapshot snapshot, String userEmail) {
        LinkedList<UserSnapshot> snapshots = userMap.get(userEmail);
        if (Objects.isNull(snapshots)) {
            snapshots = new LinkedList<>();
            snapshots.add(snapshot);
            userMap.put(userEmail, snapshots);
            incrementIndex(snapshot, snapshots);
            System.out.println(index);
            System.out.println(snapshots);
            return;
        }
        if (snapshots.size() - 1 != index) {
            List<UserSnapshot> sublist = snapshots.subList(0, index);
            snapshots = new LinkedList<>();
            for (UserSnapshot userSnapshot : sublist) {
                snapshots.add(userSnapshot);
            }
        }
        snapshots.add(snapshot);
        userMap.put(userEmail, snapshots);
        incrementIndex(snapshot, snapshots);
        System.out.println(index);
        System.out.println(snapshots);
    }

    private void incrementIndex(UserSnapshot snapshot, LinkedList<UserSnapshot> snapshots) {
        if (snapshots.size() == 1) {
            index = 0;
            return;
        }
        ++index;
    }

    public UserSnapshot getLastSnapshotByUserEmail(String userEmail) {
        LinkedList<UserSnapshot> snapshots = userMap.get(userEmail);
        if (Objects.nonNull(snapshots)) {
            if (Objects.isNull(index)) {
                UserSnapshot userSnapshot = snapshots.getLast();
                index = snapshots.indexOf(userSnapshot);
                return userSnapshot;
            }

            System.out.println(index + "update");
            System.out.println(snapshots);
            if (index != 0) {
                return snapshots.get(index--);
            }
            return snapshots.get(index);
        }
        return null;
    }

    public UserSnapshot forward(String userEmail) {
        LinkedList<UserSnapshot> snapshots = userMap.get(userEmail);
        if (Objects.nonNull(snapshots)) {
            if (Objects.isNull(index)) {
                return null;
            }
            if (index < snapshots.size()) {
                return snapshots.get(++index);
            }

        }
        return null;
    }
}
