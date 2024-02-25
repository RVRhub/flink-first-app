export JAVA_HOME=/opt/java/openjdk
echo "JAVA_HOME=$JAVA_HOME"
sleep 30

#  /opt/flink/bin/flink run -c dev.rvr.WordCount /opt/flink/usrlib/artifact/flink-app-0.0.1.jar
#  /opt/flink/bin/flink run -c dev.rvr.BatchToElasticsearch /opt/flink/usrlib/artifact/flink-app-0.0.1.jar
/opt/flink/bin/flink run -c dev.rvr.KafkaStreaming /opt/flink/usrlib/artifact/flink-app-0.0.1.jar
