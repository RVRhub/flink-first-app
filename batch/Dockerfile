FROM flink:latest

COPY script.sh /opt/flink/usrlib/script.sh
RUN chmod +x /opt/flink/usrlib/script.sh

CMD /opt/flink/bin/jobmanager.sh start-foreground & /opt/flink/usrlib/script.sh & wait
