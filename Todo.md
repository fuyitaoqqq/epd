project: 
* name
* base_url
* description
* private
* id
* create_time
* update_time
* owner
* return_format

user:
* id
* uuid
* name
* password

api:
* 接口名称
* 接口描述
* 接口路径
* content-type
* 请求方法
* 请求参数
* 相应参数

2019/4/1
1. 查看项目详情，moduleList中新增uri字段.
2. /projects返回自己创建的项目和公开项目（open=1），返回数据中有创建人信息

moduleService update method