---
title : "alias setting"
date : 2020-09-11 00:00:00 +0000
categories: [linux]
---
+ checking a bashrc file
  
  ```
  vi ~/.bashrc
  ```
  result[EX]
  ```
  # .bashrc
  # User specific aliases and functions
  alias rm='rm -i'
  alias cp='cp -i'
  alias mv='mv -i'
  # Source global definitions
  if [ -f /etc/bashrc ]; then
          . /etc/bashrc
          . ~/.bash_aliases
  fi
  ```  
+ add bash_aliases
```
vi ~/.bash_aliases
```
+ add alias
  ```
  alias pet="ps -ef|grep tomcat"
  alias tst="./bin/catalina.sh start && tail -f logs/catalina.out"
  ```
+ reloading a resource file
  ```
  source ~/.bashrc
  ```
   
