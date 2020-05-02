module ca.donaldmartin.netutils {
    requires org.apache.tomcat.embed.core;
    requires org.slf4j;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;

    opens ca.donaldmartin.netutils to spring.core;
    exports ca.donaldmartin.netutils to spring.beans, spring.context;
    exports ca.donaldmartin.netutils.controller to spring.beans, spring.web;
}