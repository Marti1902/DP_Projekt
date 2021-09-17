import java.util.*;
import java.io.*;
public class TotalLinker extends Linker {
    boolean haveToken;
    final int leader = 0;
    IntLinkedList pendingQ = new IntLinkedList();
    public TotalLinker(String basename, int id, int numProc) throws Exception {
        super(basename, id, numProc);
        haveToken = (myId == leader);
    }
    public synchronized void sendMsg(int destId, String tag, String msg) {    
        if (myId == leader)
            super.sendMsg(destId, tag, msg);
        else{
            super.sendMsg(leader, "Id", destId); 
            super.sendMsg(leader, tag, msg);
        }
    }
    public synchronized void multicast(IntLinkedList destIds, String tag, String msg){
        if (myId == leader)
            super.multicast(destIds, tag, msg);
        else{
            super.sendMsg(leader, "Ids", destIds);
            super.sendMsg(leader, tag, msg);
        }
    }
    public Msg receiveMsg(int fromId) throws IOException  {
        if (myId == leader){
            checkPendingQ();
            //sad ode on triba multicastat poruku dalje
        }     
        //ako nije leader ne triba nista radit s porukon valjda  
    }
    public int getMyId() { return myId; }
    public int getNumProc() { return N; }
    public void close() {connector.closeSockets();}
}
