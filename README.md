# E-Commerce Admin Dashboard API系統開發
根據 http://marmelab.com/react-admin-demo ，使用Springboot+MySQL開發管理後台API系統，並以Swagger呈現API文件。

## 模組規劃 
- 銷售模組
  - 訂單管理
  - 發票管理

- 產品模組
  - 產品管理
  - 產品類別管理

- 用戶模組
  - 用戶管理
  - 用戶標籤管理

- 評論模組

```
- 2025/08/30 
build project
- 2025/08/30 
finish commit init project & variable definition
- 2025/08/30 
begin design repositories & UserController
- 2025/09/04 
no swagger text
used Chatgpt design
--------------------------------------------
User Service fixed
User Repository fixed
User Segment fixed
User Response fixed
User Request fixed
User fixed
User CRUD fixed
--------------------------------------------
Create UserSegmentResponse
Create Mapper
Used Mapper in Service
--------------------------------------------
- 2025/09/05 
used Chatgpt design
--------------------------------------------
Fixed User logic  in UserService
--------------------------------------------
Insert UserSegment insert to UserController
--------------------------------------------
UserSegment Integer id replace int id
--------------------------------------------
The content of UserController is more concise
--------------------------------------------
- 2025/09/06 
Controller(PageDesign)
Fixed UserController in UserService
--------------------------------------------
- 2025/09/08
fixed Product&reviews
Design ProductCRUD&Request&Response&Mapper
&link Category
--------------------------------------------
- 2025/09/09
部分優化
正在新增order&reviews

```

#### 下方為各資料表設計
|      users     |           |             |   |   |
|:--------------:|:---------:|:-----------:|---|---|
|       id       |    int    | primary_key |   |   |
|   first_name   |   string  |             |   |   |
|    last_name   |   string  |             |   |   |
|      email     |   string  |             |   |   |
|    birthday    |    date   |             |   |   |
|     address    |   string  |             |   |   |
|      city      |   string  |             |   |   |
|      state     |   string  |             |   |   |
|     zipcode    |   string  |             |   |   |
|    password    |   string  |             |   |   |
| has_newsletter |  boolean  |             |   |   |
|  last_seen_at  | date_time |             |   |   |
|   created_at   | date_time |             |   |   |
|   deleted_at   | date_time |             |   |   |
|    is_delete   |  boolean  |             |   |   |


|  user_segments |           |             |   |   |
|:--------------:|:---------:|:-----------:|---|---|
|       id       |    int    | primary_key |   |   |
|     user_id    |    int    | foreign_key |   |   |
|   segment_id   |    int    | foreign_key |   |   |
|   created_at   | date_time |             |   |   |
|   deleted_at   | date_time |             |   |   |
|   deleted_at   | date_time |             |   |   |
|   updated_at   | date_time |             |   |   |
|      state     |   string  |             |   |   |
|     zipcode    |   string  |             |   |   |
|    password    |   string  |             |   |   |
| has_newsletter |  boolean  |             |   |   |
|  last_seen_at  | date_time |             |   |   |
|   created_at   | date_time |             |   |   |
|   deleted_at   | date_time |             |   |   |
|    is_delete   |  boolean  |             |   |   |


|    segments    |           |             |   |   |
|:--------------:|:---------:|:-----------:|---|---|
|       id       |    int    | primary_key |   |   |
|      name      |   string  |             |   |   |
|   created_at   | date_time |             |   |   |
|   deleted_at   | date_time |             |   |   |
|   deleted_at   | date_time |             |   |   |
|   deleted_at   | date_time |             |   |   |
|   updated_at   | date_time |             |   |   |
|      state     |   string  |             |   |   |
|     zipcode    |   string  |             |   |   |
|    password    |   string  |             |   |   |
| has_newsletter |  boolean  |             |   |   |
|  last_seen_at  | date_time |             |   |   |
|   created_at   | date_time |             |   |   |
|   deleted_at   | date_time |             |   |   |
|    is_delete   |  boolean  |             |   |   |


|     products    |           |             |   |   |
|:---------------:|:---------:|:-----------:|---|---|
|        id       |    int    | primary_key |   |   |
|  categories_id  |    int    | foreign_key |   |   |
| image_thumbnail |   string  |             |   |   |
|    image_url    |   string  |             |   |   |
|   description   |   string  |             |   |   |
|      width      |   double  |             |   |   |
|      height     |   double  |             |   |   |
|      price      |  decimal  |             |   |   |
|      sales      |    int    |             |   |   |
|      stock      |    int    |             |   |   |
|    created_at   | date_time |             |   |   |
|    deleted_at   | date_time |             |   |   |
|    updated_at   | date_time |             |   |   |
|    deleted_at   | date_time |             |   |   |
|    is_delete    |  boolean  |             |   |   |


| categories |           |             |   |   |
|:----------:|:---------:|:-----------:|---|---|
|     id     |    int    | primary_key |   |   |
|    name    |   string  |             |   |   |
| created_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
|    width   |   double  |             |   |   |
|   height   |   double  |             |   |   |
|    price   |  decimal  |             |   |   |
|    sales   |    int    |             |   |   |
|    stock   |    int    |             |   |   |
| created_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
| updated_at | date_time |             |   |   |
| created_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
|  is_delete |  boolean  |             |   |   |


|    orders   |           |             |   |   |
|:-----------:|:---------:|:-----------:|---|---|
|      id     |    int    | primary_key |   |   |
|   user_id   |    int    | foreign_key |   |   |
| total_price |  decimal  |             |   |   |
|    status   |    enum   |             |   |   |
|  created_at | date_time |             |   |   |
|  deleted_at | date_time |             |   |   |
|    height   |   double  |             |   |   |
|    price    |  decimal  |             |   |   |
|    sales    |    int    |             |   |   |
|    stock    |    int    |             |   |   |
|  created_at | date_time |             |   |   |
|  deleted_at | date_time |             |   |   |
|  updated_at | date_time |             |   |   |
|  deleted_at | date_time |             |   |   |
|  is_delete  |  boolean  |             |   |   |


| order_item |           |             |   |   |
|:----------:|:---------:|:-----------:|---|---|
|     id     |    int    | primary_key |   |   |
|  order_id  |    int    | foreign_key |   |   |
| product_id |    int    | foreign_key |   |   |
|  quantity  |    int    |             |   |   |
|    price   |  decimal  |             |   |   |
| created_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
|    price   |  decimal  |             |   |   |
|    sales   |    int    |             |   |   |
|    stock   |    int    |             |   |   |
| created_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
| updated_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
|  is_delete |  boolean  |             |   |   |


|   reviews  |           |             |   |   |
|:----------:|:---------:|:-----------:|---|---|
|     id     |    int    | primary_key |   |   |
|   user_id  |    int    | foreign_key |   |   |
| product_id |    int    | foreign_key |   |   |
|   rating   |    int    |             |   |   |
|   comment  |    TEXT   |             |   |   |
| created_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
| updated_at | date_time |             |   |   |
|    sales   |    int    |             |   |   |
|    stock   |    int    |             |   |   |
| created_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
| updated_at | date_time |             |   |   |
| deleted_at | date_time |             |   |   |
|  is_delete |  boolean  |             |   |   |

```



DB連線資訊
Created commerce_service in DBeaver



spring.application.name=commerce.admin.service

spring.datasource.url=jdbc:mysql://localhost:3306/commerce_service
spring.datasource.username=<username>
spring.datasource.password=<password>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

