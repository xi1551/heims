医院员工信息管理系统
基于 Spring Boot 3.x + Vue 3 的前后端分离项目
一、项目简介
基于 Spring Boot 3.x + Vue 3 的前后端分离项目，实现医院科室、医生、护士的信息管理，支持 JWT 登录认证和分页搜索。
二、技术栈
后端	前端
Spring Boot 3.2.5	Vue 3.4
MyBatis 3.0.3	Vite 5
MySQL 8.0	Element Plus 2.7
JWT (jjwt 0.12.5)	Axios 1.7
Lombok	Vue Router 4
三、功能模块
•	登录认证：账号密码登录，JWT Token 存储，路由守卫拦截
•	科室管理：科室信息的增删改查
•	医生管理：医生信息的增删改查，支持按姓名/科室分页搜索
•	护士管理：护士信息的增删改查，支持按姓名/科室分页搜索
四、项目结构
heims/
├── hospital-backend/          # Spring Boot 后端
│   ├── sql/hospital.sql       # 数据库初始化脚本
│   └── src/main/java/com/hospital/
│       ├── entity/            # 实体类
│       ├── mapper/            # MyBatis Mapper
│       ├── service/           # 业务逻辑
│       ├── controller/        # REST API
│       ├── config/            # 跨域/拦截器配置
│       └── util/              # JWT 工具类
└── hospital-frontend/         # Vue 3 前端
    └── src/
        ├── views/             # 页面组件
        ├── components/        # 表单组件
        ├── api/               # API 请求封装
        └── router/            # 路由配置
五、快速启动
1. 初始化数据库
mysql -u root -p < hospital-backend/sql/hospital.sql
默认密码：1234，如需修改请编辑 application.yml
2. 启动后端
用 IDEA 打开 hospital-backend，运行 HospitalApplication.java
后端地址：http://localhost:8080
3. 启动前端
cd hospital-frontend
npm install
npm run dev
前端地址：http://localhost:3000
4. 登录系统
•	用户名：admin
•	密码：admin123
六、API 接口
方法	     路径	        说明
POST	/api/auth/login	登录
GET	/api/departments	科室列表
GET	/api/doctors	医生分页查询
GET	/api/nurses	护士分页查询
七、作者
GitHub: xi1551
