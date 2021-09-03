FROM ubuntu:14.04
RUN apt-get update -y
RUN apt-get install -y apache2
RUN echo 'ServerName webserver' >> /etc/apache2/apache2.conf
RUN a2enmod proxy && a2enmod proxy_http && a2enmod proxy_balancer && a2enmod lbmethod_byrequests
RUN echo '<VirtualHost *:80> \n\
    ProxyRequests Off \n\
    ProxyPreserveHost On \n\
    <Proxy balancer://mycluster> \n\
        BalancerMember http://alpha:8080 \n\
        BalancerMember http://betta:8080 \n\
        BalancerMember http://gamma:8080 \n\
        ProxySet lbmethod=byrequests \n\
    </Proxy> \n\
    ProxyPass / balancer://mycluster/ \n\
    ProxyPassReverse / balancer://mycluster/ \n\
</VirtualHost> \n'\
> /etc/apache2/sites-enabled/000-default.conf
RUN service apache2 restart
EXPOSE 80
CMD ["apache2ctl", "-D", "FOREGROUND"]