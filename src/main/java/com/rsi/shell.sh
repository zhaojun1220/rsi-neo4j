#!/bin/bash
############################################
# this script function is :
# deploy new docker container
#
# USER        YYYY-MM-DD - ACTION
# zj          2018-03-06 - CREATED
#
############################################

parasnum=5
# function
help_msg()
{
cat << help
+----------------------------------------------------+
+ Error Cause:
+ you enter $# parameters
+ the total paramenter number must be $parasnum
+ 1st :DOCKER_NAME
+ 2nd :PROJECT_NAME
+ 3rd :PROJECT_VERSION
+ 4th :SOURCE_PORT
+ 5th :DESTINATION_PORT
+----------------------------------------------------+
help
}

# ----------------------------------------------------
# Check parameter number
# ----------------------------------------------------
if [ $# -ne ${parasnum} ]
then
        help_msg
        exit
fi

# ----------------------------------------------------
# Initialize the parameter.
# ----------------------------------------------------
DOCKER_NAME=$1
PROJECT_NAME=$2
PROJ_VERSION=$3
SPORT=$4
DPORT=$5

PROJ_VERSION=${PROJ_VERSION/"origin/"/""}

DOCKER_FILE="/data/dockerfiles/${DOCKER_NAME}/Dockerfile"
DOCKER_FILE_DIR=/data/dockerfiles/${DOCKER_NAME}
if [ ! -d ${DOCKER_FILE_DIR} ]; then
        mkdir -p ${DOCKER_FILE_DIR}
fi

# ----------------------------------------------------
# check docker images
# ----------------------------------------------------
DOCKER_IMAGE=`/usr/bin/docker images | grep ${DOCKER_NAME} | awk -F ' ' '{print $3}'`
if [ -n "${DOCKER_IMAGE}" ]; then
        # check docker container
        for dc in `/usr/bin/docker ps -a | grep ${DOCKER_NAME} | awk -F " " '{print $1}'`
        do
                echo "Stop container: ${dc}"
                /usr/bin/docker stop ${dc}
                # delete while docker container was exists
                echo "##Delete exists Container_Id: "${dc}
                /usr/bin/docker rm ${dc}
        done

        # delete while docker image was exists
        echo "##Delete exists Image: "${DOCKER_IMAGE}
        /usr/bin/docker rmi ${DOCKER_IMAGE}
fi
echo ""

# ----------------------------------------------------
# Init dockerfile
# ----------------------------------------------------
echo "**Init dockerfile start: "${DOCKER_FILE}
echo "FROM tomcat" > ${DOCKER_FILE}
echo 'MAINTAINER zj "zj@sina.com"' >> ${DOCKER_FILE}
echo "ADD *.war /usr/local/tomcat/webapps/${PROJECT_NAME}.war" >> ${DOCKER_FILE}
echo "EXPOSE 8080" >> ${DOCKER_FILE}
echo "CMD /usr/local/tomcat/bin/startup.sh && tail -f /usr/local/tomcat/logs/catalina.out" >> ${DOCKER_FILE}
cat ${DOCKER_FILE}
echo "**Init dockerfile end."

# ----------------------------------------------------
# Build dockerfile
# ----------------------------------------------------
cd ${DOCKER_FILE_DIR}
rm *.war -rf
mv /data/dockerfiles/war/${DOCKER_NAME}/*.war ./
echo ""
echo "##Build dockerfile for "${DOCKER_NAME}
/usr/bin/docker build -t ${DOCKER_NAME}:${PROJ_VERSION} .


# ----------------------------------------------------
# Run docker container
# ----------------------------------------------------
echo ""
echo "##Running docker container: "${DOCKER_NAME}
/usr/bin/docker run --name ${DOCKER_NAME}_d1 -d -p ${SPORT}:${DPORT} ${DOCKER_NAME}:${PROJ_VERSION}

#/usr/bin/docker run ${cmd}

echo ""