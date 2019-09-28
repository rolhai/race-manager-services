#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)

exec 3>&1

function _out() {
  echo "$(date +'%F %H:%M:%S') $@"
}

function setup() {
  oc login -u admin -p admin
  oc new-project race-manager
  oc adm policy add-scc-to-user anyuid -z default -n race-manager
  oc adm policy add-scc-to-user privileged -z default -n race-manager
  oc adm policy add-role-to-user admin developer
  cd ${root_folder}/minishift-scripts
  echo skip oc apply -f no-mtls.yaml
}

setup
