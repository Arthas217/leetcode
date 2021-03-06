package leetcode;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 208. 实现 Trie (前缀树、字典树）
 * @Date 2021/3/6 16:33
 */
public class Trie {

    public Trie() {
    }

    /**
     * 定义字典树节点
     */
    private class TrieNode {
        // 26个字母
        TrieNode[] childs = new TrieNode[26];
        // 类似是叶子节点，是某条路径，代表某个字符串
        boolean isLeaf;
    }

    private TrieNode root = new TrieNode();

    /**
     * 插入word到字典树
     */
    public void insert(String word) {
        insert(root, word);
    }

    private void insert(TrieNode root, String word) {
        if (root == null) {
            return;
        }
        //终止条件
        if (word.length() == 0) {
            root.isLeaf = true;
            return;
        }
        // 相对位置（index =0代表是a字符）
        int index = indexForChar(word.charAt(0));
        if (root.childs[index] == null) {
            root.childs[index] = new TrieNode();
        }
        // 递归函数
        insert(root.childs[index], word.substring(1));
    }

    private int indexForChar(char c) {
        return c - 'a';
    }


    /**
     * 在Trie树中查找一个字符串
     */
    public boolean search(String word) {
        return search(root, word);
    }

    private boolean search(TrieNode root, String word) {
        if (root == null) {
            return false;
        }
        if (word.length() == 0) {
            return root.isLeaf;
        }
        int index = indexForChar(word.charAt(0));
        return search(root.childs[index], word.substring(1));
    }


    /**
     * 判断某字符串是匹配前缀树
     */
    public boolean startsWith(String prefix) {
        return startsWith(root, prefix);
    }

    private boolean startsWith(TrieNode root, String prefix) {
        if (root == null) {
            return false;
        }
        if (prefix.length() == 0) {
            return true;
        }
        int index = indexForChar(prefix.charAt(0));
        return startsWith(root.childs[index], prefix.substring(1));
    }


    public static void main(String[] args) {
        String[] words = {"how", "hi", "her", "hello", "so", "see"};
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        boolean exist = trie.search("hello");
        System.out.println(exist);

        boolean pipei = trie.startsWith("her");
        System.out.println(pipei);
    }
}
