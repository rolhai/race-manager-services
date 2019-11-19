#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)

exec 3>&1

function login() {
  eval $(minishift docker-env)
  oc login -u developer -p developer
  docker login -u developer -p $(oc whoami -t) $(minishift openshift registry)
  oc project race-manager
}

function _out() {
  echo "$(date +'%F %H:%M:%S') $@"
}

function setup() {
  _out Deploying drivers
  
  cd ${root_folder}/drivers
  oc delete all -l app=drivers --ignore-not-found
  oc delete all -l app=drivers --ignore-not-found
  oc delete configmap -l app=drivers --ignore-not-found
  oc delete -f deployment/istio.yaml --ignore-not-found

  file="${root_folder}/drivers/liberty-opentracing-zipkintracer-1.2-sample.zip"
  if [ -f "$file" ]
  then
	  echo "$file found"
  else
	  curl -L -o $file https://github.com/WASdev/sample.opentracing.zipkintracer/releases/download/1.2/liberty-opentracing-zipkintracer-1.2-sample.zip
  fi
  unzip -o liberty-opentracing-zipkintracer-1.2-sample.zip -d liberty-opentracing-zipkintracer/
  
  mvn package

  docker build -f Dockerfile -t drivers:1 .
  docker tag drivers:1 $(minishift openshift registry)/race-manager/drivers:1
  docker push $(minishift openshift registry)/race-manager/drivers:1

  cd ${root_folder}/drivers/deployment
  sed "s+drivers:1+$(minishift openshift registry)/race-manager/drivers:1+g" kubernetes.yaml > kubernetes-minishift.yaml

  oc apply -f kubernetes-minishift.yaml
  oc apply -f istio.yaml
  oc expose svc/drivers

  _out Done deploying drivers
  _out Wait until the pod has been started: "oc get pod --watch | grep drivers"
  _out OpenAPI explorer: http://$(oc get route drivers -o jsonpath={.spec.host})/openapi/ui/
}

login
setup

