#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)

exec 3>&1

function login() {
  oc login -u developer -p developer
  oc project race-manager
}

function _out() {
  echo "$(date +'%F %H:%M:%S') $@"
}

function setup() {
  _out Deleting drivers-java-ee
  
  cd ${root_folder}/drivers-java-ee
  oc delete all -l app=drivers --ignore-not-found
  oc delete all -l app=drivers --ignore-not-found
  oc delete configmap -l app=drivers --ignore-not-found
  oc delete -f deployment/istio.yaml --ignore-not-found

  _out Done Deleting drivers-java-ee
}

login
setup

