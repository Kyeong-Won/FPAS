package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "slave_master_info")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "slave_master_info")
public class SlaveMasterInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The channel on which the slave is connected to a source. Used in Multisource Replication
     */
    @Id
    @Column(name = "Channel_name", nullable = false)
    private String channelName;

    /**
     * Number of lines in the file.
     */
    @Column(name = "Number_of_lines", nullable = false)
    private Long numberOfLines;

    /**
     * The master log position of the last read event.
     */
    @Column(name = "Master_log_pos", nullable = false)
    private Long masterLogPos;

    /**
     * The host name of the master.
     */
    @Column(name = "Host")
    private String host;

    /**
     * The network port used to connect to the master.
     */
    @Column(name = "Port", nullable = false)
    private Long port;

    /**
     * The period (in seconds) that the slave will wait before trying to reconnect to the master.
     */
    @Column(name = "Connect_retry", nullable = false)
    private Long connectRetry;

    /**
     * Indicates whether the server supports SSL connections.
     */
    @Column(name = "Enabled_ssl", nullable = false)
    private Boolean enabledSsl;

    /**
     * Whether to verify the server certificate.
     */
    @Column(name = "Ssl_verify_server_cert", nullable = false)
    private Boolean sslVerifyServerCert;

    @Column(name = "Heartbeat", nullable = false)
    private Float heartbeat;

    /**
     * Number of reconnect attempts, to the master, before giving up.
     */
    @Column(name = "Retry_count", nullable = false)
    private Long retryCount;

    /**
     * Indicates whether GTIDs will be used to retrieve events from the master.
     */
    @Column(name = "Enabled_auto_position", nullable = false)
    private Boolean enabledAutoPosition;

    /**
     * The name of the master binary log currently being read from the master.
     */
    @Column(name = "Master_log_name", nullable = false)
    private String masterLogName;

    /**
     * The user name used to connect to the master.
     */
    @Column(name = "User_name")
    private String userName;

    /**
     * The password used to connect to the master.
     */
    @Column(name = "User_password")
    private String userPassword;

    /**
     * The file used for the Certificate Authority (CA) certificate.
     */
    @Column(name = "Ssl_ca")
    private String sslCa;

    /**
     * The path to the Certificate Authority (CA) certificates.
     */
    @Column(name = "Ssl_capath")
    private String sslCapath;

    /**
     * The name of the SSL certificate file.
     */
    @Column(name = "Ssl_cert")
    private String sslCert;

    /**
     * The name of the cipher in use for the SSL connection.
     */
    @Column(name = "Ssl_cipher")
    private String sslCipher;

    /**
     * The name of the SSL key file.
     */
    @Column(name = "Ssl_key")
    private String sslKey;

    /**
     * Displays which interface is employed when connecting to the MySQL server
     */
    @Column(name = "Bind")
    private String bind;

    /**
     * The number of server IDs to be ignored, followed by the actual server IDs
     */
    @Column(name = "Ignored_server_ids")
    private String ignoredServerIds;

    /**
     * The master server uuid.
     */
    @Column(name = "Uuid")
    private String uuid;

    /**
     * The file used for the Certificate Revocation List (CRL)
     */
    @Column(name = "Ssl_crl")
    private String sslCrl;

    /**
     * The path used for Certificate Revocation List (CRL) files
     */
    @Column(name = "Ssl_crlpath")
    private String sslCrlpath;

    /**
     * Tls version
     */
    @Column(name = "Tls_version")
    private String tlsVersion;

}
