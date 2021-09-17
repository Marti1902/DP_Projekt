public class CentLockTester {
 public static void main(String[] args) throws Exception {
 Linker comm = null;
 try {
 String baseName = args[0];
 int myId = Integer.parseInt(args[1]);
 int numProc = Integer.parseInt(args[2]);
 comm = new Linker(baseName, myId, numProc);
 Lock lock = new CentMutex(comm);
 for (int i = 0; i  numProc; i++)
 if (i != myId)
 (new ListenerThread(i, (MsgHandler)lock)).start();
 while (true) {
 if (myId == 0) {
 Util.mySleep(8000);
continue;
 }
 System.out.println(myId +  is not in CS);
 Util.mySleep(8000);
lock.requestCS();
 System.out.println(myId +  is in CS );
 Util.mySleep(8000);
 lock.releaseCS();
 }
 }
 catch (InterruptedException e) {
 if (comm != null) comm.close();
 }
 catch (Exception e) {
 System.out.println(e);
 e.printStackTrace();
 }
 }
}
