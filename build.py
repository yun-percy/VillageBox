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
origin_name="build/libs/sVillagebox-1.10.2-1.1.2.jar"
print "start build"
print "="*50
print "Name=",mod_name
print "version:",version_name
print "output_name:",output_name
print "="*50
def init_env():
    cmd="./gradlew setupDecompWorkspace"
    os.system(cmd)
def build_libs():
    cmd="./gradlew build --offline"
    status,output=commands.getstatusoutput(cmd);
    if(status==0):
        os.rename(origin_name,os.path.join("build","libs",output_name))
    else:
        print output
        raise exception("build Faile!")

def run_client():
    cmd="./gradlew runClient --offline"
    os.system(cmd)

if not os.path.isdir(".gradle"):
    init_env()

build_libs()
run_client()


cf.set("version", "build_version", build_version+1)
cf.write(open(conf_ini, "w"))
