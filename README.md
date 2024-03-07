## 研究背景

### 技术选型

后端：springboot，mybatis，mysql，springsecurity认证授权。

前端：vue，uni-app，UniUI，VUE-Element-Admin

## 需求分析

普通用户有如下功能：

- 登录：对用户进行账号管理。
- 咨询维修师：对维修师进行留言。
- 阅读文章：用户可以查看文章。
- 用户反馈：用户可以对小程序的功能进行返回说明。
- 举报：用户可以针对维修师进行举报，举报后由管理员进行管理。
- 针对某篇文章打赏：用户可以针对某篇文章打赏，表示对维修师的肯定。
- 搜索文章：输入关键字搜索。
- 对维修师评分：当维修师处理完成维修后，由用户确认并且打分，每个用户对每个维修师只能评分一次。
- 预约搜索维修师：可以针对城市搜索地址，也可以针对维修师名搜索。

维修师有如下功能：

- 技能管理：维修师可以维修哪些产品。
- 预约管理：针对用户的预约进行管理。
- 证书荣誉管理：维修师参加的比赛和证书。
- 文章管理：管理自己发布的文章信息
- 写关于维修方面的文章：可以针对某个分类标签写文章。
- 业务范围管理：维修师可以添加多个城市。
- 回复用户消息：维修师针对某个咨询进行回复。

管理员用户有如下功能：

- 用户管理：对维修师用户和管理员用户进行管理。
- 文章管理：对维修师写的文章进行管理。
- 用户反馈管理：
- 举报管理：
- 分类管理：
- 审核维修文章：审核管理员发布文章，审核不通过用户看不了。
- 审核维修师上传的技能证书：审核荣誉证书。
- 咨询消息管理：
- 评分信息管理：

## 系统设计

### 数据库设计

#### 用户表user

|     字段     |               名称                |   类型   |     描述     |
| :----------: | :-------------------------------: | :------: | :----------: |
|      id      |               主键                |  bigint  |   用户主键   |
|   username   |               昵称                | varcher  |   用户名称   |
| userAccount  |               账号                | varcher  |   用户账号   |
|  avatarUrl   |               头像                | varcher  | 用户头像地址 |
|    gender    |               性别                | tinyint  | 用户性别0-1  |
| userPassword |               密码                | varcher  |  加密后密码  |
|  userStatus  |             用户状态              |   int    | 用户是否可用 |
|  createTime  |             创建时间              | datetime |   创建时间   |
|   userRole   | 用户角色(0用户，1维修师，2管理员) |   int    |   用户角色   |

|      |      |      |      |
| :--: | :--: | :--: | :--: |

#### 咨询信息表consult

|     字段      |       名称       |   类型   |         描述         |
| :-----------: | :--------------: | :------: | :------------------: |
|      id       |       主键       |  bigint  |                      |
|  userSendId   | 那个用户发布消息 |  bigint  |                      |
| userAcceptId  | 那个用户接收数据 |  bigint  |                      |
|    content    |     消息内容     |   text   |                      |
| consultStatus |       状态       |   int    |                      |
|  createTime   |     创建时间     | datetime |                      |
|    status     |       状态       |   int    | 0没有回复，1已经回复 |

#### 文章表article

删除时，需要真删数据。articleStatus状态被用于审核文章里

|     字段      |    名称    |   类型   | 描述 |
| :-----------: | :--------: | :------: | :--: |
|      id       |    主键    |  bigint  |      |
|    userId     | 管理员主键 |  bigint  |      |
|    sortId     |    分类    |  bigint  |      |
|  createTime   |  创建时间  | datetime |      |
|     title     |    题目    | varchar  |      |
| articleStatus |    状态    |   int    |      |
|    content    |    内容    |   text   |      |

#### 反馈信息表feedback

