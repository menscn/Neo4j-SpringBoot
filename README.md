# Neo4j-学习笔记

科研所需，需要采用图数据库存储装配工艺信息，构建装配网络，并与MySQL数据库联动，构建零部件选配数据库

故记录Neo4j的SpringBootDemo

# 下面是Neo4j的简单使用教程

# Neon4j使用

## Neo4j简介

图形数据库主要用于存储更多的连接数据，相比于传统数据库关系系统，图形数据库查询更加高效

### 特点

1. 支持索引
2. 支持UNIQUE约束
3. 有可用于执行CQL命令的可视化数据浏览器
4. 支持ACID（原子性，一致性，隔离性和耐久性）
5. 支持将数据导出为JSON和XLS
6. 提供REST API，供任何编程语言进行访问

### 数据模型

每个节点和关系都有个内部属性ID，创建一个新的节点或关系时，Neo4j数据库服务器将为内部使用分配一个数字。 它会自动递增。

图模型-》 节点，关系以及属性 -》 一个节点可以有多个属性，就通过属性存储数据

属性图的连边都是有方向的

每个节点与边都可以有一个或多个标签

图形中的属性采用键值对来进行表示

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655172402277-388579e7-684f-47b4-88e4-f44a365f9fe9.png)

两个节点之间创建关系称为 跟随 ，如图所示，Profile1跟随Profile2，也可以称这个follows关系是Profile2的进入关系

Follows关系也可以有属性！

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655172489838-6030e772-bb88-46b9-a0ff-011c77562bf8.png)

### 数据浏览器

$符号后输入CQL语句，对图数据库进行操作

可以从浏览器中导出PNG和CSV等

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655172941734-ae36dc4c-677c-4ff3-adf5-f5fb5ce6268f.png)

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655172959277-f7f0d683-a056-43b3-8582-2a1458ad3da5.png)

更改节点的显示

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655260804679-2f4ca49d-fc25-4387-b840-d30f16d31c77.png)

## CQL语言学习

CQL中常见命令

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655173251711-cc5a8ee9-9764-4678-8c9e-5b6701bcd4d5.png)

CQL函数

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655173277527-3856702c-0a11-42d1-9879-acf803cbc819.png)

CQL数据类型

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655173314003-8295e6a8-fee4-43f2-ba8a-46070aeff796.png)

### 节点有关命令

#### CREATE命令

**创建一个没有属性的节点**

创建语法如下

CREATE (<node-name>:<label-namel>)

nodel-name：替换为我们要创建的节点名称

label-name：替换为节点的标签名

注意：

Neo4j数据库将使用node-name将节点信息存储再Database.AS中作为Neo4j DBA，但是不能用来节点名来访问节点的详细信息

label-name作为内部节点名称的别名，可以通过其来访问节点详细信息

例如

CREATE (emp:Employee)

CREATE (dept:Dept)

**创建有多个标签名的节点**

CREATE (<node-name>:<label-name1>:<label-name2>.....:<label-namen>)

注意点-》 节点名与节点标签之间的分割符号

**创建具有属性的节点**

语法如下

CREATE (    

<node-name>:<label-name>    

{ 	       <Property1-name>:<Property1-Value> 

​      ........       

<Propertyn-name>:<Propertyn-Value>   

 } 

)

| 语法元素                              | 描述                                            |
| ------------------------------------- | ----------------------------------------------- |
| <node-name>                           | 它是我们将要创建的节点名称。                    |
| <label-name>                          | 它是一个节点标签名称                            |
| <Property1-name>...<Propertyn-name>   | 属性是键值对。 定义将分配给创建节点的属性的名称 |
| <Property1-value>...<Propertyn-value> | 属性是键值对。 定义将分配给创建节点的属性的值   |

其中Property1-name是作为键值对中的key；Property1-value作为键值对中的value

例如

CREATE (dept:Dept { deptno:10,dname:"Accounting",location:"Hyderabad" })

节点名为dept,标签名Dept;属性1为deptno，其值为10；以此往后分别为属性2和属性3：dname和location；

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655174288555-82363867-227f-432d-89a0-be2e87901bef.png)

