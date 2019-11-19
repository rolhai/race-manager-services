#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)

exec 3>&1

function _out() {
  echo "$(date +'%F %H:%M:%S') $@"
}

function setup() {
  echo falls docker-registry nicht laeuft, login als admin bei oc-console und manuell starten

  echo login...developer
  oc login -u developer -p developer

  echo create new project...race-manager
  oc new-project race-manager

  echo create new build...drivers
  oc new-build --strategy docker --dockerfile - --code ${root_folder}/drivers/. --name drivers < ${root_folder}/drivers/src/main/docker/Dockerfile.jvm

  echo start build...drivers
  oc start-build --from-dir . drivers

  echo create new app...drivers
  oc new-app --image-stream race-manager/drivers --name drivers

  echo expose service...drivers
  oc expose svc/drivers

  echo skip oc adm policy add-scc-to-user anyuid -z default -n race-manager
  echo skip oc adm policy add-scc-to-user privileged -z default -n race-manager
  echo skip oc adm policy add-role-to-user admin developer
  echo skip cd ${root_folder}/minishift-scripts
  echo skip oc apply -f no-mtls.yaml
}

setup
