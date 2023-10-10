
-- 创建表空间
create tablespace supermarket_system
    datafile 'D:\SoftwareForCode\oracle19c\tablespace\supermarket_system.dbf'
    size 100m
    autoextend on
    next 10m;

-- 创建用户
create user sms_user
    identified by Du1933du
    default tablespace supermarket_system;

-- 赋权限
grant dba to sms_user;



