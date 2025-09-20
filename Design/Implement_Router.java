import java.util.*;

class Packet {
    public int source;
    public int destination;
    public int timestamp;

    public Packet(int source, int destination, int timestamp) {
        this.source = source;
        this.destination = destination;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Packet))
            return false;
        Packet other = (Packet) o;
        return this.source == other.source &&
                this.destination == other.destination &&
                this.timestamp == other.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, timestamp);
    }

    @Override
    public String toString() {
        return "Packet[ source = " + source + ", destination = " + destination + ", timestamp = " + timestamp + "]";
    }
}

class Router {
    private Queue<Packet> queue;
    private HashSet<Packet> packetSet; // to check duplicate source
    private HashMap<Integer, List<Integer>> destinationTimeStampMap; // {destinatio -> {timesstamp, timestamp}};
    private Map<Integer, Integer> startIndexOfRemovedCount; // destination -> index of first valid timestamp
    private int memoryLimit;

    public Router(int memoryLimit) {
        queue = new LinkedList<>();
        packetSet = new HashSet<>();
        destinationTimeStampMap = new HashMap<>();
        startIndexOfRemovedCount = new HashMap<>();
        this.memoryLimit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        // Check duplicate packet or not
        Packet newPacket = new Packet(source, destination, timestamp);
        if (packetSet.contains(newPacket)) {
            return false;
        }

        // Whether check once Queue is exceed memoeryLimit or not.
        if (queue.size() >= memoryLimit) {
            forwardPacket(); // Evict oldest
        }

        // Then We can a new Packet
        queue.offer(newPacket);
        packetSet.add(newPacket); // add to set as well

        // add to destination map
        destinationTimeStampMap.computeIfAbsent(destination, _ -> new ArrayList<>()).add(timestamp);
        // If no removedCount for d, default is 0
        startIndexOfRemovedCount.putIfAbsent(destination, 0);

        return true;
    }

    public int[] forwardPacket() {
        if (queue.isEmpty()) {
            return new int[] {};
        }

        // for (Packet packet : queue) {
        // System.out.println(packet.toString());
        // }

        Packet packet = queue.poll();
        packetSet.remove(packet); // remove from set as well

        // update startIndexOfRemovedCount
        startIndexOfRemovedCount.put(packet.destination,
                startIndexOfRemovedCount.getOrDefault(packet.destination, 0) + 1);

        return new int[] { packet.source, packet.destination, packet.timestamp };
    }

    // public int getCount_TC_O_N(int destination, int startTime, int endTime) {
    // int count = 0;
    // for (Packet packet : queue) {
    // if (packet.destination == destination && packet.timestamp >= startTime &&
    // packet.timestamp <= endTime) {
    // count++;
    // }
    // }

    // return count;
    // }

    // public int getCount(int destination, int startTime, int endTime) {
    // if (!destinationMap.containsKey(destination)) {
    // return 0;
    // }

    // TreeMap<Integer, Integer> timeMap = destinationMap.get(destination);
    // int count = 0;

    // for (Map.Entry<Integer, Integer> entry : timeMap.subMap(startTime, true,
    // endTime, true).entrySet()) {
    // count += entry.getValue();
    // }

    // return count;
    // }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destinationTimeStampMap.containsKey(destination)) {
            return 0;
        }

        List<Integer> timeList = destinationTimeStampMap.get(destination);
        int startIndex = startIndexOfRemovedCount.getOrDefault(destination, 0);

        // Find the first index >= startTime
        int left = startIndex, right = timeList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (timeList.get(mid) >= startTime) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int firstIndex = left;

        // Find the last index <= endTime
        left = startIndex;
        right = timeList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (timeList.get(mid) <= endTime) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int lastIndex = right;
        int result = lastIndex - firstIndex + 1;

        return Math.max(0, result);
    }
}

public class Implement_Router {
    public static void main(String[] args) {
        // Router router = new Router(3); // Initialize Router with memoryLimit of 3.

        // System.out.println(router.addPacket(1, 4, 90)); // true
        // System.out.println(router.addPacket(2, 5, 90)); // true
        // System.out.println(router.addPacket(1, 4, 90)); // false (duplicate)
        // System.out.println(router.addPacket(3, 5, 95)); // true
        // System.out.println(router.addPacket(4, 5, 105)); // true, removes [1,4,90]

        // System.out.println(Arrays.toString(router.forwardPacket())); // [2, 5, 90]

        // System.out.println(router.addPacket(5, 2, 110)); // true
        // System.out.println(router.getCount(5, 100, 110)); // 1 (only [4,5,105])

        Router router = new Router(5); // memory limit = 5

        System.out.println(router.addPacket(2, 3, 1)); // true
        System.out.println(router.addPacket(5, 2, 5)); // true
        System.out.println(router.addPacket(2, 3, 5)); // true

        System.out.println(router.getCount(3, 4, 4)); // 0
        System.out.println(router.getCount(3, 5, 5)); // 1

        System.out.println(router.addPacket(3, 2, 5)); // true

        System.out.println(Arrays.toString(router.forwardPacket())); // [2, 3, 1]

        System.out.println(router.addPacket(2, 3, 5)); // false (duplicate!)

        // Collections.binarySearch(null, null)
    }

}
