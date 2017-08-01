/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2017 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.di.repository;

import java.util.List;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleSecurityException;

/**
 * This is the interface to the security provider for the repositories out there.
 * <p>
 *
 * This allows the repository to transparently implement any kind of authentication supported by Kettle.
 *
 * @author matt
 *
 */
public interface RepositorySecurityProvider extends IRepositoryService {

  String CREATE_CONTENT_ROLE = "org.pentaho.di.creator"; //$NON-NLS-1$

  String READ_CONTENT_ROLE = "org.pentaho.di.reader"; //$NON-NLS-1$

  String ADMINISTER_SECURITY_ROLE = "org.pentaho.di.securityAdministrator"; //$NON-NLS-1$

  String CREATE_CONTENT_ACTION = "org.pentaho.repository.create"; //$NON-NLS-1$

  String READ_CONTENT_ACTION = "org.pentaho.repository.read"; //$NON-NLS-1$

  String EXECUTE_CONTENT_ACTION = "org.pentaho.repository.execute"; //$NON-NLS-1$

  String SCHEDULE_CONTENT_ACTION = "org.pentaho.scheduler.manage";

  String ADMINISTER_SECURITY_ACTION = "org.pentaho.security.administerSecurity"; //$NON-NLS-1$

  String NAMESPACE = "org.pentaho"; //$NON-NLS-1$

  /**
   * @return the user information set on the security provider
   */
  public IUser getUserInfo();

  /**
   * Validates the supplied operation.
   *
   * @throws KettleSecurityException
   *           in case the provided user is not know or the password is incorrect
   * @throws KettleException
   *           in case the action couldn't be validated because of an unexpected problem.
   */
  public void validateAction( RepositoryOperation... operations ) throws KettleException, KettleSecurityException;

  /**
   * @return true if the repository or the user is read only
   */
  public boolean isReadOnly();

  /**
   * @return true if this repository supports file locking and if the user is allowed to lock a file
   */
  public boolean isLockingPossible();

  /**
   * @return true if the repository supports revisions AND if it is possible to give version comments
   */
  public boolean allowsVersionComments( String fullPath );

  /**
   * @return true if version comments are mandatory if allowed.
   */
  public boolean isVersionCommentMandatory();

  /**
   * Retrieves all users in the system
   *
   * @return list of username
   * @throws KettleSecurityException
   *           in case anything went wrong
   */
  public List<String> getAllUsers() throws KettleException;

  /**
   * Retrieves all roles in the system
   *
   * @return list of role
   * @throws KettleSecurityException
   *           in case anything went wrong
   */
  public List<String> getAllRoles() throws KettleException;

  public String[] getUserLogins() throws KettleException;

  /**
   * Determines whether versioning should be enabled in the UI for the given fully qualified file/path.
   * If set to false, the repository may still support versioning, but the versioning will not be
   * apparent in the UI.
   * @return true if version are enabled, false if they if are not
   */
  public boolean isVersioningEnabled( String fullPath );

}
