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

- Validarsi ya funciona el ajuste.

--- 
## PARTE II. USANDO SPRING DATA DESDE CERO

## A Quick Start with Spring Boot and Spring Data JPA

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
