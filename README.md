# CVDS-Lab7
## Mateo Olaya Garzón - Andrés Camilo Oñate Quimbayo
--- 

# Verificación APP

Verificar funcionamiento de app MyShutle en mi cuenta de la nube de Azure.

![225794584-e72bb917-26f5-4512-b1b2-e515b21036aa](https://user-images.githubusercontent.com/63562181/226106959-27e6b377-51ef-4aef-841c-ad6b76530738.png)

## Troubleshooting - Activando logs

1. Revisión de logs del sistema:

- Ingresar al los recursos de nuestra cuenta en la nube de Azure.

![image](https://user-images.githubusercontent.com/63562181/226107089-47f33687-05ad-4483-8b24-268bc79c225f.png)

- Entrar al recurso de nuestra aplicaciónweb.

![image](https://user-images.githubusercontent.com/63562181/226107131-d21d805b-7342-4a32-b460-408dd9920fbb.png)

- Buscar la opción de Logs Stream, ingresar allí.Nos debe salir un mensaje que nosindica que primero debemos activar ó configurar loslogs.

Iniciamos la aplicación web y entregamos a Secuencia de registro:

![image](https://user-images.githubusercontent.com/63562181/226107719-74de2351-12a7-4c5b-8b27-a073db9fc1ad.png)

- Application logging: ON(Level: Error)
- Application login (Blob): OF
- Web server logging: File System
- Quota 35 MB
- Retention Period: 3
- Detailed error messages: ON
- Failed request tracing: ON
- —> Guardar.

![image](https://user-images.githubusercontent.com/63562181/226107959-a22532ec-ff5c-49bf-8834-45a4fe9ce2b9.png)

- Revisar qué está pasando a la hora de hacer login.

![image](https://user-images.githubusercontent.com/63562181/226108161-977ad2f3-c4af-4b88-bdd3-6946e3c17209.png)

En Secuencia de registro

![image](https://user-images.githubusercontent.com/63562181/226108493-23d58e0b-6efb-4728-8e75-91b285113128.png)

This error means that there was a problem while processing the request. The request was received by the Web server, but during processing a fatal error occurred, causing the 500 error.

- Revisión del código fuente

Descargue el repositorio de MyShuttle de su cuenta de AzureDevOps(ADO).
Ingresamos a AzureDevOps(ADO):

![image](https://user-images.githubusercontent.com/63562181/226108350-18f90039-cce1-4ad8-a77f-14b58664d579.png)

- Busque el repositorio MyShuttle en su cuenta de ADO

![image](https://user-images.githubusercontent.com/63562181/226108389-79abd1b0-e8a9-433d-98eb-ac4116be3880.png)

- Ejecute el comando gitclone para descargar el proyecto.

![image](https://user-images.githubusercontent.com/63562181/226108942-c70ee15b-46be-49f8-a245-c3797538ae19.png)

Dado que generaba error al usar las credenciales generadas, se ingresa las credenciales de la universidad:

![image](https://user-images.githubusercontent.com/63562181/226108990-0593d2b7-7617-41e5-abb0-2c22531a90ef.png)

## ContinuousIntegration - Activando la opción integración continua en el Pipeline.

- Buscar en ADO los pipelines, y editar el pipeline de nuestro proyecto.
  Activar elcheckbox de “continuousintegration” en el menú Triggers. —> Guardar.
  
![image](https://user-images.githubusercontent.com/63562181/226109092-2ef4e42c-d212-4e53-b720-8fc8894f7845.png)

- Ahora cuando hagamos un push,se ejecutará el pipeline de construcción de la nueva versión de nuestro proyecto.
- Para desplegarlo, ir al Menu Releases y manualmente crear un release.

![image](https://user-images.githubusercontent.com/63562181/226109249-fcbd81dc-ca61-4dc8-9e2d-74deded76f29.png)

![image](https://user-images.githubusercontent.com/63562181/226109296-d86f60be-0716-4fad-bd49-f180fcf5b1df.png)

![image](https://user-images.githubusercontent.com/63562181/226109507-654a54ee-6635-43d2-a589-4225f3cff9df.png)

## Bugfixing - Solucionando un Bug

- Revisar en qué parte está ubicada la información de la base de datos.

![image](https://user-images.githubusercontent.com/63562181/226109534-9d3a56b3-e7c2-4321-9c54-d961416ab0a4.png)

- Revisa las propiedades de conexión del proyecto java a la base de datos.

![image](https://user-images.githubusercontent.com/63562181/226109566-b94088b3-d95a-497e-af37-a4b4b96f3b57.png)

- Validar cuales son los datos de conexión correctos.

![image](https://user-images.githubusercontent.com/63562181/226109782-038bf0bb-732a-4170-b99b-ac4486c70f67.png)

- Actualizar información de conexión a la BD en la clase.

![image](https://user-images.githubusercontent.com/63562181/226110410-935ca455-00e4-4782-b2e9-826a6695d2e3.png)

- Subir los cambios al repositorio remoto.

Configuración final:
```
jdbc:mysql://olayaonateserver.mysql.database.azure.com:3306/alm?useSSL=false&requireSSL=false&autoReconnect=true&user=mysqldbuser@olayaonateserver&password=P2ssw0rd@123
```

![image](https://user-images.githubusercontent.com/63562181/227722055-2d21e078-96c2-40e8-9c2f-eb8e76b27921.png)

Confirmamos que esta haciendo Login:

![image](https://user-images.githubusercontent.com/63562181/227722092-b48f45f9-8ec8-4f5a-a371-bb1d32ec2593.png)

![image](https://user-images.githubusercontent.com/63562181/227722110-244067dd-e7b8-4d22-885b-3c6828e577a1.png)

Base de datos alm:

![image](https://user-images.githubusercontent.com/63562181/227722130-ff6bd57f-1df2-4447-aa5f-d2ddcfeccb89.png)

## PARTE II. USANDO SPRING DATA DESDE CERO



- Revisemos las clases: DataAccess.java y veamosla manera en la que se crearon los métodos: login, employeeFares, getFareTotal.
```
private static PreparedStatement LOGIN;
private static PreparedStatement FARES;
private static PreparedStatement GETTOTAL;
static {
	try {
		LOGIN = theConnection.prepareStatement("SELECT * FROM employees WHERE username=? AND password=?");
		FARES = theConnection.prepareStatement("SELECT * FROM fares WHERE emp_id=?");
		GETTOTAL = theConnection.prepareStatement("SELECT SUM(fare_charge) as totalfare, sum(driver_fee) as totaldriverfee FROM fares WHERE emp_id=?");
	}
	catch (SQLException sqlEx) {
		// Eh.... just give up
		sqlEx.printStackTrace();
		System.exit(-1);
	}
}
 ```
- Actualmente el proyecto se conecta a la base de datos usando JDBC (Java Data Base Connection)

`private static final String DB_DRIVER = "com.mysql.jdbc.Driver";`

- Crea un nuevo repositorio en tu cuenta de github llamado cvds-7, y sigue lasinstrucciones delsiguiente tutorial de spring.

--- 

## A Quick Start with Spring Boot and Spring Data JPA

### Let’s Get Started

Ingresamos a la pagina https://start.spring.io/ e ingresamos los datos del tutorial:

![image](https://user-images.githubusercontent.com/63562181/227720139-cd6dda76-5259-415e-a6a5-6af05028c571.png)

Damos clic en Generate. se descarga un archivo .zip el cual abrimos con el IDE visual code y verificamos la información del POM.xml:

![image](https://user-images.githubusercontent.com/63562181/227720339-d2f6f772-0a6c-4f8e-b565-f0fac2744640.png)

Vamos a src/main/java y confirmamos que existe la clase con la etiqueta @SpringBootApplication:

![image](https://user-images.githubusercontent.com/63562181/227720456-1e4da455-3008-49bf-a396-81386ed3a226.png)


### application.properties

Ingresamos al archivo application.properties y configuraremos las propiedades para nuestra base de datos H2.

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### Our Entity Model

Implementamos el codigo de las clases del spring:

```
package com.tobias.saul.springbootjpamediumdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

	public Employee(){}
	
    public Employee(String firstName, String lastName, String role, Double salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.salary = salary;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String role;
	private Double salary;


	public Long getEmployeeId(){
		return this.employeeId;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getLastName(){
		return this.lastName;
	}


	public String getRole(){
		return this.role;
	}

	public Double getSalary(){
		return this.salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", role="
				+ role + ", salary=" + salary + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		return true;
	}

}
```

```
package com.tobias.saul.springbootjpamediumdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobias.saul.springbootjpamediumdemo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
```

```
package com.tobias.saul.springbootjpamediumdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobias.saul.springbootjpamediumdemo.model.Employee;
import com.tobias.saul.springbootjpamediumdemo.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee getEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId).get();
	}
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public Employee updateEmployee(Employee employee) {
		if(employeeRepository.existsById(employee.getEmployeeId())) {
			return employeeRepository.save(employee);
		}
		
		return null;
	}
	
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}	
}
```
Comprobamos que la aplicación esta corriendo:

![image](https://user-images.githubusercontent.com/63562181/229292540-a6127c36-ade4-4f83-b49d-50dd931e16c5.png)

Iniciamos docker:

![image](https://user-images.githubusercontent.com/63562181/229293132-9f9957ef-d9f4-4066-bff9-636583544901.png)

Descarmagameos la imagen mysql

![image](https://user-images.githubusercontent.com/63562181/229293213-2aa27887-5a8d-437b-8379-35ce14f79aae.png)

Correr contenedor de MySQL

![image](https://user-images.githubusercontent.com/63562181/229293271-8717dcf7-a6f6-4cfd-ba40-bee7b6af7e45.png)

Descargar un cliente de base de datos: DBeaver

![image](https://user-images.githubusercontent.com/63562181/229293569-e76f130a-b416-4153-8a06-f1c25a862fc4.png)
