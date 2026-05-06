# Warehouse Management System (WMS) - SaaS Platform

## Production-Ready Multi-Tenant Cloud Architecture

A comprehensive Warehouse Management System built for Indian small-to-medium businesses with complete inventory, sales, purchases, and delivery tracking.

### Tech Stack

**Backend:**
- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- Spring Data JPA/Hibernate
- PostgreSQL/MySQL
- Maven

**Frontend:**
- React 18
- Axios for API calls
- React Router for navigation
- Tailwind CSS for styling
- Chart.js for analytics
- Redux for state management

**DevOps & Cloud:**
- Docker & Docker Compose
- AWS/Azure ready
- Environment-based configuration
- CI/CD pipeline ready

### Key Features

✅ Multi-Tenant SaaS Architecture
✅ Complete Authentication (Email/Password + Google OAuth)
✅ Warehouse Management Setup Wizard
✅ Inventory Transaction Tracking
✅ Sales & Purchase Management
✅ Delivery Tracking (Incoming/Outgoing)
✅ Analytics Dashboard
✅ Role-Based Access Control
✅ Notification System (Email/SMS ready)
✅ Production-Level Security
✅ RESTful API
✅ Responsive UI

### Project Structure

```
wms-saas/
├── backend/                    # Spring Boot Application
│   ├── src/main/java/
│   │   └── com/wms/
│   │       ├── config/         # Configuration classes
│   │       ├── controller/     # REST Controllers
│   │       ├── dto/            # Data Transfer Objects
│   │       ├── entity/         # JPA Entities
│   │       ├── exception/      # Custom Exceptions
│   │       ├── repository/     # Spring Data Repositories
│   │       ├── service/        # Business Logic
│   │       ├── security/       # JWT & Security utilities
│   │       ├── validation/     # Input Validators
│   │       └── WmsApplication.java
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   ├── application-dev.yml
│   │   ├── application-prod.yml
│   │   └── db/migration/      # Flyway migrations (optional)
│   ├── pom.xml
│   └── Dockerfile
├── frontend/                   # React Application
│   ├── public/
│   ├── src/
│   │   ├── components/        # Reusable components
│   │   ├── pages/            # Page components
│   │   ├── services/         # API services
│   │   ├── hooks/            # Custom React hooks
│   │   ├── context/          # React Context for auth
│   │   ├── utils/            # Utility functions
│   │   ├── App.jsx
│   │   └── index.jsx
│   ├── package.json
│   ├── Dockerfile
│   └── .env.example
├── docker-compose.yml
├── .gitignore
├── .env.example
└── IMPLEMENTATION_GUIDE.md
```

### Quick Start

#### Prerequisites
- Java 17+
- Node.js 16+
- PostgreSQL 13+
- Maven 3.8+

#### Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

#### Frontend Setup

```bash
cd frontend
npm install
npm start
```

#### Using Docker

```bash
docker-compose up -d
```

### API Documentation

All API endpoints are documented in the IMPLEMENTATION_GUIDE.md file.

**Base URL:** `http://localhost:8080/api/v1`

#### Authentication Endpoints
- `POST /auth/signup` - User registration
- `POST /auth/login` - User login
- `POST /auth/refresh` - Refresh JWT token
- `POST /auth/google-login` - Google OAuth login

#### Warehouse Endpoints
- `POST /warehouses` - Create warehouse
- `GET /warehouses` - Get user's warehouse
- `PUT /warehouses/{id}` - Update warehouse

#### Inventory Endpoints
- `GET /companies` - List suppliers
- `POST /companies` - Add supplier
- `GET /products` - List products
- `POST /products` - Add product
- `GET /inventory/transactions` - Inventory history

#### Sales & Purchase
- `POST /purchases` - Create purchase order
- `POST /sales` - Create sales order
- `GET /deliveries` - List deliveries

### Database Schema

Key entities:
- **User** - Application users
- **Warehouse** - User's warehouse
- **Company** - Suppliers/Partners
- **Product** - Inventory items
- **InventoryTransaction** - Stock movements
- **Purchase** - Purchase orders
- **Sale** - Sales orders
- **Delivery** - Delivery tracking

### Security Features

- JWT-based authentication
- Role-Based Access Control (RBAC)
- Multi-tenant data isolation
- Password hashing with bcrypt
- Input validation
- SQL injection prevention
- CORS configuration
- Rate limiting
- Environment-based secrets management

### Deployment

#### AWS Deployment
1. Push Docker images to ECR
2. Deploy using ECS or EKS
3. Use RDS for PostgreSQL
4. Configure CloudFront for CDN

#### Azure Deployment
1. Use Azure Container Registry
2. Deploy to App Service
3. Use Azure Database for PostgreSQL
4. Configure Azure CDN

### Environment Variables

See `.env.example` for required variables.

### Contributing

Follow the branch naming convention:
- `feature/` - New features
- `bugfix/` - Bug fixes
- `hotfix/` - Production hotfixes

### License

Proprietray - All rights reserved

### Support

For issues and feature requests, please create an issue in the repository.
