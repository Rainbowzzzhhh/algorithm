package LeekCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<int[], List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                count[str.charAt(i) - 'a']++;
            }

            List<String> list = map.getOrDefault(count, new ArrayList<>());
            list.add(str);
            map.put(count, list);
        }
        return new ArrayList<>(map.values());
    }
}
