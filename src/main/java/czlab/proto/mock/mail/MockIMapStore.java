/**
 * Copyright (c) 2013-2017, Kenneth Leung. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 */

package czlab.proto.mock.mail;

import javax.mail.MessagingException;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;


/**
 * @author Kenneth Leung
 *
 */
@SuppressWarnings("unused")
public class MockIMapStore extends MockStore {

  /**
   */
  public MockIMapStore(Session s,URLName url) {
    super(s, url);
    _name="imap";
    _dftPort=143;
  }

  /**
   */
  @Override
  public Folder getFolder(String name) {
    checkConnected();
    return new MockIMapFolder(name,this);
  }

  /**
   */
  @Override
  public Folder getFolder(URLName url) {
    checkConnected();
    return new MockIMapFolder( url.getFile(), this);
  }

}


