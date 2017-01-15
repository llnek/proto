/**
 * Copyright (c) 2013-2017, Kenneth Leung. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 */


package czlab.wabbit.mock.jms;


import javax.jms.ConnectionConsumer;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;


/**
 * @author Kenneth Leung
 *
 */
@SuppressWarnings("unused")
public class MockTopicConnection implements TopicConnection {

  public MockTopicConnection(String n, String p) {
    _user=n;
    _pwd=p;
  }

  private volatile boolean _active = true;
  private String _user;
  private String _pwd;

  public boolean isActive() { return _active; }

  @Override
  public TopicSession createTopicSession(boolean b, int i) throws JMSException {
    TopicSession s= new MockTopicSession(this, b, i);
    s.run();
    return s;
  }

  @Override
  public ConnectionConsumer createConnectionConsumer(Topic topic, String s, ServerSessionPool serverSessionPool, int i) throws JMSException {
    return null;
  }

  @Override
  public Session createSession(boolean b, int i) throws JMSException {
    return null;
  }

  @Override
  public String getClientID() throws JMSException {
    return null;
  }

  @Override
  public void setClientID(String s) throws JMSException {

  }

  @Override
  public ConnectionMetaData getMetaData() throws JMSException {
    return null;
  }

  @Override
  public ExceptionListener getExceptionListener() throws JMSException {
    return null;
  }

  @Override
  public void setExceptionListener(ExceptionListener exceptionListener) throws JMSException {

  }

  @Override
  public void start() throws JMSException {
    _active=true;
  }

  @Override
  public void stop() throws JMSException {
    _active=false;
  }

  @Override
  public void close() throws JMSException {
    stop();
  }

  @Override
  public ConnectionConsumer createConnectionConsumer(Destination destination, String s, ServerSessionPool serverSessionPool, int i) throws JMSException {
    return null;
  }

  @Override
  public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String s, String s2, ServerSessionPool serverSessionPool, int i) throws JMSException {
    return null;
  }
}



