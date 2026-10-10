public class HashTable {
    private static class HashNode {
        int key;
        HashNode next;
        HashNode(int key) {
            this.key = key;
        }
    }
    private HashNode[] buckets;
    private int capacity;
    private int size = 0;
    private static final double LOAD_FACTOR = 0.75;

    public HashTable(int capacity){
        this.capacity = capacity;
        this.buckets = new HashNode[capacity];
    }

    private int getBucketIndex(int key){
        return Math.abs(key) % capacity;
    }

    public void insert(int key){
        int idx = getBucketIndex(key);
        HashNode head = buckets[idx];

        while (head != null){
            if( head.key == key) return;
            head = head.next;
        }

        HashNode newNode = new HashNode(key);
        newNode.next = buckets[idx];
        buckets[idx] = newNode;
        size++;

        if (size > capacity * LOAD_FACTOR){
            resize();
        }
    }

    public boolean search(int key){
        int idx = getBucketIndex(key);
        HashNode head = buckets[idx];
        while (head != null) {
            if (head.key == key) return true;
            head = head.next;
        }
        return false;
    }

    public void delete(int key){
        int idx = getBucketIndex(key);
        HashNode head = buckets[idx];
        HashNode prev = null;

        while(head != null){
            if (head.key == key){
                if (prev != null) {
                    prev.next = head.next;
                }
                else {
                    buckets[idx] = head.next;
                }
                size--;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    private void resize() {
        HashNode[] oldBuckets = buckets;
        capacity = capacity * 2;
        buckets = new HashNode[capacity];

        for (HashNode head : oldBuckets){
            while (head != null) {
                HashNode next = head.next;

                int newIdx = getBucketIndex(head.key);
                head.next = buckets[newIdx];
                buckets[newIdx] = head;

                head = next;
            }
        }
    }

}
