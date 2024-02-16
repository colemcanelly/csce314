
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 6 Problem 3
   First, study how this class should work with the test code in SimMain.java
   carefully!

   Student Name:
   Student UIN:
   Acknowledgements:
*/

import java.util.*;

class SimBox implements Runnable {
  static final int MAX_SIZE = 10;

  class Message {
    String sender;
    String recipient;
    String msg;

    Message(String sender, String recipient, String msg) {
      this.sender = sender;
      this.recipient = recipient;
      this.msg = msg;
    }

    @Override
    public String toString() {
      return "From " + sender + " to " + recipient + ": " + msg;
    }
  }

  private final LinkedList<Message> messages;
  private LinkedList<Message> myMessages;
  private String myId;
  private boolean stop = false;

  // private Lock edit_messages_lock;
  // private Lock edit_my_messages_lock;

  public SimBox(String myId) {
    messages = new LinkedList<Message>();
    this.myId = myId;
    this.myMessages = new LinkedList<Message>();
    // this.edit_messages_lock = new ReentrantLock();
    // this.edit_my_messages_lock = new ReentrantLock();
    new Thread(this).start();
  }

  public SimBox(String myId, SimBox s) {
    this.messages = s.messages;
    this.myId = myId;
    this.myMessages = new LinkedList<Message>();
    // this.edit_messages_lock = new ReentrantLock();
    // this.edit_my_messages_lock = new ReentrantLock();
    new Thread(this).start();
  }

  public String getId() {
    return myId;
  }

  public void stop() {
    this.stop = true;
  }

  // deposit
  public void send(String recipient, String msg) {
    // add a message to the shared message queue (messages)
    // you will have to synchronize the message queue
    synchronized (messages) {
      Message m = new Message(this.myId, recipient, msg);
      this.messages.add(m);
    }
  }

  // withdraw
  public List<String> retrieve() {
    // return the contents of myMessages
    // and empty myMessages
    // you will have to synchronize myMessages
    // each message should be in the following format:
    // From (the sender) to (the recipient) (actual message)
    // this.edit_my_messages_lock.lock();
    // try {
    synchronized (myMessages)
    {
      List<String> ret = new LinkedList<String>();
      for (Message m : this.myMessages) {
        ret.add(m.toString());
      }
      this.myMessages.clear();
      return ret;
    }
  }

  public void run() {
    // loop forever
    // 1. Approximately once every second move all messages
    // addressed to this mailbox from the shared message queue
    // to the private myMessages queue
    // To do so, you need to synchronize messages and myMessages.
    // Furthermore, you need to explicitly use the iterator() of messages
    // with a while loop. A for-each loop will not work here.
    // 2. Also approximately once every second, if the message
    // queue has more than MAX_SIZE messages, delete oldest messages
    // so that size is at most MAX_SIZE. This part of code is provided
    // below.

    for (;;) { // loop forever
      // synchronize messages and myMessages
      synchronized (messages) {
        synchronized (myMessages) {
          // have the iterator of messages referred by iter of
          // type Iterator<Message>
          Iterator<Message> it = this.messages.iterator();
          // while there is more to access on iter, access the message
          while (it.hasNext()) {
            Message m = it.next();
            // System.out.println(m);
            // if the message's recipient is equal to myId, then remove the
            // message from messages and add the message to myMessages
            if ((m.recipient).equals(this.myId)) {
              this.myMessages.add(m);
              it.remove();
            }
          }
        }
        // end of synchronized myMessages
        while (messages.size() > MAX_SIZE) { messages.removeFirst(); }
      }
      // end of synchronized messages
      if (stop) return;
      try { Thread.sleep(1000); } catch (InterruptedException e) {}
    } // endfor
  } // end run()
} // end SimBox
