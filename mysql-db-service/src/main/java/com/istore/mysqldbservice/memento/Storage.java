package com.istore.mysqldbservice.memento;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

@Component
@ToString
public class Storage {
    private Map<String, LinkedList<UserSnapshot>> userMap = new HashMap<>();

    public void addSnapshot(UserSnapshot snapshot, String userEmail) {
        LinkedList<UserSnapshot> snapshots = userMap.get(userEmail);
        if (Objects.isNull(snapshots)) {
            snapshots = new LinkedList<>();
            snapshots.add(snapshot);
            userMap.put(userEmail, snapshots);
            return;
        }
        snapshots.add(snapshot);
        userMap.put(userEmail, snapshots);
    }

    public UserSnapshot getLastSnapshotByUserEmail(String userEmail) {
        LinkedList<UserSnapshot> snapshots = userMap.get(userEmail);
        if (Objects.nonNull(snapshots)) {
            return snapshots.getLast();
        }
        return null;
    }
}
