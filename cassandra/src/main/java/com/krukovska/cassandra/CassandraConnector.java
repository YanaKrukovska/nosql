package com.krukovska.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CassandraConnector {

    private static final Logger logger = LogManager.getLogger(CassandraConnector.class);

    private Cluster cluster;
    private Session session;

    public void connect(String node, Integer port) {
        logger.info("Connecting to cassandra client at {}:{}", node, port);

        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        Metadata metadata = cluster.getMetadata();
        logger.info("Connected to cluster: {}", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            logger.info("Datacenter: {}. Host: {}. Rack: {}", host.getDatacenter(), host.getAddress(), host.getRack());
        }
        session = cluster.connect();

    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        logger.info("Closing cassandra client");
        session.close();
        cluster.close();
    }
}