#### MATCH命令

主要用于从数据库中获取有关节点、关系和属性的数据

语法

MATCH  (    <node-name>:<label-name> )

注意事项

不能单独使用macth命令从数据库中检索数据，将会报错

MATCH需要配合其他语句进行使用，例如配合return语句进行匹配数据库数据

macth (n) return n

```cypher
# 查询Dept下的内容
MATCH (dept:Dept) return dept

# 查询Employee标签下 id=123，name="Lokesh"的节点
MATCH (p:Employee {id:123,name:"Lokesh"}) RETURN p

## 查询Employee标签下name="Lokesh"的节点，使用（where命令）
MATCH (p:Employee)
WHERE p.name = "Lokesh"
RETURN p
```

MATCH (dept:Dept) return dept -》 通过node-label进入数据库查询，将匹配到的结果赋值给dept，最后通过return将dept返回

MATCH (p:Employee {id:123,name:"Lokesh"}) RETURN p ->与上一个语句不同的是，加入了属性的限制，得到的结果将小于等于没有限制的匹配方式

\## 查询Employee标签下name="Lokesh"的节点，使用（where命令）

MATCH (p:Employee)

WHERE p.name = "Lokesh"

RETURN p -》 首先利用match匹配出所有标签为Employee的节点，然后使用where对结果筛选

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655175140228-dbbe0213-7284-4f07-9ec7-7bf20920d12e.png)

#### RETURN命令

可以用于检索节点和关联关系的属性

语法

RETURN     

<node-name>.<property1-name>,    

........    

<node-name>.<propertyn-name>

返回节点（用节点名代替node-name）的某个属性（由属性名代替property1-name）

例如

RETURN dept.deptno

#### MATCH & RETURN

neo4j中不能单独使用MATCH或RETURN命令，故需要两者合作从数据库中检索数据

例如

MATCH (dept: Dept) 

RETURN dept.deptno,dept.dname

利用MATCH检索标签名为Dept的节点，然后返回dept的属性deptno和dname

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655175916394-c02b331b-63e8-445f-bc8a-ba3cec00c4e3.png)

也可以直接返回节点，如

MATCH (dept: Dept) 

RETURN dept

#### WHERE命令

基本语法

WHERE <condition>  单个条件查询

WHERE <condition> <boolean-operator> <condition>  多条件查询，其中boolean-operator是and，or等布尔运算符

<condition> 的语法

<property-name> <comparison-operator> <value>  comparison-operator是比较运算符，如>=，<等

neo4j的布尔运算符

| S.No. | 布尔运算符 | 描述                                   |
| ----- | ---------- | -------------------------------------- |
| 1     | AND        | 它是一个支持AND操作的Neo4j CQL关键字。 |
| 2     | OR         | 它是一个Neo4j CQL关键字来支持OR操作。  |
| 3     | NOT        | 它是一个Neo4j CQL关键字支持NOT操作。   |
| 4     | XOR        | 它是一个支持XOR操作的Neo4j CQL关键字。 |

neo4j的比较运算符

| **S.No.** | **布尔运算符** | **描述**                                  |
| --------- | -------------- | ----------------------------------------- |
| **1.**    | **=**          | **它是Neo4j CQL“等于”运算符。**           |
| **2.**    | **<>**         | **它是一个Neo4j CQL“不等于”运算符。**     |
| **3.**    | **<**          | **它是一个Neo4j CQL“小于”运算符。**       |
| **4.**    | **>**          | **它是一个Neo4j CQL“大于”运算符。**       |
| **5.**    | **<=**         | **它是一个Neo4j CQL“小于或等于”运算符。** |
| **6.**    | **>=**         | **它是一个Neo4j CQL“大于或等于”运算符。** |

例如

MATCH (emp:Employee)  WHERE emp.name = 'Abc' RETURN emp

上面语句仅返回员工名字为'Abc'的节点

MATCH (e:Employee) 

WHERE e.id IS NULL

