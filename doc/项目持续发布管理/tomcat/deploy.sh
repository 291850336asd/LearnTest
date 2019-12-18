#!/bin/bash
# 1.基于当前时间、应用名称、版本号创建appBase
pom_a=$1
pom_v=$2
export work_time=$(date +%Y-%m-%d_%H-%M-%S)
echo "deploy time: $work_time"

# 2.下载WAR包 并保存至WAR 目录
mkdir -p war/
# wget 省略
war=war/${pom_a}_${pom_v}.war


# 3.解压WAR文件  解压目录 /war/$pom_a + $pom_v  + worktime
# 4.删除原来的软连接
# 5.添加新的软连接
# 6.停止tomcat
# 7.创建并写入context文件保存<application docBase="xx">
# 8.启动tomcat
deploy_war(){
  target_d=war/${pom_a}-${pom_v}-$work_time
  target_dir=`pwd`/$target_d
  if [ ! -f "$war" ]; then
     echo "war not exist : $war"
     exit 1
  fi
  unzip -q $war -d $target_dir
  # 复制配置文件到classes目录
  # cp -r app-config/* $target_dir/WEB-INF/classes
  # 删除软连接
  rm -f appwar
  # 创建软连接
  ln -sf $target_d appwar

  #current_deploy.sh 指的是deploy.sh中 pom_a 发布的项目名称
  if [ -f current_deploy.sh ];
     then 
       ./tomcat.sh stop
       cat current_deploy_dir > last_deploy
  fi
  
  target_ln=`pwd`/appwar
  echo '<?xml version="1.0" encoding="UTF-8" ?>
           <Context docBase="'$target_ln'" allowLinking="true"> 
       </Context>'  > conf/Catalina/localhost/ROOT.xml
  echo -ne "#!/bin/bash -e\npom_a=${pom_a}\npom_v=${pom_v}" > current_deploy.sh
  echo -ne "${target_d}" > current_deploy_dir
  chmod +x current_deploy.sh
  ./tomcat.sh start
}
deploy_war
