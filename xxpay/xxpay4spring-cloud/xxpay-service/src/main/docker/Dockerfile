FROM java:8
VOLUME /tmp
RUN mkdir /app
ADD xxpay-service.jar /app/app.jar
ADD runboot.sh /app/
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
EXPOSE 3000
CMD /app/runboot.sh