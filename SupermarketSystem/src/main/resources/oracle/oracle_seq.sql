
-- 设置自增
drop sequence SEQ_ORA_INTEGER_KEY_ADDRESS;

drop sequence SEQ_ORA_INTEGER_KEY_BILL;

drop sequence SEQ_ORA_INTEGER_KEY_MESSAGE;

drop sequence SEQ_ORA_INTEGER_KEY_PROVIDER;

drop sequence SEQ_ORA_INTEGER_KEY_USER;


create sequence SEQ_ORA_INTEGER_KEY_ADDRESS
    minvalue 1  --增长最小值
    maxvalue 99999999  --增长最大值,也可以设置NOMAXvalue -- 不设置最大值
    start with 50  --从1开始计数
    increment by 1  --自增步长为1
    cache 50  --设置缓存cache个序列，如果系统down掉了或者其它情况将会导致序列不连续，也可以设置为---NOCACHE防止跳号
    cycle;  --循环,当达到最大值时,不是从start with设置的值开始循环。而是从1开始循环


create sequence SEQ_ORA_INTEGER_KEY_BILL
    minvalue 1  --增长最小值
    maxvalue 99999999  --增长最大值,也可以设置NOMAXvalue -- 不设置最大值
    start with 50  --从1开始计数
    increment by 1  --自增步长为1
    cache 50  --设置缓存cache个序列，如果系统down掉了或者其它情况将会导致序列不连续，也可以设置为---NOCACHE防止跳号
    cycle;  --循环,当达到最大值时,不是从start with设置的值开始循环。而是从1开始循环


create sequence SEQ_ORA_INTEGER_KEY_MESSAGE
    minvalue 1  --增长最小值
    maxvalue 99999999  --增长最大值,也可以设置NOMAXvalue -- 不设置最大值
    start with 50  --从1开始计数
    increment by 1  --自增步长为1
    cache 50  --设置缓存cache个序列，如果系统down掉了或者其它情况将会导致序列不连续，也可以设置为---NOCACHE防止跳号
    cycle;  --循环,当达到最大值时,不是从start with设置的值开始循环。而是从1开始循环


create sequence SEQ_ORA_INTEGER_KEY_PROVIDER
    minvalue 1  --增长最小值
    maxvalue 99999999  --增长最大值,也可以设置NOMAXvalue -- 不设置最大值
    start with 50  --从1开始计数
    increment by 1  --自增步长为1
    cache 50  --设置缓存cache个序列，如果系统down掉了或者其它情况将会导致序列不连续，也可以设置为---NOCACHE防止跳号
    cycle;  --循环,当达到最大值时,不是从start with设置的值开始循环。而是从1开始循环


create sequence SEQ_ORA_INTEGER_KEY_USER
    minvalue 1  --增长最小值
    maxvalue 99999999  --增长最大值,也可以设置NOMAXvalue -- 不设置最大值
    start with 50  --从1开始计数
    increment by 1  --自增步长为1
    cache 50  --设置缓存cache个序列，如果系统down掉了或者其它情况将会导致序列不连续，也可以设置为---NOCACHE防止跳号
    cycle;  --循环,当达到最大值时,不是从start with设置的值开始循环。而是从1开始循环
