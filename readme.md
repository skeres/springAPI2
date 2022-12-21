
### Inspired from
[tutorial](https://medium.com/@javatechie/kubernetes-tutorial-run-deploy-spring-boot-application-in-k8s-cluster-using-yaml-configuration-3b079154d232)  

### Build image and push it to personnal Dockerhub repository

```
docker build --tag skeres95250/spring-api2:v1.0 --file ./Dockerfile .
```

```
docker image push skeres95250/spring-api2:v1.0
```

### Create deployment

```
kubectl apply -f Deployment.yml
```

```
kubectl get deployments.apps
```

```
kubectl delete deployments.apps spring-boot-k8s
```

### Create service

```
kubectl get service
``` 

```
kubectl apply -f Service.yml
```

```
kubectl delete service springboot-k8s-svc
```

### Get cluster IP adress

```
kubectl get nodes -o wide
```

*Alternative to get local IP of minikube*      
```
minikube ip
```

### Test running application : curl or browser request clusterIP/service port
`curl http://192.168.49.2:31747`  
`curl http://192.168.49.2:31747/listeEtudiants`  

### Wget to standard output : example
`wget -S -O - http://10.104.246.237:8282/listeEtudiants`

### >>> adapt to local deployment for Docker
mvn clean install
docker build --tag local/spring-api:v1.0 --file ./Dockerfile .
docker stop my_running_springAPI && docker rm my_running_springAPI
docker run -dit --name my_running_springAPI -p 8383:8383 local/spring-api:v1.0
docker logs my_running_springAPI
curl -v http://localhost:8383/api/
curl -v http://localhost:8383/api/listeEtudiants

### >>> adapt to kubernetes
mvn clean install
docker build --tag skeres95250/spring-api2:v1.0 --file ./Dockerfile .
docker image push skeres95250/spring-api2:v1.0
kubectl apply -f Service.yml
kubectl apply -f Deployment.yml


### >>> helm for kubernetes
helm create backend : create tree of helm files
helm template ./helm/backend : show yml files that will be generated
helm install my-helm-backend --debug --dry-run ./helm/backend : to simulate a deployment
helm install my-helm-backend ./helm/backend 
helm uninstall my-helm-backend  : to remove deployment from cluster

For debugging, create a busybox POD and ssh into it
kubectl run -i --tty debug --image=busybox --restart=Never -- sh
kubectl exec -it debug -- sh
inside the pod : wget -S -O - http://springboot-k8s-svc:8484/api/listeEtudiants
kubectl delete -n default pod debug

alternate busybox tool :  
source : https://kubernetes.io/docs/tasks/debug/debug-application/debug-service/  
kubectl run -it --rm --restart=Never busybox --image=gcr.io/google-containers/busybox sh  

kubectl apply -f ./ConfigMapEnv.yml  
kubectl delete -n default configmap springapi2-configmap-env

To use kubernetes secrets : secrets data MUST BE base64 encoded
To get encoded value, type in a terminal :
echo -n "stephane" | base64
result :
c3RlcGhhbmU=

echo -n "postgres" | base64
result :
cG9zdGdyZXM=