RETURN e.id,e.name,e.sal,e.deptno

WHERE可以搭配NULL值进行使用

#### DELETE命令

可以删除节点以及关系

基本语法

DELETE <node-name-list>

其中，node-name-list是数据库中要删除的节点的名称

注意：节点名之间用，相隔

例如

MATCH (e: Employee) DELETE e

找到标签为Employee的节点，并将其进行删除

注意，有连接关系的节点，不能只删除其中一个节点，需要连同关系一起删除

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655187492145-01e5ea76-16be-4373-a0bc-bd65b1482446.png)

#### REMOVE命令

删除节点的标签和属性

remove和delete的区别

1. DELETE主要用于删除节点和关联关系
2. REMOVE主要用于删除标签和属性

remove和delete的相似点

1. 两者都不能单独使用，需要与MATCH一起使用

基本语法

REMOVE <property-name-list>

property-name-list语法

<node-name>.<property1-name>, 

<node-name>.<property2-name>,  

...  

<node-name>.<propertyn-name> 

| S.No. | 语法元素        | 描述                 |
| ----- | --------------- | -------------------- |
| 1。   | <node-name>     | 它是节点的名称。     |
| 2。   | <property-name> | 它是节点的属性名称。 |

注意点，使用逗号 , 来分割；

例如

MATCH (book { id:122 }) REMOVE book.price RETURN book

匹配出id为22的book节点，删除这些节点的price属性，然后返回book

**删除标签名**

REMOVE <label-name-list> 

| S.No. | 语法元素          | 描述                                                 |
| ----- | ----------------- | ---------------------------------------------------- |
| 1.    | REMOVE            | 它是一个Neo4j CQL关键字。                            |
| 2.    | <label-name-list> | 它是一个标签列表，用于永久性地从节点或关系中删除它。 |

label-name-list

<node-name>:<label2-name>, 

 ....  

<node-name>:<labeln-name>  

注意点：删除节点用DELETE，后面直接根节点名；这里是删除标签，需要传入节点名：标签名的键值对

例如

MATCH (m:Movie)  REMOVE m:Picture

#### SET命令

向现有的节点的节点添加新的属性或进行更新

语法

SET  <property-name-list>

其中，property-name-list的语法为

<node-label-name>.<property1-name>, 

<node-label-name>.<property2-name>,  

....  

<node-label-name>.<propertyn-name> 

注意：各个属性之间应该用逗号进行分开

例如：

MATCH (book:Book)

SET book.title = 'superstar'

RETURN book

查找标签为Book的节点，设置这些节点的title为superstar，然后返回book

#### ORDER BY排序命令

对match返回的结果进行排序，可以按升序或者降序进行排序，默认情况下是按照升序排列，若需要降序，则采用DESC子句

基本语法

ORDER BY  <property-name-list>  [DESC]

根据标签名进行排序，DESC表示进行降序排列，若没有该子句，则为升序排列

例如

MATCH (emp:Employee)

RETURN emp.empid,emp.name,emp.salary,emp.deptno

ORDER BY emp.name

语句为按照标签查找出emp节点，然后返回节点的属性 empid，name,salary和deptno；返回的顺序按照name进行升序排列

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655258270536-fed4ce2e-9d86-4704-b156-47d0696d6496.png)

MATCH (emp:Employee)

RETURN emp.empid,emp.name,emp.salary,emp.deptno

ORDER BY emp.name DESC

上列语句为降序排列

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655258305005-7e455642-ca12-4f5d-a94a-a5e48e75e1fb.png)

#### UNION合并命令

将两个不同结果合并成一组结果

将两组结果的公共行组合返回到一组结果中，不会返回重复的行

限制：结果的列类型和结果名称需要匹配

基本语法：

<MATCH Command1>

   UNION

<MATCH Command2>

注意：如果两个查询不返回相同的列名和数据类型，就会抛出错误

例如

MATCH (cc:CreditCard) RETURN cc.id,cc.number

UNION

MATCH (dc:DebitCard) RETURN dc.id,dc.number

