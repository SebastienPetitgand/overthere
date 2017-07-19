/**
 * Copyright (c) 2008-2016, XebiaLabs B.V., All rights reserved.
 *
 *
 * Overthere is licensed under the terms of the GPLv2
 * <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most XebiaLabs Libraries.
 * There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
 * this software, see the FLOSS License Exception
 * <http://github.com/xebialabs/overthere/blob/master/LICENSE>.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation; version 2
 * of the License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
 * Floor, Boston, MA 02110-1301  USA
 */
package com.xebialabs.overthere.ssh;

import com.xebialabs.overthere.ConnectionOptions;
import com.xebialabs.overthere.UnixCloudHost;
import com.xebialabs.overthere.WindowsCloudHost;
import com.xebialabs.overthere.itest.OverthereConnectionItestBase;
import org.testng.annotations.Test;

import static com.xebialabs.overthere.ConnectionOptions.*;
import static com.xebialabs.overthere.OperatingSystemFamily.WINDOWS;
import static com.xebialabs.overthere.WindowsCloudHost.ADMINISTRATIVE_WINDOWS_USER_PASSWORD;
import static com.xebialabs.overthere.WindowsCloudHost.ADMINISTRATIVE_WINDOWS_USER_USERNAME;
import static com.xebialabs.overthere.proxy.ProxyConnection.PROXY_PROTOCOL;
import static com.xebialabs.overthere.proxy.ProxyConnection.PROXY_TYPE;
import static com.xebialabs.overthere.ssh.SshConnectionBuilder.CONNECTION_TYPE;
import static com.xebialabs.overthere.ssh.SshConnectionBuilder.SSH_PROTOCOL;
import static com.xebialabs.overthere.ssh.SshConnectionType.SFTP_CYGWIN;
import static java.net.Proxy.Type.HTTP;

@Test
public class SshSftpCygwinConnectionWithAdministrativeUserOverHttpProxyItest extends OverthereConnectionItestBase {

    @Override
    protected String getProtocol() {
        return SSH_PROTOCOL;
    }

    @Override
    protected ConnectionOptions getOptions() {
        ConnectionOptions proxyOptions = new ConnectionOptions();
        proxyOptions.set(PROTOCOL, PROXY_PROTOCOL);
        proxyOptions.set(PROXY_TYPE, HTTP);
        proxyOptions.set(ADDRESS, UnixCloudHost.getHostName());
        proxyOptions.set(PORT, 8888);

        ConnectionOptions options = new ConnectionOptions();
        options.set(OPERATING_SYSTEM, WINDOWS);
        options.set(CONNECTION_TYPE, SFTP_CYGWIN);
        options.set(ADDRESS, WindowsCloudHost.getHostName());
        options.set(PORT, 22);
        options.set(USERNAME, ADMINISTRATIVE_WINDOWS_USER_USERNAME);
        options.set(PASSWORD, ADMINISTRATIVE_WINDOWS_USER_PASSWORD);
        options.set(JUMPSTATION, proxyOptions);
        return options;
    }

    @Override
    protected String getExpectedConnectionClassName() {
        return SshSftpCygwinConnection.class.getName();
    }

}
