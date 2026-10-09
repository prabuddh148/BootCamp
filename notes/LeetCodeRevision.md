# LeetCode Revision

28 questions, **pattern ke hisaab se** grouped. Har question mein: asli trick, chhota code, complexity, aur jahan galti hoti hai.

"Repo" link wale questions is project mein solve ho chuke hain. Baaki ka code yahin notes mein hai.

Level Order Traversal (102) ke detail notes alag file mein hain: [LevelOrderTraversal.md](LevelOrderTraversal.md)

---

## Index

| # | Problem | Level | Pattern | Repo |
|---|---|---|---|---|
| 2 | [Add Two Numbers](#2-add-two-numbers) | Medium | Linked List | [LinkedList.java:278](../src/main/java/com/example/bootcamp/LinkedList.java#L278) |
| 3 | [Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters) | Medium | Sliding Window | [Strings.java:9](../src/main/java/com/example/bootcamp/Strings.java#L9) |
| 15 | [3Sum](#15-3sum) | Medium | Two Pointers | [Array.java:50](../src/main/java/com/example/bootcamp/Array.java#L50) |
| 16 | [3Sum Closest](#16-3sum-closest) | Medium | Two Pointers | — |
| 26 | [Remove Duplicates from Sorted Array](#26-remove-duplicates-from-sorted-array) | Easy | Arrays | [Array.java:85](../src/main/java/com/example/bootcamp/Array.java#L85) |
| 33 | [Search in Rotated Sorted Array](#33-search-in-rotated-sorted-array) | Medium | Binary Search | [Array.java:111](../src/main/java/com/example/bootcamp/Array.java#L111) |
| 61 | [Rotate List](#61-rotate-list) | Medium | Linked List | [LinkedList.java:447](../src/main/java/com/example/bootcamp/LinkedList.java#L447) |
| 88 | [Merge Sorted Array](#88-merge-sorted-array) | Easy | Arrays | — |
| 112 | [Path Sum](#112-path-sum) | Easy | Trees | — |
| 128 | [Longest Consecutive Sequence](#128-longest-consecutive-sequence) | Medium | Hashing | [Strings.java:55](../src/main/java/com/example/bootcamp/Strings.java#L55) |
| 143 | [Reorder List](#143-reorder-list) | Medium | Linked List | [LinkedList.java:210](../src/main/java/com/example/bootcamp/LinkedList.java#L210) |
| 153 | [Find Minimum in Rotated Sorted Array](#153-find-minimum-in-rotated-sorted-array) | Medium | Binary Search | — |
| 155 | [Min Stack](#155-min-stack) | Medium | Stack | [StackAndQueues.java:82](../src/main/java/com/StackAndQueues.java#L82) |
| 168 | [Excel Sheet Column Title](#168-excel-sheet-column-title) | Easy | Math | [Strings.java:100](../src/main/java/com/example/bootcamp/Strings.java#L100) |
| 189 | [Rotate Array](#189-rotate-array) | Medium | Arrays | — |
| 209 | [Minimum Size Subarray Sum](#209-minimum-size-subarray-sum) | Medium | Sliding Window | — |
| 234 | [Palindrome Linked List](#234-palindrome-linked-list) | Easy | Linked List | [LinkedList.java:340](../src/main/java/com/example/bootcamp/LinkedList.java#L340) |
| 268 | [Missing Number](#268-missing-number) | Easy | Arrays / Math | — |
| 278 | [First Bad Version](#278-first-bad-version) | Easy | Binary Search | — |
| 287 | [Find the Duplicate Number](#287-find-the-duplicate-number) | Medium | Fast & Slow Pointer | — |
| 350 | [Intersection of Two Arrays II](#350-intersection-of-two-arrays-ii) | Easy | Hashing | — |
| 441 | [Arranging Coins](#441-arranging-coins) | Easy | Binary Search | — |
| 442 | [Find All Duplicates in an Array](#442-find-all-duplicates-in-an-array) | Medium | Index Marking | — |
| 541 | [Reverse String II](#541-reverse-string-ii) | Easy | Two Pointers | [TwoPointer.java:30](../src/main/java/com/example/bootcamp/TwoPointer.java#L30) |
| 735 | [Asteroid Collision](#735-asteroid-collision) | Medium | Stack | [StackAndQueues.java:217](../src/main/java/com/StackAndQueues.java#L217) |
| 739 | [Daily Temperatures](#739-daily-temperatures) | Medium | Monotonic Stack | [StackAndQueues.java:140](../src/main/java/com/StackAndQueues.java#L140) |
| 875 | [Koko Eating Bananas](#875-koko-eating-bananas) | Medium | Binary Search on Answer | — |
| 1472 | [Design Browser History](#1472-design-browser-history) | Medium | Design | [LinkedList.java:377](../src/main/java/com/example/bootcamp/LinkedList.java#L377) |

---

## Pattern kaise pehchane

| Question mein ye dikhe | Socho |
|---|---|
| Sorted array, kuch dhoondhna hai | Binary Search |
| "Minimum speed / capacity jisme kaam ho jaaye" | Binary Search on Answer |
| Subarray / substring, "longest" ya "shortest" | Sliding Window |
| Sorted array mein pair ya triplet ka sum | Two Pointers |
| "Next greater / next warmer" | Monotonic Stack |
| Values `1..n` ke beech, O(1) extra space | Index marking ya Fast & Slow pointer |
| Linked list ka beech / dusra half | Slow-fast pointer, phir reverse |
| Kitni baar aaya, ya hai ki nahi | HashMap / HashSet |

---

## 1. Arrays & Hashing

### 26. Remove Duplicates from Sorted Array
Repo: [Array.java:85](../src/main/java/com/example/bootcamp/Array.java#L85)

- Array sorted hai, isliye duplicates hamesha saath-saath hote hain.
- `k` = agla unique value kahan likhna hai. Naya value mile to `nums[k++]` mein likho.

```java
int k = 1;
for (int i = 1; i < nums.length; i++) {
    if (nums[i] != nums[k - 1]) {
        nums[k++] = nums[i];
    }
}
return k;
```

**O(n) time, O(1) space.** Answer `k` hai, poora array nahi. Sirf pehle `k` elements check hote hain.

### 88. Merge Sorted Array

- `nums1` ke end mein khaali jagah hai, isliye **peeche se** bharo. Aage se bharoge to `nums1` ki values overwrite ho jaayengi.
- Teen pointers: `i` (nums1 ka aakhri asli element), `j` (nums2 ka aakhri), `k` (likhne ki jagah).

```java
int i = m - 1, j = n - 1, k = m + n - 1;
while (j >= 0) {
    if (i >= 0 && nums1[i] > nums2[j]) {
        nums1[k--] = nums1[i--];
    } else {
        nums1[k--] = nums2[j--];
    }
}
```

**O(m + n) time, O(1) space.** Loop `j >= 0` tak chalao. `nums2` khatam ho gaya to `nums1` ka bacha hissa pehle se sahi jagah pe hai.

### 189. Rotate Array

- Right rotate by `k` = **teen reverse**: poora array, phir pehle `k`, phir baaki.
- `[1,2,3,4,5,6,7], k=3` → reverse all `[7,6,5,4,3,2,1]` → pehle 3 `[5,6,7,…]` → baaki `[5,6,7,1,2,3,4]`

```java
k %= nums.length;
reverse(nums, 0, nums.length - 1);
reverse(nums, 0, k - 1);
reverse(nums, k, nums.length - 1);

void reverse(int[] a, int l, int r) {
    while (l < r) {
        int t = a[l]; a[l++] = a[r]; a[r--] = t;
    }
}
```

**O(n) time, O(1) space.** `k %= n` bhoolna mat. `k` array ki length se bada ho sakta hai.

### 268. Missing Number

- `0..n` ka sum = `n(n+1)/2`. Usme se array ka sum ghatao, jo bacha wahi missing hai.
- XOR wala tarika: `x ^ x = 0`, isliye saare index aur saari values XOR karo. Jo akela bacha, wahi missing hai.

```java
int n = nums.length, xor = n;
for (int i = 0; i < n; i++) {
    xor ^= i ^ nums[i];
}
return xor;
```

**O(n) time, O(1) space.** XOR ki shuruaat `n` se karo, kyunki index `0..n-1` tak hi jaate hain.

### 350. Intersection of Two Arrays II

- `nums1` ki har value kitni baar aayi, HashMap mein gino.
- `nums2` mein chalo: count > 0 ho to answer mein daalo aur count ek kam karo.

```java
Map<Integer, Integer> count = new HashMap<>();
for (int x : nums1) count.put(x, count.getOrDefault(x, 0) + 1);

List<Integer> res = new ArrayList<>();
for (int x : nums2) {
    if (count.getOrDefault(x, 0) > 0) {
        res.add(x);
        count.put(x, count.get(x) - 1);
    }
}
return res.stream().mapToInt(Integer::intValue).toArray();
```

**O(n + m) time.** Follow-up: arrays sorted hon to HashMap ki jagah do pointers, O(1) extra space.

### 128. Longest Consecutive Sequence
Repo: [Strings.java:55](../src/main/java/com/example/bootcamp/Strings.java#L55)

- Saari values HashSet mein daalo.
- Sequence sirf wahan se **shuru** karo jahan `x - 1` set mein nahi hai. Phir `x+1, x+2, …` jab tak mile, gino.

```java
Set<Integer> set = new HashSet<>();
for (int x : nums) set.add(x);

int best = 0;
for (int x : set) {
    if (!set.contains(x - 1)) {          // sequence ki shuruaat
        int len = 1;
        while (set.contains(x + len)) len++;
        best = Math.max(best, len);
    }
}
return best;
```

**O(n) time.** Har number ek hi sequence ki shuruaat se gina jaata hai. Sort karke bhi ho jaata hai, par wo O(n log n) hai, aur question O(n) maangta hai.

### 442. Find All Duplicates in an Array

- Values `1..n` hain, to har value ek index bata sakti hai: `abs(x) - 1`.
- Us index ki value ko **negative** karke "dekh liya" mark karo. Agar pehle se negative hai, to `abs(x)` doosri baar aaya hai.

```java
List<Integer> res = new ArrayList<>();
for (int x : nums) {
    int idx = Math.abs(x) - 1;
    if (nums[idx] < 0) {
        res.add(Math.abs(x));
    } else {
        nums[idx] = -nums[idx];
    }
}
return res;
```

**O(n) time, O(1) extra space.** `Math.abs` zaroori hai, kyunki `x` khud pehle negative ho chuka ho sakta hai.

### 287. Find the Duplicate Number

- Shart: array badalna nahi hai aur O(1) space chahiye. Isliye sort ya HashSet nahi chalega.
- `i → nums[i]` ko linked list ka `next` maano. Duplicate value pe do arrows aate hain, yaani **cycle** banti hai.
- Floyd: slow-fast milne tak chalao, phir ek pointer `0` se shuru karo. Dono jahan milein, wahi duplicate hai.

```java
int slow = nums[0], fast = nums[0];
do {
    slow = nums[slow];
    fast = nums[nums[fast]];
} while (slow != fast);

slow = nums[0];
while (slow != fast) {
    slow = nums[slow];
    fast = nums[fast];
}
return slow;
```

**O(n) time, O(1) space.** Ye wahi Linked List Cycle II (142) wala logic hai. Repo mein `detectCycle` dekho.

---

## 2. Two Pointers

### 15. 3Sum
Repo: [Array.java:50](../src/main/java/com/example/bootcamp/Array.java#L50)

- Pehle **sort** karo. Ek number `i` fix karo, baaki do ke liye `l` aur `r` pointer.
- Sum chhota hai to `l++`, bada hai to `r--`.
- Duplicate triplets na aayein, isliye same values skip karo.

```java
Arrays.sort(nums);
List<List<Integer>> res = new ArrayList<>();
for (int i = 0; i < nums.length - 2; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) continue;
    int l = i + 1, r = nums.length - 1;
    while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];
        if (sum < 0) l++;
        else if (sum > 0) r--;
        else {
            res.add(Arrays.asList(nums[i], nums[l], nums[r]));
            while (l < r && nums[l] == nums[l + 1]) l++;
            while (l < r && nums[r] == nums[r - 1]) r--;
            l++; r--;
        }
    }
}
return res;
```

**O(n²) time.** Duplicates teen jagah skip hote hain: `i` pe, aur match milne ke baad `l` aur `r` pe.

### 16. 3Sum Closest

- 3Sum jaisa hi: sort karo, `i` fix karo, `l`/`r` chalao.
- Ab exact zero nahi dhoondhna. `|sum - target|` sabse chhota wala yaad rakho.

```java
Arrays.sort(nums);
int best = nums[0] + nums[1] + nums[2];
for (int i = 0; i < nums.length - 2; i++) {
    int l = i + 1, r = nums.length - 1;
    while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];
        if (Math.abs(sum - target) < Math.abs(best - target)) best = sum;
        if (sum < target) l++;
        else if (sum > target) r--;
        else return sum;                 // isse closer kuch nahi ho sakta
    }
}
return best;
```

**O(n²) time.** `best` ko `Integer.MAX_VALUE` se shuru mat karo, `best - target` overflow ho jaayega. Pehle teen numbers ka sum lo.

### 541. Reverse String II
Repo: [TwoPointer.java:30](../src/main/java/com/example/bootcamp/TwoPointer.java#L30)

- Har `2k` ke block mein sirf pehle `k` characters reverse karo.
- Aakhri block chhota ho sakta hai, isliye end = `min(i + k - 1, n - 1)`.

```java
char[] a = s.toCharArray();
for (int i = 0; i < a.length; i += 2 * k) {
    int l = i, r = Math.min(i + k - 1, a.length - 1);
    while (l < r) {
        char t = a[l]; a[l++] = a[r]; a[r--] = t;
    }
}
return new String(a);
```

**O(n) time.** `min` wali line hi edge case sambhalti hai: `k` se kam characters bache hon to sab reverse.

---

## 3. Sliding Window

### 3. Longest Substring Without Repeating Characters
Repo: [Strings.java:9](../src/main/java/com/example/bootcamp/Strings.java#L9)

- Window `[left, i]` mein koi character repeat nahi hona chahiye.
- Har character ka aakhri index yaad rakho. Repeat mile to `left` ko us index ke **aage** le jao.

```java
Map<Character, Integer> last = new HashMap<>();
int left = 0, best = 0;
for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
    if (last.containsKey(c)) {
        left = Math.max(left, last.get(c) + 1);
    }
    last.put(c, i);
    best = Math.max(best, i - left + 1);
}
return best;
```

**O(n) time.** `Math.max` zaroori hai. `left` kabhi peeche nahi jaana chahiye. `"abba"` mein doosra `a` purane index pe le jaata.

### 209. Minimum Size Subarray Sum

- Saare numbers **positive** hain, isliye window badhane se sum badhta hai aur chhota karne se ghatata hai.
- `right` se badhao. Jab tak `sum >= target`, answer update karo aur `left` se chhota karo.

```java
int left = 0, sum = 0, best = Integer.MAX_VALUE;
for (int right = 0; right < nums.length; right++) {
    sum += nums[right];
    while (sum >= target) {
        best = Math.min(best, right - left + 1);
        sum -= nums[left++];
    }
}
return best == Integer.MAX_VALUE ? 0 : best;
```

**O(n) time**, kyunki `left` aur `right` dono ek-ek baar hi aage jaate hain. Negative numbers hote to ye trick nahi chalti. Koi window na mile to `0` return karna hai.

---

## 4. Binary Search

### 278. First Bad Version

- `isBadVersion` ka pattern hai: `false false … true true`. Pehla `true` dhoondhna hai.
- Bad mile to answer yahi ya iske left mein hai: `hi = mid`. Warna `lo = mid + 1`.

```java
int lo = 1, hi = n;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (isBadVersion(mid)) hi = mid;
    else lo = mid + 1;
}
return lo;
```

**O(log n).** `(lo + hi) / 2` mat likho. `n` ~ 2³¹ tak hai, overflow ho jaayega.

### 441. Arranging Coins

- `k` poori rows ke liye `k(k+1)/2` coins chahiye. Sabse bada `k` dhoondho jisme `k(k+1)/2 <= n` ho.

```java
long lo = 0, hi = n;
while (lo <= hi) {
    long mid = lo + (hi - lo) / 2;
    long coins = mid * (mid + 1) / 2;
    if (coins == n) return (int) mid;
    if (coins < n) lo = mid + 1;
    else hi = mid - 1;
}
return (int) hi;
```

**O(log n).** `long` use karo, `mid * (mid + 1)` int mein overflow ho jaata hai. Loop ke baad `hi` aakhri valid `k` hota hai.

### 875. Koko Eating Bananas

- **Binary Search on Answer:** speed `k` ki range `1..max(piles)` hai.
- Speed `k` pe ghante = har pile ka `ceil(p / k)`. `h` ke andar ho gaya to aur kam speed try karo.

```java
int lo = 1, hi = 0;
for (int p : piles) hi = Math.max(hi, p);
while (lo < hi) {
    int k = lo + (hi - lo) / 2;
    long hours = 0;
    for (int p : piles) hours += (p + k - 1) / k;   // ceil(p / k)
    if (hours <= h) hi = k;
    else lo = k + 1;
}
return lo;
```

**O(n log max).** `hours` ko `long` rakho. `ceil` ke liye `(p + k - 1) / k` likho, double se mat karo.

### 153. Find Minimum in Rotated Sorted Array

- `mid` ko **`hi`** se compare karo.
- `nums[mid] > nums[hi]` matlab rotation ka "girna" right mein hai, minimum `mid` ke right mein hai. Warna minimum `mid` pe ya uske left mein hai.

```java
int lo = 0, hi = nums.length - 1;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] > nums[hi]) lo = mid + 1;
    else hi = mid;
}
return nums[lo];
```

**O(log n).** `lo` se compare karoge to bina rotation wale array (`[1,2,3]`) pe galat answer aayega.

### 33. Search in Rotated Sorted Array
Repo: [Array.java:111](../src/main/java/com/example/bootcamp/Array.java#L111)

- Har `mid` pe **ek half zaroor sorted** hota hai.
- Pata karo kaunsa half sorted hai, phir dekho target uski range mein hai ya nahi. Hai to us taraf jao, warna doosri taraf.

```java
int lo = 0, hi = nums.length - 1;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] == target) return mid;
    if (nums[lo] <= nums[mid]) {                        // left half sorted
        if (target >= nums[lo] && target < nums[mid]) hi = mid - 1;
        else lo = mid + 1;
    } else {                                            // right half sorted
        if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
        else hi = mid - 1;
    }
}
return -1;
```

**O(log n).** `nums[lo] <= nums[mid]` mein `=` zaroori hai. Do elements bachne pe `lo == mid` hota hai.

---

## 5. Stack

### 155. Min Stack
Repo: [StackAndQueues.java:82](../src/main/java/com/StackAndQueues.java#L82)

- Har element ke saath **us waqt tak ka minimum** bhi rakho. Pop karne pe purana minimum apne aap wapas aa jaata hai.

```java
Stack<int[]> st = new Stack<>();          // {value, minSoFar}

void push(int x) {
    int min = st.isEmpty() ? x : Math.min(x, st.peek()[1]);
    st.push(new int[] { x, min });
}
void pop()   { st.pop(); }
int top()    { return st.peek()[0]; }
int getMin() { return st.peek()[1]; }
```

**Saare operations O(1).** Ek `min` variable kaafi nahi hai: minimum pop ho gaya to pichla minimum kahan se laaoge?

### 739. Daily Temperatures
Repo: [StackAndQueues.java:140](../src/main/java/com/StackAndQueues.java#L140)

- **Monotonic decreasing stack** mein un dino ke index rakho jinka garam din abhi nahi mila.
- Aaj ka temperature stack ke top se zyada hai, to top wale din ka answer `i - top` hai. Pop karte raho.

```java
int[] ans = new int[t.length];
Stack<Integer> st = new Stack<>();         // indices
for (int i = 0; i < t.length; i++) {
    while (!st.isEmpty() && t[i] > t[st.peek()]) {
        int prev = st.pop();
        ans[prev] = i - prev;
    }
    st.push(i);
}
return ans;
```

**O(n) time**, kyunki har index ek baar push aur ek baar pop hota hai. Stack mein **index** rakho, value nahi. Din ka gap chahiye. Jo stack mein bach gaye, unka answer `0` hi rehta hai.

### 735. Asteroid Collision
Repo: [StackAndQueues.java:217](../src/main/java/com/StackAndQueues.java#L217)

- Takkar sirf tab hoti hai jab stack ka top **right** ja raha ho (`> 0`) aur naya **left** ja raha ho (`< 0`).
- Chhota phat jaata hai. Barabar hon to dono phat jaate hain.

```java
Stack<Integer> st = new Stack<>();
for (int a : asteroids) {
    boolean alive = true;
    while (alive && a < 0 && !st.isEmpty() && st.peek() > 0) {
        if (st.peek() < -a) {
            st.pop();                    // top phata, naya aage badhta hai
        } else {
            if (st.peek() == -a) st.pop();   // dono phate
            alive = false;               // naya phata
        }
    }
    if (alive) st.push(a);
}
// stack ko array mein badlo (neeche se upar)
```

**O(n) time.** `[-2, 1]` mein takkar nahi hoti, kyunki left wala pehle hi left ja raha hai.

---

## 6. Linked List

### 2. Add Two Numbers
Repo: [LinkedList.java:278](../src/main/java/com/example/bootcamp/LinkedList.java#L278)

- Digits ulte order mein hain, isliye seedha aage se jodte jao, school wale tarike se `carry` ke saath.
- `dummy` node se shuru karo, taaki head ka alag case na likhna pade.

```java
ListNode dummy = new ListNode(0), cur = dummy;
int carry = 0;
while (l1 != null || l2 != null || carry != 0) {
    int sum = carry;
    if (l1 != null) { sum += l1.val; l1 = l1.next; }
    if (l2 != null) { sum += l2.val; l2 = l2.next; }
    cur.next = new ListNode(sum % 10);
    carry = sum / 10;
    cur = cur.next;
}
return dummy.next;
```

**O(max(m, n)).** Loop condition mein `carry != 0` zaroori hai. `5 + 5` pe aakhri `1` warna chhoot jaata hai.

### 143. Reorder List
Repo: [LinkedList.java:210](../src/main/java/com/example/bootcamp/LinkedList.java#L210)

Teen kaam, teeno pehle seekhe hue:
1. **Beech dhoondho** (slow-fast).
2. **Doosra half reverse** karo.
3. Dono halves ko **ek-ek karke jodo**: `1 → n → 2 → n-1 → …`

```java
ListNode slow = head, fast = head;
while (fast.next != null && fast.next.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
ListNode second = reverse(slow.next);
slow.next = null;                        // pehla half alag karo

ListNode first = head;
while (second != null) {
    ListNode n1 = first.next, n2 = second.next;
    first.next = second;
    second.next = n1;
    first = n1;
    second = n2;
}
```

**O(n) time, O(1) space.** `slow.next = null` bhoole to list mein cycle ban jaati hai.

### 234. Palindrome Linked List
Repo: [LinkedList.java:340](../src/main/java/com/example/bootcamp/LinkedList.java#L340)

- 143 jaisa: beech dhoondho, doosra half reverse karo, phir dono halves ko saath-saath compare karo.

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
ListNode second = reverse(slow), first = head;
while (second != null) {
    if (first.val != second.val) return false;
    first = first.next;
    second = second.next;
}
return true;
```

**O(n) time, O(1) space.** Poori list copy karke reverse karna bhi chalta hai, par wo O(n) space leta hai. Follow-up O(1) space maangta hai.

### 61. Rotate List
Repo: [LinkedList.java:447](../src/main/java/com/example/bootcamp/LinkedList.java#L447)

- Length `n` nikaalo aur `k %= n` karo.
- Naya tail head se `n - k - 1` steps pe hai. Uske baad wala naya head hai.

```java
if (head == null || head.next == null) return head;
int n = 1;
ListNode tail = head;
while (tail.next != null) { tail = tail.next; n++; }

k %= n;
if (k == 0) return head;

ListNode newTail = head;
for (int i = 0; i < n - k - 1; i++) newTail = newTail.next;
ListNode newHead = newTail.next;
newTail.next = null;
tail.next = head;
return newHead;
```

**O(n).** `k` bahut bada ho sakta hai (2 × 10⁹), isliye `k %= n` ke bina baar-baar ghumaoge to TLE aayega.

---

## 7. Design

### 1472. Design Browser History
Repo: [LinkedList.java:377](../src/main/java/com/example/bootcamp/LinkedList.java#L377)

- Repo mein doubly linked list se bana hai. ArrayList se aur aasaan hai: `cur` = abhi kaunsa page, `end` = forward kahan tak ja sakte hain.
- `visit` karne pe aage ki history mit jaati hai, isliye `end = cur`.

```java
List<String> pages = new ArrayList<>();
int cur = 0, end = 0;

BrowserHistory(String homepage) { pages.add(homepage); }

void visit(String url) {
    cur++;
    if (cur < pages.size()) pages.set(cur, url);
    else pages.add(url);
    end = cur;                           // forward history khatam
}
String back(int steps)    { cur = Math.max(0, cur - steps);   return pages.get(cur); }
String forward(int steps) { cur = Math.min(end, cur + steps); return pages.get(cur); }
```

**Saare operations O(1).** `forward` ki limit `pages.size() - 1` nahi, **`end`** hai. List mein purane pages pade reh sakte hain.

---

## 8. Math

### 168. Excel Sheet Column Title
Repo: [Strings.java:100](../src/main/java/com/example/bootcamp/Strings.java#L100)

- Ye base-26 jaisa hai, par **0 nahi hai**: `A = 1 … Z = 26`. Isliye har baar pehle `n--` karo.

```java
StringBuilder sb = new StringBuilder();
while (n > 0) {
    n--;
    sb.append((char) ('A' + n % 26));
    n /= 26;
}
return sb.reverse().toString();
```

**O(log n).** `n--` bhoole to `26` ka answer `"Z"` ki jagah `"BA"` aayega. Ulta: [171 titleToNumber](../src/main/java/com/example/bootcamp/Strings.java) repo mein hai.

---

## 9. Trees

### 112. Path Sum

- Har node pe `target` mein se uski value ghatao.
- Check sirf **leaf** pe karo: bacha hua target node ki value ke barabar hai?

```java
public boolean hasPathSum(TreeNode root, int target) {
    if (root == null) return false;
    if (root.left == null && root.right == null) {
        return target == root.val;
    }
    int rest = target - root.val;
    return hasPathSum(root.left, rest) || hasPathSum(root.right, rest);
}
```

**O(n) time, O(h) space** (recursion). `root == null` pe `target == 0` return mat karo. Wo path leaf pe khatam nahi hota. `[1, 2]` aur target `1` pe galat `true` aayega.
