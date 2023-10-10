create table BILL
(
    ID           NUMBER(20) not null
        primary key,
    BILLCODE     NVARCHAR2(20),
    PRODUCTNAME  NVARCHAR2(20),
    PRODUCTDESC  NVARCHAR2(50),
    PRODUCTUNIT  NVARCHAR2(10),
    PRODUCTCOUNT NUMBER,
    TOTALPRICE   NUMBER,
    ISPAYMENT    NUMBER(11),
    CREATEDBY    NUMBER(20),
    CREATIONDATE DATE,
    MODIFYBY     NUMBER(20),
    MODIFYDATE   DATE,
    PROVIDERID   NUMBER(20)
)
/

comment on column BILL.ID is '主键ID'
/

comment on column BILL.BILLCODE is '账单编码'
/

comment on column BILL.PRODUCTNAME is '商品名称'
/

comment on column BILL.PRODUCTDESC is '商品描述'
/

comment on column BILL.PRODUCTUNIT is '商品单位'
/

comment on column BILL.PRODUCTCOUNT is '商品数量'
/

comment on column BILL.TOTALPRICE is '商品总额'
/

comment on column BILL.ISPAYMENT is '是否支付（1：未支付 2：已支付）'
/

comment on column BILL.CREATEDBY is '创建者（userId）'
/

comment on column BILL.CREATIONDATE is '创建时间'
/

comment on column BILL.MODIFYBY is '更新者（userId）'
/

comment on column BILL.MODIFYDATE is '更新时间'
/

comment on column BILL.PROVIDERID is '供应商ID'
/


create table MESSAGE
(
    ID        NUMBER(20) not null
        primary key,
    CONTENT   NCLOB,
    USERNAME  NVARCHAR2(40),
    TIME      DATE,
    PARENTID  NUMBER(20),
    FOREIGNID NUMBER(20)
)
/

comment on column MESSAGE.ID is 'ID'
/

comment on column MESSAGE.CONTENT is '内容'
/

comment on column MESSAGE.USERNAME is '评论人'
/

comment on column MESSAGE.TIME is '评论时间'
/

comment on column MESSAGE.PARENTID is '父ID'
/

comment on column MESSAGE.FOREIGNID is '关联id'
/

create table PROVIDER
(
    ID           NUMBER(20) not null
        primary key,
    PROCODE      NVARCHAR2(20),
    PRONAME      NVARCHAR2(20),
    PRODESC      NVARCHAR2(50),
    PROCONTACT   NVARCHAR2(20),
    PROPHONE     NVARCHAR2(20),
    PROADDRESS   NVARCHAR2(50),
    PROFAX       NVARCHAR2(20),
    CREATEDBY    NUMBER(20),
    CREATIONDATE DATE,
    MODIFYDATE   DATE,
    MODIFYBY     NUMBER(20)
)
/

comment on column PROVIDER.ID is '主键ID'
/

comment on column PROVIDER.PROCODE is '供应商编码'
/

comment on column PROVIDER.PRONAME is '供应商名称'
/

comment on column PROVIDER.PRODESC is '供应商详细描述'
/

comment on column PROVIDER.PROCONTACT is '供应商联系人'
/

comment on column PROVIDER.PROPHONE is '联系电话'
/

comment on column PROVIDER.PROADDRESS is '地址'
/

comment on column PROVIDER.PROFAX is '传真'
/

comment on column PROVIDER.CREATEDBY is '创建者（userId）'
/

comment on column PROVIDER.CREATIONDATE is '创建时间'
/

comment on column PROVIDER.MODIFYDATE is '更新时间'
/

comment on column PROVIDER.MODIFYBY is '更新者（userId）'
/

create table ROLE
(
    ID           NUMBER(20) not null
        primary key,
    ROLECODE     NVARCHAR2(15),
    ROLENAME     NVARCHAR2(15),
    CREATEDBY    NUMBER(20),
    CREATIONDATE DATE,
    MODIFYBY     NUMBER(20),
    MODIFYDATE   DATE
)
/

comment on column ROLE.ID is '主键ID'
/

comment on column ROLE.ROLECODE is '角色编码'
/

comment on column ROLE.ROLENAME is '角色名称'
/

comment on column ROLE.CREATEDBY is '创建者'
/

comment on column ROLE.CREATIONDATE is '创建时间'
/

comment on column ROLE.MODIFYBY is '修改者'
/

comment on column ROLE.MODIFYDATE is '修改时间'
/

create table "USER"
(
    ID           NUMBER(20) not null
        primary key,
    USERCODE     NVARCHAR2(15),
    USERNAME     NVARCHAR2(15),
    USERPASSWORD NVARCHAR2(15),
    GENDER       NUMBER(11),
    BIRTHDAY     DATE,
    PHONE        NVARCHAR2(15),
    ADDRESS      NVARCHAR2(30),
    USERROLE     NUMBER(20),
    CREATEDBY    NUMBER(20),
    CREATIONDATE DATE,
    MODIFYBY     NUMBER(20),
    MODIFYDATE   DATE
)
/

comment on column "USER".ID is '主键ID'
/

comment on column "USER".USERCODE is '用户编码'
/

comment on column "USER".USERNAME is '用户名称'
/

comment on column "USER".USERPASSWORD is '用户密码'
/

comment on column "USER".GENDER is '性别（1:女、 2:男）'
/

comment on column "USER".BIRTHDAY is '出生日期'
/

comment on column "USER".PHONE is '手机'
/

comment on column "USER".ADDRESS is '地址'
/

comment on column "USER".USERROLE is '用户角色（取自角色表-角色id）'
/

comment on column "USER".CREATEDBY is '创建者（userId）'
/

comment on column "USER".CREATIONDATE is '创建时间'
/

comment on column "USER".MODIFYBY is '更新者（userId）'
/

comment on column "USER".MODIFYDATE is '更新时间'
/

