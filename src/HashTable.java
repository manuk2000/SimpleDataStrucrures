import java.util.Arrays;
import java.util.LinkedList;

public class HashTable<T extends Comparable> {
    public static void main(String[] args) {
        HashTable<Integer> hashtable = new HashTable<>();

        // Scenario 1: Collision-Heavy Insertions
        System.out.println("Scenario 1: Collision-Heavy Insertions");
        for (int i = 0; i < 10; i++) {
            hashtable.insert(i * 10);
        }
        printHashTable(hashtable);

        // Scenario 2: Resizing
        System.out.println("Scenario 2: Resizing");
        for (int i = 0; i < 50; i++) {
            hashtable.insert(i);
        }
        printHashTable(hashtable);

        // Scenario 3: Load Factor Exceeding Threshold
        System.out.println("Scenario 3: Load Factor Exceeding Threshold");
        for (int i = 0; i < 80; i++) {
            hashtable.insert(i);
        }
        printHashTable(hashtable);
    }

    private static void printHashTable(HashTable<Integer> hashtable) {
        System.out.println("Hash Table Contents:");
        for (LinkedList<Integer> bucket : hashtable.hashTable) {
            if (bucket != null) {
                for (Integer value : bucket) {
                    System.out.println(value);
                }
            }
        }
    }


    private static final double LOAD_FUCTOROF_LEVEL_ONE
            = 0.8;
    private static final int START_SIZE = 2;
    private int elemCount = 0;
    private LinkedList<T>[] hashTable;

    public HashTable() {
        hashTable = new LinkedList[START_SIZE];
    }

    private void initHashTable() {
        hashTable = (LinkedList<T>[]) Arrays.stream(hashTable).map(list -> new LinkedList<>()).toArray();
//        for (ArrayList<T> ts : hashTable) {
//            ts = new ArrayList<>();
//        }
    }

    public void insert(T val) {
        insert(val, hashTable);
    }

    public void insert(T val, LinkedList<T>[] hashTable) {
        if (elemCount / hashTable.length >= LOAD_FUCTOROF_LEVEL_ONE) {
            hashTable = reHash();
        }
        int i = universalHash1(val, hashTable.length);
        if (hashTable[i] == null) {
            hashTable[i] = new LinkedList<>();
        }
        hashTable[i].add(val);
        ++elemCount;
    }

    private LinkedList<T>[] reHash() {
        int newLength = findNextPrimitiveNumber(hashTable.length * 2);
        LinkedList<T>[] newHashTable = new LinkedList[newLength];
        int count = elemCount;
        elemCount = 0;
        for (int i = 0; i < hashTable.length && count > 0; ++i) {
            if (hashTable[i] != null) {
//                newHashTable[i] = new LinkedList<>();

                for (T val : hashTable[i]) {
                    insert(val , newHashTable);
                    --count;
                }
            }
        }
        hashTable = newHashTable;
        return hashTable;
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    private int findNextPrimitiveNumber(int start) {
        int nextNumber = start + 1;
        while (!isPrime(nextNumber)) {
            nextNumber++;
        }
        return nextNumber;
    }

    public static int stringHash(String key, int tableSize) {
        int hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }

        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        return Math.abs(hash) % tableSize;
    }

    public static int mediumIntHash(int key, int tableSize) {
        double A = (Math.sqrt(5) - 1) / 2; // A constant for multiplication
        double product = key * A;
        double fractionalPart = product - Math.floor(product);

        return (int) (tableSize * fractionalPart);
    }

    public int universalHash1(Object key, int tableSize) {
        int hashCode = key.hashCode();
        double A = (Math.sqrt(5) - 1) / 2; // A constant for multiplication
        double product = hashCode * A;
        double fractionalPart = product - Math.floor(product);

        return (int) (tableSize * fractionalPart);
    }

    public int universalHash2(Object key, int tableSize) {
        int hashCode = key.hashCode();
        long hash = 5381; // Initial hash value

        String hashCodeString = String.valueOf(hashCode);
        for (int i = 0; i < hashCodeString.length(); i++) {
            hash = ((hash << 5) + hash) + hashCodeString.charAt(i);
        }

        return (int) (hash % tableSize);
    }

}
