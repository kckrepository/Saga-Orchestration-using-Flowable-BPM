# Saga-Orchestration-using-Flowable-BPM

This is implementation of Saga Orchestration using Flowable BPM. Compansation will be handled if failure occure. There are 2 services in this project, Account Service and Flowable BPM Service. the Flowable BPM Service is responsable to handle saga Orchestartion.

Below Flow diagram in BPM process that handle Saga Orchestration :

<img width="449" alt="image" src="https://user-images.githubusercontent.com/17265754/228769422-5601771c-4282-4789-9b53-fff543967981.png">

for every BPM service task call to Account service via Rest API

<img width="325" alt="image" src="https://user-images.githubusercontent.com/17265754/228771637-fc26e04e-f7bf-46e6-bfa7-0e6f73aee887.png">