这个语句将报错，虽然都是id和number，但是两个节点对应属性的前缀是不同的，所以会报错

应该采用AS，将列名进行统一转换，如下列语句

MATCH (cc:CreditCard)

RETURN cc.id as id,cc.number as **number**,cc.name as **name**,

   cc.valid_from as **valid_from**,cc.valid_to as **valid_to**

UNION

MATCH (dc:DebitCard)

RETURN dc.id as id,dc.number as **number**,dc.name as **name**,

   dc.valid_from as **valid_from**,dc.valid_to as **valid_to**

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655258890800-2d27e475-7836-41e8-bd9e-aceca4a3c63f.png)

**UNION ALL子句**

注意：返回结果一定要相同，具有相同的列名，否则会报错

注意UNION 和UNION ALL的区别-》 UNION ALL不会过滤重复

MATCH (cc:CreditCard)

RETURN cc.id as id,cc.number as number,cc.name as name,

   cc.valid_from as valid_from,cc.valid_to as valid_to

UNION ALL

MATCH (dc:DebitCard)

RETURN dc.id as id,dc.number as number,dc.name as name,

   dc.valid_from as valid_from,dc.valid_to as valid_to

结果

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655259088878-6e91ad6e-52a0-4be9-8c8a-89881619a0f4.png)
信用卡节点

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655259102345-a0789a4f-9a72-4cfd-bc69-8dba23b99110.png)

借记卡节点

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655259114140-782cf366-d752-46ee-ac4e-5a73fd4e8984.png)

#### LIMIT命令

LIMIT用于过滤限制返回的行数，修建CQL返回的结果

语句：

LIMIT <number>

| S.No. | 语法元素 | 描述                      |
| ----- | -------- | ------------------------- |
| 1。   | LIMIT    | 它是一个Neo4j CQL关键字。 |
| 2。   | <number> | 它是一个跨值。            |

例如

MATCH (emp:Employee) 

RETURN emp

LIMIT 2

只返回两行数据

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655259279027-94ae7d05-0876-4233-bdd6-44de420d8e1d.png)

#### SKIP命令

与LIMIT类似，都是用于过滤限制行数，不同的是SKIP是从顶部开始的，而LIMIT从底部开始

如不添加LIMIT和SKIP的查询语句，返回结果为

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655259396420-eb119e34-8eba-4228-8ffe-16c99bfb56e4.png)

语句：

SKIP <number>

执行以下语句

MATCH (emp:Employee) 

RETURN emp

SKIP 2

得到的结果为

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655259426901-42421020-e4bd-43f1-bad1-67a60d612841.png)

#### MERGE命令

MERGE=CREATE+MATCH

如果数据库中存在该节点、关系、属性，则返回结果；如果不存在，则根据要求创建新的节点、关系等

基本语法:

MERGE (<node-name>:<label-name>

{

   <Property1-name>:<Pro<rty1-Value>

   .....

   <Propertyn-name>:<Propertyn-Value>

})

例如

MERGE (gp2:GoogleProfile2{ Id: 201402,Name:"Nokia"})

由于gp2是不存在的，所以MERGE就作为了CREATE，进行了节点的创建

#### IN操作符

与SQL一样

基本语言

IN[<Collection-of-values>]

| S.No. | 语法元素               | 描述                                  |
| ----- | ---------------------- | ------------------------------------- |
| 1。   | IN                     | 它是一个Neo4j CQL关键字。             |
| 2。   | [                      | 它告诉Neo4j CQL，一个值的集合的开始。 |
| 3。   | ]                      | 它告诉Neo4j CQL，值集合的结束。       |
| 4。   | <Collection-of-values> | 它是由逗号运算符分隔的值的集合。      |

例如

MATCH (e:Employee) 

WHERE e.id IN [123,124]

RETURN e.id,e.name,e.sal,e.deptno

返回节点id在123-124的Employee节点的属性

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655260381877-f75428b0-ddfa-4d58-a6a6-95586cff06a4.png)

------

### 节点关系有关命令

