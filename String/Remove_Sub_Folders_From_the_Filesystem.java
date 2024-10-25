import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Remove_Sub_Folders_From_the_Filesystem
 */
public class Remove_Sub_Folders_From_the_Filesystem {

    public static List<String> removeSubfolders(String[] folder) {
        List<String> ans = new ArrayList<>();
        int n = folder.length;
        if (n == 0)
            return ans;

        Set<String> set = new HashSet<>();
        for (String path : folder) {
            set.add(path);
        }

        for (int i = 0; i < n; i++) {

            // find the last '/'
            int index = -1;
            int m = folder[i].length();
            for (int j = m - 1; j >= 0; j--) {
                if (folder[i].charAt(j) == '/') {
                    index = j;
                    break;
                }
            }

            // take Substring
            String substrPath = folder[i].substring(0, index);
            if (set.contains(substrPath)) {
                continue;
            } else {

                // futhtur checking for parent directory
                // back seraching
                boolean found = false;
                for (int j = index - 1; j > 0; j--) {
                    if (folder[i].charAt(j) == '/') {
                        // take now substring again
                        if (set.contains(folder[i].substring(0, j))) {
                            found = true;
                            break;
                        }
                    }
                }

                // means : not found
                // this is legit directory
                if (!found) {
                    ans.add(folder[i]);
                }

            }

        }

        return ans;
    }

    public static void main(String[] args) {
        String[] folder = { "/a/b/c", "/a/b/ca", "/a/b/d" };
        // String[] folder = {"/a","/a/b/c","/a/b/d"};
        System.out.println("OUTPUT : " + removeSubfolders(folder));
    }
}