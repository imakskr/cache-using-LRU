import java.util.LinkedList;
import java.util.Scanner;

class LRUCache {

	private LinkedList<Integer> cache=new LinkedList<Integer>(); //stores the process ID which is of type int.
	
	private final int MAX_CACHE_SIZE=10; //limiting the size of cache to 10 elements.
	private int currentMaxSize=0; //current total size of the list.
	
/** -------------Util methods which can be used later--------------- */
	
	private int getIndexOfElement(int data) {		
		for(int i=0; i<cache.size(); i++) {
			if(cache.get(i)==data) {
				return i;
			}
		}
		return -1;		
	}
	private void clearCache() {
		cache=new LinkedList<Integer>();
		currentMaxSize=0;
	}
	
/** ---------------Used methods------------------------ */	
	
	private void swapAsRecentlyUsed(int processId) {
		cache.remove((Integer)processId);
		cache.addFirst(processId);	
	}
	private void addAsRecentlyUsed(int processId) {
		//remove the last element from the list and add the new process at first position in the list
		if(cache.isEmpty()) {
			cache.add(processId);
			currentMaxSize++;
		} 
		else {			
			if(currentMaxSize < MAX_CACHE_SIZE) {
				cache.addFirst(processId);
				currentMaxSize++;
			}else {
				cache.removeLast();
				cache.addFirst(processId);
			}			
		}
	}
	private boolean isProcessAvailableInCache(int processId) {
		if(cache.isEmpty()) {
			return false;
		}
		if(cache.contains(processId)) {
			return true;
		}
		return false;		
	}	
	private void printCacheItems() {
		for(int i=0; i<cache.size(); i++) {
			System.out.print(cache.get(i)+"  ");
		}
		System.out.println();
	}
	public void getProcess(int processId) {
		if(cache.isEmpty()) {
			cache.add(processId);
			currentMaxSize++;
		}else {
			if(isProcessAvailableInCache(processId)) {
				swapAsRecentlyUsed(processId);
			}else {
				addAsRecentlyUsed(processId);
			}
		}
		printCacheItems();	
	}
}

class LRUCacheImplementation{
	public static void main(String a[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter process Id");
		int pid;
		LRUCache lc=new LRUCache();
		do {		
			pid=sc.nextInt();
			lc.getProcess(pid);			
		}while(pid!=0);
		sc.close();
	}
}