neo4j中主要节点为单向关系，双向关系 -》 没有无向边，无向边可以采用双向边来替换

#### CREATE命令

**单个标签到关系**

语法

CREATE (<node1-name>:<label1-name>)- 	

[<relationship-name>:<relationship-label-name>] 	

->(<node2-name>:<label2-name>)

| S.No. | 语法元素                                 | 描述                      |
| ----- | ---------------------------------------- | ------------------------- |
| 1     | CREATE 创建                              | 它是一个Neo4J CQL关键字。 |
| 2     | <node1-name> <节点1名>                   | 它是From节点的名称。      |
| 3     | <node2-name> <节点2名>                   | 它是To节点的名称。        |
| 4     | <label1-name> <LABEL1名称>               | 它是From节点的标签名称    |
| 5     | <label2-name> <LABEL2名称>               | 它是To节点的标签名称。    |
| 6     | <relationship-name> <关系名称>           | 它是一个关系的名称。      |
| 7     | <relationship-label-name> <相关标签名称> | 它是一个关系的标签名称。  |

连边关系在neo4j中也有名字与标签

注意：各个标签名与节点名，关系名之间的分割符号

例如

CREATE (p1:Profile1)-[r1:LIKES]->(p2:Profile2)

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655177114807-753c2104-07ae-43d8-801c-e6619ed950d1.png)

可以看出连边关系和语句的形状非常相似

#### WHERE命令

使用where创建节点关系

语法

MATCH (<node1-label-name>:<node1-name>),(<node2-label-name>:<node2-name>)  

WHERE <condition> 

CREATE (<node1-label-name>)-[<relationship-label-name>:<relationship-name>        {<relationship-properties>}]->(<node2-label-name>) 

| S.No. | 语法元素                  | 描述                                                         |
| ----- | ------------------------- | ------------------------------------------------------------ |
| 1     | MATCH,WHERE,CREATE        | 他们是Neo4J CQL关键字。                                      |
| 2     | <node1-label-name>        | 它是一个用于创建关系的节点一标签名称。                       |
| 3     | <node1-name>              | 它是一个用于创建关系的节点名称。                             |
| 4     | <node2-label-name>        | 它是一个用于创建关系的节点一标签名称。                       |
| 5     | <node2-name>              | 它是一个用于创建关系的节点名称。                             |
| 6     | <condition>               | 它是一个Neo4J CQL WHERE子句条件。 它可以是简单的或复杂的。   |
| 7     | <relationship-label-name> | 这是新创建的节点一和节点二之间的关系的标签名称。             |
| 8     | <relationship-name>       | 这是新创建的节点1和节点2之间的关系的名称。                   |
| 9     | <relationship-properties> | 这是一个新创建节点一和节点二之间关系的属性列表（键 - 值对）。 |

注意：这个基本语法中体现了节点之间关系也可以添加上属性relationship-properties，这个也是个键值对，形式和节点属性创建方法相同

例如

MATCH (cust:Customer),(cc:CreditCard)  

WHERE cust.id = "1001" AND cc.id= "5001"  

CREATE (cust)-[r:DO_SHOPPING_WITH{shopdate:"12/12/2014",price:55000}]->(cc)  RETURN r

匹配出标签为Customer和CreditCard的节点，从中筛选出id为1001和5001的节点，创建cust-》cc的关系，其中关系名为r，其标签为DO_SHOPPING_WITH；属性为shopdate和price；最后返回r

#### DELETE语句

基本语法

DELETE <node1-name>,<node2-name>,<relationship-name>

| S.No. | 语法元素            | 描述                                                       |
| ----- | ------------------- | ---------------------------------------------------------- |
| 1.    | DELETE              | 它是一个Neo4j CQL关键字。                                  |
| 2.    | <node1-name>        | 它是用于创建关系<relationship-name>的一个结束节点名称。    |
| 3.    | <node2-name>        | 它是用于创建关系<relationship-name>的另一个节点名称。      |
| 4.    | <relationship-name> | 它是一个关系名称，它在<node1-name>和<node2-name>之间创建。 |

