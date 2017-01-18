/**
 * Copyright (c) 2013-2017, Kenneth Leung. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 */

package czlab.proto.mock.jms;

import static czlab.proto.mock.jms.MockUtils.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

/**
 * @author Kenneth Leung
 *
 */
@SuppressWarnings("unused")
public class MockQueueSession implements QueueSession {

  private Map<String,QueueReceiver> _subs= new HashMap<String,QueueReceiver>();
  private volatile boolean _active =false;

  private MockQueueConnection _conn;
  private boolean _tx;
  private int _ack;

  public MockQueueSession(MockQueueConnection c, boolean tx, int ack) {
    _conn = c;
    _tx= tx;
    _ack= ack;
  }

  @Override
  public BytesMessage createBytesMessage() throws JMSException {
    return null;
  }

  @Override
  public MapMessage createMapMessage() throws JMSException {
    return null;
  }

  @Override
  public Message createMessage() throws JMSException {
    return null;
  }

  @Override
  public ObjectMessage createObjectMessage() throws JMSException {
    return null;
  }

  @Override
  public ObjectMessage createObjectMessage(Serializable serializable) throws JMSException {
    return null;
  }

  @Override
  public StreamMessage createStreamMessage() throws JMSException {
    return null;
  }

  @Override
  public TextMessage createTextMessage() throws JMSException {
    return null;
  }

  @Override
  public TextMessage createTextMessage(String s) throws JMSException {
    return new MockTextMessage(s);
  }

  @Override
  public boolean getTransacted() throws JMSException {
    return false;
  }

  @Override
  public int getAcknowledgeMode() throws JMSException {
    return 0;
  }

  @Override
  public void commit() throws JMSException {

  }

  @Override
  public void rollback() throws JMSException {

  }

  @Override
  public void close() throws JMSException {
    _active=false;
  }

  @Override
  public void recover() throws JMSException {

  }

  @Override
  public MessageListener getMessageListener() throws JMSException {
    return null;
  }

  @Override
  public void setMessageListener(MessageListener messageListener) throws JMSException {

  }

  @Override
  public void run() {
    String s= System.getProperty("czlab.proto.mock.jms.loopsecs","1");
    final int v= 1000 * Integer.valueOf(s);
    _active=true;
    Thread t= new Thread(new Runnable() {
      public void run() {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        while (_active && _conn.isActive())    try  {
          trigger();
          Thread.sleep(v);
        } catch (Throwable e) {}
        _active=false;
      }
    });
    t.setDaemon(true);
    t.start();
  }

  private void trigger() throws Exception {

    Message m= createTextMessage( makeNewTextMsg_plus());
    m.setJMSType("Mock-Queue-Type");

    for (QueueReceiver r: _subs.values() ) {
      MessageListener ml= r.getMessageListener() ;
      if (ml != null) {
        ml.onMessage(m);
      }
    }

  }

  @Override
  public MessageProducer createProducer(Destination destination) throws JMSException {
    return null;
  }

  @Override
  public MessageConsumer createConsumer(Destination destination) throws JMSException {
    return null;
  }

  @Override
  public MessageConsumer createConsumer(Destination destination, String s) throws JMSException {
    return null;
  }

  @Override
  public MessageConsumer createConsumer(Destination destination, String s, boolean b) throws JMSException {
    return null;
  }

  @Override
  public Queue createQueue(String s) throws JMSException {
    return null;
  }

  @Override
  public Topic createTopic(String s) throws JMSException {
    return null;
  }

  @Override
  public TopicSubscriber createDurableSubscriber(Topic topic, String s) throws JMSException {
    return null;
  }

  @Override
  public TopicSubscriber createDurableSubscriber(Topic topic, String s, String s2, boolean b) throws JMSException {
    return null;
  }

  @Override
  public QueueReceiver createReceiver(Queue q) throws JMSException {
    QueueReceiver r= new MockQueueReceiver(q);
    _subs.put( q.getQueueName(), r);
    return r;
  }

  @Override
  public QueueReceiver createReceiver(Queue queue, String s) throws JMSException {
    return null;
  }

  @Override
  public QueueSender createSender(Queue queue) throws JMSException {
    return null;
  }

  @Override
  public QueueBrowser createBrowser(Queue queue) throws JMSException {
    return null;
  }

  @Override
  public QueueBrowser createBrowser(Queue queue, String s) throws JMSException {
    return null;
  }

  @Override
  public TemporaryQueue createTemporaryQueue() throws JMSException {
    return null;
  }

  @Override
  public TemporaryTopic createTemporaryTopic() throws JMSException {
    return null;
  }

  @Override
  public void unsubscribe(String s) throws JMSException {

  }
}



