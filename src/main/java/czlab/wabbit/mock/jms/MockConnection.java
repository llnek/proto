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

import javax.jms.Connection;
import javax.jms.ConnectionConsumer;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;

/**
 * @author Kenneth Leung
 *
 */
@SuppressWarnings("unused")
public class MockConnection implements Connection {

  public MockConnection(String uid, String pwd) {
    _uid= uid;
    _pwd=pwd;
  }

  private String _uid;
  private String _pwd;

  public MockConnection() {
    this("","");
  }

  public void close() {}

  public ConnectionConsumer createConnectionConsumer(Destination d, String a1, ServerSessionPool p, int a3) {
    return null;
  }

  public ConnectionConsumer createDurableConnectionConsumer(Topic t, String a1, String a2, ServerSessionPool p,
      int a4) {
    return null;
  }

  public Session createSession(boolean b, int ack) {
    return new MockSession(b, ack);
  }

  public String getClientID() { return ""; }

  public ExceptionListener getExceptionListener() {
    return null;
  }

  public ConnectionMetaData getMetaData() {
    return null;
  }

  public void setClientID(String a) {}

  public void setExceptionListener(ExceptionListener e) {}

  public void start() {}

  public void stop() {}

}


