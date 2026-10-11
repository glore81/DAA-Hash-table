import java.util.Random;

public class Benchmark {
    public static void main(String[] args){
        int[] sizes = {100, 1000, 10000};
        int seed = 12;
        Random random = new Random(seed);


        System.out.println("Starting Performance Benchmark: BST vs Hash Table\n");

        for(int size : sizes) {
            BST bst = new BST();
            HashTable hashTable = new HashTable(size);
            int[] data = new int[size];

            for(int i = 0; i < size; i++){
                data[i] = random.nextInt(size*10);
            }

            System.out.println();

            System.out.println("====================================================================");
            System.out.println(" Array Size: " + size + " elements");
            System.out.println("====================================================================");

            long startBstInsert = System.nanoTime();
            for(int key : data) {
                bst.insert(key);
            }
            double bstInsertTime = (System.nanoTime() - startBstInsert) / 1_000_000.0;

            long startBstSearch = System.nanoTime();
            for(int key : data) {
                bst.search(key);
            }
            double bstSearchTime = (System.nanoTime() - startBstSearch) / 1_000_000.0;

            long startBstDelete = System.nanoTime();
            for(int key : data) {
                bst.delete(key);
            }
            double bstDeleteTime = (System.nanoTime() - startBstDelete) / 1_000_000.0;

            long startHashTInsert = System.nanoTime();
            for(int key : data) {
                hashTable.insert(key);
            }
            double hashTInsertTime = (System.nanoTime() - startHashTInsert) / 1_000_000.0;

            long startHashTSearch = System.nanoTime();
            for(int key : data) {
                hashTable.search(key);
            }
            double hashTSearchTime = (System.nanoTime() - startHashTSearch) / 1_000_000.0;

            long startHashTDelete = System.nanoTime();
            for(int key : data) {
                hashTable.delete(key);
            }
            double hashTDeleteTime = (System.nanoTime() - startHashTDelete) / 1_000_000.0;

            System.out.printf("%-15s | %-10s | %-10s | %-10s | %-10s\n",
                    "Structure", "Insert(ms)", "Search(ms)", "Delete(ms)", "Total(ms)");
            System.out.println("--------------------------------------------------------------------");

            System.out.printf("%-15s | %-10.4f | %-10.4f | %-10.4f | %-10.4f\n",
                    "BST", bstInsertTime, bstSearchTime, bstDeleteTime,
                    (bstInsertTime + bstSearchTime + bstDeleteTime));

            System.out.printf("%-15s | %-10.4f | %-10.4f | %-10.4f | %-10.4f\n",
                    "Hash Table", hashTInsertTime, hashTSearchTime, hashTDeleteTime,
                    (hashTInsertTime + hashTSearchTime + hashTDeleteTime));
            System.out.println();
        }
    }
}