例如

MATCH (cc: CreditCard)-[rel]-(c:Customer)  

DELETE cc,c,rel

找到如同match所示的连接关系，然后删除两个节点和连接关系rel

注意：这里的连接关系不需要用到 ->，因为这里不是定义连接关系，不需要定义边的关系

#### REMOVE命令

删除关系的标签和属性

#### 图形字体

使用数据浏览器来执行和查看Neo4j CQL命令或查询的结果

例如

MATCH ( cc: CreditCard)-[r]-()

RETURN r

得到的结果：与CreditCard节点有连接关系的节点

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655260664937-00157742-ab2c-40a6-bb2f-b518e1efff9e.png)

### CQL函数

#### 字符串函数

函数列表

| S.No. | 功能      | 描述                             |
| ----- | --------- | -------------------------------- |
| 1。   | UPPER     | 它用于将所有字母更改为大写字母。 |
| 2。   | LOWER     | 它用于将所有字母改为小写字母。   |
| 3。   | SUBSTRING | 它用于获取给定String的子字符串。 |
| 4。   | REPLACE   | 它用于替换一个字符串的子字符串。 |

**UPPER**

基本语法

UPPER (<input-string>)

将输入全部转换为大写字母

<input-string>可以是来自Neo4J数据库的节点或关系的属性名称。

例如

MATCH (e:Employee) 

RETURN e.id,e.name,e.sal,e.deptno

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655261807138-9dcbd358-bcc6-4e3f-8938-f442d4e6f711.png)

采用UPPER函数后

MATCH (e:Employee)  RETURN e.id,UPPER(e.name),e.sal,e.deptn

结果为

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655261837318-795c6c8f-26d0-42e7-b77f-45e839f02756.png)

**LOWER**

将字母全部转换为小写，用法同UPPER类似

**SUBSTRING**

分割字符串，输入需要被截取的字符串，截取的开始位置以及结束位置。同JAVA一样，都是左闭右开

#### AGGREGATION聚合函数

使用MATCH命令中的RETURN +聚合函数来处理一组节点并返回一些聚合值

| S.No. | 聚集功能 | 描述                                    |
| ----- | -------- | --------------------------------------- |
| 1。   | COUNT    | 它返回由MATCH命令返回的行数。           |
| 2。   | MAX      | 它从MATCH命令返回的一组行返回最大值。   |
| 3。   | MIN      | 它返回由MATCH命令返回的一组行的最小值。 |
| 4。   | SUM      | 它返回由MATCH命令返回的所有行的求和值。 |
| 5。   | AVG      | 它返回由MATCH命令返回的所有行的平均值。 |

例如，以下列数据为案例

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655262101194-48a7c729-ddb3-4a43-a95f-725dfcc79b8f.png)

输入

MATCH (e:Employee) RETURN COUNT(*)

返回结果

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655262152595-db9f82ac-7a64-46c1-8463-77f5b0fd40ae.png)

**MAX、MIN**

基本语法

返回给定<property-name>列中的最大值

MAX(<property-name> )

MIN(<property-name> )

以一下数据为例

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655262269003-2c3efd60-38fb-4fc3-a607-7b9d654bd5c5.png)

测试

MATCH (e:Employee)  RETURN MAX(e.sal),MIN(e.sal)

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655262294905-eb0ce7fd-8181-42de-a88b-b755afa65987.png)

**SUM、AVG**

SUM负责将列进行求和

AVG负责将列数据求平均

使用方法同MAX、MIN类似

例如

MATCH (e:Employee)  RETURN SUM(e.sal),AVG(e.sal)

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655262371187-746a1284-eaae-49c8-9c95-d9152a98f37b.png)

#### 关系函数

以在获取开始节点，结束节点等细节时知道关系的细节。

| S.No. | 功能      | 描述                                     |
| ----- | --------- | ---------------------------------------- |
| 1。   | STARTNODE | 它用于知道关系的开始节点。               |
| 2。   | ENDNODE   | 它用于知道关系的结束节点。               |
| 3。   | ID        | 它用于知道关系的ID。                     |
| 4。   | TYPE      | 它用于知道字符串表示中的一个关系的TYPE。 |

