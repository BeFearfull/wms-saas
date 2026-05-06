# WMS-SaaS: Production-Ready Warehouse Management System

A modern, scalable, multi-tenant SaaS Warehouse Management System designed for Indian small-to-medium warehouses.

## 🏗️ Architecture Overview

- **Frontend**: React.js with responsive design
- **Backend**: Spring Boot with clean architecture
- **Database**: PostgreSQL
- **Authentication**: JWT + Google OAuth2
- **Deployment**: AWS/Azure ready
- **Currency**: Indian Rupees (₹)
- **Timezone**: IST (Indian Standard Time)

## 📁 Project Structure

```
wms-saas/
├── backend/               # Spring Boot Application
│   ├── src/main/java/
│   │   └── com/wms/
│   │       ├── config/           # Configuration classes
│   │       ├── controller/       # REST Controllers
│   │       ├── service/          # Business Logic
│   │       ├── repository/       # Data Access Layer
│   │       ├── entity/           # JPA Entities
│   │       ├── dto/              # Data Transfer Objects
│   │       ├── exception/        # Custom Exceptions
│   │       ├── security/         # JWT & Security
│   │       ├── util/             # Utility Classes
│   │       └── WmsApplication.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   ├── application-dev.properties
│   │   ├── application-prod.properties
│   │   └── db/migration/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/              # React Application
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── store/
│   │   ├── hooks/
│   │   ├── utils/
│   │   ├── styles/
│   │   ├── App.js
│   │   └── index.js
│   ├── public/
│   ├── package.json
│   └── Dockerfile
├── database/              # Database Schema
│   ├── schema.sql
│   ├── migrations/
│   └── seed-data.sql
├── docs/                  # Documentation
│   ├── API_DOCUMENTATION.md
│   ├── DATABASE_SCHEMA.md
│   ├── DEPLOYMENT.md
│   └── SETUP.md
├── docker-compose.yml
├── .env.example
└── .gitignore
```

## 🚀 Quick Start

### Prerequisites
- Java 11+
- Node.js 14+
- PostgreSQL 12+
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
npm start
```

### Docker Setup
```bash
docker-compose up -d
```

## 🔐 Authentication Flow

1. User signup/login with email & password
2. Google OAuth2 integration
3. JWT token generation
4. Refresh token mechanism
5. Role-based access control

## 📊 Core Features

- **Multi-tenant SaaS Architecture**: Completely isolated warehouse data per user
- **Warehouse Setup Wizard**: Easy onboarding
- **Inventory Management**: Real-time stock tracking with transaction history
- **Sales & Purchase**: Order management and tracking
- **Delivery Management**: Incoming and outgoing delivery tracking
- **Analytics Dashboard**: Real-time insights and reporting
- **Notification System**: Email & SMS alerts
- **Security**: Industry-standard encryption and validation

## 🛠️ Technology Stack

### Backend
- Spring Boot 2.7+
- Spring Security
- JPA/Hibernate
- JWT (io.jsonwebtoken)
- PostgreSQL Driver
- Lombok
- MapStruct
- OpenAPI/Swagger

### Frontend
- React 18+
- Redux Toolkit
- Axios
- React Router
- Chart.js / Recharts
- Tailwind CSS / Material-UI
- React Hook Form
- Yup (Validation)

## 📝 API Endpoints Structure

```
POST   /api/v1/auth/signup
POST   /api/v1/auth/login
POST   /api/v1/auth/refresh
POST   /api/v1/auth/google
POST   /api/v1/auth/logout

GET    /api/v1/warehouses
POST   /api/v1/warehouses
GET    /api/v1/warehouses/{id}
PUT    /api/v1/warehouses/{id}

GET    /api/v1/companies
POST   /api/v1/companies
GET    /api/v1/companies/{id}
PUT    /api/v1/companies/{id}
DELETE /api/v1/companies/{id}

GET    /api/v1/products
POST   /api/v1/products
GET    /api/v1/products/{id}
PUT    /api/v1/products/{id}
DELETE /api/v1/products/{id}

GET    /api/v1/inventory/transactions
POST   /api/v1/inventory/transactions
GET    /api/v1/inventory/stock

POST   /api/v1/purchases
GET    /api/v1/purchases
GET    /api/v1/purchases/{id}

POST   /api/v1/sales
GET    /api/v1/sales
GET    /api/v1/sales/{id}

GET    /api/v1/deliveries/incoming
POST   /api/v1/deliveries/incoming
GET    /api/v1/deliveries/outgoing
POST   /api/v1/deliveries/outgoing

GET    /api/v1/dashboard/stats
GET    /api/v1/dashboard/charts
```

## 🗄️ Database Schema

See `database/schema.sql` for complete schema with:
- users
- warehouses
- companies/suppliers
- products
- inventory_transactions
- purchases
- sales
- deliveries
- notifications

## 🔒 Security Features

- JWT Token-based authentication
- Spring Security with role-based authorization
- Bcrypt password hashing
- Input validation (JSR 303)
- SQL injection prevention (Hibernate parameterized queries)
- CORS configuration
- Rate limiting ready
- Environment variable secret management
- Secure HTTP headers

## 📚 Documentation

Detailed documentation available in `/docs` folder:
- API Documentation
- Database Schema
- Deployment Guide
- Setup Instructions

## 🤝 Contributing

This is a production-ready template. Follow these guidelines:
- Clean Architecture principles
- SOLID principles
- Proper code commenting
- Unit and integration tests

## 📄 License

Proprietary - All rights reserved

## 📞 Support

For issues and support, contact the development team.

---

**Last Updated**: May 2026
**Version**: 1.0.0-BETA
