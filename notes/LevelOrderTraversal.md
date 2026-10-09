# Level Order Traversal (BFS) — LeetCode 102

Code: `Trees.levelOrder()` in [Trees.java](../src/main/java/com/example/bootcamp/Trees.java)

## Problem

Binary tree ka root diya hai. Har level ki values alag list mein, upar se neeche aur left se right, return karni hain.

```
        3
       / \
      9   20
         /  \
        15   7
```

Input: `[3,9,20,null,null,15,7]`
Output: `[[3], [9, 20], [15, 7]]`

## Idea

- Tree ko **level by level** padhna hai, isliye **BFS** lagate hain, aur BFS ke liye **Queue** chahiye.
- Queue **FIFO** hai: jo pehle andar gaya, wahi pehle bahar aata hai. Pehle root nikalta hai, phir uske bachche, phir unke bachche, yaani apne aap upar se neeche order milta hai.
- Problem sirf order ki nahi hai, **grouping** bhi chahiye: kaunsa node kis level ka hai.

## Asli trick: `levelSize = queue.size()`

Har round ki shuruaat mein queue mein **sirf ek hi level ke saare nodes** hote hain.

1. `levelSize = queue.size()` se ginti **fix** kar lo.
2. Bas utne hi nodes `poll()` karo, yahi poora level hai.
3. In nodes ke bachche queue ke **peeche** judte jaate hain. Wo agle round (agle level) ke liye ruke rehte hain.

`levelSize` pehle se save na karo aur loop mein seedha `queue.size()` likh do, to bachche judte hi size badal jaayega aur levels mix ho jaayenge.

## Code

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
        return result;                       // khaali tree -> []
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);                       // shuruaat root se

    while (!queue.isEmpty()) {
        int levelSize = queue.size();        // is level mein kitne nodes
        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
            TreeNode current = queue.poll(); // aage se nikalo
            level.add(current.val);

            if (current.left != null) {
                queue.offer(current.left);   // agle level ke liye
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        result.add(level);                   // ek level poora
    }

    return result;
}
```

## Dry run: `[3,9,20,null,null,15,7]`

| Round | Queue (shuru mein) | levelSize | poll() kiye | Queue mein jude | level | result |
|---|---|---|---|---|---|---|
| 1 | [3] | 1 | 3 | 9, 20 | [3] | [[3]] |
| 2 | [9, 20] | 2 | 9, 20 | 15, 7 | [9, 20] | [[3], [9, 20]] |
| 3 | [15, 7] | 2 | 15, 7 | — | [15, 7] | [[3], [9, 20], [15, 7]] |
| — | [] | — | while khatam | — | — | return |

## Complexity

- **Time: O(n).** Har node ek baar queue mein jaata hai aur ek baar nikalta hai.
- **Space: O(n).** Queue mein ek waqt pe zyada se zyada ek level ke nodes hote hain. Sabse chauda level ~n/2 nodes ka ho sakta hai. Result bhi n values rakhta hai.

## Queue ke methods

| Method | Kaam | Khaali queue pe |
|---|---|---|
| `offer(x)` | peeche daalo | — |
| `poll()` | aage se nikalo | `null` deta hai |
| `peek()` | aage wala dekho, nikalo mat | `null` deta hai |
| `isEmpty()` / `size()` | khaali hai? / kitne hain | — |

`Queue` ek interface hai, isliye object `new LinkedList<>()` (ya `new ArrayDeque<>()`) se banta hai.

## Galtiyan jo hamare code mein hui thi

1. **Sirf print kiya, `result` mein add nahi kiya.** Isliye answer hamesha `[]` aata tha.
2. **Ek hi loop, bina `levelSize` ke.** Is se order sahi milta hai, par levels alag nahi hote. Output `[3, 9, 20, 15, 7]` jaisa flat ban jaata hai.
3. **Galat `TreeNode` import.** VS Code ne `javax.swing.tree.TreeNode` import kar diya tha, jisme `val`, `left`, `right` nahi hote. Apni LeetCode wali `TreeNode` class use karo.
4. **`LinkedList` ka naam clash.** Project mein apni `LinkedList` class bhi hai. `Trees.java` mein `import java.util.LinkedList;` zaroori hai, warna `new LinkedList<>()` hamari class ban jaayegi aur `Queue` mein fit nahi hogi.

## Isi template se banne wale sawaal

Bas round ke andar ya `result.add(level)` ke aas-paas chhota sa badlaav karna hota hai:

| Problem | Badlaav |
|---|---|
| 107. Level Order II (neeche se upar) | end mein `Collections.reverse(result)`, ya `result.add(0, level)` |
| 103. Zigzag Level Order | har doosre level ko reverse karo (ek `boolean leftToRight` flip karte raho) |
| 199. Right Side View | har level ka **aakhri** node (`i == levelSize - 1`) lo |
| 637. Average of Levels | level ka sum / `levelSize` |
| 515. Largest Value in Each Row | level ka max |
| 104. Maximum Depth | har round pe `depth++`, end mein `depth` return |
| 111. Minimum Depth | pehla leaf node milte hi current depth return |

## Yaad rakhne ki line

> **Queue se BFS, aur `levelSize` se levels alag.**
> Round shuru: size fix karo → utne `poll` karo → bachche `offer` karo → level ko result mein daalo.
