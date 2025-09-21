import java.util.*;

class Entry {
    int shop;
    int movie;
    int price;

    public Entry(int shop, int movie, int price) {
        this.shop = shop;
        this.movie = movie;
        this.price = price;
    }
}

class MovieRentingSystem {

    private Map<Integer, HashMap<Integer, Entry>> centralRepository;
    private Map<Integer, TreeSet<Entry>> availableMoviesMap; // Movie -> [ {shop, movie, price} ] ( sorted according to
                                                             // price)
    TreeSet<Entry> cheaptestEntries;

    public MovieRentingSystem(int n, int[][] entries) {
        availableMoviesMap = new HashMap<>();
        centralRepository = new HashMap<>();
        cheaptestEntries = new TreeSet<>((a, b) -> {
            if (a.price != b.price) {
                return Integer.compare(a.price, b.price);
            } else if (a.shop != b.shop) {
                return Integer.compare(a.shop, b.shop);
            } else {
                return Integer.compare(a.movie, b.movie);
            }
        });

        // Add All Entries
        for (int[] entry : entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];

            Entry newEntry = new Entry(shop, movie, price);
            availableMoviesMap.putIfAbsent(movie, new TreeSet<>((a, b) -> {
                if (a.price != b.price) {
                    return Integer.compare(a.price, b.price);
                } else {
                    return Integer.compare(a.shop, b.shop);
                }
            }));
            availableMoviesMap.get(movie).add(newEntry);

            centralRepository.putIfAbsent(shop, new HashMap<>());
            centralRepository.get(shop).put(movie, newEntry);
        }
    }

    public List<Integer> search(int movie) {
        if (!availableMoviesMap.containsKey(movie)) {
            return new ArrayList<>();
        } else {
            TreeSet<Entry> availableEntries = availableMoviesMap.get(movie);
            List<Integer> result = new ArrayList<>();
            for (Entry entry : availableEntries) {
                if (result.size() == 5)
                    break;              
                result.add(entry.shop); // guaranted that those are unrented 
            }
            return result;
        }
    }

    public void rent(int shop, int movie) {
        if (!centralRepository.containsKey(shop)) {
            return;
        } else {
            HashMap<Integer, Entry> moviesMap = centralRepository.get(shop);
            if (moviesMap == null || !moviesMap.containsKey(movie)) {
                return;
            }

            // then find movie
            Entry entry = moviesMap.get(movie);
            // remove it from available Movies map
            availableMoviesMap.get(movie).remove(entry);
            // now added to Rented Entires
            cheaptestEntries.add(entry);
        }
    }

    public void drop(int shop, int movie) {
        HashMap<Integer, Entry> moviesMap = centralRepository.get(shop);
        if (moviesMap == null || !moviesMap.containsKey(movie)) {
            return;
        }

        Entry entry = moviesMap.get(movie);
        // Now add it to Available Movies Map
        availableMoviesMap.get(movie).add(entry);
        // and Remove from Cheapeset Entries and Rented Entries
        cheaptestEntries.remove(entry);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> report = new ArrayList<>();
        for (Entry entry : cheaptestEntries) {
            if (report.size() == 5)
                break;
            List<Integer> rentedEntry = new ArrayList<>();
            rentedEntry.add(entry.shop);
            rentedEntry.add(entry.movie);

            report.add(rentedEntry);
        }

        return report;
    }
}

public class Design_Movie_Rental_System_v2 {
    public static void main(String[] args) {
        // Initialize MovieRentingSystem with shops and movies
        MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, new int[][] {
                { 0, 1, 5 },
                { 0, 2, 6 },
                { 0, 3, 7 },
                { 1, 1, 4 },
                { 1, 2, 7 },
                { 2, 1, 5 }
        });

        // Example operations
        System.out.println(movieRentingSystem.search(1));
        // Expected: [1, 0, 2]

        movieRentingSystem.rent(0, 1);
        movieRentingSystem.rent(1, 2);

        System.out.println(movieRentingSystem.report());
        // Expected: [[0, 1], [1, 2]]

        movieRentingSystem.drop(1, 2);

        System.out.println(movieRentingSystem.search(2));
        // Expected: [0, 1]
    }
}