**STARTNODE和ENDNODE**

知道关系开始和结束的节点

基本语法

STARTNODE (<relationship-label-name>) 

ENDNODE (<relationship-label-name>)

例如

MATCH (a)-[movie:ACTION_MOVIES]->(b) 

RETURN STARTNODE(movie)

\--

MATCH (a)-[movie:ACTION_MOVIES]->(b) 

RETURN ENDNODE(movie)

返回movie边的开始节点与结束节点

**ID和TYPE**

ID和TYPE关系函数来检索关系的Id和类型详细信息。

使用方法

MATCH (a)-[movie:ACTION_MOVIES]->(b) 

RETURN ID(movie),TYPE(movie)

例如

MATCH (a)-[movie:ACTION_MOVIES]->(b) 

RETURN STARTNODE(movie)

语句中a和b都是固定的，不能代表某个具体的节点，需要数据库自行匹配

结果

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655262804974-afadc6f5-8063-44e3-8fc2-0b64c6b967fe.png)

MATCH (a)-[movie:ACTION_MOVIES]->(b)  RETURN ID(movie),TYPE(movie)

![img](https://cdn.nlark.com/yuque/0/2022/png/21455022/1655262876732-5cc68bb9-3e36-4c5f-9063-7bea971ffc0a.png)

### 索引

#### 创建索引语法：

Create Index 创建索引

语法

CREATE INDEX ON :<label_name> (<property_name>)

在节点或关系的<label_name>的<property_name>上创建一个新索引。

例如

CREATE INDEX ON :Customer (name)

#### Drop Neo4j索引

DROP INDEX ON :<label_name> (<property_name>)

删除在节点或关系的<label_name>的<property_name>上创建的现有索引

例如

DROP INDEX ON :Customer (name)、

### UNIQUE约束

CQL CREATE命令始终创建新的节点或关系，这意味着即使您使用相同的值，它也会插入一个新行

因此引入CREATE约束可以避免重复

#### 创建UNIQUE约束

CREATE CONSTRAINT

在NODE或关系的属性上创建唯一约束

**创建唯一约束语法**

CREATE CONSTRAINT ON (<label_name>)

ASSERT <property_name> IS UNIQUE

| S.No. | 语法元素             | 描述                                                         |
| ----- | -------------------- | ------------------------------------------------------------ |
| 1。   | CREATE CONSTRAINT ON | 它是一个Neo4j CQL关键字。                                    |
| 2。   | <label_name>         | 它是节点或关系的标签名称。                                   |
| 3。   | ASSERT               | 它是一个Neo4j CQL关键字。                                    |
| 4。   | <property_name>      | 它是节点或关系的属性名称。                                   |
| 5。   | IS UNIQUE            | 它是一个Neo4j CQL关键字，通知Neo4j数据库服务器创建一个唯一约束。 |

例如

CREATE CONSTRAINT ON (cc:CreditCard) ASSERT cc.number IS UNIQUE

将cc的number属性作为唯一键，不能重复

#### 删除UNIQUE约束

提供了“DROP CONSTRAINT”命令，以从NODE或Relationship的属性中删除现有的Unique约束。

基本语法

DROP CONSTRAINT ON (<label_name>)

ASSERT <property_name> IS UNIQUE

| S.No. | 语法元素           | 描述                                                         |
| ----- | ------------------ | ------------------------------------------------------------ |
| 1。   | DROP CONSTRAINT ON | 它是一个Neo4j CQL关键字。                                    |
| 2。   | <label_name>       | 它是节点或关系的标签名称。                                   |
| 3。   | ASSERT             | 它是一个Neo4j CQL关键字。                                    |
| 4。   | <property_name>    | 它是节点或关系的属性名称。                                   |
| 5。   | IS UNIQUE          | 它是一个Neo4j CQL关键字，通知Neo4j数据库服务器创建一个唯一约束。 |
