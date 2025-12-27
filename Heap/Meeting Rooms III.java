class Meeting {
    long endTime;
    int room;

    Meeting(long endTime, int room) {
        this.endTime = endTime;
        this.room = room;
    }
}

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        int[] roomUsedCount = new int[n];

        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap sorted by endTime, then room number
        PriorityQueue<Meeting> usedRooms = new PriorityQueue<>(
            (a, b) -> {
                if (a.endTime == b.endTime) {
                    return a.room - b.room;
                }
                return Long.compare(a.endTime, b.endTime);
            }
        );

        // Min-heap of available rooms
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();

        // Initially all rooms are available
        for (int room = 0; room < n; room++) {
            availableRooms.offer(room);
        }

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            int duration = end - start;

            // Free rooms that have finished before current meeting starts
            while (!usedRooms.isEmpty() && usedRooms.peek().endTime <= start) {
                availableRooms.offer(usedRooms.poll().room);
            }

            if (!availableRooms.isEmpty()) {
                int room = availableRooms.poll();
                usedRooms.offer(new Meeting(end, room));
                roomUsedCount[room]++;
            } else {
                // Delay meeting: take the room that finishes earliest
                Meeting earliest = usedRooms.poll();
                usedRooms.offer(new Meeting(earliest.endTime + duration, earliest.room));
                roomUsedCount[earliest.room]++;
            }
        }

        // Find room with maximum usage
        int result = 0;
        int maxUse = 0;
        for (int room = 0; room < n; room++) {
            if (roomUsedCount[room] > maxUse) {
                maxUse = roomUsedCount[room];
                result = room;
            }
        }

        return result;
    }
}
