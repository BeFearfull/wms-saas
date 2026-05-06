# Warehouse Management System (WMS) - SaaS Platform

A production-ready, cloud-native multi-tenant Warehouse Management System designed for Indian small-to-medium warehouses.

## 🎯 Features

- **Multi-Tenant Architecture**: Complete data isolation for each warehouse owner
- **JWT Authentication**: Secure API access with token-based authentication
- **OAuth Integration**: Google Login support
- **Inventory Tracking**: Real-time stock management with transaction history
- **Sales & Purchase Management**: Complete order tracking and management
- **Delivery Management**: Incoming and outgoing delivery tracking
- **Analytics Dashboard**: Real-time insights with charts and statistics
- **Notification System**: Email and SMS notification support
- **Cloud Ready**: AWS/Azure deployment ready with Docker support
- **Responsive UI**: Mobile-friendly React frontend

## 🛠️ Tech Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Language**: Java 17+
- **ORM**: JPA/Hibernate
- **Authentication**: Spring Security + JWT
- **Build Tool**: Maven
- **Server**: Embedded Tomcat

### Frontend
- **Framework**: React 18+
- **State Management**: Redux Toolkit / Context API
- **HTTP Client**: Axios
- **Charts**: Chart.js / Recharts
- **Styling**: Tailwind CSS
- **Build Tool**: Vite

### Database
- **Primary**: PostgreSQL 14+
- **Alternative**: MySQL 8+

### Infrastructure
- **Containerization**: Docker
- **Cloud**: AWS (RDS, EC2, S3) / Azure (App Service, SQL Database)
- **Currency**: Indian Rupees (₹)
- **Timezone**: IST (UTC+5:30)

## 📁 Project Structure

```
wms-saas/
├── backend/                    # Spring Boot Application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/wms/
│   │   │   │   ├── WmsApplication.java
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── entity/
│   │   │   │   ├── dto/
│   │   │   │   ├── security/
│   │   │   │   ├── exception/
│   │   │   │   ├── util/
│   │   │   │   └── validator/
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       ├── application-dev.yml
│   │   │       └── application-prod.yml
│   │   └── test/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/                   # React Application
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── store/
│   │   ├── hooks/
│   │   ├── utils/
│   │   ├── styles/
│   │   ├── App.jsx
│   │   └── main.jsx
│   ├── package.json
│   ├── vite.config.js
│   └── Dockerfile
├── database/
│   ├── schema.sql
│   ├── initial-data.sql
│   └── migrations/
├── docker-compose.yml
├── docs/
│   ├── API_DOCUMENTATION.md
│   ├── DATABASE_SCHEMA.md
│   ├── DEPLOYMENT_GUIDE.md
│   └── IMPLEMENTATION_GUIDE.md
├── .env.example
├── .gitignore
└── LICENSE
```

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Node.js 18+
- PostgreSQL 14+ (or MySQL 8+)
- Docker & Docker Compose (optional)

### Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

### Database Setup

```bash
# Using Docker
docker-compose up -d

# Or manually
psql -U postgres -d wms_saas -f database/schema.sql
```

## 📚 Documentation

- [API Documentation](./docs/API_DOCUMENTATION.md)
- [Database Schema](./docs/DATABASE_SCHEMA.md)
- [Deployment Guide](./docs/DEPLOYMENT_GUIDE.md)
- [Implementation Guide](./docs/IMPLEMENTATION_GUIDE.md)

## 🔐 Security Features

- ✅ JWT Token-based Authentication
- ✅ Spring Security Configuration
- ✅ Role-Based Access Control (RBAC)
- ✅ Password Hashing with BCrypt
- ✅ CORS Configuration
- ✅ Input Validation & Sanitization
- ✅ SQL Injection Prevention
- ✅ Rate Limiting Ready
- ✅ Environment Variables for Secrets
- ✅ Exception Handling & Logging

## 📱 Responsive Design

- Mobile-first approach
- Tailwind CSS for responsive styling
- Fully functional on all device sizes

## 🗄️ Multi-Tenancy

Each warehouse owner:
- Has isolated database context
- Cannot access other users' data
- Gets complete data ownership
- Supports unlimited warehouses per user

## 🔄 Core Modules

1. **Authentication Module** - User registration, login, password recovery
2. **Warehouse Management** - Setup and configuration
3. **Inventory Management** - Stock tracking with transaction history
4. **Supplier/Company Management** - Vendor management
5. **Sales Module** - Sales order management
6. **Purchase Module** - Purchase order management
7. **Delivery Module** - Incoming and outgoing deliveries
8. **Dashboard** - Analytics and KPIs
9. **Notification System** - Email and SMS alerts
10. **User Settings** - Profile and preferences

## 🌐 API Endpoints

### Authentication
- `POST /api/v1/auth/register` - User registration
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/google-login` - Google OAuth login
- `POST /api/v1/auth/refresh-token` - Refresh JWT token
- `POST /api/v1/auth/logout` - User logout

### Warehouse
- `GET /api/v1/warehouses` - List all warehouses
- `POST /api/v1/warehouses` - Create warehouse
- `GET /api/v1/warehouses/{id}` - Get warehouse details
- `PUT /api/v1/warehouses/{id}` - Update warehouse
- `DELETE /api/v1/warehouses/{id}` - Delete warehouse

### Inventory
- `GET /api/v1/inventory` - Get all products
- `POST /api/v1/inventory` - Add product
- `GET /api/v1/inventory/{id}` - Get product details
- `PUT /api/v1/inventory/{id}` - Update product
- `GET /api/v1/inventory/{id}/transactions` - Get transaction history

### More endpoints documented in [API_DOCUMENTATION.md](./docs/API_DOCUMENTATION.md)

## 📊 Dashboard Metrics

- Total Inventory Value
- Total Sales
- Total Purchases
- Profit/Loss Analysis
- Low Stock Alerts
- Pending Deliveries
- Monthly Trends
- Top Selling Products
- Company-wise Sales

## 🐳 Docker Deployment

```bash
docker-compose up -d
```

## ☁️ Cloud Deployment

### AWS
- RDS for PostgreSQL
- EC2 for application server
- S3 for document storage
- CloudFront for CDN

### Azure
- Azure Database for PostgreSQL
- App Service for application
- Blob Storage for documents
- CDN for static assets

## 📝 Environment Variables

See [.env.example](./.env.example) for complete list.

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📄 License

This project is licensed under the MIT License - see [LICENSE](./LICENSE) file.

## 💬 Support

For support, email support@wms-saas.com or open an issue on GitHub.

## 🎓 Learning Resources

- Spring Boot Documentation: https://spring.io/projects/spring-boot
- React Documentation: https://react.dev
- PostgreSQL Documentation: https://www.postgresql.org/docs/
- JWT Authentication: https://jwt.io

---

**Made with ❤️ for Indian SMB Warehouses**