export JAVA_HOME=/opt/java/openjdk
echo "JAVA_HOME=$JAVA_HOME"
sleep 30
#while true
#do
#  /opt/flink/bin/flink run -c dev.rvr.WordCount /opt/flink/usrlib/artifact/flink-app-0.0.1.jar
#  /opt/flink/bin/flink run -c dev.rvr.BatchToElasticsearch /opt/flink/usrlib/artifact/flink-app-0.0.1.jar
/opt/flink/bin/flink run -c dev.rvr.StreamingWithSnapshot /opt/flink/usrlib/artifact/flink-app-0.0.1.jar
#  sleep 300
#done