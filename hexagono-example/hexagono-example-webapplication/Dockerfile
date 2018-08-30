FROM jboss/wildfly:13.0.0.Final

USER jboss
RUN $JBOSS_HOME/bin/add-user.sh admin admin@123 --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--debug"]
