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


## 初始化專案
### SQL連線設定
#### 預先於本地建立資料庫
```
- none commit&git&application.properties
```




#### users
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





















#### DB連線資訊
```
Created commerce_service in DBeaver



spring.application.name=commerce.admin.service

spring.datasource.url=jdbc:mysql://localhost:3306/commerce_service
spring.datasource.username=<username>
spring.datasource.password=<password>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true