|      字段      |   名称   |   类型   | 描述 |
| :------------: | :------: | :------: | :--: |
|       id       |   主键   |  bigint  |      |
|     userId     | 用户主键 |  bigint  |      |
|   createTime   | 创建时间 | datetime |      |
|    content     |   内容   |   text   |      |
| feedbackStatus |   状态   |   int    |      |

#### 举报信息表report

|     字段     |    名称    |   类型   | 描述 |
| :----------: | :--------: | :------: | :--: |
|      id      |    主键    |  bigint  |      |
|  createTime  |  创建时间  | datetime |      |
| reportStatus |    状态    |   int    |      |
|   reportId   |  举报用户  |  bigint  |      |
|  byReportId  | 被举报用户 |  bigint  |      |
|   content    |  举报内容  |   text   |      |

#### 评分信息表score

|    字段     |    名称    |   类型   | 描述 |
| :---------: | :--------: | :------: | :--: |
|     id      |    主键    |  bigint  |      |
| createTime  |  创建时间  | datetime |      |
| scoreStatus |    状态    |   int    |      |
|   scoreId   |  打分用户  |  bigint  |      |
|  byScoreId  | 被打分用户 |  bigint  |      |
|    point    |    分数    |   int    |      |

#### 预约信息表appoint

|     字段      |       名称       |   类型   |                             描述                             |
| :-----------: | :--------------: | :------: | :----------------------------------------------------------: |
|      id       |       主键       |  bigint  |                                                              |
|  createTime   |     创建时间     | datetime |                                                              |
| appointStatus |       状态       |   int    |                                                              |
|   appointId   |     发布者id     |  bigint  |                                                              |
|  byAppointId  |     预约者id     |  bigint  |                                                              |
|    content    | 发布预约人的信息 |   text   |                                                              |
|  appointTime  |     预约时间     | datetime |                         精确到某一天                         |
|    message    | 预约人上传的消息 |   text   |                                                              |
|    status     |     预约状态     |   int    | 0表示待预约阶段，1表示预约成功(管理员在这估算价格，变为状态2)，2表示价格估算成功(用户在这确认估算价格，变为状态3)，3表示维修成功(维修师在这确认，表示维修成功变为状态4待打分)，待打分阶段可以举报，4待打分(用户在这打分后编程状态5，表示完成)，5表示完成。 |

| 字段  | 名称 |  类型  | 描述 |
| :---: | :--: | :----: | :--: |
| price | 价格 | double |      |



#### 文章分类表sort

|    字段    |   名称   |   类型   | 描述 |
| :--------: | :------: | :------: | :--: |
|     id     |   主键   |  bigint  |      |
| createTime | 创建时间 | datetime |      |
| sortStatus |   状态   |   int    |      |
|  content   | 分类名称 | varchar  |      |

#### 技能信息表skill

|    字段     |   名称   |   类型   | 描述 |
| :---------: | :------: | :------: | :--: |
|     id      |   主键   |  bigint  |      |
| createTime  | 创建时间 | datetime |      |
| skillStatus |   状态   |   int    |      |
|   userId    | 维修师id |  bigint  |      |
|   content   | 技能名称 | varchar  |      |

#### 证书荣誉表certificate

删除时，需要真删数据。certificateStatus状态被用于审核证书了

|       字段        |     名称     |   类型   | 描述 |
| :---------------: | :----------: | :------: | :--: |
|        id         |     主键     |  bigint  |      |
|    createTime     |   创建时间   | datetime |      |
| certificateStatus |     状态     |   int    |      |
|      userId       |   维修师id   |  bigint  |      |
|      content      |   证书名称   | varchar  |      |
|       image       | 技能照片展示 | varchar  |      |

#### 业务范围表（城市）scope

|    字段     |     名称     |   类型   | 描述 |
| :---------: | :----------: | :------: | :--: |
|     id      |     主键     |  bigint  |      |
| createTime  |   创建时间   | datetime |      |
| scopeStatus |     状态     |   int    |      |
|   content   | 服务城市名称 | varchar  |      |
|   userId    |    维修师    |  bigint  |      |

## 部署

