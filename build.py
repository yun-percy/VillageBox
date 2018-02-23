#!/usr/bin/env python
# coding:utf8

import sys
import os
import ConfigParser
import commands
conf_ini="config.ini"
cf = ConfigParser.ConfigParser()
cf.read(conf_ini)
mod_name = cf.get("info", "name")
mc_version = cf.get("info","mc_version")
major_version = cf.getint("version","major_version")
fix_version = cf.getint("version","fix_version")
build_version = cf.getint("version","build_version")
version_name="%s.%s.%s" % (major_version,fix_version,build_version)
output_name="%s-%s-%s.jar" % (mod_name,mc_version,version_name)
print "start build"
print "="*50
print "Name=",mod_name
print "version:",version_name
print "output_name:",output_name
print "="*50
def export_env():
    os.environ["VERSION_NAME"]="%s-%s" %(mc_version,version_name)
    os.environ["BASE_NAME"]=mod_name
def init_env():
    cmd="./gradlew setupDecompWorkspace"
    os.system(cmd)
def build_libs():
    cmd="./gradlew build --offline"
    os.system(cmd)
    # status,output=commands.getstatusoutput(cmd);
    # if(status==0):
        # os.rename(origin_name,os.path.join("build","libs",output_name))
    # else:
        # print output
        # raise Exception("build Faile!")

def run_client():
    cmd="./gradlew runClient --offline"
    os.system(cmd)

if not os.path.isdir(".gradle"):
    init_env()

if len(sys.argv)>1:
    if sys.argv[1]=="run":
        print "starting simle client...."
        run_client()
        sys.exit(0)
export_env()
build_libs()


cf.set("version", "build_version", build_version+1)
cf.write(open(conf_ini, "w"))
